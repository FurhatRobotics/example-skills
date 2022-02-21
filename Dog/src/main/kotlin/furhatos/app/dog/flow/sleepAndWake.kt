package furhatos.app.dog.flow

import furhatos.app.dog.gestures.shake1
import furhatos.app.dog.gestures.yawn1
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import gestures.FallAsleep
import gestures.WakeUpWithHeadShake

val Sleep: State = state(Parent) {
    onExit {
        furhat.gesture(WakeUpWithHeadShake, priority=10)
    }
    onEntry {
        furhat.gesture(FallAsleep, priority=10)
    }
}
val WakeUp: State = state(Parent) {
    onEntry {
        delay(2300)
        furhat.gesture(shake1, async = false)
        delay(1000)
        furhat.gesture(yawn1, async = false)
    }
}