package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.delayWhenUsersAreGone
import furhatos.app.complimentbot.flow.main.*
import furhatos.app.complimentbot.utils.Zone
import furhatos.app.complimentbot.utils.onUserEnterC
import furhatos.app.complimentbot.utils.onUserLeaveC
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
        logger.info("${thisState.name} exited")
        propagate()
    }

    onUserEnterC(instant = true, priority = true) { user: User, zone: Zone ->
        logger.info("${user.id} entered ${zone.name}")
        propagate()
    }
    onUserLeaveC(instant = true, priority = true) { user: User, zone: Zone ->
        logger.info("${user.id} left towards ${zone.name}")
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

    onUserEnterC { user: User, zone: Zone ->
        if (zone == Zone.ZONE1) {
            goto(startReading(user))
        }
    }
}


val InteractionParent: State = state(UniversalParent) {

    onUserEnterC(instant = true) { user: User, zone: Zone ->
        furhat.glance(user)
    }

    onUserLeaveC(instant = true) { user: User, zone: Zone ->
        println("Left state ${this.currentState.name}")

        if (this.currentState == attentionState) {
            if (users.hasAny()) {
                when (user) {
                    users.current -> furhat.attend(users.other)
                }
            } else {
                goto(ActiveIdle)
            }
        } else {
            Timer().schedule(delay = delayWhenUsersAreGone) {
                send(UserGoneForAWhileInstant(user))
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