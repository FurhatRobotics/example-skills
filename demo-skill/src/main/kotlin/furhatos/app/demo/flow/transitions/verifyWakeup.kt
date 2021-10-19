package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Parent
import furhatos.app.demo.nlu.ConversationalIntent
import furhatos.app.demo.personas.phrases
import furhatos.app.demo.util.AttendUsers
import furhatos.app.demo.util.setLED
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.Response
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

fun VerifyWakeup(resp: Response<*>? = null) : State = state(Parent) {
    onEntry {
        send(AttendUsers())
        furhat.setLED("White")
        furhat.setDefaultMicroexpression()
        furhat.gesture(Gestures.OpenEyes, priority = 1)

        // If we have an intent that we have flagged as a conversational intent, we go to active to answer it
        if (resp != null && resp.multiIntent && resp.secondaryIntent is ConversationalIntent) { // is working
            resp.intent = resp.secondaryIntent
            resp.multiIntent = false
            resp.secondaryIntent = null
            goto(Active(resp))
        }
        // If not, we check if the user wanted to address us or if it triggered as a mistake
        else {
            furhat.ask("Are you talking to me?")
        }
    }

    onResponse<Yes> {
        furhat.say {
            +"Cool!"
            +phrases.greeting
        }
        goto(Active(resp))
    }

    onResponse<No> {
        furhat.say("Oh, okay")
        goto(Sleeping)
    }

    onNoResponse {
        goto(Sleeping)
    }

    onResponse {
        goto(Sleeping)
    }
}