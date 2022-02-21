package gestures

import furhatos.gestures.defineGesture
import furhatos.gestures.BasicParams

/**
 * Take a breath right before Speaking.
 * Leave about 1 second before beginning to speak. This method would benefit from slower motor movements
 */
val BreathIn = defineGesture("BreathIn") {
    frame(0.35){
        BasicParams.PHONE_AAH to 0.0
        BasicParams.NECK_TILT to -14.0
    }
    frame(0.7){
        BasicParams.PHONE_AAH to 0.4
        BasicParams.NECK_TILT to -14.0
    }
    frame(1.4){
        BasicParams.PHONE_AAH to 0.0
    }
    frame(5.8){
        BasicParams.NECK_TILT to 5.0
        BasicParams.EYE_SQUINT_LEFT to 0.3
        BasicParams.EYE_SQUINT_RIGHT to 0.3
    }
    reset(6.2)
}