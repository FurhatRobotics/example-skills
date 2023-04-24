package furhatos.app.customasr.extensions

import furhatos.app.customasr.ListenStarted
import furhatos.app.customasr.NoSpeechDetected
import furhatos.app.customasr.com.FurhatAudioStream
import furhatos.app.customasr.com.TranscribeApp
import furhatos.event.EventSystem
import furhatos.event.senses.SenseSpeech
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.Furhat
import furhatos.flow.kotlin.StateBuilder
import furhatos.flow.kotlin.TriggerRunner
import furhatos.monitor.FurhatAudioFeedStreamer
import kotlin.concurrent.thread

val transcribeApp = TranscribeApp()
val furStream = FurhatAudioStream()

fun Furhat.enableStartAudioStream() {
    this.audioFeed.enable()
    FurhatAudioFeedStreamer.start("127.0.0.1")
}

fun StateBuilder.onUserSilence(trigger: TriggerRunner<*>.(NoSpeechDetected) -> Unit) {
    onEvent<NoSpeechDetected> {
        trigger.invoke(this, it)
    }
}

fun Furhat.customAsk(text: String) {
    this.say(text)
    this.customListen()
}

fun Furhat.customListen(
    timeout: Long = 5000L,
    endSil: Long = 1000L,
    maxSpeech: Long = 15000L
) {
    val lang = this.inputLanguages.first()
    EventSystem.send(ListenStarted())
    furStream.resetForListen()
    thread { //Starts a thread returns when done.
        transcribeApp.startListen(lang, furStream, timeout, endSil, maxSpeech)
        println("Done")
    }
    thread { // Stops the [furStream] from sending data, otherwise recognition will indefinitely continue.
        Thread.sleep(maxSpeech)
        furStream.active = false
    }
}