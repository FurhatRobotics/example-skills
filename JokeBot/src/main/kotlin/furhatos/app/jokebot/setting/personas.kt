package furhatos.app.jokebot.setting

import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.flow.kotlin.voice.Voice

class Persona(val name: String, val mask: String = "adult", val face: List<String>, val voice: List<Voice>) {
}

fun FlowControlRunner.activate(persona: Persona) {
    for (voice in persona.voice) {
        if (voice.isAvailable) {
            furhat.voice = voice
            break
        }
    }

    for (face in persona.face) {
        if (furhat.faces.get(persona.mask)?.contains(face)!!){
            furhat.setCharacter(face)
            break
        }
    }
}

val mainPersona = Persona(
        name = "Host",
        face = listOf("Alex"),
        voice = listOf(PollyNeuralVoice.Matthew(),PollyNeuralVoice.Joanna()).shuffled() // randomize what voice to select
)