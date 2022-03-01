package furhatos.app.fortuneteller.setting

import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.flow.kotlin.voice.Voice
import furhatos.util.Language

class Persona(val name: String, val mask: String = "adult", val face: List<String>, val voice: List<Voice>)

fun FlowControlRunner.activate(persona: Persona) {
    for (voice in persona.voice) {
        if (voice.isAvailable) {
            furhat.voice = voice
            break
        }
    }

    for (face in persona.face) {
        if (furhat.faces.get(persona.mask)?.contains(face)!!) {
            furhat.character = face
            break
        }
    }
}

val furhatPersona = Persona(
        name = "Furhat",
        face = listOf("Alex",
                "default"),
        voice = listOf(
                PollyNeuralVoice.Matthew(),
                PollyNeuralVoice.Joanna()).shuffled() // randomize what voice to select
)

val fortuneTellerPersona = Persona(
        name = "FortuneTeller",
        face = listOf(
                "Titan",
                "default"),
        voice = listOf(
                Voice(name = "WillBadGuy22k_HQ", language = Language.ENGLISH_US, rate = 1.1, pitch = "high"),
                Voice(name = "Kimberly-neural", language = Language.ENGLISH_US, rate = 1.0, pitch = "low")) // Kimberly is backup voice if Acapela voice is not available
)