package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.delayWhenUsersAreGone
import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.flow.LeaderGoneForAWhile
import furhatos.app.complimentbot.flow.LeaderGoneForAWhileInstant
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.*
import furhatos.records.User
import java.awt.Color
import java.util.*
import kotlin.concurrent.schedule

fun complimentNextGroup(groupLeader: User): State = state(InteractionParent) {

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
        for (user in activeGroup.filter { it != leader }) {
            if (user.zone <= Zone.ZONE2) {
                furhat.attendC(user)
                delay(500)
                if (user.hasBeenGreeted) {
                    furhat.gesture(GesturesLib.PerformBigSmile1)
                    delay(1000)
                } else {
                    greetUser(isOtherGreet = true)
                }
            }
        }

        //furhat.gesture(GesturesLib.PerformTripleBlink, priority = 10) //Does not mix well with the waking gesture
        delay(200)
        furhat.ledStrip.solid(Color(0, 120, 0))

        if (leader.zone <= Zone.ZONE2) {
            furhat.attendC(leader)
            complimentUser()
        }
        for (user in activeGroup.filter { it != leader }) {
            if (user.zone <= Zone.ZONE2) {
                furhat.attendC(user)
                complimentUser(isOtherCompliment = true)
            }
        }

        if (leader.zone <= Zone.ZONE2) {
            furhat.attendC(leader)
        }

        goto(endReading(leader))
    }

    onUserLeave(instant = true) {userThatLeft ->
        if (userThatLeft == leader) {
            Timer().schedule(delay = delayWhenUsersAreGone) {
                send(LeaderGoneForAWhileInstant(userThatLeft))
            }
        }
    }
    onEvent<LeaderGoneForAWhileInstant>(instant = true) {event ->
        println("User ${event.user.id} has left compliment for $delayWhenUsersAreGone milliseconds.")
        // React only if the leader has left
        if (!users.list.contains(event.user)) {
            if (activeGroup.any { it != leader && it.zone < Zone.ZONE2}) {
                // Assign the leader position to another user from the same group
                leader = activeGroup.find { it != leader && it.zone < Zone.ZONE2}?:leader
                println("new leader : ${leader.id}")
            } else {
                raise(LeaderGoneForAWhile(event.user))
            }
        }
    }
    onEvent<LeaderGoneForAWhile> {
        goto(endReading())
    }

    onExit {
        furhat.ledStrip.solid(Color.BLACK)
    }
}