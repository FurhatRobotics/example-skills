package furhatos.app.customasr.nlu

import furhatos.app.customasr.InterimResult
import furhatos.app.customasr.ListenDone
import furhatos.app.customasr.ListenStarted
import furhatos.event.EventSystem
import furhatos.flow.kotlin.state

/**
 * Listens to event send by the [furhatos.app.customasr.com.getTranscriptor]
 */
val ListenState = state {
    var fullText = ""
    var loudness = 0

    onEvent<ListenStarted> {
        println("Received listen started")
        fullText = ""
        loudness = 0
    }

    onEvent<InterimResult> {
        if (!it.isPartial) {
            fullText += "${it.interimText} "
        } else {
            println("INTERIM: ${it.interimText}")
        }
    }

    onEvent<ListenDone> {
        println("Listen done")
        var eventSend = false
        /**
         * It would be wise to implement a smarter NLU here.
         */
        NLUList.forEach { (example, constructor) ->
            if(fullText.contains(example, ignoreCase = true)) {
                eventSend = true
                EventSystem.send(
                    constructor.invoke(fullText, loudness) // Send the specific Intent Event
                )
            }
        }
        if (!eventSend) {
            EventSystem.send(TextAndMetrics(fullText, loudness)) // If no specific intent was sent, send a generic one.
        }
    }
}