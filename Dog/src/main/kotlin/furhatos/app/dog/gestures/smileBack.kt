package gestures

import furhatos.gestures.defineGesture
import furhatos.gestures.BasicParams

/**
 * Gestures for smileBack.kt
 */
//Big Smile
val indefiniteBigSmile = defineGesture {
    frame(0.32, 0.64, persist = true) {
        BasicParams.BROW_UP_LEFT to 1.0
        BasicParams.BROW_UP_RIGHT to 1.0
        BasicParams.SMILE_OPEN to 0.5
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