package furhatos.app.chitchat.snippets

import furhatos.gestures.Gestures
import furhatos.nlu.common.Greeting
import furhatos.snippets.NoMatch
import furhatos.snippets.UserLabel
import furhatos.snippets.snippets

object UT_Greeting : UserLabel(Greeting())

val greeting = snippets {

    snippet {
        request { +Gestures.Smile; +"Nice to meet you" }
        user(NoMatch)
        proceed()
    }
}