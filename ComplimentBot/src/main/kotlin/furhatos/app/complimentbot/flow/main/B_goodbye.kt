package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.origin
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.records.User

/**
 * Greets goodbye after complimenting users.
 * If the leader is null, Furhat will proceed to a general goodbye
 */
fun endReading(leader: User? = null) = state(InteractionParent) {
    onEntry {
        //Put the leader in first position
        val remainingUsers = listOfNotNull(leader)+activeGroup.filter{ it != leader }
        // Note that if the leader is explicitly null no goodbye is given (happens when compliment state is not exited properly)
        if (remainingUsers.isEmpty()) {
            generalGoodbye()
        } else if (leader != null) {
            endCompliments(remainingUsers)
            for (user in remainingUsers) {
                if (user.zone <= Zone.ZONE2) {
                    furhat.attendC(user)
                    greetUserGoodbye()
                }
            }
        }
        // Reset users' interaction parameters - we leave greeted as it was though
        activeGroup.forEach{
            it.hasSmiled = false
            it.hasReceivedGeneralResponse = false
        }

        delay(2000)

        if (nextGroup.isNotEmpty()) {
            furhat.attendC(nextGroup.minBy { it.head.location.distance(origin) }!!)
            goto(attentionState)
        } else {
            furhat.attendNobodyC()
            goto(ActiveIdle)
        }
    }
}