package furhatos.app.presentation.flow

import furhatos.app.presentation.nlu.StartIntent
import furhatos.flow.kotlin.*
import furhatos.records.Location
import furhatos.util.*

val Idle : State = state {

    var shouldListen = true

    init {
        furhat.setTexture("default")
        furhat.setVoice(Language.ENGLISH_GB,"william", Gender.MALE)

        if (users.count > 0) {
            furhat.attend(users.random)
        }
        else {
            furhat.attendRandomUserOrLocation()
        }
    }

    onUserEnter {
        furhat.attend(it)
    }

    onEntry {
        if (shouldListen) {
            furhat.listen(16000)
        }
    }

    onResponse<StartIntent> { // If the user says that we should start
        goto(Start)
    }

    onButton("Start") { // Creating a wizard button to start the presentation
        goto(Start)
    }

    onResponse { // Any other speech than our Start intent should just keep the listen loop
        reentry()
    }

    onNoResponse { // Silence, keep listening
        reentry()
    }

    onResponseFailed { // No recognizer working, we don't need the listen loop anymore
        shouldListen = false
    }

    /*onTime(Interval(4000,16000)) { // Not currently available ;-)
        random(
            { furhat.gesture(Gestures.Oh) },
            { furhat.gesture(Gestures.Surprise) },
            { furhat.gesture(Gestures.Smile) },
            { furhat.gesture(Gestures.Thoughtful) },
            { furhat.gesture(Gestures.Wink) },
            { furhat.say("GESTURE_SNIFF_1") },
            { furhat.say("GESTURE_COUGH_2") }
        )
    }*/
}

fun Furhat.attendRandomUserOrLocation() {
    if (runner.users.count > 0) {
        runner.random(
            { attend(runner.users.other) },
            { attendRandomLocation() }
        )
    }
    else {
        attendRandomLocation()
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