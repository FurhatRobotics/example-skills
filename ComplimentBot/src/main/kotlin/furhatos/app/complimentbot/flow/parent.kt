package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.flow.main.*
import furhatos.flow.kotlin.*


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

    onUserLeave(instant = true) {
        if (it == users.current) {
            goto(EndReading)
        }
        /*if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(EndReading)
        }*/
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

}