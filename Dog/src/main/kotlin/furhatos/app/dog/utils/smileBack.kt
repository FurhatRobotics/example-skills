package furhatos.app.dog.utils

/**
 * This can be included using include(SmileBackState)
 *
 * Todo. See if smiles should have rondomized strength
 */

import furhatos.flow.kotlin.*
import furhatos.skills.emotions.UserGestures
import gestures.indefiniteBigSmile
import gestures.indefiniteSmile
import gestures.stopSmile
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var flagSmileBack = true

var smileProbability = 0.40
var bigSmileProbability = 0.60
/**
 * Noticed that 3000ms was too quick, that's why this is set to 5000ms
 */
var smileBlockDelay: Long = 5000
var smilingIsAllowed = true

val SmileBackState = state {

    /**
     * If a smile is allowed, one of three things will happen
     * Big Smile
     * Smile
     * or Nothing
     */
    onUserGesture(UserGestures.Smile, cond = { smilingIsAllowed }) {
        val randomValue = Math.random()
        when {
            randomValue < bigSmileProbability -> {
                smilingIsAllowed = false
                furhat.gesture(indefiniteBigSmile)
                resetAllowedToSmile()
            }
            randomValue < smileProbability + bigSmileProbability -> {
                smilingIsAllowed = false
                furhat.gesture(indefiniteSmile)
                resetAllowedToSmile()
            }
            else -> {
                //No Smile
            }
        }
        //reentry()
    }

    onUserGestureEnd(UserGestures.Smile) {
        furhat.gesture(stopSmile)
        //reentry()
    }
}

/**
 * Resets the variable to allow for a smile after a default of smileBlockDelay ms
 */
fun resetAllowedToSmile() {
    GlobalScope.launch {
        //val currentTime = System.currentTimeMillis()
        delay(smileBlockDelay)
        smilingIsAllowed = true
    }
}


