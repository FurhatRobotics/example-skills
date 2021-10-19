package furhatos.app.demo.util

import furhatos.app.demo.personas.Persona
import furhatos.event.Event
import furhatos.records.Location
import furhatos.util.Language

/*
    Normal flow
 */
class ExitEvent : Event()
class PersonaChange(val persona: Persona? = null) : Event()
class LanguageChange(val language: Language? = null) : Event()

/*
    Automatic behavior
 */
class AttendLocation(val location: Location) : Event()
class LookAround : Event()
class AttendUsers(val shouldAlterAttentionOnSpeech: Boolean = true) : Event()
class LookStraight(val randomMovements: Boolean = true) : Event()
class StopAutoBehavior : Event()