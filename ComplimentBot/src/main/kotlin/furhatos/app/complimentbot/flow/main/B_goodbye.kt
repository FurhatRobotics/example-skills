package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.*
import furhatos.records.User

var firstResponse = true

/**
 * Greets goodbye after complimenting users.
 * If the leader is null, Furhat will proceed to a general goodbye
 */
fun endReading(leader: User? = null) = state(InteractionParent) {
    onEntry {
//        //Put the leader in first position
//        val remainingUsers = (listOfNotNull(leader)+activeGroup.filter{ it != leader }).filter { it.zone.isCloser(Zone.ZONE3) }
//        if (leader == null || remainingUsers.isEmpty()) {
//            generalGoodbye()
//        } else {
//            endCompliments(remainingUsers)
//            for (user in remainingUsers) {
//                furhat.attendC(user)
//                greetUserGoodbye()
//            }
//        }
//
//        delay(2000)
//
//        if (nextGroup.isNotEmpty()) {
//            furhat.attendC(nextGroup.minBy { it.head.location.distance(origin) }!!)
//            goto(attentionState)
//        } else {
//            furhat.attendNobodyC()
//            goto(ActiveIdle)
//        }

        firstResponse = true
        furhat.ask("But pardon me ! I'm a furhat social robot and I can introduce myself properly later if that's all right with you.")
    }

    onResponse {
        handleAnyResponse()
    }
    onNoResponse {
        handleAnyResponse()
    }
}

fun FlowControlRunner.handleAnyResponse() {
    if (firstResponse) {
        firstResponse = false
        furhat.ask("I’ll take a power nap now, will you wake me up when it’s time for me to do my little presentation of myself and my kind ?")
    } else {
        delay(1000)
        furhat.attendNobodyC()
        goto(SleepingIdle(false))
    }
}