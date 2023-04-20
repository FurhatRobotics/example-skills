package furhatos.app.customasr.extensions

import furhatos.app.customasr.com.FurhatAudioStream
import furhatos.app.customasr.com.TranscribeApp
import furhatos.flow.kotlin.Furhat
import furhatos.monitor.FurhatAudioFeedStreamer
import kotlin.concurrent.thread

val transcribeApp = TranscribeApp()
val furStream = FurhatAudioStream()

fun Furhat.enableStartAudioStream() {
    this.audioFeed.enable()
    FurhatAudioFeedStreamer.start("127.0.0.1")
}

fun Furhat.customListen(
    maxSpeech: Long = 15000L
) {
    val lang = this.inputLanguages.first()
    furStream.active = true
    furStream.data = byteArrayOf() // Remove old data
    thread { //Starts a thread returns when done.
        transcribeApp.startListen(lang, furStream, maxSpeech)
        println("Done")
    }
    thread { // Stops the [furStream] from sending data, otherwise recognition will indefinitely continue.
        Thread.sleep(maxSpeech)
        furStream.active = false
    }
}