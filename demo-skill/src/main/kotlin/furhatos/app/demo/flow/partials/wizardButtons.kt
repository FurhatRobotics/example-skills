package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Passive
import furhatos.app.demo.imported.quiz.Idle
import furhatos.app.demo.personas.Persona
import furhatos.app.demo.util.LanguageChange
import furhatos.app.demo.util.PersonaChange
import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.partialState

val wizardButtons = partialState {
    onButton("Go to Sleeping", section = Section.LEFT, color = Color.Red) {
        goto(Sleeping)
    }

    onButton("Go to Passive", section = Section.LEFT, color = Color.Yellow) {
        goto(Passive)
    }

    onButton("Go to Active", section = Section.LEFT, color = Color.Green) {
        goto(Active())
    }

    onButton("Stop speaking", section = Section.LEFT, color = Color.Red, instant = true) {
        furhat.stopSpeaking()
        furhat.listen()
    }

    onButton("Random persona", section = Section.RIGHT, color = Color.Blue) {
        raise(PersonaChange(Persona.other))
    }

    val personas = Persona.list

    personas.forEach { persona ->
        onButton("Be ${persona.name}", section = Section.RIGHT, color = Color.Blue) {
            if (Persona.active.model != persona.model) {
                goto(ModelChange(persona, Active()))
            }
            else {
                raise(PersonaChange(persona))
            }
        }
    }

    personas.map { it.language }.distinct().forEach { language ->
        onButton("Speak ${language.name}", section = Section.RIGHT, color = Color.Green) {
            raise(LanguageChange(language))
        }
    }

    onButton("Play quiz", section = Section.RIGHT, color = Color.Yellow) {
        goto(RequireUsers(Idle))
    }
}