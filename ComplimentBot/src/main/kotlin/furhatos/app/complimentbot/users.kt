package furhatos.app.complimentbot

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

var User.served by NullSafeUserDataDelegate(false) { false }