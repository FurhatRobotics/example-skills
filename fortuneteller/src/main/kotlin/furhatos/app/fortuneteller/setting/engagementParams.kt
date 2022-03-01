package furhatos.app.fortuneteller.setting

import furhatos.records.Location

val maxNumberOfUsers = 2
val distanceToEngage = 1.0 // not used, we use a more complex shape of the interaction space

/** Locations **/
val lookForward = Location(0.0, 0.0, 1.0)
val lookDown = Location(0.0, -10.0, 1.0)
