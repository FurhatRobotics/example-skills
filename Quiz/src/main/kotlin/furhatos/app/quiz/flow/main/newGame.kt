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
import furhatos.app.quiz.setting.googleSheetsIftttUrl
import furhatos.app.quiz.setting.googleSheetsLog

var sheetLink = "1MKJb6VC71JZ2MyKrw6wS64ybP8shqtvU4VsC1Ksfse8"



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
        if (QuestionSet.topicname == "" || QuestionSet.topicname != "Random: "){
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
            QuestionSet.currenttopic = allQuestions
            QuestionSet.current = QuestionSet.currenttopic.first()
        }
        else {
            if (QuestionSet.topicname == "Random: " && QuestionSet.currenttopic.size >= 4){
                furhat.say("Looks like you want to have more questions.")
                QuestionSet.next()
                goto(AskQuestion)
            }
            else {
                furhat.say("I am afraid i don't have enough questions about this topic for another round any more.")
                furhat.say("You now have had every Question available. Come again soon!")
                goto(Idle)

            }
        }


        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
    onResponse<Again>{
        if (QuestionSet.topicname != "" && QuestionSet.currenttopic.size >= 4){
            furhat.say("Here we go again")
            QuestionSet.next()
            goto(AskQuestion)

        }
        else if (QuestionSet.topicname != "" && QuestionSet.currenttopic.size <= 2){
            furhat.say("Looks like i don't have any more questions for that topic")
            furhat.ask("Which one of the other topics do you wanna try?")
        }

        else {
            furhat.say("I am sorry, i didn't catch that.")
        }

    }
    onResponse<Mix> {
        raise(DontKnow())
    }

    onResponse<Science> {
        if (QuestionSet.topicname == "" || QuestionSet.topicname != "Science: "){
            furhat.say("Its science time")
            delay(200)
            QuestionSet.topicname = "Science: "
            if (questionsScienceEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Science", questionsScienceEnglish) }
            questionsScienceEnglish.shuffle()

            QuestionSet.currenttopic = questionsScienceEnglish
            QuestionSet.current = QuestionSet.currenttopic.first()}

        else {
            if (QuestionSet.topicname == "Science: " && QuestionSet.currenttopic.size >= 4){
                furhat.say("Looks like you want to have more Science questions.")
                QuestionSet.next()
                goto(AskQuestion)
            }
            else {
                furhat.say("I am afraid i don't have enough questions about this topic for another round any more.")
                furhat.ask("Do you want to try out another topic? Maybe you are also interested in Sweden, Robots or Music?")
            }
        }
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }

    onResponse<Sweden> {

        if (QuestionSet.topicname == "" || QuestionSet.topicname != "Sweden: "){
            furhat.say("Let's have some questions about my native country.")
            delay(200)
            QuestionSet.topicname = "Sweden: "
            if (questionsSwedenEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Sweden", questionsSwedenEnglish) }
            questionsSwedenEnglish.shuffle()

            QuestionSet.currenttopic = questionsSwedenEnglish
            QuestionSet.current = QuestionSet.currenttopic.first()}

        else {
            if (QuestionSet.topicname == "Sweden: " && QuestionSet.currenttopic.size >= 4){
                furhat.say("Looks like you want to have more Sweden questions.")
                QuestionSet.next()
                goto(AskQuestion)
            }
            else {
                furhat.say("I am afraid i don't have enough questions about this topic for another round any more.")
                furhat.ask("Do you want to try out another topic? Maybe you are also interested in Science, Robots or Music?")
            }
        }
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }


    onResponse<Robots> {

        if (QuestionSet.topicname == "" || QuestionSet.topicname != "Robots: "){
            furhat.say("I love questions about my kind!")
            delay(200)
            QuestionSet.topicname = "Robots: "
            if (questionsRobotEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Robots", questionsRobotEnglish) }
            questionsRobotEnglish.shuffle()

            QuestionSet.currenttopic = questionsRobotEnglish
            QuestionSet.current = QuestionSet.currenttopic.first()}

        else {
            if (QuestionSet.topicname == "Robots: " && QuestionSet.currenttopic.size >= 4){
                furhat.say("Looks like you want to have more robot questions.")
                QuestionSet.next()
                goto(AskQuestion)
            }
            else {
                furhat.say("I am afraid i don't have enough questions about this topic for another round any more.")
                furhat.ask("Do you want to try out another topic? Maybe you are also interested in Sweden, Science or Music?")
            }
        }
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }


    onResponse<Music> {
        if (QuestionSet.topicname == "" || QuestionSet.topicname != "Music: "){
            furhat.say("Now i can find out, how good your taste in Music is")
            delay(200)
            QuestionSet.topicname = "Music: "
            if (questionsMusicEnglish.isNullOrEmpty()){ getQuestions(sheetLink, "Music", questionsMusicEnglish) }
            questionsMusicEnglish.shuffle()

            QuestionSet.currenttopic = questionsMusicEnglish
            QuestionSet.current = QuestionSet.currenttopic.first()}

        else {
            if (QuestionSet.topicname == "Music: " && QuestionSet.currenttopic.size >= 4){
                furhat.say("Looks like you want to have more Music questions.")
                QuestionSet.next()
                goto(AskQuestion)
            }
            else {
                furhat.say("I am afraid i don't have enough questions about this topic for another round any more.")
                furhat.ask("Do you want to try out another topic? Maybe you are also interested in Sweden, Science or Robots?")
            }
        }
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }

    onResponse {
        furhat.say("Sorry i didn't catch that")
        googleSheetsLog(googleSheetsIftttUrl, it.text, "Topic selection", "uncaught")
    }

}


