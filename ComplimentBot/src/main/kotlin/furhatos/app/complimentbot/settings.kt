package furhatos.app.complimentbot

import furhatos.records.Location

/** Interaction space parameters */
val lookForward = Location(0.0, 0.0, 1.0)
val origin = Location(0, 0, 0)

val zone1Params = Pair(0.2, 1.2)
val zone2Params = Pair(1.2, 1.7)
val zone3Params = Pair(1.9, 3.2)

/** Interaction timing parameters */
const val enterBufferTime = 500
const val leaveBufferTime = 2500

// Supposed to happen 10 times per second
const val averageAttentionCapacity = 5 * 10 // 5 sec average for now
const val attentionThreshold: Double = 0.70 // Percentage of attention we need in the last $averageAttentionCapacity time

/** Idle parameters */
const val MAX_ACTIVE_IDLE: Long = 5 * 1000
const val MAX_BORED_IDLE: Long = 5 * 1000

const val delayWhenUsersAreGone: Long = 1500