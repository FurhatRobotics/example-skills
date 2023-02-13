package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.gestures.TripleBlink
import furhatos.app.complimentbot.gestures.rollHead
import furhatos.app.complimentbot.hasSmiled
import furhatos.app.complimentbot.hasBeenComplimented
import furhatos.app.complimentbot.setting.lookForward
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.records.User

fun startReading(user: User): State = state(InteractionParent) {
    onEntry {
        furhat.attend(lookForward)
        furhat.gesture(TripleBlink, priority = 10)
        delay(200)
        user.hasBeenComplimented = true
        furhat.attend(user)
        furhat.ledStrip.solid(java.awt.Color(0, 120, 0))

        greetUser(users.current)

        // reading for user
        furhat.gesture(rollHead(-20.0, 2.3))
        delay(1200)
        complimentUser(users.current)

        greetUserGoodbye(users.current)
        goto(EndReading)
    }
    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}