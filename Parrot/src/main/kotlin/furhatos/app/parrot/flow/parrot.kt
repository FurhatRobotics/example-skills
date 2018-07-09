package furhatos.app.parrot.flow

import furhatos.app.parrot.Animals
import furhatos.app.parrot.Goodbye
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.*

val Start: State = state(Interaction) {

    onEntry {
        furhat.say("Hey there, I can repeat anything you say, just like a parrot.")
        furhat.ask("Try me")
    }

    onReentry {
        furhat.listen()
    }

    onResponse<Animals> {
        call(AnimalLover)
        reentry()
    }

    onResponse<Goodbye> {
        goto(End)
    }

    onResponse { // The actual Parrot mode, repeating the user
        if (!it.speech.silent) {
            furhat.say(it.speech.text)
            reentry()
        } else {
            propagate()
        }
    }
}

val AnimalLover: State = state {
    onEntry {
        furhat.say("Oh, so you love animals? I love humans")
        terminate()
    }
}

val End = state {
    onEntry {
        furhat.say("OK, thanks for playing! See you later.")
        goto(Idle)
    }
}
