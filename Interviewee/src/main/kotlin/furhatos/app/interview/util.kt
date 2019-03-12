package furhatos.app.interview.flow

import furhatos.records.Location

fun getRelativeRandomLocation(location : Location, amplitude : Double) : Location {
    val locations = mutableListOf<Location>()
    for (x in 0..3) {
        for (y in 0..3) {
            val _x = x * amplitude
            val _y = y * amplitude / 3 // Smaller changes on Y-axis
            locations.add(location.add(Location(_x, _y, 0.0)))
            locations.add(location.subtract(Location(_x, -_y, 0.0)))
        }
    }
    return locations.shuffled().first()
}