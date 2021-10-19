package furhatos.app.demo.flow.autoBehavior

import furhatos.app.demo.MICROMOVEMENTS_INTERVAL
import furhatos.app.demo.util.getRandomNearbyLocation
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.records.Location
import furhatos.records.User

fun attendingLocation(location: Location = User.NOBODY.head.location, randomMovements : Boolean = true) : State = state(autoBehavior()) {
    onEntry {
        furhat.attend(location)
    }

    onTime(repeat = MICROMOVEMENTS_INTERVAL, instant = true) {
        if (randomMovements) {
            furhat.attend(location.getRandomNearbyLocation(0.1))
        }
    }
}