package furhatos.app.complimentbot

import furhatos.app.complimentbot.utils.Persona
import furhatos.flow.kotlin.voice.AzureVoice
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.records.Location

/** Debug parameters */
const val doubleUserEventDelay: Long = 10

/** Interaction space parameters */
val origin = Location(0, 0, 0)
val topLeft = Location(3, 0, 3)
val straightAhead = Location(0, 0, 10)

val zone1Params = Pair(0.5, 1.5)
val zone2Params = Pair(1.0, 2.5)
val zone3Params = Pair(1.5, 3.5)

/** Furhat timing parameters */
const val enterBufferTime = 500
const val leaveBufferTime = 2500
const val faceDetectionThreshold = 0.9

// Supposed to happen 10 times per second
const val averageAttentionCapacity = 5 * 10 // 5 sec average for now
const val attentionThreshold: Double = 0.70 // Percentage of attention we need in the last $averageAttentionCapacity time

/** Idle parameters */
const val MAX_ACTIVE_IDLE = 10 * 1000
const val MAX_BORED_IDLE = 45 * 1000

const val delayWhenUsersAreGone: Long = 1500
const val maxTimeAttendingAUser = 15 // In seconds
const val delayToRecompliment = 20 // In seconds

/** Furhat character */
val mainPersona = Persona(
    name = "Alex",
    face = listOf("Alex"),
    voice = listOf(PollyNeuralVoice("Matthew"))
)