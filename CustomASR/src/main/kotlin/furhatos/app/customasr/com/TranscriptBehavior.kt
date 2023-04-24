package furhatos.app.customasr.com

import furhatos.app.customasr.InterimResult
import furhatos.app.customasr.ListenDone
import furhatos.app.customasr.ListenStarted
import furhatos.event.EventSystem
import software.amazon.awssdk.services.transcribestreaming.model.StartStreamTranscriptionResponse
import software.amazon.awssdk.services.transcribestreaming.model.StartStreamTranscriptionResponseHandler
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptEvent
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptResultStream
import java.io.PrintWriter
import java.io.StringWriter

fun getTranscriptor(): StartStreamTranscriptionResponseHandler {
    return StartStreamTranscriptionResponseHandler.builder()
        .onResponse { _: StartStreamTranscriptionResponse ->
            EventSystem.send(ListenStarted())
            println("=== Received Initial response ===")
        }
        .onError { e: Throwable ->
            println(e.message)
            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            println("Error Occurred: $sw")
            EventSystem.send(ListenDone())
        }
        .onComplete {
            EventSystem.send(ListenDone())
            println("=== All records stream successfully ===")
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