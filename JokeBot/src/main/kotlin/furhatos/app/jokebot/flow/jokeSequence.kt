package furhatos.app.jokebot.flow

import furhatos.app.jokebot.jokes.*
import furhatos.app.jokebot.nlu.BadJoke
import furhatos.app.jokebot.nlu.GoodJoke
import furhatos.app.jokebot.util.calculateJokeScore
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.skills.emotions.UserGestures

/**
 * This state gets jokes, tells jokes, and records the user's response to it.
 *
 * Once the joke has been told, prompts the user if they want to hear another joke.
 */
val JokeSequence: State = state(Interaction) {

    onEntry {
        //Get joke from the JokeHandler
        val joke = JokeHandler.getJoke()

        //Build an utterance with the joke.
        val utterance = utterance {
            +getJokeComment(joke.score) //Get comment on joke, using the score
            +delay(200) //Short delay
            +joke.intro //Deliver the intro of the joke
            +delay(1500) //Always let the intro sink in
            +joke.punchline //Deliver the punchline of the joke.
        }

        furhat.say(utterance)

        /**
         * Calls a state which we know returns a joke score.
         */
        JokeHandler.changeJokeScore(call(JokeScore) as Double)

        //Prompt the user if they want to hear another joke.
        furhat.ask(continuePrompt.random())
    }

    onResponse<Yes> {
        furhat.say("Sweet!")
        reentry()
    }

    onResponse<No> {
        furhat.say("Alright, thanks for letting me practice!")
        if (users.count > 1) {
            furhat.attend(users.other)
            furhat.ask("How about you? do you want some jokes?")
        } else {
            goto(Idle)
        }
    }

    onResponse {
        furhat.ask("Yes or no?")
    }

    onNoResponse {
        furhat.ask("I didn't hear you.")
    }
}

/**
 * This state records the users reaction to a joke, and terminates with calculated joke value.
 * Joke value is based on if the user smiled and/or the user said it was a good/bad joke.
 */
val JokeScore: State = state(Interaction) {

    var saidBadJoke = false
    var saidGoodJoke = false
    var timeSmiledAtJoke = 0L
    var timestampStartedLaughing = 0L
    val jokeTimeout = 4000 // We wait for a reaction for 4 seconds

    onEntry {
        furhat.listen()
    }

    onResponse<GoodJoke>(instant = true) {
        saidGoodJoke = true
    }

    onResponse<BadJoke>(instant = true) {
        saidBadJoke = true
    }

    onResponse(instant = true) {
        //Do nothing
    }

    onNoResponse(instant = true) {
        //Do nothing
    }

    onUserGesture(UserGestures.Smile, cond = { it.isCurrentUser }, instant = true) {
        timestampStartedLaughing = System.currentTimeMillis() //Timestamp the moment the user started smiling.
        propagate()
    }

    onUserGestureEnd(UserGestures.Smile, cond = { it.isCurrentUser }, instant = true) {
        timeSmiledAtJoke += System.currentTimeMillis() - timestampStartedLaughing //Calculating the amount of millis spent laughing
        timestampStartedLaughing = 0L
        propagate()
    }

    onTime(delay = jokeTimeout) {
        if (timestampStartedLaughing != 0L) {
            timeSmiledAtJoke += System.currentTimeMillis() - timestampStartedLaughing
        }

        furhat.say(getResponseOnUser(timeSmiledAtJoke != 0L, saidBadJoke, saidGoodJoke))

        terminate(calculateJokeScore(timeSmiledAtJoke, jokeTimeout, saidBadJoke, saidGoodJoke))
    }
}
