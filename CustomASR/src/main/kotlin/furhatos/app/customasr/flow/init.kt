package furhatos.app.customasr.flow

import furhatos.app.customasr.extensions.customListen
import furhatos.app.customasr.extensions.enableStartAudioStream
import furhatos.app.customasr.nlu.ListenState
import furhatos.app.customasr.nlu.No
import furhatos.app.customasr.nlu.Hmm
import furhatos.app.customasr.nlu.TextAndMetrics
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val Basic: State = state {
    init {
        furhat.enableStartAudioStream() // Start the stream and listener
        parallel(ListenState, false) // Start the state in charge of Listening and NLU.
    }

    onButton("Ask") {
        furhat.say("Hello there!")
        furhat.customListen()
    }

    onEvent<Hmm> {
        furhat.say("Why are you confused?")
    }

    onEvent<No> {
        furhat.say("No!")
    }

    onEvent<TextAndMetrics> {
        furhat.say(it.text)
    }
}

