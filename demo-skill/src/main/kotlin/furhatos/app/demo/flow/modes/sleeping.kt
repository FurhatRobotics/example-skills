package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Parent
import furhatos.app.demo.nlu.ChatIntent
import furhatos.app.demo.nlu.ConversationalIntent
import furhatos.app.demo.nlu.WakeupIntent
import furhatos.app.demo.util.LookStraight
import furhatos.app.demo.util.setLED
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.Greeting

// State requiring the user to "wake up" Furhat
val Sleeping = state(Parent) {
    onEntry {
        send(LookStraight())
        furhat.setLED("off")
        furhat.setDefaultMicroexpression(blinking = false, eyeMovements = false, facialMovements = false)
        furhat.gesture(Gestures.CloseEyes, priority = 1)
        furhat.stopSpeaking()
        furhat.listen()
    }

    onReentry {
        furhat.listen()
    }

    onExit {
        //dialogLogger.startSession(cloudToken = "a1671cbb-9b42-4127-b095-5ed12a02ec39")
    }

    onPartialResponse<Greeting>(prefix = true) {
        goto(VerifyWakeup(it))
    }

    onResponse<Greeting> {
        goto(VerifyWakeup())
    }

    onPartialResponse<WakeupIntent>(prefix = true) {
        goto(VerifyWakeup(it))
    }

    onResponse<WakeupIntent> {
        goto(Active())
    }

    onResponse<ChatIntent> {
        // We know that people use this intent to wake him, so treat it the same
        raise(it, WakeupIntent())
    }

    onResponse(cond = { it.intent is ConversationalIntent }) {
        goto(VerifyWakeup(it))
    }

    onResponse {
        furhat.gesture(Gestures.BrowRaise)
        furhat.listen()
    }

    onNoResponse {
        furhat.listen()
    }
}