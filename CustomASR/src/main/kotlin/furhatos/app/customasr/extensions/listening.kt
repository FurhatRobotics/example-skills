package furhatos.app.customasr.extensions

import furhatos.app.customasr.InterimResult
import furhatos.app.customasr.ListenStarted
import furhatos.app.customasr.com.FurhatAudioStream
import furhatos.app.customasr.aws.TranscribeApp
import furhatos.app.customasr.com.params
import furhatos.app.customasr.nlu.NoSpeechDetected
import furhatos.event.EventSystem
import furhatos.flow.kotlin.*
import furhatos.monitor.FurhatAudioFeedStreamer
import furhatos.util.CommonUtils
import kotlin.concurrent.thread

private val transcribeApp = TranscribeApp()
private val furStream = FurhatAudioStream()

fun Furhat.enableStartAudioStream() {
    this.audioFeed.enable()
    FurhatAudioFeedStreamer.start(params.IP_ADDRESS)
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
    timeout: Long = params.timeout,
    endSil: Long = params.endSil,
    maxSpeech: Long = params.maxSpeech
) {
    val lang = this.inputLanguages.first()
    EventSystem.send(ListenStarted())
    furStream.resetForListen()
    thread { //Starts a thread returns when done.
        transcribeApp.startListen(lang, furStream, timeout, endSil, maxSpeech)
    }
    runner.call(listenState(timeout, endSil, maxSpeech))
}

fun listenState(timeout: Long, endSil: Long, maxSpeech: Long) = state {
    var speechWasRecognized = false
    var lastSpeechTime = -1L
    val logger = CommonUtils.getLogger("ListenState")

    onEvent<InterimResult>(instant = true) { // Got recognized speech
        speechWasRecognized = true
        lastSpeechTime = System.currentTimeMillis()
    }

    /**
     * Checks how long ago the last speech was recognized, ends the stream if its longer than the endSil param.
     */
    onTime(repeat = 100, instant = true, cond = { speechWasRecognized }) {
        if(
            lastSpeechTime != -1L &&
            System.currentTimeMillis() - lastSpeechTime > endSil
        ) {
            logger.info("endSil for listen reached.")
            furStream.active = false
        }
    }

    /**
     * If it takes longer than initialTimeout for speech to be recognized, end the stream.
     */
    onTime(delay = timeout.toInt(), instant = true, cond = { !speechWasRecognized} ) {
        logger.info("timeout for listen reached.")
        furStream.active = false
    }

    /**
     * End the stream if maxSpeech timeout is reached.
     */
    onTime(delay = maxSpeech.toInt(), instant = true) {
        logger.info("maxSpeech for listen reached.")
        furStream.active = false
    }

    onExit {
        logger.info("Exiting listen state")
        furStream.active = false
    }
}