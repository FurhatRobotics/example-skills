package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.gestures.TripleBlink
import furhatos.app.complimentbot.gestures.rollHead
import furhatos.app.complimentbot.utils.hasBeenComplimented
import furhatos.app.complimentbot.lookForward
import furhatos.app.complimentbot.utils.attendC
import furhatos.app.complimentbot.utils.hasBeenGreeted
import furhatos.flow.kotlin.*
import furhatos.records.User

fun startReading(user: User): State = state(InteractionParent) {
    onEntry {
        furhat.attendC(lookForward)
        furhat.gesture(TripleBlink, priority = 10)
        delay(200)
        user.hasBeenComplimented = true
        furhat.attendC(user)
        furhat.ledStrip.solid(java.awt.Color(0, 120, 0))

        if (!user.hasBeenGreeted) {
            greetUser(users.current)
            delay(1000)
        }

        // reading for user
        furhat.gesture(rollHead(-20.0, 2.3))
        delay(1200)
        complimentUser(users.current)

        goto(EndReading)
    }
}