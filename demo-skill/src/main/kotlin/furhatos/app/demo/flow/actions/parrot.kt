package furhatos.app.demo.flow

import furhatos.app.demo.nlu.ExitIntent
import furhatos.app.demo.personas.phrases
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state

val ParrotMode = state(Active()) {
    onEntry {
        furhat.ask(phrases.parrotStart)
    }

    onResponse<ExitIntent> {
        furhat.say(phrases.okayIWillStop)
        terminate()
    }

    onResponse {
        furhat.ask(it.text)
    }
}