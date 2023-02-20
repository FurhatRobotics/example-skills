package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.UniversalParent
import furhatos.app.complimentbot.origin
import furhatos.app.complimentbot.utils.activeGroup
import furhatos.app.complimentbot.utils.attendNobodyC
import furhatos.app.complimentbot.utils.nextGroup
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.records.User

fun EndReading(leader: User? = null) = state(UniversalParent) {
    onEntry {

        if (leader == null) {
            generalGoodbye()
        } else {
            for (user in activeGroup.sortedBy { it == leader }) {
                greetUserGoodbye(users.current) // TODO : do we want a "other greet" ?
            }
        }

        delay(1500)

        if (nextGroup.isNotEmpty()) {
            complimentNextGroup(nextGroup.minBy { it.head.location.distance(origin) }!!)
        } else {
            furhat.attendNobodyC()
            goto(ActiveIdle)
        }
    }
}