package furhatos.app.openaichat.setting

import furhatos.app.openaichat.flow.chatbot.OpenAIChatbot
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.voice.AcapelaVoice
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.flow.kotlin.voice.Voice
import furhatos.nlu.SimpleIntent

class Persona(
    val name: String,
    val otherNames: List<String> = listOf(),
    val intro: String = "",
    val desc: String,
    val face: List<String>,
    val mask: String = "adult",
    val voice: Voice
) {
    val fullDesc = "$name, the $desc"

    val intent = SimpleIntent((listOf(name, desc, fullDesc) + otherNames))

    /** The prompt for the openAI language model **/
    val chatbot = OpenAIChatbot("You are $name, the $desc. You should speak in a conversational style. Never say more than two sentences.")
}

fun FlowControlRunner.activate(persona: Persona) {
    furhat.voice = persona.voice

    for (face in persona.face) {
        if (furhat.faces[persona.mask]?.contains(face)!!) {
            furhat.character = face
            break
        }
    }
}

val hostPersona = Persona(
    name = "Host",
    desc = "host",
    face = listOf("Alex", "default"),
    voice = PollyNeuralVoice("Matthew")
)

val personas = listOf(
    Persona(
        name = "Marvin",
        desc = "depressed robot",
        face = listOf("Titan"),
        voice = PollyNeuralVoice("Kimberly")
    ),
    Persona(
        name = "Emma",
        desc = "personal trainer",
        intro = "How do you think I could help you?",
        face = listOf("Isabel"),
        voice = PollyNeuralVoice("Olivia")
    ),
    Persona(
        name = "Albert Einstein",
        otherNames = listOf("Einstein", "Albert"),
        desc = "famous scientist",
        intro = "What can I help you with?",
        face = listOf("James"),
        voice = PollyNeuralVoice("Brian")
    )
)