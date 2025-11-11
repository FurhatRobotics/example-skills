package furhatos.app.openaichat.flow.chatbot

import furhatos.app.openaichat.flow.*
import furhatos.app.openaichat.setting.activate
import furhatos.app.openaichat.setting.hostPersona
import furhatos.app.openaichat.setting.personas
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val MainChat = state(Parent) {
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
                +"I hope that was an insightful demonstration of generative A I and social robots"
                +"I hope you enjoyed conversing with a generative A I "
                +"I hope you found that interesting"
            }
        }
        goto(AfterChat)
    }

    onResponse {
        furhat.gesture(GazeAversion(2.0))
        val response = call {
            currentPersona.chatbot.getResponse()
        } as String
        furhat.say(response)
        reentry()
    }

    onNoResponse {
        reentry()
    }
}

val AfterChat: State = state(Parent) {

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