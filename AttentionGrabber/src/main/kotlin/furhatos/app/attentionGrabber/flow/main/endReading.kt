package furhatos.app.attentiongrabber.flow.main

import furhatos.app.attentiongrabber.gestures.FallAsleep
import furhatos.app.attentiongrabber.served
import furhatos.app.attentiongrabber.setting.lookDown
import furhatos.app.attentiongrabber.setting.lookForward
import furhatos.event.EventSystem
import furhatos.event.actions.ActionAttend
import furhatos.event.actions.ActionGaze
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val EndReading = state {
    onEntry {
        furhat.attend(lookForward)
        delay(800)
        furhat.gesture(FallAsleep, priority = 10)
        delay(600)
        furhat.ledStrip.solid(java.awt.Color(0, 0, 120))
        EventSystem.send(ActionAttend.Builder().location(lookDown).speed(ActionGaze.Speed.XSLOW).buildEvent())
        delay(2500)
        val unServedUsers = users.list.filter { !it.served }
        if (!unServedUsers.isEmpty()) {
            println("There are unserved users" + unServedUsers.size)
            goto(startReading(unServedUsers.random()))
        } else {
            goto(Idle)
        }
    }
}