package furhatos.app.dog.gestures

import furhatos.app.dog.utils._defineGesture
import furhatos.app.dog.utils.getAudioURL
import furhatos.gestures.BasicParams

val whimpering1 = _defineGesture("whimpering1", frameTimes = listOf(0.0), audioURL = getAudioURL("Small_dog_whining_01.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.2, 1.5) {
        BasicParams.NECK_ROLL to 12
        BasicParams.EXPR_DISGUST to 0.5
    }
    reset(2.5)
}

val whimpering2 = _defineGesture("whimpering2", frameTimes = listOf(0.0), audioURL = getAudioURL("Small_dog_whining_02.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.0) { }
    frame(0.2, 1.5) {
        BasicParams.NECK_ROLL to -12
        BasicParams.EXPR_DISGUST to 0.5
    }

    reset(2.5)
}

val whimpering3 = _defineGesture("whimpering3", frameTimes = listOf(0.0), audioURL = getAudioURL("Small_dog_whining_03.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.0) { }
    frame(0.2, 1.5) {
        BasicParams.NECK_ROLL to 12
        BasicParams.EXPR_DISGUST to 0.5
    }
    reset(2.5)
}

val whimpering5 = _defineGesture("whimpering5", frameTimes = listOf(0.0), audioURL = getAudioURL("Small_dog_whining_05.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.2, 1.5) {
        BasicParams.NECK_ROLL to -12
        BasicParams.EXPR_DISGUST to 0.5
    }
    reset(2.5)
}