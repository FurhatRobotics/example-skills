package furhatos.app.demo.flow.partials

import furhatos.app.demo.flow.Active
import furhatos.app.demo.flow.Sleeping
import furhatos.app.demo.flow.actions.snippetRunner
import furhatos.app.demo.nlu.ExitIntent
import furhatos.app.demo.nlu.NoIntent
import furhatos.app.demo.nlu.WakeupIntent
import furhatos.app.demo.personas.Persona
import furhatos.app.demo.personas.phrases
import furhatos.app.demo.util.AttendUsers
import furhatos.app.demo.util.ExitEvent
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.partialState
import furhatos.flow.kotlin.raise
import furhatos.nlu.NullIntent
import furhatos.nlu.common.Greeting
import furhatos.nlu.common.RequestRepeat
import furhatos.nlu.common.Yes
import furhatos.snippets.SnippetFallbackTurn

val functionalHandlers = partialState {
    onResponse<WakeupIntent> {
        goto(Active())
    }

    onResponse<RequestRepeat> {
        val stack = Persona.active.conversationStack
        if (stack.isNotEmpty()) {
            raise(it, stack.last())
        }
        furhat.listen()
    }

    onResponse({ snippetRunner.getUserIntents()}) {
        if (it.intent == NullIntent) {
            propagate()
        } else {
            call(SnippetFallbackTurn(snippetRunner, snippetRunner.respond(it.text, it.intent)))
            furhat.say("So")
            reentry()
        }
    }

    onResponse(cond = { it.intent != NullIntent }){
        // Save the intent in our conversation stack so that we can remember it
        Persona.active.conversationStack.add(it.intent)
        /*
            Here we know that the user has performed a successful command,
            so we want to open up for alterations in attention, to look at
            other users aside from the closest one (our estimated confederate)
         */
        send(AttendUsers(shouldAlterAttentionOnSpeech = true))
        propagate()
    }

    onResponse<Yes> {
        reentry()
    }

    onResponse<ExitIntent>(cond = { currentState != Sleeping }) {
        raise(ExitEvent())
    }

    onResponse<NoIntent> {
        raise(ExitEvent())
    }

    onEvent<ExitEvent> {
        furhat.say {
            +phrases.okay
            +","
            +phrases.goodbye
        }
        goto(Sleeping)
    }

    onResponse<Greeting> {
        furhat.say(phrases.greeting)
        reentry()
    }
}