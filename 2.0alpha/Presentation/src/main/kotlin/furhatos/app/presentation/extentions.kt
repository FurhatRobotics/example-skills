package furhatos.app.presentation

import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.Furhat
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users
import furhatos.records.Location

/*
    The FlowControlRunner has access to both users (a UserManager instance) and furhat (A Furhat instance).
    We need both of these for this extention method, hence we extend the FlowControlRunner class.
 */
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

/*
    Here we don't need access to users so we can add the extention method to the Furhat class instead.
 */
fun Furhat.attendRandomLocation() {
    runner.random(
            { attend(Location(0.3,0.0,1.0))  },
            { attend(Location(-0.3,0.0,1.0))  },
            { attend(Location(0.1,0.1,1.0))  },
            { attend(Location(-0.1,-0.1,1.0))  },
            { attend(Location(0.0,0.0,1.0))  }
    )
}