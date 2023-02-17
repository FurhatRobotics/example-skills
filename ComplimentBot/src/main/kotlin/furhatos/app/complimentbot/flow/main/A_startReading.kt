package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.gestures.rollHead
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.*
import furhatos.records.User

fun startReading(user: User): State = state(InteractionParent) {
    onEntry {
        activeGroup = nextGroup
        nextGroup = mutableListOf()

        greatActiveGroup(users.current)

        furhat.gesture(GesturesLib.PerformTripleBlink, priority = 10)
        delay(200)
        furhat.attendC(user)
        furhat.ledStrip.solid(java.awt.Color(0, 120, 0))

        if (!user.hasBeenGreeted) {
            greetUser(users.current)
            delay(1000)
        }

        // reading for user
        furhat.gesture(rollHead(2.0, 2.3))
        delay(1200)
        complimentUser(users.current)
        user.hasBeenComplimented = true

        goto(EndReading)
    }
}

fun FlowControlRunner.greatActiveGroup(leader: User) {
    if (!leader.hasBeenGreeted) {
        greetUser(leader)
    }
    val usersNotGreeted = activeGroup.filter { !it.hasBeenGreeted && it != leader }
    for (user in usersNotGreeted) {
        if (user.zone < Zone.ZONE2) {
            furhat.attendC(user)
            greetUser(isOtherGreet = true)
        }
    }
    if (leader.zone.isCloser(Zone.ZONE2)) {
        furhat.attendC(leader)
    }
}