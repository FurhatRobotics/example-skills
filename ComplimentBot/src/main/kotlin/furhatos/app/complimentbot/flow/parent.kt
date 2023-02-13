package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.flow.main.*
import furhatos.app.complimentbot.setting.delayWhenUsersAreGone
import furhatos.event.Event
import furhatos.flow.kotlin.*
import furhatos.records.User
import java.util.*
import kotlin.concurrent.schedule

class UserGoneForAWhileInstant(val user: User): Event()
class UserGoneForAWhile(val user: User): Event()

val UniversalParent = state {
    onEntry(inherit = true, priority = true) {
        logger.info("${thisState.name} entered")
        propagate()
    }
    onReentry (inherit = true, priority = true) {
        logger.info("${thisState.name} re-entered")
        propagate()
    }
    onExit (inherit = true, priority = true) {
//        logger.info("${thisState.name} exited")
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
        goto(startReading(it))
    }
}


val InteractionParent: State = state(UniversalParent) {

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

    onUserLeave(instant = true) {
        if (it == users.current) {
            goto(EndReading)
        }
    }

    onUserLeave(instant = true) {
        println(this.currentState.name)
        if (this.currentState == attentionState) {
            if (users.hasAny()) {
                when (it) {
                    users.current -> furhat.attend(users.other)
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
    }
}
