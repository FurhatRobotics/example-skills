package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Parent
import furhatos.app.demo.nlu.ExitIntent
import furhatos.app.demo.util.StopAutoBehavior
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

fun RequireUsers(nextState: State) = state(Parent) {
    onEntry {
        send(StopAutoBehavior())
        if (users.list.count() == 0) {
            furhat.ask {
                +Gestures.Smile
                +"could you step a bit closer, perhaps?"
            }
        }
        else {
            goto(nextState)
        }
    }

    onReentry {
        furhat.ask {
            +"I still can't see you, and I really wanna see you before proceeding."
            +"Could you step in front of the camera?"
        }
    }

    onUserEnter {
        furhat.stopListening()
        furhat.say("Perfect")
        goto(nextState)
    }

    onResponse<ExitIntent> {
        furhat.say("okay, no worries")
        goto(Return())
    }

    onNoResponse {
        reentry()
    }
}