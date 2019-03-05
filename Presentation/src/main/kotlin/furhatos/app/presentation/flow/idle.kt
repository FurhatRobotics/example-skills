package furhatos.app.presentation.flow

import furhatos.app.presentation.attendRandomUserOrLocation
import furhatos.app.presentation.StartIntent
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.*
import kotlin.Pair

val globalDefaultMale = Pair(Language.ENGLISH_US, "Matthew")
val globalDefaultFemale = Pair(Language.ENGLISH_US, "Joanna")
val Idle : State = state(Parent) {

    var shouldListen = true

    init {
        if (users.count > 0) {
            furhat.attend(users.random)
        }
        else {
            attendRandomUserOrLocation()
        }
    }

    onEntry {
        furhat.setTexture("default")
        furhat.setVoice(globalDefaultMale.first, globalDefaultMale.second)

        if (shouldListen) {
            furhat.listen()
        }
    }

    onExit {
        furhat.stopListening()
    }

    onUserEnter(instant = true) {
        furhat.attend(it)
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

    onTime(repeat = 6000..12000) { // Random look arounds on timer
        attendRandomUserOrLocation()
    }

    onTime(repeat = 8000..32000) { // Random things to spicy up his Idle time
        random(
            { furhat.gesture(Gestures.Oh) },
            { furhat.gesture(Gestures.Surprise) },
            { furhat.gesture(Gestures.Smile) },
            { furhat.gesture(Gestures.Thoughtful) },
            { furhat.gesture(Gestures.Wink) }
        )
    }


}