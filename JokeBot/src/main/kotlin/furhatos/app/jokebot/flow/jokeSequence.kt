package furhatos.app.jokebot.flow

import furhatos.app.jokebot.jokes.*
import furhatos.app.jokebot.nlu.BadJoke
import furhatos.app.jokebot.nlu.GoodJoke
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.skills.emotions.UserGestures
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
        furhat.ask("I didn't hear you ")
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

    var saidBadJoke = false
    var saidGoodJoke = false
    var timeSmiledAtJoke: Long = 0L
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

    onResponse {
        //Do nothing
    }

    onNoResponse {
        //Do nothing
    }

    onUserGesture(UserGestures.Smile, cond = { it.isCurrentUser }, instant = true) {
        timestampStartedLaughing = System.currentTimeMillis()
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


fun calculateJokeScore(timeSpentLaughing: Long, maxTimeLaughing: Int, badJoke: Boolean, goodJoke: Boolean): Double {
    val smileModifier = when {
        badJoke -> -0.25
        goodJoke -> 0.25
        else -> 0.0
    }

    val gestureModifier = if (timeSpentLaughing != 0L) {
        (maxTimeLaughing / timeSpentLaughing) * 0.5
    } else {
        0.0
    }

    return gestureModifier + smileModifier
}
