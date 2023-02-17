package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.nlu.CanIGetCompliment
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Greeting
import furhatos.records.User
import java.util.LinkedList
import java.util.Queue

var userQueue: Queue<User> = LinkedList()

val attentionState: State = state(InteractionParent) {

    onEntry { reentry() }
    onReentry {
        println("Attention confidence : ${getAttentionConfidence(users.current)}")
        when (users.current.zone) {
            Zone.ZONE1 -> goto(startReading(users.current))
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
            goto(startReading(users.current))
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
        goto(startReading(users.current))
    }
    onResponse {
        //goto interaction here seems risky
        furhat.say {
            random {
                +"Hello ?"
                +"You can come closer if you want"
            }
        }
        handleNext()
    }
    onNoResponse {
        users.current.isBeingEngaged = false
        handleNext()
    }

    onUserEnterC { user, zone -> //TODO PROBLEM ! if non instant that trigger stops listening actions...
        if (user == users.current) {
            if (zone == Zone.ZONE1) {
                goto(startReading(users.current))
            } else if (zone == Zone.ZONE2) {
                reentry() //Entering from zone3
            }
        } else if (!users.current.isBeingEngaged) {
            furhat.attendC(user)
            if (zone == Zone.ZONE1) {
                goto(startReading(users.current))
            } else { //Entering zone 2 or 3
                reentry()
            }
        } else {
            // Don't interrupt if interacting with current user, but keep in mind that the user has to be engaged.
            userQueue.add(user)
            furhat.listen()
        }
    }

    //TODO :onUserLeave (maybe in the parent)

    onExit {
        users.current.isBeingEngaged = false
    }
}

fun FlowControlRunner.handleNext() {
    try {
        val next = userQueue.remove()
        if (next.zone.ordinal <= Zone.ZONE2.ordinal) { // Treat only if the user is active
            furhat.attendC(next)
            reentry()
        }
    } catch (_: NoSuchElementException) {
        furhat.listen()
    }
}