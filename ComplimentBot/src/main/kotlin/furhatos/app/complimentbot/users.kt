package furhatos.app.complimentbot

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

var User.isSmiling by NullSafeUserDataDelegate { false }
var User.hasSmiled by NullSafeUserDataDelegate { false }

var User.hasBeenGreeted by NullSafeUserDataDelegate { false }
var User.hasBeenComplimented by NullSafeUserDataDelegate { false }
var User.hasBeenGreetedGoodbye by NullSafeUserDataDelegate { false }