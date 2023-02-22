package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.BehaviorLib
import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.flow.IdleParent
import furhatos.app.complimentbot.MAX_ACTIVE_IDLE
import furhatos.app.complimentbot.MAX_BORED_IDLE
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.event.Event
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.gestures.ARKitParams.*
import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture
import java.util.*
import kotlin.concurrent.schedule

class GotoBored: Event()
class GotoSleeping: Event()

val ActiveIdle: State = state(IdleParent) {

    include(BehaviorLib.AutomaticMovements.randomHeadMovements(repetitionPeriod = 3000..5000))

    onEntry {
        Timer().schedule(delay = MAX_ACTIVE_IDLE) {
            send(GotoBored())
        }}
    onEvent<GotoBored> {
        goto(BoredIdle)
    }
}

val BoredIdle: State = state(IdleParent) {

    onEntry {
        furhat.setDefaultMicroexpression(blinking = false)

        furhat.gesture(BoredBasic)
        Timer().schedule(delay = MAX_BORED_IDLE) {
            send(GotoSleeping())
        }}
    onEvent<GotoSleeping> {
        goto(SleepingIdle)
    }

    onTime(100,2000..8000) {
        furhat.gesture(BoredBlink)
    }
    onExit {
        furhat.setDefaultMicroexpression(blinking = true)
    }

//    onTime(1000, 4000..8000) {
//        furhat.gesture(random(
//            GesturesLib.PerformSceptical1,
//            GesturesLib.PerformMouthSide1
//        ))
//    }
}

val SleepingIdle: State = state(IdleParent) {

    onEntry {
        furhat.stopGestures() // Plays a whole reset gesture a tiny bit after
        delay(1000)
        furhat.gesture(GesturesLib.PerformFallAsleepPersist, priority = 10)
    }

    onExit {
        furhat.gesture(GesturesLib.PerformWakeUpWithHeadShake, priority = 10)
        delay(500)
    }
}

const val blinkBoredRightValue = 0.25
const val blinkBoredLeftValue = 0.18
val BoredBlink =
    defineGesture("BoredBlink") {
        frame(0.2) {
            BasicParams.BLINK_RIGHT to 1.0
            BasicParams.BLINK_LEFT to 1.0
        }
        frame(0.4) {
            BasicParams.BLINK_RIGHT to blinkBoredRightValue
            BasicParams.BLINK_LEFT to blinkBoredLeftValue
        }
    }

val BoredBasic = defineGesture {
    frame(1.0, persist = true) {

//        BasicParams.EXPR_ANGER to 0.34
        BasicParams.BROW_UP_LEFT to 0.42
        BasicParams.BROW_UP_RIGHT to -0.25
        BasicParams.PHONE_B_M_P to 1.40
        BasicParams.BLINK_LEFT to blinkBoredLeftValue
        BasicParams.BLINK_RIGHT to blinkBoredRightValue
        BROW_DOWN_RIGHT to 0.45

//        BROW_DOWN_LEFT to 0.3
//        BROW_OUTER_UP_LEFT to 0.8
//        BROW_OUTER_UP_RIGHT to -0.3
//
//        JAW_RIGHT to -0.27
//        MOUTH_LEFT to -0.50
//        MOUTH_PRESS_LEFT to 0.6

//        MOUTH_FUNNEL to 0.054
//        MOUTH_LEFT to 0.14
//        MOUTH_ROLL_LOWER to 0.199
//        MOUTH_SMILE_LEFT to -0.4
//        MOUTH_SMILE_RIGHT to -0.201
//        MOUTH_FROWN_LEFT to 0.271
//        MOUTH_FROWN_RIGHT to 0.38
//        MOUTH_DIMPLE_RIGHT to -0.09
//        MOUTH_PRESS_LEFT to 0.184
//        MOUTH_PRESS_RIGHT to 0.184
//        MOUTH_STRETCH_LEFT to 0.073
    }
}