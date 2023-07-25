package furhatos.app.demo.flow.actions

import furhatos.app.chitchat.snippets.chatSnippets
import furhatos.app.demo.flow.Active
import furhatos.app.demo.flow.wizardButtons
import furhatos.app.demo.nlu.ExitIntent
import furhatos.app.demo.util.AttendUsers
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.snippets.SnippetRunner
import furhatos.snippets.SnippetState
import furhatos.snippets.TakeInitiative
import furhatos.snippets.TerminateSnippetState

val snippetRunner = SnippetRunner(chatSnippets)


val ChatState = state(SnippetState(snippetRunner)) {
    val exit = "EXIT"

    include(wizardButtons)

    onEntry {
        send(AttendUsers(shouldAlterAttentionOnSpeech = true))
        raise(TakeInitiative())
    }


    onResponse<ExitIntent> {
        raise(exit)
    }

    onEvent<TerminateSnippetState> {
        raise(exit)
    }

    onEvent(exit) {
        furhat.say("Okay, let's stop chatting")
        goto(Active(returning = true))
    }

}
