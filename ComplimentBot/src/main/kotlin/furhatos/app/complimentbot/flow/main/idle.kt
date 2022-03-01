package furhatos.app.complimentbot.flow.main

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state

val Idle: State = state {

    init {
        //furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        //if (users.count > 0) {
        //furhat.attend(users.random)
        //goto(Start)
        //}
    }

    onEntry {
        //furhat.attendNobody()
    }

    onUserEnter {
        goto(startReading(it))
    }
}