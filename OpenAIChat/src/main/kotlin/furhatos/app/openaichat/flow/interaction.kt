package furhatos.app.openaichat.flow

import furhatos.app.openaichat.serviceKey
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.gestures.ARKitParams
import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture
import furhatos.records.Location

val Idle : State = state {

    init {
        // Check API key for the OpenAI GPT3 language model has been set
        if (serviceKey.isEmpty()){
            println("Missing API key for OpenAI GPT3 language model. ")
            exit()
        }

        activate(hostPersona)
        if (users.hasAny()) {
            furhat.attend(users.userClosestToPosition(Location(0.0,0.0,0.5)))
            goto(Greeting)
        }
    }

    onEntry {
        activate(hostPersona)
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Greeting)
    }

}

val Greeting = state(Interaction) {

    onEntry {
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

fun ChoosePersona() = state(Interaction) {

    val selectedPersonas = personas.take(3)

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

val MainChat = state(Interaction) {
    onEntry {
        activate(currentPersona)
        delay(2000)
        Furhat.dialogHistory.clear()
        furhat.say("Hello, I am ${currentPersona.fullDesc}. ${currentPersona.intro}")
        reentry()
    }

    onReentry {
        furhat.listen()
    }

    onResponse("can we stop", "goodbye") {
        furhat.say("Okay, goodbye")
        activate(hostPersona)
        delay(2000)
        furhat.say {
            random {
                +"I hope that was fun"
                +"I hope you enjoyed that"
                +"I hope you found that interesting"
            }
        }
        goto(AfterChat)
    }

    onResponse {
        furhat.gesture(GazeAversion(2.0))
        val response = call {
            currentPersona.chatbot.getNextResponse()
        } as String
        furhat.say(response)
        reentry()
    }

    onNoResponse {
        reentry()
    }


}

val AfterChat: State = state(Interaction) {

    onEntry {
        furhat.ask("Would you like to talk to someone else?")
    }

    onPartialResponse<Yes> {
        raise(it.secondaryIntent)
    }

    onResponse<Yes> {
        goto(ChoosePersona())
    }

    onResponse<No> {
        furhat.say("Okay, goodbye then")
        goto(Idle)
    }

    for (persona in personas) {
        onResponse(persona.intent) {
            furhat.say("Okay, I will let you talk to ${persona.name}")
            currentPersona = persona
            goto(MainChat)
        }
    }

}