package furhatos.app.jokebot.flow

import furhatos.app.jokebot.jokes.JokeHandler
import furhatos.flow.kotlin.*
import furhatos.gestures.BasicParams
import furhatos.gestures.Gestures
import furhatos.gestures.defineGesture
import furhatos.skills.emotions.UserGestures
import furhatos.util.CommonUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val newIntros = listOf(
        "I haven't tried this one before actually.",
        "This is a new one."
)

private val neutralIntros = listOf(
        "Let’s see what you think of this one.",
        "Mixed reactions on this one so far."
)

private val positiveIntros = listOf(
        "People usually like this one.",
        "This one has been a success so far."
)

private val negativeIntros = listOf(
        "This one is not liked by many, but maybe it's your kind of humor.",
        "I haven’t had much luck with this one.",
        "I’ll give this one another chance"
)

fun getJokeIntro(score: Double?) : String {
    return when {
        score == null -> newIntros.random()
        score > 0.0 -> positiveIntros.random()
        score < 0.0 -> negativeIntros.random()
        else -> neutralIntros.random()
    }
}

val JokeSequence: State = state {

    onEntry {
        val joke = JokeHandler.getJoke()
        val utterance = utterance {
            +getJokeIntro(joke.score)
            +delay(200)
            +joke.intro
            +delay(1500) //Always let the intro sink in
            +joke.punchline
        }
        furhat.say(utterance)

        val jokeRanking = call(JokeScore) as Double
        JokeHandler.changeJokeScore(jokeRanking)


    }
}

val SmileBack: State = state {


    val smileProbability = 0.25
    val bigSmileProbability = 0.5
    val smileTimeout = 5000L
    var smilingIsAllowed = true
    val resetAllowedToSmile = { GlobalScope.launch { delay(smileTimeout); smilingIsAllowed = true }} //Lambda function

    onUserGesture(UserGestures.Smile, cond = { it.isCurrentUser && smilingIsAllowed}) {
        val randomNumber = Math.random()
        when {
            randomNumber > bigSmileProbability -> {
                smilingIsAllowed = false
                furhat.gesture(indefiniteBigSmile)
                resetAllowedToSmile()
            }
            randomNumber > smileProbability -> {
                smilingIsAllowed = false
                furhat.gesture(indefiniteSmile)
                resetAllowedToSmile()
            }
            else -> {
                //Nothing
            }
        }
    }

    onUserGestureEnd(UserGestures.Smile, cond = { it.isCurrentUser }) {
        furhat.gesture(stopSmile)
    }
}

val JokeScore: State = state(SmileBack) {

    var timeSmiledAtJoke: Long
    var timestampStartedLaughing = 0L
    val jokeTimeout = 6000 // We wait for a reaction for 6 seconds
    val jokeNegativeScore = 0.25

    onUserGesture(UserGestures.Smile, cond = { it.isCurrentUser }) {
        timestampStartedLaughing = System.currentTimeMillis()
        propagate()
    }

    onUserGestureEnd(UserGestures.Smile, cond = { it.isCurrentUser }) {
        timeSmiledAtJoke = System.currentTimeMillis() - timestampStartedLaughing //Calculating the amount of millis spent laughing
        //TODO FIX THIS
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


/**
 * The gestures are defined here.
 */

//Big Smile
val indefiniteBigSmile = defineGesture {
    frame(0.32, 0.64, persist = true) {
        BasicParams.BROW_UP_LEFT to 1.0
        BasicParams.BROW_UP_RIGHT to 1.0
        BasicParams.SMILE_OPEN to 0.4
        BasicParams.SMILE_CLOSED to 0.7
    }
}

//Small smile
val indefiniteSmile = defineGesture {
    frame(0.32, 0.72, persist = true) {
        BasicParams.SMILE_CLOSED to 0.5
    }
    frame(0.2, 0.72){
        BasicParams.BROW_UP_LEFT to 1.0
        BasicParams.BROW_UP_RIGHT to 1.0
    }
    frame(0.16, 0.72){
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1
    }
}

//No more smiling
val stopSmile = defineGesture {
    frame(0.32, 0.64) {
        BasicParams.BROW_UP_LEFT to 0.0
        BasicParams.BROW_UP_RIGHT to 0.0
        BasicParams.SMILE_OPEN to 0.0
        BasicParams.SMILE_CLOSED to 0.0
    }
}