package furhatos.app.complimentbot.utils

import furhatos.app.complimentbot.*
import furhatos.event.Event
import furhatos.event.EventSystem
import furhatos.event.senses.SenseInteractionSpaces
import furhatos.event.senses.SenseUserEnter
import furhatos.event.senses.SenseUserLeave
import furhatos.flow.kotlin.Runner
import furhatos.flow.kotlin.StateBuilder
import furhatos.flow.kotlin.Trigger
import furhatos.flow.kotlin.TriggerRunner
import furhatos.records.Ellipse
import furhatos.records.Space
import furhatos.records.User
import furhatos.skills.EngagementPolicy
import furhatos.skills.UserManager

enum class Zone(val space: Space) {
    ZONE1(Ellipse("zone1", origin, zone1Params.first, zone1Params.second)),
    ZONE2(Ellipse("zone2", origin, zone2Params.first, zone2Params.second)),
    ZONE3(Ellipse("zone3", origin, zone3Params.first, zone3Params.second)),
    OUT(Ellipse());

    fun isCloser(other: Zone): Boolean {
        return ordinal < other.ordinal
    }
    fun isFurther(other: Zone): Boolean {
        return ordinal > other.ordinal
    }

    override fun toString(): String {
        return "Zone(name=$name, space=$space)"
    }
}

fun getZoneFromName(name: String): Zone {
    return Zone.values().firstOrNull { it.name == name } ?: Zone.OUT
}

/**
 * Complex engagement policy definition.
 * Note that the zone have to be defined from the smallest to the largest !
 *
 * No max users for this engagement policy at the moment
 */
class ComplexEngagementPolicy(private val userManager: UserManager, private var zones : List<Zone>) : EngagementPolicy {

    override fun checkEngagement() {
        val engagedUsers = userManager.list.map { user -> user.id }

        userManager.all.forEach { user ->
            val activeZone = findUserZone(user)

            // 1. Check engagement
            if (!user.isEngaged && user.isVisible && zones.last().space.contains(user.head.location)) {
                // User entered
                user.isEngaged = true
            }

            // 2. Send zone events
            if (user.isEngaged && user.isVisible) {
                if (activeZone.isCloser(user.zone)) {
                    user.zone = activeZone
                    sendSenseEnter(user.id, activeZone.name)
                }
                else if (activeZone.isFurther(user.zone)) {
                    user.zone = activeZone
                    sendSenseLeave(user.id, activeZone.name)
                    if (activeZone == Zone.OUT) {
                        user.isEngaged = false
                    }
                }
            } else if (user.isEngaged && !user.isVisible) {
                // User left
                user.isEngaged = false
                sendSenseLeave(user.id, Zone.OUT.name)
            }
        }

        val newEngagedUsers = userManager.list.map { user -> user.id }

        if (engagedUsers != newEngagedUsers) {
            userManager.sendUserStatus()
        }
    }

    override fun requestInteractionSpaces() {
        EventSystem.send(SenseInteractionSpaces.Builder().spaces(zones.map { it.space }).buildEvent())
    }

    private fun sendSenseLeave(userId: String, space: String) {
        EventSystem.send(SenseUserLeave.Builder().userId(userId).space(space).buildEvent())
    }

    private fun sendSenseEnter(userId: String, space: String) {
        EventSystem.send(SenseUserEnter.Builder().userId(userId).space(space).buildEvent())
    }

    private fun findUserZone(user: User): Zone {
        for (zone in zones) {
            // From the smallest zone to the largest, the user belongs to the smallest one
            if (zone.space.contains(user.head.location)) {
                return zone
            }
        }
        return Zone.OUT
    }
}


/**
 * Custom onUserEnter when user coming from a further zone
 */
fun StateBuilder.onUserEnterC(
    cond: Runner.(User, Zone) -> Boolean = { _: User, _: Zone -> true },
    instant: Boolean = false,
    priority : Boolean = false,
    trigger: TriggerRunner<*>.(User, Zone) -> Unit) {
    val enterTrigger = Trigger<Event>(
        { trigger(UserManager.getUser((it as SenseUserEnter).userId), getZoneFromName(it.space)) },
        instant, priority,
        {it is SenseUserEnter && cond(UserManager.getUser(it.userId), getZoneFromName(it.space))}
    )
    addTrigger("event", enterTrigger)
}

/**
 * Custom onUserLeave when user exiting to a further zone
 */
fun StateBuilder.onUserLeaveC(
    cond: Runner.(User, Zone) -> Boolean = { _: User, _: Zone -> true },
    instant: Boolean = false,
    priority : Boolean = false,
    trigger: TriggerRunner<*>.(User, Zone) -> Unit) {
    val leaveTrigger = Trigger<Event>(
        { trigger(UserManager.getUser((it as SenseUserLeave).userId), getZoneFromName(it.space)) },
        instant, priority,
        {it is SenseUserLeave && cond(UserManager.getUser(it.userId), getZoneFromName(it.space))}
    )
    addTrigger("event", leaveTrigger)
}