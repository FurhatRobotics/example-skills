package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.gestures.rollHead
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.*
import furhatos.records.User

fun startReading(user: User): State = state(InteractionParent) {
    onEntry {

        greetAndComplimentGroup(users.current)

        furhat.gesture(GesturesLib.PerformTripleBlink, priority = 10)
        delay(200)
        furhat.attendC(user)
        furhat.ledStrip.solid(java.awt.Color(0, 120, 0))

        if (!user.hasBeenGreeted) {
            greetUser(users.current)
            delay(1000)
        }

        // reading for user
        furhat.gesture(rollHead(-20.0, 2.3)) //TODO : way to fast
        delay(1200)
        complimentUser(users.current)
        user.hasBeenComplimented = true

        goto(EndReading)
    }

    onUserEnterC { user, zone ->
        val activeGroupIndex = findGroup(users.current)
        if (zone.ordinal <= Zone.ZONE2.ordinal) {
            if (activeGroupIndex == userGroups.lastIndex) {
                userGroups.add(mutableListOf(user))
            } else {
                userGroups[userGroups.size-1].add(user)
            }
        }
    }
}

fun FlowControlRunner.greetAndComplimentGroup(mainUser: User) {
    val users = getGroup(mainUser)
    if (!mainUser.hasBeenGreeted) {
        greetUser(mainUser)
    }
    val usersNotGreeted = users.filter { !it.hasBeenGreeted && it != mainUser }
    for (user in usersNotGreeted) {
        greetUser(isOtherGreet = true)
    }
}