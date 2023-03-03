package furhatos.app.complimentbot.gestures

import furhatos.gestures.ARKitParams
import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture

const val blinkBoredRightValue = 0.25
const val blinkBoredLeftValue = 0.18
val BoredBlink =
    defineGesture("BoredBlink") {
        frame(0.2) {
            BasicParams.BLINK_RIGHT to 1.0
            BasicParams.BLINK_LEFT to 1.0
        }
        frame(0.4) {
            BasicParams.BLINK_RIGHT to blinkBoredRightValue
            BasicParams.BLINK_LEFT to blinkBoredLeftValue
        }
    }

val BoredBasic = defineGesture {
    frame(1.0, persist = true) {
        BasicParams.BROW_UP_LEFT to 0.42
        BasicParams.BROW_UP_RIGHT to -0.25
        BasicParams.PHONE_B_M_P to 1.40
        BasicParams.BLINK_LEFT to blinkBoredLeftValue
        BasicParams.BLINK_RIGHT to blinkBoredRightValue
        ARKitParams.BROW_DOWN_RIGHT to 0.45
    }
}

val BoredLookAround = defineGesture {
    frame(1.5) {
        BasicParams.BROW_UP_LEFT to 0.6
        BasicParams.BROW_UP_RIGHT to 0.6
        BasicParams.BLINK_LEFT to 0.0
        BasicParams.BLINK_RIGHT to 0.0

        BasicParams.LOOK_UP to 1.25
        BasicParams.LOOK_RIGHT to 1.20

        BasicParams.NECK_ROLL to -4.0
        BasicParams.NECK_TILT to -15.0
        BasicParams.NECK_PAN to -20.0
    }
    frame(4.5) {
        BasicParams.BROW_UP_LEFT to 0.6
        BasicParams.BROW_UP_RIGHT to 0.6
        BasicParams.BLINK_LEFT to 0.0
        BasicParams.BLINK_RIGHT to 0.0

        BasicParams.LOOK_UP to 1.25
        BasicParams.LOOK_RIGHT to 1.20

        BasicParams.NECK_ROLL to -4.0
        BasicParams.NECK_TILT to -15.0
        BasicParams.NECK_PAN to -20.0
    }

    frame(5.5) {
        BasicParams.LOOK_UP to 0.0
        BasicParams.LOOK_RIGHT to 0.0
        BasicParams.LOOK_LEFT to 0.80

        BasicParams.NECK_ROLL to 0.0
        BasicParams.NECK_TILT to 0.0
        BasicParams.NECK_PAN to 22.0
    }

    frame(8.0) {
        BasicParams.LOOK_UP to 0.0
        BasicParams.LOOK_LEFT to 0.80

        BasicParams.NECK_ROLL to 0.0
        BasicParams.NECK_TILT to 0.0
        BasicParams.NECK_PAN to 22.0
    }

    frame(9.0) { // RESET
        BasicParams.BROW_UP_LEFT to 0.6
        BasicParams.BROW_UP_RIGHT to 0.6
        BasicParams.BLINK_LEFT to blinkBoredLeftValue
        BasicParams.BLINK_RIGHT to blinkBoredRightValue
        BasicParams.LOOK_LEFT to 0.0
        BasicParams.NECK_PAN to 0.0
    }
}

val resetBoredFace = defineGesture {
    frame(0.5) {
        BasicParams.BROW_UP_LEFT to 0.0
        BasicParams.BROW_UP_RIGHT to 0.0
        BasicParams.PHONE_B_M_P to 0.0
        ARKitParams.BROW_DOWN_RIGHT to 0.0
    }
}

val resetEyes = defineGesture {
    frame(0.5) {
        BasicParams.BLINK_LEFT to 0.0
        BasicParams.BLINK_RIGHT to 0.0
    }
}