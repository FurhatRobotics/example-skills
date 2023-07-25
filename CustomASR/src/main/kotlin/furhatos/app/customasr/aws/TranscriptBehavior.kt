package furhatos.app.customasr.aws

import furhatos.app.customasr.InterimResult
import furhatos.app.customasr.ListenDone
import furhatos.app.customasr.ListenStarted
import furhatos.event.EventSystem
import furhatos.util.CommonUtils
import software.amazon.awssdk.services.transcribestreaming.model.StartStreamTranscriptionResponse
import software.amazon.awssdk.services.transcribestreaming.model.StartStreamTranscriptionResponseHandler
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptEvent
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptResultStream
import java.io.PrintWriter
import java.io.StringWriter

private val logger = CommonUtils.getLogger("TranscriptResponseHandler")

/**
 * Handles the events returned by AWS transcribe. Mostly sends events back in the system.
 */
fun getTranscriptor(): StartStreamTranscriptionResponseHandler {
    return StartStreamTranscriptionResponseHandler.builder()
        .onResponse { _: StartStreamTranscriptionResponse ->
            EventSystem.send(ListenStarted())
            logger.info("=== Received Initial response ===")
        }
        .onError { e: Throwable ->
            logger.warn(e.message)
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            logger.warn("Error Occurred: $sw")
            EventSystem.send(ListenDone())
        }
        .onComplete {
            EventSystem.send(ListenDone())
            logger.info("=== All records stream successfully ===")
        }
        .subscriber { event: TranscriptResultStream ->
            val results = (event as TranscriptEvent).transcript().results()
            if (results.size > 0) {
                if (results[0].alternatives().size > 0) {
                    if (results[0].alternatives()[0].transcript().isNotEmpty()) {
                        val result = results[0]
                        val message = result.alternatives()[0].transcript()
                        EventSystem.send(InterimResult(message, result.isPartial))
                    }
                }
            }
        }
        .build()
}