package furhatos.app.customasr.nlu

import furhatos.event.Event

open class TextAndMetrics(
    val text: String,
    val loudness: Int
) : Event()

class Hmm(t: String, l: Int): TextAndMetrics(t, l) // An Intent
class No(t: String, l: Int): TextAndMetrics(t, l) // An Intent

val NLUList = mapOf<String, (text: String, loudness: Int) -> TextAndMetrics>( // Maps words to Intents
    "Hmm" to { text, loudness -> Hmm(text, loudness) },
    "No" to { text, loudness -> No(text, loudness) }
)
