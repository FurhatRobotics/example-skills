package furhatos.app.parrot.flow

import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserLeave
import furhatos.flow.kotlin.state

import furhatos.flow.kotlin.*

val Idle : State = state {
    /*
        On the first run only, if we have users in interaction
        space, we attend a random user and start the interaction.
        If not, we simply wait for a user to enter. This is also
        the case when returning to this state.
     */
    init {
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        } else {
            furhat.attendNobody()
        }
    }

    onUserEnter {
        furhat.attend(it)
        goto(Start)
    }
}

val Interaction : State = state {
    /*
        Generic state to inherit for states where we are
        attending a user.

        If an attended user leaves, the system either
        attends another user if existing or goes back to Idle.

        If a user enters, we glance at the user.
     */
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
