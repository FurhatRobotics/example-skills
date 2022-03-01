package furhatos.app.complimentbot.flow.main

import furhatos.flow.kotlin.*

val Idle: State = state {

    init {
        //furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        //if (users.count > 0) {
        //furhat.attend(users.random)
        //goto(Start)
        //}
        if (furhat.isVirtual() && users.hasAny() == false) {
            furhat.say("Add a Virtual User to start the interaction. ")
        }
    }

    onEntry {
        //furhat.attendNobody()
    }

    onUserEnter {
        goto(startReading(it))
    }
}