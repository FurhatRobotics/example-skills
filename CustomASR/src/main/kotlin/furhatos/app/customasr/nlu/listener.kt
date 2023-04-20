package furhatos.app.customasr.nlu

import furhatos.app.customasr.InterimResult
import furhatos.app.customasr.ListenDone
import furhatos.app.customasr.ListenStarted
import furhatos.event.EventSystem
import furhatos.flow.kotlin.state

/**
 * Listens to event send by the [furhatos.app.customasr.com.StreamTranscriptionBehaviorImpl]
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
        println("Received interim result")
        if (!fullText.contains(it.interimText)) { // AWS likes repeating itself
            fullText += "${it.interimText} "
        }
    }

    onEvent<ListenDone> {
        println("Listen done")
        var eventSend = false
        NLUList.forEach { (example, constructor) ->
            if(fullText.contains(example, ignoreCase = true)) {
                println("Sending an event")
                eventSend = true
                EventSystem.send(
                    constructor.invoke(fullText, loudness)
                )
            }
        }
        if (!eventSend) {
            EventSystem.send(TextAndMetrics(fullText, loudness))
        }
    }
}