package furhatos.app.furhatdog.flow

import furhatos.app.furhatdog.gestures.*
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val ShowAllGestures: State = state(Parent) {
    onEntry {
        furhat.gesture(panting1, async = false)
        delay(500)
        furhat.gesture(growlPositive2, async = false)
        delay(500)
        furhat.gesture(sniffing3, async = false)
        delay(500)
        furhat.gesture(growlPositive4, async = false)
        delay(500)
        furhat.gesture(sniffing3, async = false)
        delay(500)
        furhat.gesture(yawn1, async = false)
        delay(500)
        furhat.gesture(bark1, async = false)
        delay(500)
        furhat.gesture(whimpering3, async = false)
        delay(500)
        furhat.gesture(growl2, async = false)
        delay(500)
        furhat.gesture(whimpering2, async = false)
        delay(500)
        furhat.gesture(bark3, async = false)
        delay(500)
        furhat.gesture(shake1, async = false)
        delay(500)
        furhat.gesture(sniffing1, async = false)
        delay(500)
        furhat.gesture(whimpering5, async = false)
        delay(500)
        furhat.gesture(growl4, async = false)
        delay(500)
        furhat.gesture(meow, async = false)
        delay(500)
        furhat.gesture(bark2, async = false)
        delay(500)
        furhat.gesture(whimpering1, async = false)
        delay(500)

        goto(Main)
    }
}