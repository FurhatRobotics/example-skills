package furhatos.app.demo.flow

import furhatos.app.demo.flow.modes.Parent
import furhatos.app.demo.flow.modes.Passive
import furhatos.app.demo.nlu.GoToPassiveIntent
import furhatos.app.demo.personas.phrases
import furhatos.app.demo.util.AttendUsers
import furhatos.app.demo.util.ExitEvent
import furhatos.app.demo.util.setLED
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.NullIntent
import furhatos.nlu.Response

var modelChange = false

// State where Furhat assumes the user will respond
fun Active(response: Response<*>? = null, returning: Boolean = false) : State = state(Parent) {
    onEntry {
        send(AttendUsers(shouldAlterAttentionOnSpeech = false))
        furhat.setLED("White")
        furhat.gesture(Gestures.OpenEyes, priority = 1)
        furhat.attend(users.random)
        furhat.setDefaultMicroexpression()
        when {
            response != null -> raise(response)  // Raise our response so that our handlers can do their magic
            returning -> reentry()
            else -> furhat.ask(phrases.howCanIhelp)
        }
    }

    onReentry {
        send(AttendUsers(shouldAlterAttentionOnSpeech = false))
        furhat.ask(phrases.canIhelpWithAnythingElse())
    }

    var nomatch = 0

    onResponse<GoToPassiveIntent> {
        furhat.say(phrases.okay)
        goto(Passive)
    }

    onResponse(cond = { it.intent == NullIntent }) {
        when(++nomatch) {
            1 -> furhat.say("Sorry, I didn't understand that")
            else -> furhat.say("Sorry, I still didn't understand that")
        }
        reentry()
    }

    onNoResponse {
        goto(Passive)
    }

    onResponseFailed {
        furhat.say("Sorry, my speech recognizer is not working")
        raise(ExitEvent())
    }
}

fun main(args: Array<String>) {
//    while(true) {
//        val utterance = readLine()
//        val results = Active().getIntentClassifier(lang = Language.ENGLISH_US).classify(utterance!!)
//        if (results.isEmpty()) {
//            println("No match")
//        }
//        else {
//            results.forEach {
//                println("Matched ${it.intents} with ${it.conf} confidence")
//            }
//        }
//    }
}