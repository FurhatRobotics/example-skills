package furhatos.app.attentiongrabber.flow

import furhatos.app.attentiongrabber.flow.main.EndReading
import furhatos.flow.kotlin.*

val Parent: State = state {

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