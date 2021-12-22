package furhatos.app.jokebot.flow.main

import furhatos.flow.kotlin.*

val Idle: State = state {

    init {
        when {
            users.count > 0 -> {
                furhat.attend(users.random)
                goto(Start)
            }
            users.count == 0 && furhat.isVirtual() -> goto(Start) // if the skill is run on virtual furhat, ignore if there are no users and start anyway.
            users.count == 0 && !furhat.isVirtual() -> furhat.say("I can't see anyone. Step closer please. ")
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