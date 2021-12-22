package furhatos.app.jokebot.flow.main

import furhatos.app.jokebot.flow.Parent
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*

val Start : State = state(Parent) {

    onEntry {
        furhat.ask("Hi there.")
    }

    onResponse {
        goto(AreYouHappy)
    }

    onNoResponse {
        goto(AreYouHappy)
    }
}

val AreYouHappy: State = state(Parent) {

    onEntry {
        furhat.ask("I am wondering, are you happy today?")
    }

    onResponse<Yes> {
        furhat.say("Great to hear, then you are in the right mood!")
        goto(RequestJokeTest)
    }

    onResponse<No> {
        furhat.say("I'm sorry to hear that. Hmm, perhaps we can do something to cheer you up.")
        goto(RequestJokeTest)
    }

    onResponse {
        furhat.say("Perhaps we can try to increase your happiness a few notches.")
        goto(RequestJokeTest)
    }
}

val RequestJokeTest: State = state(Parent) {
    onEntry {
        furhat.ask("I’m trying to learn some humor you see. So. Could I test a few jokes on you?")
    }

    onResponse<Yes> {
        furhat.say("Awesome")
        goto(JokeSequence)
    }

    onResponse<No> {
        furhat.say("Oh, that’s a shame.")
        goto(Idle)
    }
}
