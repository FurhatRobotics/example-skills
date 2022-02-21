package furhatos.app.furhatdog.gestures

import furhatos.app.furhatdog.utils._defineGesture
import furhatos.app.furhatdog.utils.getAudioURL
import furhatos.gestures.BasicParams

val panting1 = _defineGesture("panting1", frameTimes = listOf(0.2), audioURL = getAudioURL("Panting1.wav")) {

    // This empty frame needs to be here for the audio to not play twice
    frame(0.2) { }

    frame(0.3, 0.61, 0.92, 1.23, 1.54, 1.85, 2.16, 2.47) {

        BasicParams.PHONE_EE to 0.9
        BasicParams.PHONE_AAH to 0.5
    }
    frame(0.46, 0.77, 1.08, 1.39, 1.70, 2.0, 2.31, 2.7) {
        BasicParams.PHONE_EE to 0.5
        BasicParams.PHONE_AAH to 0.2
    }

    reset(3.0)
}