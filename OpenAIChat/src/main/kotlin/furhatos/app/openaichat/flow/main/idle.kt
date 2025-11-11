package furhatos.app.openaichat.flow

import furhatos.app.openaichat.setting.activate
import furhatos.app.openaichat.setting.hostPersona
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.records.Location

val Idle : State = state {
    onEntry {
        activate(hostPersona)
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(ChoosePersona())
    }

}





