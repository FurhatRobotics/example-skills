package furhatos.app.complimentbot

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

var User.isSmiling by NullSafeUserDataDelegate { false }
var User.hasSmiled by NullSafeUserDataDelegate { false }
var User.served by NullSafeUserDataDelegate(false) { false }