package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.playing
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val NewGame = state(parent = Parent) {
    onEntry {
        playing = true
        rounds = 0

        furhat.say("I will ask you $maxRounds multiple choice questions. And we'll see how many points you can get. ")
        if (users.count > 1) {
            furhat.say("If you answer wrong, the question will go over to the next person")
        }

        furhat.say("Alright, here we go!")
        QuestionSet.next()
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
}