package furhatos.app.presentation.flow

import furhatos.app.presentation.attendRandomUserOrLocation
import furhatos.app.presentation.setLED
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.Gender
import furhatos.util.Language
import java.awt.Color

val Parent : State = state {
    include(wizardButtons)
}

val Start : State = state(Parent) {

    onUserEnter (instant = true){
        furhat.glance(it)
    }

    onButton("Stop") {
        furhat.stopSpeaking()
        goto(Idle)
    }

    onEntry {

        call(LEDBootSequence)

        furhat.say("Oh, hello there")
        delay(100)
        furhat.say("My name is Furhat, and I'm a socially intelligent robot")
        attendRandomUserOrLocation()

        call(ComplexEmotions)

        call(DifferentPersonalities)

        call(ShowLED)

        call(Ending)

        goto(Idle)
    }
}

val LEDBootSequence : State = state {
    onEntry {
        //The presentation starts with slowly lighting up his LED's.
        //And showing a growing big smile :)
        furhat.setLED("white", 25)
        furhat.gesture(Gestures.BigSmile(0.3))
        delay(300)
        furhat.setLED("white", 50)
        furhat.gesture(Gestures.BigSmile(0.6))
        delay(300)
        furhat.setLED("white", 75)
        furhat.gesture(Gestures.BigSmile(0.9))
        delay(300)
        furhat.setLED("white", 100)
        furhat.gesture(Gestures.BigSmile)
        delay(300)
        furhat.setLED("white")
        terminate()
    }
}

val ComplexEmotions : State = state {
    onEntry {
        furhat.say("I can show complex emotions, like anger")
        furhat.gesture(Gestures.ExpressAnger, false)
        furhat.say("Or sadness")
        furhat.gesture(Gestures.ExpressSad, false)
        attendRandomUserOrLocation()
        terminate()
    }
}

val DifferentPersonalities : State = state {
    onEntry {
        furhat.say("I can have different personalities")
        furhat.setTexture("female")
        furhat.say("I can look like a woman")
        furhat.setVoice(globalDefaultFemale.first, globalDefaultFemale.second)
        attendRandomUserOrLocation()

        furhat.say("And sound like a woman")
        furhat.setVoice(globalDefaultMale.first, globalDefaultMale.second)
        furhat.say("Or like anyone else.")
        furhat.setTexture("irobot")
        furhat.say("Like a famous robot")
        attendRandomUserOrLocation()

        furhat.say("Do you recognize me?")

        furhat.setTexture("default")
        delay(500)
        attendRandomUserOrLocation()
        terminate()
    }
}

val ShowLED : State = state {
    onEntry {
        furhat.say("I also have built-in LEDs")
        furhat.setLED("red")
        delay(500)
        furhat.gesture(Gestures.Surprise)
        delay(1000)
        furhat.say("I can show a variety of colours, for example these ones")
        furhat.setLED("blue")
        delay(500)
        furhat.setLED("purple")
        delay(500)
        furhat.setLED("orange")
        delay(500)
        furhat.setLED("white")
        terminate()
    }
}

val Ending : State = state{
    onEntry {
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
        terminate()
    }
}
