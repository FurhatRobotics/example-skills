package furhatos.app.quiz.flow.customGestures

import furhatos.gestures.ARKitParams
import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture
import java.nio.file.attribute.BasicFileAttributes

val awaitAnswer = defineGesture("awaitAnswer", strength = 1.0, duration = 1.0) {
    frame(0.3, 1.5) {
        BasicParams.NECK_ROLL to -8.0
        ARKitParams.BROW_OUTER_UP_RIGHT to 0.5
        ARKitParams.BROW_DOWN_LEFT to 0.3
    }
    reset(2.5)
}