package furhatos.app.presentation.flow

import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.partialState

val wizardButtons  = partialState {

    onButton(key=" ") {
        print("its a space as like empty space")
        goto(Start)
    }

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