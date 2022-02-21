package gestures

import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture

//Look Away. Must be followed by LookBack
fun LookAway() = defineGesture {
    frame(0.32, 0.72, persist = true) {
        var direction = listOf(-1, 1).shuffled().first()
        if (listOf(false, true).shuffled().first()) {
            BasicParams.NECK_PAN to -9*direction
            BasicParams.GAZE_TILT to -40*direction
            BasicParams.NECK_TILT to -6
            BasicParams.GAZE_PAN to -16
        } else {
            //BasicParams.NECK_PAN to -4
            BasicParams.GAZE_TILT to -30*direction
            BasicParams.GAZE_PAN to -8
        }
    }
}
//Reverse a LookAway
val LookBack = defineGesture {
    frame(0.25, 0.3) {
        BasicParams.NECK_PAN to 0
        BasicParams.GAZE_TILT to 0
        BasicParams.NECK_TILT to 0
        BasicParams.GAZE_PAN to 0
    }
    reset(0.3)
}