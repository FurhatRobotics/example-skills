package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.delayWhenUsersAreGone
import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.flow.LeaderGoneForAWhile
import furhatos.app.complimentbot.flow.LeaderGoneForAWhileInstant
import furhatos.app.complimentbot.flow.skillLogger
import furhatos.app.complimentbot.utils.*
import furhatos.event.monitors.MonitorSpeechEnd
import furhatos.flow.kotlin.*
import furhatos.records.User
import java.awt.Color
import java.util.*
import kotlin.concurrent.schedule

fun complimentNextGroup(groupLeader: User): State = state(InteractionParent) {

    var leader = groupLeader
    var waitingForEndSpeech = false

    onEntry {
        activeGroup = nextGroup
        nextGroup = mutableListOf()

        furhat.attendC(leader)
        //Defining the list here in case the leader changes while furhat is greeting people
        val otherUsersToGreet = activeGroup.filter { it != leader }
        if (!leader.hasBeenGreeted) {
            greetUser(leader)
        } else {
            furhat.gesture(GesturesLib.SmileRandom())
        }
        for (user in otherUsersToGreet) {
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

        furhat.gesture(GesturesLib.PerformTripleBlink, priority = 10)
        delay(200)
        furhat.ledStrip.solid(Color(0, 120, 0))

        //Defining the list here in case the leader changes while furhat is complimenting people
        val activeGroupWithLeader = activeGroup.filter { it != leader }.toMutableList()
        activeGroupWithLeader.add(0, leader)
        // When the first user has been complimented add link words - "And you", ...
        var isOtherCompliment = false
        for (user in activeGroupWithLeader) {
            if (user.zone <= Zone.ZONE2) {
                furhat.attendC(user)
                complimentUser(isOtherCompliment = isOtherCompliment)
                if (!isOtherCompliment) {
                    isOtherCompliment = true
                }
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
        // React only if the leader has left
        if (!users.list.contains(event.user)) {
            skillLogger.debug("User ${event.user.id} has left compliment for $delayWhenUsersAreGone milliseconds.")
            if (activeGroup.any { it != leader && it.zone <= Zone.ZONE2}) {
                // Assign the leader position to another user from the same group
                leader = activeGroup.find { it != leader && it.zone <= Zone.ZONE2}?:leader
                skillLogger.debug("new leader : ${leader.id}")
            } else if (!furhat.isSpeaking){
                raise(LeaderGoneForAWhile(event.user))
            } else {
                // Don't interrupt furhat in the middle of a compliment if the user leaves
                waitingForEndSpeech = true
            }
        }
    }

    onEvent<LeaderGoneForAWhile> {
        goto(endReading())
    }

    // In case the Furhat is in a middle of a compliment when the active user leaves we want to wait for the end of speech
    onEvent<MonitorSpeechEnd> {
        if (waitingForEndSpeech) {
            goto(endReading())
        }
    }

    onExit {
        furhat.ledStrip.solid(Color.BLACK)
        waitingForEndSpeech = false
    }
}