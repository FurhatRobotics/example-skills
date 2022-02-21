package gestures

import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture

/**
 * Squint
 */
fun Squint(strength: Double = 1.0, duration: Double = 1.0) = defineGesture("Squint", strength, duration) {
    frame(0.2, duration-0.2){
        BasicParams.EYE_SQUINT_LEFT to strength
        BasicParams.EYE_SQUINT_RIGHT to strength
    }
    reset(duration)
}