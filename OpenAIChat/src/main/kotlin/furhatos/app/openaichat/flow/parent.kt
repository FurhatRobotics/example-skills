package furhatos.app.openaichat.flow

import furhatos.event.actions.ActionLipSync
import furhatos.flow.kotlin.*


val Parent: State = state {

    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Idle)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

    /** Averts the eye gaze of the robot at appropriate times to avoid robot staring at the user */
    onEvent<ActionLipSync>(instant=true) {
        var silences = it.phones.phones.dropWhile { it.name == "_s" }.dropLastWhile { it.name == "_s" }.filter { it.name == "_s" }.toMutableList()
        if (silences.isNotEmpty()) {
            runThread(true) {
                var last = 0.0f
                while (silences.isNotEmpty()) {
                    val silence = silences.removeAt(0)
                    val sleepTime = (silence.start - 0.2) - last
                    val avertTime = 0.2 + (silence.end - silence.start)
                    if (sleepTime > 0.0) {
                        Thread.sleep((sleepTime * 1000.0).toLong())
                        furhat.gesture(GazeAversion(avertTime))
                    }
                    last = silence.end
                }
            }
        }
    }

}