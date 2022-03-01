package furhatos.app.fortuneteller.flow.main

import furhatos.flow.kotlin.*


val Idle: State = state {

    init {
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