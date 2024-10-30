package furhatos.app.openaichat.flow

import furhatos.app.openaichat.flow.chatbot.serviceKey
import furhatos.app.openaichat.setting.activate
import furhatos.app.openaichat.setting.hostPersona
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val Init: State = state() {
    init {
        /** Check API key for the OpenAI GPT3 language model has been set */
        if (serviceKey.isEmpty()) {
            println("Missing API key for OpenAI language model. ")
            exit()
        }

        /** Set the Persona */
        activate(hostPersona)

        /** start the interaction */
        goto(InitFlow)
    }

}

val InitFlow: State = state() {
    onEntry {
        when {
            users.hasAny() -> goto(ChoosePersona())
            !users.hasAny() -> goto(Idle)
        }
    }

}


