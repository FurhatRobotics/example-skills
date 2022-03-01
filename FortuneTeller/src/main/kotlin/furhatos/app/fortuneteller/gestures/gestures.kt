package furhatos.app.fortuneteller.gestures

import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture

val cSmile = defineGesture("cSmile") {
    frame(0.5, persist = true) {
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }

}

fun rollHead(strength: Double = 1.0, duration: Double = 1.0) =
        defineGesture("rollHead") {
            frame(0.4, duration) {
                BasicParams.NECK_ROLL to strength
            }
            reset(duration + 0.1)
        }

val FallAsleep = defineGesture("FallAsleep") {
    frame(0.5, persist = true) {
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }

}

val MySmile = defineGesture("MySmile") {
    frame(0.32, 0.72) {
        BasicParams.SMILE_CLOSED to 2.0
    }
    frame(0.2, 0.72) {
        BasicParams.BROW_UP_LEFT to 4.0
        BasicParams.BROW_UP_RIGHT to 4.0
    }
    frame(0.16, 0.72) {
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 0.1
    }
    reset(1.04)
}

val TripleBlink = defineGesture("TripleBlink") {
    frame(0.1, 0.3) {
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(0.3, 0.5) {
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1
    }
    frame(0.5, 0.7) {
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(0.7, 0.9) {
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1
        BasicParams.BROW_UP_LEFT to 2.0
        BasicParams.BROW_UP_RIGHT to 2.0
    }
    frame(0.9, 1.1) {
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(1.1, 1.4) {
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1
    }
    frame(1.4, 1.5) {
        BasicParams.BROW_UP_LEFT to 0
        BasicParams.BROW_UP_RIGHT to 0
    }
    reset(1.5)
}