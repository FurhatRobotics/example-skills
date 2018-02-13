package furhatos.app.parrot.flow

import furhatos.app.parrot.Animals
import furhatos.app.parrot.Goodbye
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.*
import furhatos.skills.SkillGUI

val Start: State = state(Interaction) {

    onEntry {
        furhat.say("Hey there, I can repeat anything you say, just like a parrot.")
        furhat.ask("Try me")
    }

    onReentry {
        furhat.listen()
    }

    onResponse {
        if (!it.speech.silent) {
            furhat.say(it.speech.text)
        } else {
            propagate()
        }
    }

    onResponse<Animals> {
        call(AnimalLover)
        reentry()
    }

    onResponse<Goodbye> {
        goto(End)
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
