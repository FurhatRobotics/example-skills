package furhatos.app.presentation.flow

import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.partialState

val wizardButtons  = partialState {

    //If the spacebar is pressed, the presentation is started
    onButton(key=" ") {
        goto(Start)
    }

     /*
        All these buttons start specific parts of the presentation, or the whole presentation.
     */
    onButton("Go to Start", section = Section.LEFT, color = Color.Yellow) {
        goto(Start)
    }

    onButton("Show Emotions", section = Section.LEFT, color = Color.Yellow) {
        call(ComplexEmotions)
    }

    onButton("Replay ending", section = Section.LEFT, color = Color.Yellow) {
        call(Ending)
    }

    onButton("Show LED boot sequence", section = Section.RIGHT, color = Color.Blue) {
        call(LEDBootSequence)
    }

    onButton("Show LEDs", section = Section.RIGHT, color = Color.Red) {
        call(ShowLED)
    }

    onButton("Show Personalities", section = Section.RIGHT, color = Color.Green) {
        call(DifferentPersonalities)
    }
}