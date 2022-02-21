package furhatos.app.furhatdog.gestures

import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture
import kotlin.random.Random

/**
 * Roll the head to a random position
 */
val RandomNeckRoll = defineGesture("RandomNeckRoll") {
    frame(0.7, 4.3){
        BasicParams.NECK_ROLL to Random.nextDouble(-12.0, 12.0)
    }
    reset(5.0)
}