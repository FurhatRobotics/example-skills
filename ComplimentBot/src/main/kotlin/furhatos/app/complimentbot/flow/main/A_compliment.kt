package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.delayWhenUsersAreGone
import furhatos.app.complimentbot.flow.LeaderGoneForAWhile
import furhatos.app.complimentbot.flow.LeaderGoneForAWhileInstant
import furhatos.app.complimentbot.flow.UniversalParent
import furhatos.app.complimentbot.utils.Zone
import furhatos.app.complimentbot.utils.attendC
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.*
import furhatos.records.User
import java.awt.Color
import java.util.*
import kotlin.concurrent.schedule

fun complimentNextGroup(groupLeader: User): State = state(UniversalParent) {

    var leader = groupLeader

    onEntry {
        activeGroup = nextGroup
        nextGroup = mutableListOf()

        furhat.attendC(leader)
        if (!leader.hasBeenGreeted) {
            greetUser(leader)
        } else {
            furhat.gesture(GesturesLib.SmileRandom())
        }
        val usersNotGreeted = activeGroup.filter { it != leader }
        for (user in usersNotGreeted) {
            if (user.zone < Zone.ZONE2) {
                furhat.attendC(user)
                greetUser(isOtherGreet = true)
            }
        }

        furhat.gesture(GesturesLib.PerformTripleBlink, priority = 10)
        delay(200)
        furhat.ledStrip.solid(Color(0, 120, 0))

        if (leader.zone.isCloser(Zone.ZONE2)) {
            furhat.attendC(leader)
            complimentUser(leader)
        }
        for (user in activeGroup.filter { it != leader }) {
            if (user.zone < Zone.ZONE2) {
                furhat.attendC(user)
                complimentUser(isOtherCompliment = true)
            }
        }

        if (leader.zone.isCloser(Zone.ZONE2)) {
            furhat.attendC(leader)
        }

        goto(EndReading(leader))
    }

    onUserLeave(instant = true) {userThatLeft ->
        if (userThatLeft == leader) {
            Timer().schedule(delay = delayWhenUsersAreGone) {
                send(LeaderGoneForAWhileInstant(userThatLeft))
            }
        }}
    onEvent<LeaderGoneForAWhileInstant>(instant = true) {
        if (!users.list.contains(it.user)) {
            raise(LeaderGoneForAWhile(it.user))
        }}
    onEvent<LeaderGoneForAWhile> { event ->
        println("User ${event.user.id} has left for $delayWhenUsersAreGone milliseconds.")
        if (activeGroup.any { it != leader }) {
            leader = activeGroup.find { it != leader }?:leader // Assign the leader position to another user from the same group
        } else {
            goto(EndReading())
        }
    }

    onExit {
        furhat.ledStrip.solid(Color.BLACK)
    }
}