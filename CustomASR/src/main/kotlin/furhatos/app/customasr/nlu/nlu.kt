package furhatos.app.customasr.nlu

import furhatos.event.Event

/**
 * Base Intent event
 */
open class TextAndMetrics(
    val text: String,
    val loudness: Int
) : Event()

class Yes(t: String, l: Int): TextAndMetrics(t, l) // An Intent
class No(t: String, l: Int): TextAndMetrics(t, l) // An Intent

open class NoSpeechDetected: Event() // No Speech

val NLUList = mapOf<String, (text: String, loudness: Int) -> TextAndMetrics>( // Maps words to Intents
    "Yes" to { text, loudness -> Yes(text, loudness) },
    "No" to { text, loudness -> No(text, loudness) }
)
