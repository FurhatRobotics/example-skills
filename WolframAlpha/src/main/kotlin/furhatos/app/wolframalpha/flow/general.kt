package furhatos.app.wolframalpha.flow

import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.util.*

val defaultVoice = PollyVoice("Matthew")

val idle: State = state {
    init {
        furhat.setTexture("male")
        furhat.voice = defaultVoice
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(start)
        }
    }

    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(start)
    }
}

val interaction: State = state {
    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

}