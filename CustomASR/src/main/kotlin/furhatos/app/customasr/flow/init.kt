package furhatos.app.customasr.flow

import furhatos.app.customasr.extensions.customListen
import furhatos.app.customasr.extensions.enableStartAudioStream
import furhatos.app.customasr.extensions.onUserSilence
import furhatos.app.customasr.nlu.*
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

/**
 * The state shows how a CustomASR could be used with custom extension functions.
 *  Listens to Intents (yes, no), or a generic response [TextAndMetrics]
 *  When no speech is recognized, the [onUserSilence] is triggered.
 */
val Basic: State = state {
    init {
        furhat.enableStartAudioStream() // Start the stream and listener
        parallel(ListenState, false) // Start the state in charge of Listening and NLU.
    }

    onButton("Ask") {
        furhat.say("Hello there!")
        furhat.customListen()
    }

    onEvent<Yes> {
        furhat.say("Yes!")
    }

    onEvent<No> {
        if (it.loudness > 54.0) {
            furhat.say("LOUD no!")
        } else {
            furhat.say("Silent no")
        }
    }

    onEvent<TextAndMetrics> {
        furhat.say(it.text)
    }

    onUserSilence {
        furhat.say("You said nothing!")
    }
}

