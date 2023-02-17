package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.UniversalParent
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val EndReading = state(UniversalParent) {
    onEntry {

        greetUserGoodbye(users.current)

        val unServedGroupUsers = users.list.filter { !it.hasBeenComplimented && activeGroup.contains(it) && (it.zone == Zone.ZONE1 || it.zone == Zone.ZONE2) }
        if (unServedGroupUsers.isNotEmpty()) {
            println("There are ${unServedGroupUsers.size} unserved users")
            goto(startReading(unServedGroupUsers.random()))
        } else {
            furhat.attendNobodyC()
            goto(ActiveIdle)
        }
    }
}