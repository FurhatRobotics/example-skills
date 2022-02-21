package furhatos.app.furhatdog.gestures

import furhatos.app.furhatdog.utils._defineGesture
import furhatos.app.furhatdog.utils.getAudioURL
import furhatos.gestures.BasicParams

val sniffing1 = _defineGesture("sniffing1", frameTimes = listOf(0.2), audioURL = getAudioURL("Sniffing1.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.2) { }

    frame(1.0, 3.0, 5.0) {
        BasicParams.BROW_DOWN_LEFT to 0.5
        BasicParams.BROW_DOWN_RIGHT to 0.5
    }
    frame(2.0, 4.0, 6.2) {
        BasicParams.BROW_DOWN_LEFT to 0.0
        BasicParams.BROW_DOWN_RIGHT to 0.0
    }

    reset(6.5)
}
val sniffing3 = _defineGesture("sniffing3", frameTimes = listOf(0.2), audioURL = getAudioURL("Sniffing3.wav")) {
    // This empty frame needs to be here for the audio to not play twice
    frame(0.2) { }

    frame(0.4, 1.7) {
        BasicParams.BROW_DOWN_LEFT to 0.5
        BasicParams.BROW_DOWN_RIGHT to 0.5
    }

    reset(1.7)
}
//
//val sniffing2 = _defineGesture("sniffing2", frameTimes = listOf(1.5), audioURL = getAudioURL("Sniffing2.wav")) {
//    // This empty frame needs to be here for the audio to not play twice
//    frame(1.5) { }
//
//    frame(0.4, 0.8, 1.2, 1.4, 1.8) {
//        BasicParams.PHONE_B_M_P to 0.0
//    }
//    frame(0.6, 1.0, 1.4, 1.6) {
//        BasicParams.PHONE_B_M_P to 1.0
//    }
//
//    reset(6.5)
//}
