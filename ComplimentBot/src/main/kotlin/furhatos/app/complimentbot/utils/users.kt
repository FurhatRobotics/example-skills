package furhatos.app.complimentbot.utils

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

var groups = mutableListOf<MutableList<User>>()

var User.zone by NullSafeUserDataDelegate { Zone.OUT }

var User.isSmiling by NullSafeUserDataDelegate { false }
var User.hasSmiled by NullSafeUserDataDelegate { false }

var User.isBeingEngaged by NullSafeUserDataDelegate { false }

var User.hasBeenGreeted by NullSafeUserDataDelegate { false }
var User.hasBeenComplimented by NullSafeUserDataDelegate { false }
var User.hasBeenGreetedGoodbye by NullSafeUserDataDelegate { false }

fun findGroup(user: User): Int {
    return groups.filter { group -> group.contains(user) }.lastIndex
}