package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Parent
import furhatos.app.demo.nlu.CancelIntent
import furhatos.app.demo.nlu.DoneIntent
import furhatos.app.demo.personas.Persona
import furhatos.app.demo.personas.phrases
import furhatos.app.demo.personas.setPersona
import furhatos.app.demo.util.LookStraight
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Yes

fun ModelChange(persona: Persona, nextState: State) : State = state(Parent) {
    onEntry {
        send(LookStraight())
        furhat.say(phrases.okay)
        furhat.ask(phrases.pleaseTakeMaskOff)
    }

    onResponse<DoneIntent> {
        // Picked up in topics state
        goto(PutMaskOn(persona, nextState))
    }

    onResponse<Yes> {
        raise(it, DoneIntent())
    }

    onNoResponse {
        furhat.listen()
    }

    onResponse<CancelIntent> {
        furhat.say("Okay, aborting!")
        goto(Active(returning = true))
    }
}

fun PutMaskOn(persona: Persona, nextState: State) = state(ModelChange(persona, nextState)) {
    onEntry {
        furhat.ask(phrases.pleaseChangeMask(persona.model))
    }

    onResponse<DoneIntent> {
        furhat.setPersona(persona = persona)
        furhat.say("great!")
        furhat.say(phrases.greeting)
        goto(nextState)
    }
}
