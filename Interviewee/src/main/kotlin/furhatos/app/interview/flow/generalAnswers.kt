package furhatos.app.interview.flow

import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.partialState
import furhatos.gestures.Gestures

val statements = listOf(
        "Yes",
        "No",
        "Maybe",
        "Absolutely",
        "Hi",
        "Bye",
        "Cheers",
        "Do you?"
)

val generalAnswers = partialState {
    statements.forEach { statement ->
        onButton(label = statement, section = Section.RIGHT, color = Color.Green) {
            furhat.say {
                +statement
                Gestures.BigSmile
            }
        }
    }

}