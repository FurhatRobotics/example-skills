package furhatos.app.quiz.flow.main


import furhat.libraries.standard.GesturesLib
import furhatos.app.quiz.*
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.flow.customGestures.awaitAnswer
import furhatos.app.quiz.questions.*
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.autobehavior.IndefiniteBigSmile
import furhatos.autobehavior.StopSmile
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*
import furhatos.nlu.common.Greeting
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import org.jetbrains.spek.api.dsl.Pending

val NewGame = state(parent = Parent) {

    onEntry {
        playing = true
        rounds = 0
        furhat.setDefaultMicroexpression(blinking = true, facialMovements= true, eyeMovements = false)
        furhat.say("I will ask you $maxRounds multiple choice questions. And we'll see how many points you can get.")
        furhat.say("I can ask you questions about Sweden, Robots, Music or Science.")
        furhat.say("We can also mix these topics with some bonus questions.")
        furhat.gesture(awaitAnswer)
        if (users.count > 1) {
            furhat.say("If you answer wrong, the question will go over to the next person")
        }
        goto(AskTopic)

    }
    onReentry {
        furhat.say("On which topic do you want questions now? Remember, we have Sweden, Science, Music or myself.")
        furhat.say("You can also get random questions out of these categories")
        goto(AskTopic)
    }
    onButton{
        goto(EndGame)
    }
}



val AskTopic = state {
    onEntry {

        furhat.ask("So. What should it be?")

    }
    onResponse<DontKnow> {
        furhat.say("A little bit of everything. This will be fun!")
        furhat.gesture(IndefiniteBigSmile)
        delay(500)
        furhat.gesture(StopSmile)
        var allQuestions = (questionsEnglish + questionsScienceEnglish + questionsSwedenEnglish + questionsMusicEnglish).toMutableList() // + questionsRobotEnglish TODO: when more questions in this section, add it again
        allQuestions.shuffle()
        QuestionSet.next(allQuestions)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }

    onResponse<Mix> {
        raise(DontKnow())
    }

    onResponse<Science> {
        furhat.say("Its science time")
        QuestionSet.next(questionsScienceEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }

    onResponse<Sweden> {
        furhat.say("Its time for some good questions about my native country Sweden")
        QuestionSet.next(questionsSwedenEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
    onResponse<Robots> {
        furhat.say("I love questions about me")
        QuestionSet.next(questionsRobotEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
    onResponse<Music> {
        furhat.say("Lets see how good your taste in music is")
        QuestionSet.next(questionsMusicEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }


}
