package furhatos.app.flightbooking.flow

import furhatos.flow.kotlin.*

val Idle : State = state {

    init {
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        }
    }

    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Start)
    }
}

val Interaction : State = state {

    onUserLeave {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it, 1)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter {
        furhat.glance(it, 1)
    }

}