package gestures

import furhatos.gestures.defineGesture
import furhatos.gestures.BasicParams

/**
 * Recall a memory
 * @param durationMillis The time the expression should last
 */
fun Recall(durationMillis: Int = 2000) = defineGesture("Recall") {
    val durationSeconds: Double = durationMillis.toDouble()/1000
    frame(0.15,durationSeconds-0.15) {
        BasicParams.NECK_TILT to -7
        BasicParams.GAZE_TILT to -30
        BasicParams.NECK_PAN to -7
        BasicParams.GAZE_PAN to -22
    }
    reset(durationSeconds)
}
