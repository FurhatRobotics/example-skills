package furhatos.app.demo.util

import furhatos.event.EventSystem
import furhatos.event.actions.ActionSetSolidLED
import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Furhat
import furhatos.records.Location
import furhatos.records.User
import furhatos.skills.UserManager

// Really any RGB combination could be used, but this is used as a starting point
val Furhat.ledColors : List<String>
    get() = mutableListOf(Color.values().map { it.name.toLowerCase() }).flatten().plus("white")

// 127 is a hardware capped maximum for the LED halo
fun Furhat.setLED(color: String, intensity: Int = 127) {
    val builder = ActionSetSolidLED.Builder()

    val event = when (color) {
        "red" -> builder.red(intensity)
        "green" -> builder.green(intensity)
        "blue" -> builder.blue(intensity)
        "yellow" -> builder.red(intensity).green(intensity/2)
        "white" -> builder.red(intensity).green(intensity).blue(intensity)
        else -> builder
    }
    EventSystem.send(event.buildEvent())
}

// Detecting the closest user, that we assume is the confederate
val UserManager.closest : User
    get() = this.list.sortedBy { it.head.location.distance(Location(0,0,0)) }.first()