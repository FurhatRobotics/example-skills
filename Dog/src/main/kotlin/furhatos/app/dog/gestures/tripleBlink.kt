package gestures

import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture

/**
 * Blink three times
 */
val TripleBlink = defineGesture("TripleBlink") {
    frame(0.1, 0.3){
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(0.3, 0.5){
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1
    }
    frame(0.5, 0.7){
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(0.7, 0.9){
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1
        BasicParams.BROW_UP_LEFT to 2.0
        BasicParams.BROW_UP_RIGHT to 2.0
    }
    frame(0.9, 1.1){
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(1.1, 1.4){
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1
    }
    frame(1.4, 1.5){
        BasicParams.BROW_UP_LEFT to 0
        BasicParams.BROW_UP_RIGHT to 0
    }
    reset(1.5)
}