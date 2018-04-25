package furhatos.app.presentation.flow

import furhatos.app.presentation.attendRandomUserOrLocation
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.Gender
import furhatos.util.Language

val Start : State = state {

    onUserEnter (instant = true){
        furhat.glance(it)
    }

    onButton("Stop") {
        furhat.stopSpeaking()
        goto(Idle)
    }

    onEntry {
        furhat.say("Oh, hello there")
        delay(100)
        furhat.say("My name is Furhat, and I'm a socially intelligent robot")
        attendRandomUserOrLocation()

        furhat.say("I can show complex emotions")
        furhat.gesture(Gestures.ExpressAnger, false)
        attendRandomUserOrLocation()

        furhat.say("I can have different personalities")
        furhat.setTexture("female")
        furhat.say("I can look like a woman")
        furhat.setVoice(Language.ENGLISH_US, Gender.FEMALE)
        attendRandomUserOrLocation()

        furhat.say("And sound like a woman")
        furhat.setVoice(Language.ENGLISH_US, "william", Gender.MALE)
        furhat.say("Or like anyone else")
        furhat.setTexture("avatar")
        furhat.say("Like an avatar")
        attendRandomUserOrLocation()

        furhat.say("GESTURE_GIGGLE")
        furhat.say("GESTURE_BREATH_IN")
        furhat.setTexture("default")
        delay(500)
        attendRandomUserOrLocation()

        furhat.say("These are exciting times")
        delay(100)
        attendRandomUserOrLocation()

        furhat.say("I believe that great things are about to happen between social robots and humans")
        delay(200)
        attendRandomUserOrLocation()

        furhat.say("I'm really happy to be here")
        attendRandomUserOrLocation()

        furhat.say("and I'm really looking forward to get to know all you smart people")
        delay(500)
        furhat.gesture(Gestures.Wink)

        goto(Idle)
    }
}
