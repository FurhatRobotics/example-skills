package furhatos.app.demo.flow.modes

import furhatos.app.demo.flow.Active
import furhatos.app.demo.nlu.ConversationalIntent
import furhatos.app.demo.util.LookAround
import furhatos.flow.kotlin.*
import furhatos.nlu.NullIntent

// State where Furhat is awake but passive on a listen loop
val Passive : State = state(Parent) {
    onEntry {
        send(LookAround())
        furhat.listen()
    }

    onReentry {
        furhat.listen()
    }

    onNoResponse {
        furhat.listen()
    }

    onResponse(cond = { it.intent == NullIntent }) {
        furhat.listen()
    }

    onResponse(cond = { it.intent is ConversationalIntent }) {
        goto(Active(it))
    }
}


