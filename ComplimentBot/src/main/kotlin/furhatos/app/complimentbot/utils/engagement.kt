package furhatos.app.complimentbot.utils

import furhatos.app.complimentbot.*
import furhatos.event.EventSystem
import furhatos.event.senses.SenseInteractionSpaces
import furhatos.event.senses.SenseUserEnter
import furhatos.event.senses.SenseUserLeave
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.records.Ellipse
import furhatos.records.Space
import furhatos.records.User
import furhatos.skills.EngagementPolicy
import furhatos.skills.UserManager
import java.io.File

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
            updateActiveAttention(user)
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
                    sendSenseEnter(user.id)
                }
                else if (activeZone.isFurther(user.zone)) {
                    user.zone = activeZone
                    sendSenseLeave(user.id)
                    if (activeZone == Zone.OUT) {
                        user.isEngaged = false
                    }
                }
            } else if (!user.isVisible) {
                // Engaged user left
                user.isEngaged = false
                user.zone = Zone.OUT
                sendSenseLeave(user.id)
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

    private fun sendSenseEnter(userId: String) {
        EventSystem.send(SenseUserEnter.Builder().userId(userId).buildEvent())
    }

    private fun sendSenseLeave(userId: String) {
        EventSystem.send(SenseUserLeave.Builder().userId(userId).buildEvent())
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

fun updateActiveAttention(user: User) {
    user.attentionAverage.add(user.isAttendingFurhat)
}



/**
 * Checks/sets the vision.properties file on the robot.
 *
 * @param enterBufferTime the entering buffer time to detect a user
 * @param leaveBufferTime the leaving buffer time to detect a leaving user
 */
fun FlowControlRunner.setEngagementTimings(enterBufferTime: Int = 500, leaveBufferTime: Int = 2500) {
    val timingParameters = mapOf("enterBufferTime" to enterBufferTime, "leaveBufferTime" to leaveBufferTime)
    if (!furhat.isVirtual() && (System.getProperty("furhatos.skills.brokeraddress") == null)) { //Only applies to the real robot
        val visionProps = File("/usr/share/furhat/properties/", "vision.properties")
        try {
            val props = visionProps.readLines().associate { stringProp -> stringProp.split("=")[0] to stringProp.split("=")[1].toInt() }
            if (props["enterBufferTime"] != enterBufferTime || props["leaveBufferTime"] != leaveBufferTime) {
                throw Exception("New vision parameters detected")
            } else {
                furhatos.app.complimentbot.flow.logger.info("Vision parameters are correctly set up.")
            }
        } catch (e: Exception) {
            furhatos.app.complimentbot.flow.logger.warn("Rewriting the vision parameters : ${e.message}. Please restart your robot for them to take effect.")
            visionProps.printWriter().use { out ->
                timingParameters.forEach {
                    out.println("${it.key}=${it.value}")
                }
            }
        }
    } else {
        furhatos.app.complimentbot.flow.logger.warn("Did not rewrite the engagement timings, machine is not a robot.")
    }
}