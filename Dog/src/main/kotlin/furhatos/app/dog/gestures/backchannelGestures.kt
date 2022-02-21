package gestures

import furhatos.app.dog.utils.getRandomInRange
import furhatos.gestures.BasicParams
import furhatos.gestures.defineGesture
import furhatos.gestures.Gesture


// making it more likely to be a small smile
val smileClosedValue = if (Math.random() < 0.6) {
    0.3 + Math.random() * 0.3
} else {
    0.3 + Math.random() * 0.5
}

// A random smile in between regular smile and bigsmile but with less open mouth, added variance to the values
fun backchannelSmile(strength: Double = 1.0, duration: Double = 1.0) =
    defineGesture("backchannelSmile", strength, duration) {
        frame(0.32, 0.88) {
            BasicParams.SMILE_OPEN to getRandomInRange(0.02, 0.2)
            BasicParams.SMILE_CLOSED to smileClosedValue
        }
        frame(0.25, 0.84) {
            BasicParams.BROW_UP_LEFT to getRandomInRange(0.5, 0.4)
            BasicParams.BROW_UP_RIGHT to getRandomInRange(0.5, 0.4)
        }
        frame(0.16, 0.86) {
            BasicParams.BLINK_LEFT to getRandomInRange(0.05, 0.05)
            BasicParams.BLINK_RIGHT to getRandomInRange(0.05, 0.05)
        }
        reset(getRandomInRange(1.08, 0.2))
    }

val normalNod = defineGesture {
    frame(0.9, 0.4) {
        BasicParams.NECK_TILT to 10
    }
    frame(2.5, 0.4) {
        BasicParams.NECK_TILT to -4
    }

    frame(3.1, 0.5) {
        BasicParams.NECK_TILT to 6
    }

    frame(3.3, 0.5) {
        BasicParams.NECK_TILT to -0
    }

    reset(3.9)
}

fun doubleNod(duration: Double = 1.0) =
    defineGesture("doubleNod", duration) {
        frame(0.4) {
            BasicParams.NECK_TILT to 10 // this means looking 10 degrees down, but here it is never reached, instead this affects the speed
        }
        frame(0.8) {
            BasicParams.NECK_TILT to -10
        }
        frame(1.2) {
            BasicParams.NECK_TILT to 10
        }
        frame(1.6) {
            BasicParams.NECK_TILT to -10
        }
        reset(2.0)
    }

fun smallNod(duration: Double = 1.0, iterations: Int = 1) =
    defineGesture("SmallNod", duration) {
        for (i in 0..(iterations - 1)) {
            frame(0.4 + 1.2 * i) {
                BasicParams.NECK_TILT to 10
            }
            frame(0.9 + 1.2 * i) {
                BasicParams.NECK_TILT to -10
            }
        }
        reset(1.3 * iterations)
    }

// less intense surprise with variation
fun slightlySurprised(strength: Double = 1.0, duration: Double = 1.0): Gesture {
    val howsurprisedvalue = getRandomInRange(0.30, 0.2)
    return defineGesture("slightlySurprised", strength, duration) {
        frame(0.32) {
            listOf(BasicParams.SURPRISE, BasicParams.BROW_UP_LEFT, BasicParams.BROW_UP_RIGHT) to howsurprisedvalue
        }
        frame(0.64) {
            listOf(BasicParams.BROW_UP_RIGHT, BasicParams.BROW_UP_LEFT) to howsurprisedvalue
            BasicParams.SURPRISE to 0.0
        }
        reset(0.96)
    }
}

// less intense thoughtful with variation
fun slightlyThoughtful(strength: Double = 1.0, duration: Double = 1.0) =
    defineGesture("slightlyThoughtful", strength, duration) {
        frame(0.28, 0.76) {
            BasicParams.BROW_IN_LEFT to getRandomInRange(0.70, 0.2)
            BasicParams.BROW_IN_RIGHT to getRandomInRange(0.70, 0.2)
        }
        frame(0.36, 0.76) {
            BasicParams.PHONE_W to getRandomInRange(0.40, 0.15)
            BasicParams.PHONE_B_M_P to getRandomInRange(0.40, 0.15)
            BasicParams.BLINK_LEFT to getRandomInRange(0.05, 0.05)
            BasicParams.BLINK_RIGHT to getRandomInRange(0.05, 0.05)
        }
        frame(0.32, 1.08) {
            listOf(BasicParams.BROW_DOWN_LEFT, BasicParams.BROW_DOWN_RIGHT) to getRandomInRange(0.7, 0.2)
        }
        reset(1.30)
    }
