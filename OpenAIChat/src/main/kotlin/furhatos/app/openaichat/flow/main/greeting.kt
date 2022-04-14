package furhatos.app.openaichat.flow

import furhatos.app.openaichat.flow.chatbot.MainChat
import furhatos.app.openaichat.setting.Persona
import furhatos.app.openaichat.setting.hostPersona
import furhatos.app.openaichat.setting.personas
import furhatos.flow.kotlin.*
import furhatos.records.Location

val Greeting = state(Parent) {

    onEntry {
        furhat.attend(users.userClosestToPosition(Location(0.0, 0.0, 0.5)))
        askForAnything("Hi there")
        furhat.say("I was recently introduced to the A I GPT3 from open A I.")
        if (furhat.askYN("Have you heard about GPT3?") == true) {
            furhat.say("Good, let's try it out")
        } else {
            furhat.say("GPT3 is a so-called language model, developed by OpenAI. It can be used to generate any text, for example a conversation, based on the description of a character. ")
            if (furhat.askYN("Are you ready to try it out?") == true) {
            } else {
                furhat.say("Okay, maybe another time then")
                goto(Idle)
            }
        }
        goto(ChoosePersona())
    }
}

var currentPersona: Persona = hostPersona

fun ChoosePersona() = state(Parent) {

    val personasWithAvailableVoice = personas.filter { it.voice.first().isAvailable }
    val selectedPersonas = personasWithAvailableVoice.take(3)

    fun FlowControlRunner.presentPersonas() {
        furhat.say("You can choose to speak to one of these characters:")
        for (persona in selectedPersonas) {
            //activate(persona)
            delay(300)
            furhat.say(persona.fullDesc)
            delay(300)
        }
        //activate(hostPersona)
        reentry()
    }

    onEntry {
        presentPersonas()
    }

    onReentry {
        furhat.ask("Who would you like to talk to?")
    }

    onResponse("can you present them again", "could you repeat") {
        presentPersonas()
    }

    for (persona in personas) {
        onResponse(persona.intent) {
            furhat.say("Okay, I will let you talk to ${persona.name}.")
            furhat.say("When you want to end the conversation, just say goodbye.")
            currentPersona = persona
            goto(MainChat)
        }
    }
}