package furhatos.app.customasr

import furhatos.event.Event

/**
 * Events send by [TranscriptBehavior]
 */
open class InterimResult(val interimText: String, val isPartial: Boolean): Event()
open class ListenDone: Event()
open class ListenStarted: Event()
