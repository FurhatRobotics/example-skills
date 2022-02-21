package furhatos.app.furhatdog.gestures

import furhatos.app.furhatdog.utils._defineGesture
import furhatos.app.furhatdog.utils.getAudioURL
import furhatos.gestures.BasicParams

val meow = _defineGesture("meow", frameTimes = listOf(0.2), audioURL = getAudioURL("Meow.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(1.0) { }

    frame(0.5, 1.5) {
        BasicParams.EXPR_ANGER to 0.0
    }
    frame(1.3, 2.7) {
        BasicParams.NECK_ROLL to 0
        BasicParams.BROW_UP_RIGHT to 0.0
        BasicParams.BROW_UP_LEFT to 0.0
    }
    frame(1.5, 2.5) {
        BasicParams.NECK_ROLL to 11
        BasicParams.BROW_UP_RIGHT to 1.0
        BasicParams.BROW_UP_LEFT to 1.0
        BasicParams.SMILE_CLOSED to 0.0
        BasicParams.SMILE_OPEN to 0.0
    }
    frame(2.8, 3.5) {
        BasicParams.SMILE_CLOSED to 1.0
        BasicParams.SMILE_OPEN to 0.3
    }

    reset(3.9)
}
