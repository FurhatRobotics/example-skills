package furhatos.app.dog.gestures

import furhatos.app.dog.utils._defineGesture
import furhatos.app.dog.utils.getAudioURL
import furhatos.gestures.BasicParams

val yawn1 = _defineGesture("yawn1", frameTimes = listOf(0.2), audioURL = getAudioURL("Medium_large_dog_yawning_02.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(1.0) { }

    frame(0.2, 1.5) {
        BasicParams.PHONE_AAH to 1.0
        BasicParams.NECK_TILT to -15.0
        BasicParams.NECK_ROLL to 8.0
        BasicParams.GAZE_PAN to -18.0
    }

    reset(2.0)
}
