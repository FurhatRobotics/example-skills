package furhatos.app.jokebot.flow

import furhatos.app.jokebot.jokes.JokeHandler
import furhatos.flow.kotlin.*
import furhatos.skills.emotions.UserGestures

val JokeSequence: State = state {

    onEntry {
        val joke = JokeHandler.getJoke()
        when {
            joke.score == 0.0 -> {
                furhat.say("I haven't tried this one before actually")
            }
            joke.score > 0.0 -> {
                furhat.say("People usually like this one")
            }
            joke.score < 0.0 -> {
                furhat.say("This one is not liked by many, but maybe it's your kind of humor.")
            }
        }
        furhat.say(joke.intro)

        delay(1500) //Always let the intro sink in

        furhat.say(joke.punchline)
        val jokeRanking = call(JokeScore) as Double
    }
}

val JokeScore: State = state {

    var timeSmiledAtJoke: Long
    var timestampStartedLaughing = 0L
    val jokeTimeout = 6000 // We wait for a smile for 6 seconds
    val jokeNegativeScore = 0.25

    onUserGesture(UserGestures.Smile, cond = { it.isCurrentUser }) {
        timestampStartedLaughing = System.currentTimeMillis()
    }

    onUserGestureEnd(UserGestures.Smile, cond = { it.isCurrentUser }) {
        timeSmiledAtJoke = System.currentTimeMillis() - timestampStartedLaughing //Calculating the amount of millis spent laughing
        terminate(calculateJokeScore(timeSmiledAtJoke, jokeTimeout))
    }

    onTime(delay = jokeTimeout) {
        if (timestampStartedLaughing != 0L) {
            timeSmiledAtJoke = System.currentTimeMillis() - timestampStartedLaughing
            terminate(calculateJokeScore(timeSmiledAtJoke, jokeTimeout))
        } else {
            terminate(jokeNegativeScore)
        }
    }
}


fun calculateJokeScore(timeSpentLaughing: Long, maxTimeLaughing: Int): Double {
    return if (timeSpentLaughing != 0L) {
        (maxTimeLaughing / timeSpentLaughing) * 0.5
    } else {
        0.0
    }
}