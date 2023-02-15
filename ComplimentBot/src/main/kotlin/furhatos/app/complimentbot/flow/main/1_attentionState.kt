package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.nlu.CanIGetCompliment
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Greeting

val attentionState: State = state(InteractionParent) {

    onEntry { reentry() }
    onReentry {
        when (users.current.zone) {
            Zone.ZONE1 -> {
                if (users.current.isAttendingFurhat) {
                    goto(startReading(users.current))
                }}
            Zone.ZONE2 -> {
                if (users.current.isAttendingFurhat && !users.current.hasBeenGreeted) {
                    greetUser(fromAfar = true)
                    users.current.isBeingEngaged = true
                }}
            else -> furhat.attendC(users.current)
            //TODO : seek attention more than that ?
        }
        furhat.listen()
    }

    onResponse<Greeting> {
        // TODO : Check sound ?? not reliable
        if (users.current.zone != Zone.ZONE3) { //TODO : Add some delays for the zone 2
            goto(startReading(users.current))
        }
        // Rest happens only for far away users
        else if (!users.current.hasBeenGreeted) {
            greetUser(users.current)
        }
        furhat.say("Can you step closer please?")
    }
    onResponse<CanIGetCompliment> {
        furhat.say {
            random {
                +"Sure!"
                +"For sure"
                +"Of course!"
            }
        }
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
        furhat.listen()
    }
    onNoResponse {
        users.current.isBeingEngaged = false
        furhat.listen()
    }

    onUserEnterC { user, zone ->
        if (user == users.current) {
            if (zone == Zone.ZONE1) {
                goto(startReading(users.current))
            } else if (zone == Zone.ZONE2) {
                reentry() //Entering from zone3
            }
        }  else if (!users.current.isBeingEngaged) { // Don't interrupt if interacting with current user
            furhat.attendC(user)
            if (zone == Zone.ZONE1) {
                goto(startReading(users.current))
            } else { //Entering zone 2 or 3
                reentry()
            }
        }
    }

    onExit {
        users.current.isBeingEngaged = false
    }
}