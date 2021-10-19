package furhatos.app.demo.util

import furhatos.gestures.Gesture
import furhatos.gestures.Gestures
import furhatos.records.Location
import furhatos.util.Language
import java.util.*

fun String.camelCaseToSpaces(): String {
    var text = ""
    var isFirst = true
    this.forEach {
        if (it.isUpperCase()) {
            if (isFirst) {
                isFirst = false
            }
            else {
                text += " "
            }
            text += it.toLowerCase()
        } else {
            text += it
        }
    }
    return text
}

fun getRandomString(list: List<String>) : String {
    return list[Random().nextInt(list.size)]
}

// The subset of gestures we want to showcase when asked to show gestures
val gesturesForShow = Gestures.getGestureNames().filter { listOf(
        Gestures.ExpressDisgust.name,
        Gestures.Thoughtful.name,
        Gestures.Wink.name,
        Gestures.ExpressFear
        ).contains(it)  }

fun Gestures.getRandom(amount : Int = 1, gestureNames: List<String> = gesturesForShow) : List<Gesture> {
    val _gestureNames = gestureNames.toMutableList()
    val randomGesture = getByName(_gestureNames.removeAt(Random().nextInt(_gestureNames.size)))!!
    return if (amount == 1) {
        listOf(randomGesture)
    }
    else {
        listOf(listOf(randomGesture), getRandom(amount - 1, _gestureNames)).flatten()
    }
}

// Remove the "(Country)" part of the language name
fun Language.getSpokenForm() : String {
    return this.name.substringBefore("(").trim()
}

fun Location.getRandomNearbyLocation(amplitude : Double) : Location {
    val locations = mutableListOf<Location>()
    for (x in 0..2) {
        for (y in 0..2) {
            val _x = x * amplitude * z // scale with z
            val _y = y * amplitude / 3 * z // scale with z, and smaller changes on Y-axis (1/3)
            locations.add(this.add(Location(_x, _y, 0.0)))
            locations.add(this.subtract(Location(_x, -_y, 0.0)))
        }
    }
    return locations.shuffled().first()
}