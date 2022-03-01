package furhatos.app.fortuneteller.flow.main

import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state


val Idle: State = state {

    init {
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