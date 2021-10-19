package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Parent
import furhatos.flow.kotlin.Utterance
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.utterance

fun Return(bridge: Utterance = utterance { +"alright" }) = state(Parent) {
    onEntry {
        furhat.say(utterance = bridge)
        goto(Active(returning = true))
    }
}