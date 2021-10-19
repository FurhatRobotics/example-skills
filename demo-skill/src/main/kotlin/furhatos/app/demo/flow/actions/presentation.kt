package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Parent
import furhatos.app.demo.personas.Persona
import furhatos.app.demo.personas.phrases
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.voice.AcapelaVoice
import furhatos.gestures.Gestures

val Presentation = state(Parent) {

    onEntry {
        val persona = Persona.active

        if (persona.voice is AcapelaVoice) {
            furhat.say("#THROAT01#")
        }

        phrases.presentation.forEach {
            furhat.say(it)
            delay(100)
        }

        furhat.gesture(Gestures.BigSmile)

        delay(1500)

        terminate()
    }
}