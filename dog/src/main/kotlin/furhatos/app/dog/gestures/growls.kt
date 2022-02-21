package furhatos.app.dog.gestures

import furhatos.app.dog.utils._defineGesture
import furhatos.app.dog.utils.getAudioURL
import furhatos.gestures.BasicParams

val growlPositive2 = _defineGesture("growlPositive2", frameTimes = listOf(0.2), audioURL = getAudioURL("Small_dog_single_growl_non_aggressive_02.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(1.0) { }

    frame(0.5, 1.5) {
        BasicParams.NECK_ROLL to 10
        BasicParams.SMILE_OPEN to 0.8
    }

    reset(2.0)
}
val growlPositive4 = _defineGesture("growlPositive4", frameTimes = listOf(0.2), audioURL = getAudioURL("Small_dog_single_growl_non_aggressive_04.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(1.0) { }

    frame(0.5, 1.5) {
        BasicParams.NECK_ROLL to -10
        BasicParams.SMILE_OPEN to 0.8
    }

    reset(2.0)
}


val growl2 = _defineGesture("growl2", frameTimes = listOf(0.2), audioURL = getAudioURL("Medium_dog_growl_vicious_02.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.2) { }

    frame(0.5, 1.5) {
        BasicParams.EXPR_ANGER to 1.0
    }

    reset(2.0)
}

val growl4 = _defineGesture("growl4", frameTimes = listOf(1.5), audioURL = getAudioURL("Medium_dog_growl_vicious_04.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(1.5) { }

    frame(0.5, 3.0) {
        BasicParams.EXPR_ANGER to 1.0
    }

    reset(4.0)
}
