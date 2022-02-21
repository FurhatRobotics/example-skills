package furhatos.app.dog.gestures

import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture

// Randomizes if Furhat is looking left/right and up/down
fun getRandomDirection(): Double {
    return if (Math.random() < 0.5)
        -1.0 // negative
    else
        +1.0 // positive
}

// Adds variation to the amplitude of the random head movements and sets the range
fun getScaleParameter(): Double {
    return getRandomDirection() * (Math.random() + 0.55)
}

fun idleHeadMovements(strength: Double = 1.0, duration: Double = 1.0, amplitude: Double = 5.0, gazeAway: Boolean = false) =
        defineGesture("headMove", strength = strength, duration = duration) {
            frame(1.5, 8.5) {
                // This regulates how long the position will be held and how fast Furhat gets there. Multiplied with duration.
                BasicParams.NECK_TILT to amplitude * getScaleParameter()
                BasicParams.NECK_ROLL to amplitude * getScaleParameter()
                BasicParams.NECK_PAN to amplitude * getScaleParameter()
                // the direction (tilt/roll/pan) of the position shift is completely random. Does not affect attention
            }
            reset(10.0)
        }
