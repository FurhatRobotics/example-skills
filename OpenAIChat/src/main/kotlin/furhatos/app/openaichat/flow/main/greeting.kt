package furhatos.app.openaichat.flow

import furhatos.app.openaichat.flow.chatbot.MainChat
import furhatos.app.openaichat.setting.Persona
import furhatos.app.openaichat.setting.hostPersona
import furhatos.app.openaichat.setting.personas
import furhatos.flow.kotlin.*
import furhatos.records.Location

var currentPersona: Persona = hostPersona

fun ChoosePersona() = state(Parent) {

    fun FlowControlRunner.presentPersonas() {
        furhat.say("You can choose to speak to one of these characters:")
        for (persona in personas) {
            //activate(persona)
            delay(300)
            furhat.say(persona.fullDesc)
            delay(300)
        }
        //activate(hostPersona)
        reentry()
    }

    onEntry {
        furhat.attend(users.random)
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
            furhat.say {
                random {
                    +"Okay, I will let you talk to ${persona.name}."
                    +"Okay, let's have a chat with ${persona.name}."
                    +"Sure, we can talk to ${persona.name}."
                }
                random {
                    +"When you want to end the conversation, just say, goodbye."
                    +"When it's time to end the conversation, just say, goodbye."
                }
            }
            currentPersona = persona
            goto(MainChat)
        }
    }
}