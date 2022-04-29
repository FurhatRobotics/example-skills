package furhatos.app.quiz.flow

import furhat.libraries.standard.AutomaticHeadMovements.RandomHeadMovements
import furhat.libraries.standard.getResourceGesture
import furhatos.app.quiz.RequestRules
import furhatos.app.quiz.flow.main.Idle
import furhatos.app.quiz.flow.main.NewQuestion
import furhatos.app.quiz.flow.main.maxRounds
import furhatos.app.quiz.setting.nextPlaying
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Goodbye
val EyebrowRaise = getResourceGesture("/json/EyebrowRaise.json")
val surprise = getResourceGesture("/json/surprise.json")

val Parent: State = state {
    include(RandomHeadMovements)
    onResponse<RequestRules> {
        furhat.say("You get $maxRounds questions, each with 4 options. You get one point for each correct answer.")
        if (users.count > 1) {
            furhat.say("If you answer wrong, the question will go over to the next person")
        }
        reentry()
    }

    onResponse<Goodbye> {
        furhat.say("It was nice talking to you. Goodbye")
        goto(Idle)
    }

    onUserEnter(instant = true) {
        furhat.glance(it.id)
    }

    onUserLeave(instant = true) {
        it.quiz.playing = false
        if (users.current?.id == it.id) {
            furhat.stopSpeaking()
            if (users.playing().isNotEmpty()) {
                furhat.attend(users.nextPlaying())
                goto(NewQuestion)
            } else {
                goto(Idle)
            }
        }
    }
}
