package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.BehaviorLib
import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.flow.IdleParent
import furhatos.app.complimentbot.MAX_ACTIVE_IDLE
import furhatos.app.complimentbot.MAX_BORED_IDLE
import furhatos.app.complimentbot.gestures.BoredBasic
import furhatos.app.complimentbot.gestures.BoredBlink
import furhatos.app.complimentbot.gestures.BoredLookAround
import furhatos.app.complimentbot.gestures.FallAsleepPersist
import furhatos.app.complimentbot.topLeft
import furhatos.app.complimentbot.utils.attendCSlow
import furhatos.app.complimentbot.utils.attendNobodyC
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.event.Event
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import java.util.*
import kotlin.concurrent.schedule

class GotoBored: Event()
class GotoSleeping: Event()

val ActiveIdle: State = state(IdleParent) {
    lateinit var timer: Timer

    include(BehaviorLib.AutomaticMovements.randomHeadMovements(repetitionPeriod = 2500..5000))

    onEntry {
        timer = Timer()
        timer.schedule(delay = MAX_ACTIVE_IDLE) {
            send(GotoBored())
        }}
    onEvent<GotoBored> {
        goto(BoredIdle)
    }

    onExit {
        timer.cancel() // If we come back to the state afterwards we don't want the hanging event
    }
}

val BoredIdle: State = state(IdleParent) {
    lateinit var timer: Timer

    include(BehaviorLib.AutomaticMovements.randomHeadMovements(repetitionPeriod = 5000..9000))

    // Custom blink that defaults back to the bored eyes openness
    onTime(100,2000..8000) {
        furhat.gesture(BoredBlink)
    }
    onTime(MAX_BORED_IDLE.toInt() / 3) {
        furhat.gesture(BoredLookAround, priority = 10)
    }
    onTime(MAX_BORED_IDLE.toInt() * 2 / 3) {
        furhat.attendCSlow(topLeft)
        delay(4000)
        furhat.attendNobodyC()
    }
    onEntry {
        timer = Timer()
        furhat.setDefaultMicroexpression(blinking = false) // Replaced by the custom blink
        furhat.gesture(BoredBasic)

        timer.schedule(delay = MAX_BORED_IDLE) {
            send(GotoSleeping())
        }}
    onEvent<GotoSleeping> {
        goto(SleepingIdle)
    }

    onExit {
        println("On exit bored idle")
        furhat.setDefaultMicroexpression(blinking = true)
        println("On exit bored idle with micro-expressions")
        timer.cancel() // If we come back to the state afterwards we don't want the hanging event
        println("On exit bored idle with timer")
        furhat.stopGestures()
        println("Stop gestures called")
    }
}

val SleepingIdle: State = state(IdleParent) {

    onEntry {
        delay(1000)
        furhat.gesture(FallAsleepPersist, priority = 10)
    }

    onExit {
        furhat.gesture(GesturesLib.PerformWakeUpWithHeadShake, priority = 10)
         // TODO : find a way to have a delay included here somewhere
    }
}