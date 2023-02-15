package furhatos.app.complimentbot

import furhatos.records.Ellipse
import furhatos.records.Location

/** Interaction space parameters */
const val maxNumberOfUsers = 4
val lookForward = Location(0.0, 0.0, 1.0)
val origin = Location(0, 0, 0)

val zone1Params = Pair(0.2, 1.2)
val zone2Params = Pair(1.2, 1.7)
val zone3Params = Pair(1.9, 3.2)

/** Interaction timing parameters */
const val enterBufferTime = 0
const val leaveBufferTime = 2500
val timingParameters = mapOf("enterBufferTime" to enterBufferTime, "leaveBufferTime" to leaveBufferTime)

/** Idle parameters */
const val MAX_ACTIVE_IDLE: Long = 5 * 1000
const val MAX_BORED_IDLE: Long = 5 * 1000

const val delayWhenUsersAreGone: Long = 1500