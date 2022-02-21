package furhatos.app.dog.gestures

import furhatos.app.dog.utils._defineGesture
import furhatos.app.dog.utils.getAudioURL
import furhatos.gestures.BasicParams

val bark1 = _defineGesture("bark1", frameTimes = listOf(0.1), audioURL = getAudioURL("Small_dog_1_bark.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.1) { }

    frame(0.15) {
        BasicParams.SMILE_OPEN to 0.5
        BasicParams.NECK_TILT to -14
    }

    frame(0.30) {
        BasicParams.NECK_TILT to -0
    }

    reset(0.4)
}

val bark2 = _defineGesture("bark2", frameTimes = listOf(0.0), audioURL = getAudioURL("Small_dog_2_barks.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.0) { }

    frame(0.15, 0.45) {
        BasicParams.SMILE_OPEN to 0.5
        BasicParams.NECK_TILT to -14
    }

    frame(0.3, 0.6) {
        BasicParams.NECK_TILT to 6
    }

    reset(0.7)
}

val bark3 = _defineGesture("bark3", frameTimes = listOf(0.0), audioURL = getAudioURL("Small_dog_3_barks.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.0) { }

    frame(0.15, 0.45, 0.8) {
        BasicParams.SMILE_OPEN to 0.5
        BasicParams.NECK_TILT to -14
    }

    frame(0.3, 0.65, 0.95) {
        BasicParams.NECK_TILT to -6
    }

    reset(1.0)
}