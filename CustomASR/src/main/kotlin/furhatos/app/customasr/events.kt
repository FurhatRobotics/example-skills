package furhatos.app.customasr

import furhatos.event.Event

open class InterimResult(val interimText: String, val isPartial: Boolean): Event()
open class ListenDone: Event()
open class ListenStarted: Event()
open class NoSpeechDetected: Event()