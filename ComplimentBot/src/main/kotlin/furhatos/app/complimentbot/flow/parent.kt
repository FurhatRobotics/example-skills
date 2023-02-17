package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.delayWhenUsersAreGone
import furhatos.app.complimentbot.flow.main.*
import furhatos.app.complimentbot.utils.*
import furhatos.event.Event
import furhatos.flow.kotlin.*
import furhatos.records.User
import java.util.*
import kotlin.concurrent.schedule

class UserGoneForAWhileInstant(val user: User): Event()
class UserGoneForAWhile(val user: User): Event()

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

    onEvent<GotoBored> {
        goto(BoredIdle)
    }
    onEvent<GotoSleeping> {
        goto(SleepingIdle)
    }

    onUserEnter {
        furhat.attendC(it)
        // Group attribution
        nextGroup.add(it)
        goto(attentionState)
    }
}


val InteractionParent: State = state(UniversalParent) {

    onUserLeave(instant = true) {
        println("Left state ${this.currentState.name}") // TODO Does not work...
        if (!it.isBeingEngaged && it.zone == Zone.ZONE3) {
            return@onUserLeave
        }
        if (this.currentState == attentionState) {
            if (users.hasAny()) {
                when (it) {
                    users.current -> furhat.attendC(users.other)
                }
            } else {
                goto(ActiveIdle)
            }
        } else {
            Timer().schedule(delay = delayWhenUsersAreGone) {
                send(UserGoneForAWhileInstant(it))
            }
        }
    }
    onEvent<UserGoneForAWhileInstant>(instant = true) {
        if (!users.list.contains(it.user)) {
            raise(UserGoneForAWhile(it.user))
        }
    }
    onEvent<UserGoneForAWhile> {
        println("User ${it.user.id} has left for $delayWhenUsersAreGone milliseconds.")
        if (it.user == users.current) {
            goto(EndReading)
        }
    }
}