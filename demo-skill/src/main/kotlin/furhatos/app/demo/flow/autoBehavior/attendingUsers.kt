package furhatos.app.demo.flow.autoBehavior

import furhatos.app.demo.DEFAULT_LOCATION
import furhatos.app.demo.MICROMOVEMENTS_INTERVAL
import furhatos.app.demo.util.closest
import furhatos.app.demo.util.getRandomNearbyLocation
import furhatos.event.monitors.MonitorSpeechEnd
import furhatos.event.senses.SenseSpeechStart
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import java.util.*

fun attendingUsers(shouldToggleAttention: Boolean = true) : State = state(autoBehavior()) {
    onEntry {
        if (users.count > 0) {
            furhat.attend(users.closest)
        }
    }

    onTime(repeat = MICROMOVEMENTS_INTERVAL, instant = true) {
        // This will make sure Furhat does some random micromovements with his head while attending
        furhat.attend(users.current.head.location.getRandomNearbyLocation(0.03))
    }

    onEvent<MonitorSpeechEnd> {
        // 66% likelihood to, after Furhat has spoken, change attention to another
        // user if we have one, to get some variation.
        if (users.count > 1 && shouldToggleAttention && Random().nextInt(3) != 2) {
            furhat.attend(users.other)
        }
    }

    onEvent<SenseSpeechStart> {
        when {
            users.count > 0 -> furhat.attend(users.closest)
            else -> furhat.attend(DEFAULT_LOCATION)
        }
    }
}

