package furhatos.app.customasr.nlu

import furhatos.event.Event

/**
 * Base Intent event
 */
open class TextAndMetrics(
    val text: String,
    val loudness: Double
) : Event()

class Yes(t: String, l: Double): TextAndMetrics(t, l) // An Intent
class No(t: String, l: Double): TextAndMetrics(t, l) // An Intent

open class NoSpeechDetected: Event() // No Speech

val NLUList = mapOf<String, (text: String, loudness: Double) -> TextAndMetrics>( // Maps words to Intents
    "Yes " to { text, loudness -> Yes(text, loudness) },
    "No " to { text, loudness -> No(text, loudness) }
)
