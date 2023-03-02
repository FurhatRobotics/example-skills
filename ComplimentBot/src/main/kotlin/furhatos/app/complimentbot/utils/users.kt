package furhatos.app.complimentbot.utils

import furhatos.app.complimentbot.attentionThreshold
import furhatos.app.complimentbot.averageAttentionCapacity
import furhatos.app.complimentbot.delayToRecompliment
import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.flow.kotlin.UserDataDelegate
import furhatos.records.User
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

var activeGroup = mutableListOf<User>()
var nextGroup = mutableListOf<User>()

var User.zone by NullSafeUserDataDelegate { Zone.OUT }
var User.isBeingEngaged by NullSafeUserDataDelegate { false }
var User.hasReceivedGeneralResponse by NullSafeUserDataDelegate { false }
var User.hasBeenGreeted by NullSafeUserDataDelegate { false }

var User.isSmiling by NullSafeUserDataDelegate { false }
var User.hasSmiled by NullSafeUserDataDelegate { false }

var User.lastComplimented by UserDataDelegate<LocalDateTime>()

var User.attentionAverage by NullSafeUserDataDelegate { RollingList<Boolean>(averageAttentionCapacity) }

fun isReadyToBeComplimented(user: User): Boolean {
    return user.lastComplimented == null ||
            ChronoUnit.SECONDS.between(user.lastComplimented, LocalDateTime.now()) > delayToRecompliment
}
fun isAttendingFurhatAvg(user: User): Boolean {
    return if (user.attentionAverage.isNotEmpty) {
        getAttentionConfidence(user) > attentionThreshold
    } else {
        user.isAttendingFurhat
    }
}

fun getAttentionConfidence(user: User): Double {
    return user.attentionAverage.list.count { it }.toDouble() / user.attentionAverage.list.size
}

fun handleUserGroupEntry(user: User) {
    if (user.zone <= Zone.ZONE2 && !(activeGroup+nextGroup).contains(user) ) {
        nextGroup.add(user)
    }
}