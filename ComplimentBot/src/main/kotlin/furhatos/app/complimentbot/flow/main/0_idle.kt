package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.BehaviorLib
import furhatos.app.complimentbot.flow.IdleParent
import furhatos.app.complimentbot.MAX_ACTIVE_IDLE
import furhatos.app.complimentbot.MAX_BORED_IDLE
import furhatos.app.complimentbot.flow.UniversalParent
import furhatos.app.complimentbot.gestures.*
import furhatos.app.complimentbot.straightAhead
import furhatos.app.complimentbot.topLeft
import furhatos.app.complimentbot.utils.*
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.event.Event
import furhatos.flow.kotlin.*

class GotoAttentionIfPresent: Event()

val ActiveIdle: State = state(IdleParent) {
    include(BehaviorLib.AutomaticMovements.randomHeadMovements(repetitionPeriod = 2500..5000))

    onTime(MAX_ACTIVE_IDLE) {
        goto(BoredIdle)
    }
}

val BoredIdle: State = state(IdleParent) {
    include(BehaviorLib.AutomaticMovements.randomHeadMovements(repetitionPeriod = 5000..9000))

    onEntry {
        furhat.setDefaultMicroexpression(blinking = false) // Replaced by the custom blink
        furhat.gesture(BoredBasic)
    }

    // Custom blink that defaults back to the bored eyes openness
    onTime(100,2000..8000) {
        furhat.gesture(BoredBlink, async = true)
    }

    onTime(MAX_BORED_IDLE / 3) {
        furhat.gesture(BoredLookAround, priority = 10)
    }
    onTime(MAX_BORED_IDLE * 2 / 3) {
        furhat.attendCSlow(topLeft)
    }
    onTime((MAX_BORED_IDLE * 2 / 3) + 4000) {
        furhat.attendCSlow(straightAhead)
    }
    onTime(MAX_BORED_IDLE) {
        goto(SleepingIdle)
    }

    onExit(priority = true) {
        furhat.setDefaultMicroexpression(blinking = true)
        furhat.stopGestures(reset = false)
        furhat.gesture(resetBoredFace, priority = 10)
    }
}

val SleepingIdle: State = state(UniversalParent) {

    var isWakingUp = false

    onEntry {
        furhat.setDefaultMicroexpression(blinking = false)
        delay(1000)
        furhat.gesture(fallAsleep, priority = 10)
    }

    onTime(8000, 22000..25000, cond = { !isWakingUp }) {
        furhat.gesture(sleep1, priority = 10)
    }

    onUserEnter(instant = true) {
        if (!isWakingUp) {
            isWakingUp = true
            furhat.setDefaultMicroexpression(blinking = true)
            furhat.stopGestures(reset = false)
            furhat.gesture(wakeUp, priority = 10)
            furhat.attendC(it)
            raise(GotoAttentionIfPresent())
        } else {
            addNewUserToQueue(it)
        }
    }
    onEvent<GotoAttentionIfPresent> {
        delay(4000)
        try {
            if (!users.current.isEngaged) {
                furhat.attendC(users.list.first())
            }
            goto(attentionState)
        } catch (_: NoSuchElementException) {
            furhat.attendNobodyC()
            goto(ActiveIdle)
        }
    }
}