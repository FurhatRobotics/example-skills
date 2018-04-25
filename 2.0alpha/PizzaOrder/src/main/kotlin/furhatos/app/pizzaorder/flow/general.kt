package furhatos.app.pizzaorder.flow

import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserLeave
import furhatos.flow.kotlin.state

import furhatos.flow.kotlin.*

val Idle : State = state {
    /*
        On the first run only, if we have users in interaction
        space, we attend a random user and start the interaction.
        If not, we simply wait for a user to enter.

        If we return to this state, we attend nobody and wait for
        users to enter.
     */
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
    /*
        Generic state to inherit for states where we are
        attending a user.

        If an attended user leaves, the system either
        attends another user if existing or goes back to Idle.

        If a user enters, we glance at the user.
     */
    onUserLeave (instant = true){
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}