package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.BehaviorLib
import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.flow.IdleParent
import furhatos.app.complimentbot.MAX_ACTIVE_IDLE
import furhatos.app.complimentbot.MAX_BORED_IDLE
import furhatos.event.Event
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.gestures.ARKitParams
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
        furhat.gesture(BoredBasic)
        Timer().schedule(delay = MAX_BORED_IDLE) {
            send(GotoSleeping())
        }}
    onEvent<GotoSleeping> {
        goto(SleepingIdle)
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
    }
}


val BoredBasic = defineGesture {
    frame(1.0, persist = true) {
//        ARKitParams.MOUTH_FUNNEL to 0.054
//        ARKitParams.MOUTH_LEFT to 0.14
//        ARKitParams.MOUTH_ROLL_LOWER to 0.199
//        ARKitParams.MOUTH_SMILE_LEFT to -0.4
//        ARKitParams.MOUTH_SMILE_RIGHT to -0.201
//        ARKitParams.MOUTH_FROWN_LEFT to 0.271
//        ARKitParams.MOUTH_FROWN_RIGHT to 0.38
//        ARKitParams.MOUTH_DIMPLE_RIGHT to -0.09
//        ARKitParams.MOUTH_PRESS_LEFT to 0.184
//        ARKitParams.MOUTH_PRESS_RIGHT to 0.184
//        ARKitParams.MOUTH_STRETCH_LEFT to 0.073

        ARKitParams.BROW_DOWN_LEFT to 0.5
        ARKitParams.BROW_DOWN_RIGHT to 0.5
        ARKitParams.BROW_OUTER_UP_LEFT to 0.5
        ARKitParams.BROW_OUTER_UP_RIGHT to 0.5

        BasicParams.SMILE_CLOSED to 0
    }
}