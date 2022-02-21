package furhatos.app.dog.dog

import furhatos.records.User
import kotlin.random.Random

var lastYawnTime = System.currentTimeMillis()
const val YAWN_INTERVAL_IN_MS = 5000

enum class CurrentState {
    AWAKE,
    ASLEEP,
    INSPECTING,
    INTERACTING,
    PLAYING,
}

const val GLOBAL_MAGNITUDE = 5
const val EXCITEMENT_LOWER_THRESHOLD = 20
const val HUNGER_THRESHOLD = 70
const val FALL_ASLEEP_TIREDNESS_THRESHOLD = 65
const val WAKEUP_TIREDNESS_THRESHOLD = 20

object Dog {
    var excitementLevel: Int = 70
    var tirednessLevel: Int = 20
    var hungerLevel: Int = 50

    var currentState: CurrentState = CurrentState.AWAKE

    var currentInteractingUserId: String? = null

    val knownUsers = mutableListOf<User>()

    fun increaseExcitement(magnitude: Int = GLOBAL_MAGNITUDE) {
        excitementLevel = getIncreaseLevel(excitementLevel, magnitude)
    }

    fun decreaseExcitement(magnitude: Int = GLOBAL_MAGNITUDE) {
        excitementLevel = getDecreaseLevel(excitementLevel, magnitude)
    }

    fun increaseHunger(magnitude: Int = GLOBAL_MAGNITUDE) {
        hungerLevel = getIncreaseLevel(hungerLevel, magnitude)
    }

    fun decreaseHunger(magnitude: Int = GLOBAL_MAGNITUDE) {
        hungerLevel = getDecreaseLevel(hungerLevel, magnitude)
    }

    fun increaseTiredness(magnitude: Int = GLOBAL_MAGNITUDE) {
        tirednessLevel = getIncreaseLevel(tirednessLevel, magnitude)
    }

    fun decreaseTiredness(magnitude: Int = GLOBAL_MAGNITUDE) {
        tirednessLevel = getDecreaseLevel(tirednessLevel, magnitude)
    }

    fun updateCurrentState() {
        println("tiredness: $tirednessLevel")
        if(tirednessLevel > FALL_ASLEEP_TIREDNESS_THRESHOLD) {
            currentState = CurrentState.ASLEEP
        }
    }
}

fun getIncreaseLevel(value: Int, magnitude: Int) : Int {
    return if(value + 1 < 100) {
        Random.nextInt(value, value + magnitude)
    } else {
        value
    }
}
fun getDecreaseLevel(value: Int, magnitude: Int) : Int {
    return if(value - 1 > 0) {
        Random.nextInt(value - magnitude, value)
    } else {
        value
    }
}