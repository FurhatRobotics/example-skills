package furhatos.app.presentation

import furhatos.event.EventSystem
import furhatos.event.actions.ActionSetSolidLED
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.Furhat
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures
import furhatos.records.Location
import furhatos.records.User
import furhatos.skills.UserManager

/*
    The FlowControlRunner has access to both users (a UserManager instance) and furhat (A Furhat instance).
    We need both of these for this extention method, hence we extend the FlowControlRunner class.
 */
fun FlowControlRunner.attendRandomUserOrLocation() {
    if (users.count > 0) {
        //If there are users, furhat does one of the following things:
        //attend random user, attend all users, or shows a sick roll.
        random(
                { furhat.attend(users.random) },
                { furhat.attendAll()},
                { furhat.gesture(Gestures.Roll(0.2))}
        )
    }
    else {
        //If there are no users, Furhat will look in a random direction.
        furhat.attendRandomLocation()
    }
}

/*
    Here we don't need access to users so we can add the extention method to the Furhat class instead.
 */
fun Furhat.attendRandomLocation() {
    runner.random(
            { attend(Location(0.3,0.0,1.0))  },
            { attend(Location(-0.3,0.0,1.0))  },
            { attend(Location(0.1,0.1,1.0))  },
            { attend(Location(-0.1,-0.1,1.0))  },
            { attend(Location(0.0,0.0,1.0))  }
    )
}

val Furhat.ledColors : List<String>
    get() = mutableListOf(Cols.values().map { it.name.toLowerCase() }).flatten()

// 127 is a hardware capped maximum for the LED halo
fun Furhat.setLED(color: String, intensity: Int = 127) {
    val builder = ActionSetSolidLED.Builder()

    val event = when (color) {
        "red" -> builder.red(intensity)
        "green" -> builder.green(intensity)
        "blue" -> builder.blue(intensity)
        "yellow" -> builder.red(intensity).green(intensity)
        "orange" -> builder.red(intensity).green(intensity/2)
        "purple" -> builder.red(intensity).blue(intensity)
        "white" -> builder.red(intensity).green(intensity).blue(intensity)
        else -> builder
    }
    EventSystem.send(event.buildEvent())
}

// Detecting the closest user, that we assume is the confederate
val UserManager.closest : User
    get() = this.list.sortedBy { it.head.location.distance(Location(0,0,0)) }.first()

enum class Cols {
    RED, GREEN, BLUE, YELLOW, ORANGE, PURPLE, WHITE
}