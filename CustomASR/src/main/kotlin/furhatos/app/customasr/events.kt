package furhatos.app.customasr

import furhatos.event.Event

open class InterimResult(val interimText: String): Event()

open class ListenDone: Event()
open class ListenStarted: Event()