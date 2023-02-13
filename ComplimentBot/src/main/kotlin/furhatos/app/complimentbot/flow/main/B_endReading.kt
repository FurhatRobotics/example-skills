package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.UniversalParent
import furhatos.app.complimentbot.gestures.FallAsleep
import furhatos.app.complimentbot.hasBeenComplimented
import furhatos.app.complimentbot.setting.lookDown
import furhatos.app.complimentbot.setting.lookForward
import furhatos.event.EventSystem
import furhatos.event.actions.ActionAttend
import furhatos.event.actions.ActionGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val EndReading = state(UniversalParent) {
    onEntry {
        furhat.attend(lookForward)
        delay(800)
        furhat.gesture(FallAsleep, priority = 10)
        delay(600)
        furhat.ledStrip.solid(java.awt.Color(0, 0, 120))
        EventSystem.send(ActionAttend.Builder().location(lookDown).speed(ActionGaze.Speed.XSLOW).buildEvent())
        delay(2500)
        val unServedUsers = users.list.filter { !it.hasBeenComplimented }
        if (unServedUsers.isNotEmpty()) {
            println("There are ${unServedUsers.size} unserved users")
            goto(startReading(unServedUsers.random()))
        } else {
            goto(ActiveIdle)
        }
    }
}