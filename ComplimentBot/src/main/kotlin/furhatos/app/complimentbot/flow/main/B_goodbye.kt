package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.UniversalParent
import furhatos.app.complimentbot.origin
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.records.User

/**
 * Greets goodbye after complimenting users.
 * If the leader is null, Furhat will proceed to a general goodbye
 */
fun EndReading(leader: User? = null) = state(UniversalParent) {
    onEntry {
        //Put the leader in first position
        val remainingUsers = (listOfNotNull(leader)+activeGroup.filter{ it != leader }).filter { it.zone.isCloser(Zone.ZONE3) }
        if (leader == null || remainingUsers.isEmpty()) {
            generalGoodbye()
        } else {
            endCompliments(remainingUsers)
            for (user in remainingUsers) {
                furhat.attendC(user)
                greetUserGoodbye(user) // TODO : do we want a "other greet" ?
            }
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