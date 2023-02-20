package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.delayWhenUsersAreGone
import furhatos.app.complimentbot.flow.UniversalParent
import furhatos.app.complimentbot.flow.LeaderGoneForAWhile
import furhatos.app.complimentbot.flow.LeaderGoneForAWhileInstant
import furhatos.app.complimentbot.nlu.CanIGetCompliment
import furhatos.app.complimentbot.utils.Zone
import furhatos.app.complimentbot.utils.attendC
import furhatos.app.complimentbot.utils.attendNobodyC
import furhatos.app.complimentbot.utils.*
import furhatos.event.Event
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Greeting
import furhatos.records.User
import java.util.*
import kotlin.concurrent.schedule

var userQueue: Queue<User> = LinkedList()
class UserEnteredEvent(val user: User, val zone: Zone): Event()
class UserLeftEvent(val user: User): Event()

val attentionState: State = state(UniversalParent) {

    onEntry { reentry() }
    onReentry {
        println("Attention confidence : ${getAttentionConfidence(users.current)}")
        when (users.current.zone) {
            Zone.ZONE1 -> goto(complimentNextGroup(users.current))
            Zone.ZONE2 -> {
                if (isAttendingFurhatAvg(users.current) && !users.current.hasBeenGreeted) {
                    users.current.isBeingEngaged = true
                    greetUser(fromAfar = true)
                }
                furhat.listen()
            }
            else -> furhat.attendC(users.current)
            //TODO : seek attention more than that ?
        }
    }

    onResponse<Greeting> {
        if (users.current.zone == Zone.ZONE1) {
            goto(complimentNextGroup(users.current))
        }
        // Rest happens only for far away users
        else {
            //
            if (!users.current.hasBeenGreeted) {
                greetUser()
                furhat.say("Can you maybe step closer?")
            } else {
                positiveSecondGreeting()
            }
            users.current.isBeingEngaged = false
            handleNext()
        }
    }
    onResponse<CanIGetCompliment> {
        furhat.say {
            random {
                +"Sure!"
                +"For sure"
                +"Of course!"
            }
        }
        furhat.gesture(GesturesLib.SmileRandom())
        users.current.hasBeenGreeted = true
        goto(complimentNextGroup(users.current))
    }
    onResponse {
        if (users.current.zone.isCloser(Zone.ZONE3)) {
            //goto interaction here seems risky
            furhat.say {
                random {
                    +"Hello ?"
                    +"You can come closer if you want"
                }
            }
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
            userQueue.add(it)
        } else {
            // We have to separate instant and non-instant behavior here
            send(UserEnteredEvent(it, it.zone))
        }}
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
        if (it.isBeingEngaged && it == users.current) {
            Timer().schedule(delay = delayWhenUsersAreGone) {
                send(LeaderGoneForAWhileInstant(it))
            }
        } else {
            send(UserLeftEvent(it))
        }}
    onEvent<UserLeftEvent> {
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
        }}
    onEvent<LeaderGoneForAWhile> {
        users.current.isBeingEngaged = false
        handleNext()
    }

    onExit {
        users.current.isBeingEngaged = false
    }
}

/**
 * Flow function to handle users entering zones while furhat is trying to get zone 2 users' attention
 */
fun FlowControlRunner.handleNext() {
    try {
        val next = userQueue.remove()
        if (next.zone.isCloser(Zone.ZONE3)) { // Treat only if the user is active
            furhat.attendC(next)
            reentry()
        }
    } catch (_: NoSuchElementException) {
        furhat.listen()
    }
}