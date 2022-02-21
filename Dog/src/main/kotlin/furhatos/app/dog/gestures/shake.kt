package furhatos.app.dog.gestures

import furhatos.app.dog.utils._defineGesture
import furhatos.app.dog.utils.getAudioURL
import furhatos.gestures.BasicParams

val shake1 = _defineGesture("shake1", frameTimes = listOf(0.25), audioURL = getAudioURL("Medium_dog_shaking_01.wav")) {
    // This empty frame needs to be here for the audio to not play twice


    reset(0.1)
    frame(0.1, 0.25){
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0

        BasicParams.NECK_TILT to 12.0
    }
    frame(0.25) { }
    frame(0.25, 0.4){
        BasicParams.BLINK_LEFT to 0.9
        BasicParams.BLINK_RIGHT to 0.9

        BasicParams.NECK_PAN to 10.0

        BasicParams.BROW_UP_LEFT to 2.0
        BasicParams.BROW_UP_RIGHT to 2.0
    }
    frame(0.4, 0.55){
        BasicParams.BLINK_LEFT to 0.8
        BasicParams.BLINK_RIGHT to 0.8

        BasicParams.NECK_PAN to -10.0
    }
    frame(0.55, 0.6){
        BasicParams.BLINK_LEFT to 0.8
        BasicParams.BLINK_RIGHT to 0.8

        BasicParams.BROW_UP_LEFT to 2.0
        BasicParams.BROW_UP_RIGHT to 2.0

        BasicParams.NECK_PAN to 10.0
    }
    frame(0.6, 0.8){
        BasicParams.BLINK_LEFT to 0.9
        BasicParams.BLINK_RIGHT to 0.9

        BasicParams.NECK_PAN to -12.0
    }
    frame(0.8, 1.0){
        BasicParams.BLINK_LEFT to 0.7
        BasicParams.BLINK_RIGHT to 0.7

        BasicParams.NECK_PAN to 15.0
    }
    frame(1.0, 1.2){
        BasicParams.BLINK_LEFT to 0.8
        BasicParams.BLINK_RIGHT to 0.8

        BasicParams.BROW_UP_LEFT to 1.0
        BasicParams.BROW_UP_RIGHT to 1.0

        BasicParams.NECK_PAN to -15.0

    }
    frame(1.2, 1.5){
        BasicParams.BLINK_LEFT to 0.9
        BasicParams.BLINK_RIGHT to 0.9

        BasicParams.BROW_UP_LEFT to 0.5
        BasicParams.BROW_UP_RIGHT to 0.5

        BasicParams.NECK_PAN to 15.0
    }
    frame(1.5, 1.8){
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0

        BasicParams.BROW_UP_LEFT to 0.1
        BasicParams.BROW_UP_RIGHT to 0.1

        BasicParams.NECK_PAN to -15.0
    }
    frame(1.8, 2.0){
        BasicParams.BLINK_LEFT to 0.1
        BasicParams.BLINK_RIGHT to 0.1

        BasicParams.BROW_UP_LEFT to 0.0
        BasicParams.BROW_UP_RIGHT to 0.0

        BasicParams.NECK_PAN to 0.0
        BasicParams.NECK_TILT to 0.0
    }
    frame(2.0, 2.2){
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(2.2, 2.4){
        BasicParams.BLINK_LEFT to 0.0
        BasicParams.BLINK_RIGHT to 0.0
    }
    frame(2.4, 2.7){
        BasicParams.BLINK_LEFT to 1.0
        BasicParams.BLINK_RIGHT to 1.0
    }
    frame(2.7, 3.0){
        BasicParams.BLINK_LEFT to 0.0
        BasicParams.BLINK_RIGHT to 0.0
    }
    reset(3.2)
}

