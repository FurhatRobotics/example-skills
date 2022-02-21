package furhatos.app.dog.dog

import furhatos.event.Event
import furhatos.event.EventSystem
import furhatos.event.senses.SenseInteractionSpaces
import furhatos.event.senses.SenseUserEnter
import furhatos.event.senses.SenseUserLeave
import furhatos.records.Ellipse
import furhatos.records.Space
import furhatos.skills.EngagementPolicy
import furhatos.skills.UserManager

class DogEngagementPolicy(private val userManager: UserManager,
                          innerRadiusX: Double,
                          innerRadiusZ: Double = innerRadiusX,
                          outerRadiusX: Double,
                          outerRadiusZ: Double = outerRadiusX,
                          private val maxUsers: Int) : EngagementPolicy {
    private val innerSpace : Ellipse
    private val outerSpace : Ellipse
    private val spaces : List<Space>

    init {
        if (innerRadiusX > outerRadiusX || innerRadiusZ > outerRadiusZ) {
            throw IllegalArgumentException("Inner space has to be fully contained by outer space")
        }
        innerSpace = Ellipse("inner", userManager.robotLocation, innerRadiusX, innerRadiusZ)
        outerSpace = Ellipse("outer", userManager.robotLocation, outerRadiusX, outerRadiusZ)
        spaces = listOf(innerSpace, outerSpace)
    }

    constructor(userManager: UserManager,
                innerRadius: Double,
                outerRadius: Double,
                maxUsers: Int) : this(userManager, innerRadius, innerRadius, outerRadius, outerRadius, maxUsers)

    override fun checkEngagement() {
        val engagedUsers = userManager.list.map { user -> user.id }

        userManager.all.forEach { user ->
            if (!user.isEngaged && user.isVisible && innerSpace.contains(user.head.location)) {
                // User entered
                if (userManager.count < maxUsers) {
                    user.isEngaged = true
                    sendSenseEnter(user.id)
                }
            } else if (user.isEngaged && (!user.isVisible || !outerSpace.contains(user.head.location))) {
                // User left
                user.isEngaged = false
                sendSenseLeave(user.id)
            } else if(user.isVisible && user.data.get("isSeen") != null) {
                user.data.put("isSeen", true)
                sendSenseVisible(user.id)
            }
        }

        val newEngagedUsers = userManager.list.map { user -> user.id }

        if (engagedUsers != newEngagedUsers) {
            userManager.sendUserStatus()
        }
    }

    override fun requestInteractionSpaces() {
        EventSystem.send(SenseInteractionSpaces.Builder().spaces(spaces).buildEvent())
    }

    private fun sendSenseLeave(userId: String) {
        val senseUserLeave = SenseUserLeave.Builder()
                .userId(userId)
        EventSystem.send(senseUserLeave.buildEvent())
    }

    private fun sendSenseEnter(userId: String) {
        val senseUserEnter = SenseUserEnter.Builder()
                .userId(userId)
        EventSystem.send(senseUserEnter.buildEvent())
    }

    private fun sendSenseVisible(userId: String) {
        val senseUserVisible = SenseUserVisible.Builder()
                .userId(userId)
        EventSystem.send(senseUserVisible.buildEvent())
    }
}

class SenseUserVisible(build: Builder) : Event(build) {
    val userId: String?

    class Builder : Event.Builder<SenseUserVisible>() {
        var userId: String? = null

        override fun buildEvent(): SenseUserVisible {
            return SenseUserVisible(this)
        }

        fun userId(userId: String): Builder {
            this.userId = userId
            return this
        }
    }

    init {
        userId = build.userId
    }
}