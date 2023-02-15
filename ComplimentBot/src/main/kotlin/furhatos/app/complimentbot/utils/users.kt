package furhatos.app.complimentbot.utils

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

var userGroups = mutableListOf<MutableList<User>>()

var User.zone by NullSafeUserDataDelegate { Zone.OUT }

var User.isSmiling by NullSafeUserDataDelegate { false }
var User.hasSmiled by NullSafeUserDataDelegate { false }

var User.isBeingEngaged by NullSafeUserDataDelegate { false }

var User.hasBeenGreeted by NullSafeUserDataDelegate { false }
var User.hasBeenComplimented by NullSafeUserDataDelegate { false }
var User.hasBeenGreetedGoodbye by NullSafeUserDataDelegate { false }

fun findGroup(user: User): Int {
    return userGroups.lastIndexOf(userGroups.lastOrNull { group -> group.contains(user) }?: mutableListOf() )
}
fun getGroup(user: User): MutableList<User> {
    return userGroups.lastOrNull { group -> group.contains(user) }?: mutableListOf(user)
}