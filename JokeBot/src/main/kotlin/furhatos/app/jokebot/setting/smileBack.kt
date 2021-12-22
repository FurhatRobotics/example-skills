package furhatos.app.jokebot.setting

import furhatos.app.jokebot.jokes.indefiniteBigSmile
import furhatos.app.jokebot.jokes.indefiniteSmile
import furhatos.app.jokebot.jokes.stopSmile
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserGesture
import furhatos.flow.kotlin.onUserGestureEnd
import furhatos.flow.kotlin.partialState
import furhatos.skills.emotions.UserGestures
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Parent state that smiles to the current user, if the current user is smiling.
 *
 * When a user smiles there are three possibilities.
 *  -> Furhat gives the user a big smile.
 *  -> Furhat gives the user a smile.
 *  -> Furhat does nothing.
 *
 *  If Furhat smiled, then there is a smile cooldown.
 */
val SmileBack = partialState() {

    val smileProbability = 0.25 //25% of the time we do a small smile
    val bigSmileProbability = 0.5 //50% of the time we do a big smile
    val smileTimeout = 5000L //We don't want to trigger the smiling back too much, therefor after a smile we wait 5000ms
    var smilingIsAllowed = true //Boolean depicting if Furhat is allowed to smile.

    /**
     * Lambda function that starts a thread, which allows Furhat to smile again after the set smile timeout.
     */
    val resetAllowedToSmile = { GlobalScope.launch { delay(smileTimeout); smilingIsAllowed = true } }

    //Instant trigger ('parallel thread') that detects a user smiling and takes action.
    onUserGesture(UserGestures.Smile, cond = { it.isCurrentUser && smilingIsAllowed }, instant = true) {
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

    onUserGestureEnd(UserGestures.Smile, cond = { it.isCurrentUser }, instant = true) {
        furhat.gesture(stopSmile)
    }
}
