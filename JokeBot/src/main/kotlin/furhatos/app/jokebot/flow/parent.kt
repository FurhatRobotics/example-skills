package furhatos.app.jokebot.flow

import furhatos.app.jokebot.flow.main.Idle
import furhatos.app.jokebot.setting.SmileBack
import furhatos.flow.kotlin.*

val Parent: State = state() {

    include(SmileBack)
    onUserLeave(instant = true) {
        when {
            users.count == 0 -> goto(Idle)
            it == users.current -> furhat.attend(users.other)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}