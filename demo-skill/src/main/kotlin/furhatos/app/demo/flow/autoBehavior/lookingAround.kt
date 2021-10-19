package furhatos.app.demo.flow.autoBehavior

import furhatos.app.demo.LOOKAROUND_INTERVAL
import furhatos.app.demo.util.getRandomNearbyLocation
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.records.Location

fun lookingAround(startingLocation: Location = Location(0.0, 0.0, 1.5)) : State = state {
    onEntry {
        furhat.attend(startingLocation)
    }

    onTime(repeat = LOOKAROUND_INTERVAL, instant = true) {
        val userAttendActions = users.list.map {
            { furhat.attend(it) }
        }.toTypedArray()

        random(
            *userAttendActions,
            { furhat.attend(startingLocation.getRandomNearbyLocation(0.1)) }
        )
    }
}