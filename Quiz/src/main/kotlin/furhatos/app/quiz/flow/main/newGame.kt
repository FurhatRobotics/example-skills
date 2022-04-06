package furhatos.app.quiz.flow.main


import furhatos.app.quiz.*
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.flow.customGestures.awaitAnswer
import furhatos.app.quiz.questions.*
import furhatos.app.quiz.setting.playing
import furhatos.autobehavior.IndefiniteBigSmile
import furhatos.autobehavior.StopSmile
import furhatos.autobehavior.setDefaultMicroexpression
import furhatos.flow.kotlin.*
import furhatos.app.quiz.setting.getQuestions

var sheetLink = "1Deqm9Xmbm2bYplRSsPIVKZEJ1KhOfgM3EW6JB-VZySI"


val NewGame = state(parent = Parent) {

    onEntry {
        if (!reentering){
            playing = true
            rounds = 0
            furhat.setDefaultMicroexpression(blinking = true, facialMovements= true, eyeMovements = false)
            furhat.say("<prosody rate=\"90%\">I will ask you $maxRounds multiple choice questions. And we'll see how many points you can get.</prosody>")
            furhat.say("<prosody rate=\"90%\">I can ask you questions about Sweden, Robots, Music or Science.</prosody>")
            furhat.say("<prosody rate=\"90%\">We can also mix these topics with some bonus questions.</prosody>")
            furhat.gesture(awaitAnswer)
            if (users.count > 1) {
                furhat.say("If you answer wrong, the question will go over to the next person")
            }
        }
        else{
            furhat.say("On which topic do you want questions now? Remember, we have Sweden, Science, Music or myself.")
        furhat.say("You can also get random questions out of these categories")}
        goto(AskTopic)

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
        if (questionsRandomEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Random", questionsRandomEnglish) }
        if (questionsScienceEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Science", questionsScienceEnglish) }
        if (questionsRobotEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Robots", questionsRobotEnglish) }
        if (questionsMusicEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Music", questionsMusicEnglish) }
        QuestionSet.topicname = "Random: "
        var allQuestions = (questionsRandomEnglish + questionsScienceEnglish + questionsSwedenEnglish + questionsMusicEnglish + questionsRobotEnglish).toMutableList()
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
        delay(200)
        QuestionSet.topicname = "Science: "
        if (questionsScienceEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Science", questionsScienceEnglish) }
        QuestionSet.next(questionsScienceEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }

    onResponse<Sweden> {
        furhat.say("Its time for some good questions about my native country Sweden")
        delay(200)
        QuestionSet.topicname = "Sweden: "
        if (questionsSwedenEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Sweden", questionsSwedenEnglish) }
        QuestionSet.next(questionsSwedenEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
    onResponse<Robots> {
        furhat.say("I love questions about my kind")
        delay(200)
        QuestionSet.topicname = "Robots: "
        if (questionsRobotEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Robots", questionsRobotEnglish) }
        QuestionSet.next(questionsRobotEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
    onResponse<Music> {
        furhat.say("Lets see how good your musical taste is")
        delay(200)
        QuestionSet.topicname = "Music: "
        if (questionsMusicEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Music", questionsMusicEnglish) }
        QuestionSet.next(questionsMusicEnglish)
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }


}


