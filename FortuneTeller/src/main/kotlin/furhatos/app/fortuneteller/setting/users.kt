package furhatos.app.fortuneteller.flow

import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

var User.served by NullSafeUserDataDelegate { false }