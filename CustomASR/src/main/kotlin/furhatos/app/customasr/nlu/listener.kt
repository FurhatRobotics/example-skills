package furhatos.app.customasr.nlu

import furhatos.app.customasr.InterimResult
import furhatos.app.customasr.ListenDone
import furhatos.app.customasr.ListenStarted
import furhatos.app.customasr.NoSpeechDetected
import furhatos.event.EventSystem
import furhatos.flow.kotlin.state
import furhatos.util.CommonUtils

private val logger = CommonUtils.getLogger("ASR-EventListener")
/**
 * Listens to event send by the [furhatos.app.customasr.com.getTranscriptor]
 */
val ListenState = state {
    var fullText = ""
    var loudness = 0
    var listenEnded = false

    onEvent<ListenStarted>(instant = true) {
        logger.info("A new listen has started, resetting state.")
        fullText = ""
        loudness = 0
        listenEnded = false
    }

    onEvent<InterimResult>(instant = true) {
        if (!it.isPartial) {
            fullText += "${it.interimText} "
        } else {
            logger.info("INTERIM: ${it.interimText}")
        }
    }

    onEvent<ListenDone>(instant = true, cond = {!listenEnded}) {
        listenEnded = true
        logger.info("Listen done")
        var eventSend = false
        if (fullText.isEmpty()) {
            EventSystem.send(NoSpeechDetected())
        } else {
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
}