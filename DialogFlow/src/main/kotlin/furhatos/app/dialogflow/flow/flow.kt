package furhatos.app.dialogflow.flow

import com.google.cloud.dialogflow.v2.QueryResult
import furhatos.app.dialogflow.*
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserLeave
import furhatos.flow.kotlin.state

import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.Language

val Idle : State = state {

    init {
        furhat.setTexture("male")
        furhat.setVoice(Language.ENGLISH_US, "Matthew")
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(NewSession)
        }
    }

    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(NewSession)
    }
}

val NewSession: State = state {
    onEntry {
        val session = dialogFlowAgent.createSession(furhat.outputLanguage)
        goto(RunSession(session, session.queryWelcomeEvent()))
    }
}

fun RunSession(session: DialogFlowAgent.Session, response: QueryResult) : State = state {

    onEntry {
        response.getPayload("gesture")?.let {
            Gestures.getByName(it)?.let {
                furhat.gesture(it)
            }
        }
        furhat.say(response.fulfillmentText)
        if (response.endConversation) {
            goto(Idle)
        }
        furhat.listen()
    }

    onResponse {
        goto(RunSession(session, session.queryText(it.text)))
    }

    onUserLeave {
        goto(Idle)
    }

}
