package furhatos.app.presentation

import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.Furhat
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users
import furhatos.records.Location

fun FlowControlRunner.attendRandomUserOrLocation() {
    if (users.count > 0) {
        random(
                { furhat.attend(users.random) },
                { furhat.attendRandomLocation() }
        )
    }
    else {
        furhat.attendRandomLocation()
    }
}

fun Furhat.attendRandomLocation() {
    runner.random(
            { attend(Location(0.3,0.0,1.0))  },
            { attend(Location(-0.3,0.0,1.0))  },
            { attend(Location(0.1,0.1,1.0))  },
            { attend(Location(-0.1,-0.1,1.0))  },
            { attend(Location(0.0,0.0,1.0))  }
    )
}