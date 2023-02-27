package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.delayWhenUsersAreGone
import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.flow.LeaderGoneForAWhile
import furhatos.app.complimentbot.flow.LeaderGoneForAWhileInstant
import furhatos.app.complimentbot.flow.logger
import furhatos.app.complimentbot.maxTimeAttendingAUser
import furhatos.app.complimentbot.utils.*
import furhatos.event.Event
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Greeting
import furhatos.records.User
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.concurrent.schedule

// Attention timeout to no attend an inactive user for too long
var lastAttentionChange: LocalDateTime = LocalDateTime.now()
var userQueue: LinkedList<User> = LinkedList()
class UserEnteredEvent(val user: User, val zone: Zone): Event()
class UserLeftEvent(val user: User): Event()
class MaxAttentionWithoutActivity: Event()

val attentionState: State = state(InteractionParent) {

    include(attentionGeneralIntents)

    onEntry {
        reentry()
    }
    onReentry {
        resetLastAttentionCheck()
        when (users.current.zone) {
            Zone.ZONE1 -> goto(complimentNextGroup(users.current))
            Zone.ZONE2 -> {
                if (isAttendingFurhatAvg(users.current) && !users.current.hasBeenGreeted) {
                    users.current.isBeingEngaged = true
                    greetUser(fromAfar = true)
                }
                furhat.listen()
            }
            else -> {
                furhat.attendC(users.current)
            }
        }
    }

    onResponse<Greeting> {
        if (users.current.zone == Zone.ZONE1) {
            goto(complimentNextGroup(users.current))
        }
        // Rest happens only for far away users
        else {
            if (!users.current.hasBeenGreeted) {
                greetUser()
                furhat.say("Can you maybe step closer?")
            } else {
                positiveSecondGreeting()
                delay(1000)
            }
            users.current.isBeingEngaged = false
            resetLastAttentionCheck()
            handleNext()
        }
    }
    onResponse {
        // Encourage users only once
        if (users.current.zone.isCloser(Zone.ZONE3) && users.current.hasReceivedGeneralResponse) {
            //goto interaction here seems risky
            furhat.say {
                random {
                    +"Feel free to move closer"
                    +"You can come closer if you want"
                    +"You can come closer if you'd like"
                }
            }
            users.current.hasReceivedGeneralResponse = true
            resetLastAttentionCheck()
        }
        handleNext()
    }
    onNoResponse {
        users.current.isBeingEngaged = false
        handleNext()
    }

    onUserEnter(instant = true) {
        if (it != users.current && users.current.isBeingEngaged) {
            // Don't interrupt if interacting with current user, but keep in mind that the user has to be engaged.
            // If a user enter the zone 1 he becomes the next target
            addNewUserToQueue(it)
        } else {
            // We have to separate instant and non-instant behavior here
            send(UserEnteredEvent(it, it.zone))
        }
    }
    onEvent<UserEnteredEvent> {
        if (it.user == users.current) {
            if (it.zone == Zone.ZONE1) {
                goto(complimentNextGroup(users.current))
            } else if (it.zone == Zone.ZONE2) {
                reentry() //Entering from zone3
            }
        } else if (!users.current.isBeingEngaged) {
            furhat.attendC(it.user)
            if (it.zone == Zone.ZONE1) {
                goto(complimentNextGroup(users.current))
            } else { //Entering zone 2 or 3
                reentry()
            }
        }
    }

    onUserLeave(instant = true) {
        // Only care about the main user in the attention state
        if (it.isBeingEngaged && it == users.current) {
            // If the user is being engaged we don't want to interrupt a listen
            Timer().schedule(delay = delayWhenUsersAreGone) {
                send(LeaderGoneForAWhileInstant(it))
            }
        } else if (it == users.current) {
            send(UserLeftEvent(it))
        }
    }
    onEvent<UserLeftEvent> {
        it.user.hasReceivedGeneralResponse = false
        if (users.hasAny() && it.user == users.current) {
            furhat.attendC(users.other)
        } else {
            furhat.attendNobodyC()
            goto(ActiveIdle)
        }
    }
    onEvent<LeaderGoneForAWhileInstant>(instant = true) {
        if (!users.list.contains(it.user)) {
            raise(LeaderGoneForAWhile(it.user))
        }
    }
    onEvent<LeaderGoneForAWhile> {
        it.user.isBeingEngaged = false
        it.user.hasReceivedGeneralResponse = false
        handleNext()
    }

    onTime(1000, 1000, instant = true) {
        if (ChronoUnit.SECONDS.between(lastAttentionChange, LocalDateTime.now()) > maxTimeAttendingAUser) {
            logger.info("Max attention time reached, going back to idle")
            send(MaxAttentionWithoutActivity())
        }
    }
    onEvent<MaxAttentionWithoutActivity> {
        furhat.attendNobodyC()
        goto(ActiveIdle)
    }
}

/**
 * Flow function to handle users entering zones while furhat is trying to get zone 2 users' attention
 */
fun FlowControlRunner.handleNext() {
    try {
        var next = userQueue.remove()
        if (!next.isEngaged) {
            // Don't treat users that left
            next = userQueue.remove()
        }
        if (next.zone <= Zone.ZONE2) { // Treat only if the user is active
            furhat.attendC(next)
            reentry()
        }
        // no else listen here because we don't want users in zone 3 to be able to interact
    } catch (_: NoSuchElementException) {
        // If no next user we either continue to attend the current one
        if (users.current.isEngaged && users.current.zone <= Zone.ZONE2) {
            furhat.listen()
        }
        // or an old one if current left
        else if (users.list.any {it.zone <= Zone.ZONE2 } ) {
            furhat.attendC(users.list.find {it.zone == Zone.ZONE2 }!!)
            furhat.listen()
        } else if (users.list.any {it.zone == Zone.ZONE3 }) {
            // Used if active users leave and some other users still engaged behind zone 3
            furhat.attendC(users.list.find {it.zone == Zone.ZONE3 }!!)
        } else {
            // Or go back to Idle
            furhat.attendNobodyC()
            goto(ActiveIdle)
        }
    }
}


fun addNewUserToQueue(user: User) {
    if (user.zone == Zone.ZONE1) {
        userQueue.push(user)
    } else {
        userQueue.add(user)
    }
}

fun resetLastAttentionCheck() {
    logger.debug("Resetting attention")
    lastAttentionChange = LocalDateTime.now()
}