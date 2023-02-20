package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.flow.main.attentionState
import furhatos.app.complimentbot.utils.attendC
import furhatos.app.complimentbot.utils.handleUserGroupEntry
import furhatos.app.complimentbot.utils.zone
import furhatos.event.Event
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.onUserLeave
import furhatos.flow.kotlin.state
import furhatos.records.User

class LeaderGoneForAWhileInstant(val user: User): Event()
class LeaderGoneForAWhile(val user: User): Event()

val UniversalParent = state {
    onEntry(inherit = true, priority = true) {
        logger.debug("${thisState.name} entered")
        propagate()
    }
    onReentry (inherit = true, priority = true) {
        logger.debug("${thisState.name} re-entered")
        propagate()
    }
    onExit (inherit = true, priority = true) {
        logger.debug("${thisState.name} exited")
        propagate()
    }

    onUserEnter(instant = true, priority = true) {
        logger.info("${it.id} entered ${it.zone.name}")
        handleUserGroupEntry(it)
        propagate()
    }
    onUserLeave(instant = true, priority = true) {
        logger.info("${it.id} left towards ${it.zone.name}")
        propagate()
    }
}

val IdleParent = state(UniversalParent) {
    onUserEnter {
        furhat.attendC(it)
        goto(attentionState)
    }
}