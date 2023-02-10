package furhatos.app.complimentbot.setting

import furhatos.records.Location

/** Interaction space parameters */
const val maxNumberOfUsers = 4
val lookForward = Location(0.0, 0.0, 1.0)
val lookDown = Location(0.0, -10.0, 1.0)

/** Idle parameters */
const val MAX_ACTIVE_IDLE = 5 * 1000L
const val MAX_BORED_IDLE = 5 * 1000L