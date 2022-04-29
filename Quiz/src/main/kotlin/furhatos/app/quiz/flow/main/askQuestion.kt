package furhatos.app.quiz.flow.main

import furhat.libraries.standard.AutomaticHeadMovements.RandomHeadMovements
import furhat.libraries.standard.GesturesLib
import furhatos.app.quiz.AnswerOption
import furhatos.app.quiz.DontKnow
import furhatos.app.quiz.RequestRepeatOptions
import furhatos.app.quiz.RequestRepeatQuestion
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.flow.customGestures.awaitAnswer
import furhatos.app.quiz.questions.*
import furhatos.app.quiz.setting.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.RequestRepeat
import org.eclipse.jetty.server.Authentication.User

val AskQuestion: State = state(parent = Parent) {

    var failedAttempts = 0

    onEntry {
        failedAttempts = 0

        // Set speech rec phrases based on the current question's answers
        furhat.setSpeechRecPhrases(QuestionSet.current.speechPhrases)

        // Ask the question followed by the options
        furhat.say(QuestionSet.current.text + " ${furhat.voice.pause("500ms")} ")
        var answeroptions = QuestionSet.current.getOptionsString().replace(" or ", " , ").split(",")
        if (answeroptions.size >2) {
        furhat.say(answeroptions[0])
        delay(200)
        furhat.say(answeroptions[1])
        delay(200)
        furhat.ask("or " + answeroptions[2], timeout = 12000)
    }
        else{
            furhat.say(answeroptions[0])
            delay(200)
            furhat.say("or")
            delay(200)
            furhat.ask(answeroptions[1], timeout = 12000)
        }
    }

    // Here we re-state the question
    onReentry {
        failedAttempts = 0
        furhat.ask("The question was, ${QuestionSet.current.text} ${QuestionSet.current.getOptionsString()}")
    }

    // User is answering with any of the alternatives
    onResponse<AnswerOption> {
        val answer = it.intent

        // If the user answers correct, we up the user's score and congratulates the user
        if (answer.correct) {
            furhat.gesture(Gestures.Smile)
            users.current.quiz.score++
            furhat.say("This answer was")
            delay(600)
            furhat.ledStrip.solid(java.awt.Color(0,15,0))
            furhat.say("correct")
            furhat.say{
                random {
                    +"Congratulation!"
                    +"Good job."
                    +"Well done."
                }
            }
            random(
                {furhat.gesture(GesturesLib.ohYeah1)},
                {furhat.gesture(GesturesLib.SmileRandom())}
            )
            delay(500)
            furhat.ledStrip.solid(java.awt.Color(0,0,0))
            if (QuestionSet.current.funfact != ""){
            delay(1000)
            furhat.say(QuestionSet.current.funfact)
            delay(1000)}
            furhat.say{
            random{
                + "You now have a score of ${users.current.quiz.score}"
                + "Your current score is ${users.current.quiz.score} points"
                + "You are now at ${users.current.quiz.score} points"
            }}


            /*
            If the user answers incorrect, we give another user the chance of answering if one is present in the game.
             */
        } else {
            furhat.say("This answer was")
            delay(500)
            furhat.gesture(Gestures.BrowFrown)
            furhat.ledStrip.solid(java.awt.Color(15,0,0))
            furhat.say("sadly not correct")
            random(
                {furhat.gesture(GesturesLib.sad1)},
                {furhat.gesture(GesturesLib.mouthSide1)},
                {furhat.gesture(GesturesLib.what1)}
            )


            // Keep track of what users answered what question so that we don't ask the same user
            users.current.quiz.questionsAsked.add(QuestionSet.current.text)

            /* Find another user that has not answered this question and if so, asks them.
             For the flow of the skill, we will continue asking the new user the next question through the
             shouldChangeUser = false flag.
             */
            val availableUsers = users.notQuestioned(QuestionSet.current.text)
            if (!availableUsers.isEmpty()) {
                furhat.attend(availableUsers.first())
                shouldChangeUser = false
                furhat.ask("Maybe you know the answer?")}

            delay(600)
            furhat.say("The correct answer would have been: ${QuestionSet.current.rightanswer}")
            delay(250)
            furhat.ledStrip.solid(java.awt.Color(0,0,0))
        }

        // Check if the game has ended and if not, goes to a new question
        if (++rounds >= maxRounds) {
            furhat.say("That was the last question")
            goto(EndGame)
        }
        else if (rounds ==  maxRounds-1){
            furhat.say{
                random {
                    + "We are now heading to the last question"
                    + "Prepare for the last question"
                    + "Only one question left"
                }
            }
            delay(500)
            goto(NewQuestion)
        }
        else {
            delay(900)
            furhat.say{
                random {
                    + "Here comes the next question"
                    + "Let's proceed"
                    + "Let's go to the next round"
                }
            }
            delay(500)
            goto(NewQuestion)
        }
    }

    // The users answers that they don't know
    onResponse<DontKnow> {
        ++rounds
        furhat.say{
            random{
                +"too bad."
                +"That is unfortunate to hear"
                +"Looks like this question was too hard for you."
                +"Oh no"
            }}
        delay(600)
        furhat.say("The correct answer would have been: ${QuestionSet.current.rightanswer}")
        furhat.gesture(awaitAnswer)
        if (rounds == maxRounds){
            furhat.say("looks like you have reached the maximum amount of questions")
            goto(EndGame)

        }
        furhat.say("Let's proceed")
        goto(NewQuestion)
    }

    onResponse<RequestRepeat> {
        reentry()
    }

    onResponse<RequestRepeatQuestion> {
        furhat.gesture(Gestures.BrowRaise)
        furhat.ask(QuestionSet.current.text)
    }

    // The user wants to hear the options again
    onResponse<RequestRepeatOptions> {
        furhat.gesture(Gestures.Surprise)
        random(
                { furhat.ask("They are ${QuestionSet.current.getOptionsString()}") },
                { furhat.ask(QuestionSet.current.getOptionsString()) }
        )
    }

    // If we don't get any response, we assume the user was too slow
    onNoResponse {
        furhat.say{
            random{
                +"Too slow! Here comes the next question"
                +"A bit too slow amigo! Get ready for the next question"
                +"Looks like the question was too hard. Let's go to the next one"}}
        goto(NewQuestion)
    }

    /* If we get a response that doesn't map to any alternative or any of the above handlers,
        we track how many times this has happened in a row and give them two more attempts and
        finally moving on if we still don't get it.
     */
    onResponse {
        failedAttempts++
        googleSheetsLog(googleSheetsIftttUrl, it.text, QuestionSet.topicname + QuestionSet.current.text, "uncaught on try $failedAttempts")
        when (failedAttempts) {
            1 -> furhat.ask("I didn't get that, sorry. Try again!")
            2 -> {
                furhat.say("Sorry, I still didn't get that")
                furhat.ask("The options are ${QuestionSet.current.getOptionsString()}")
            }
            else -> {
                furhat.say("Still couldn't get that. Maybe this question was too hard for you")
                furhat.say("The correct answer would have been: ${QuestionSet.current.rightanswer}")
                furhat.say("Let's try a new question")
                shouldChangeUser = false
                googleSheetsLog(googleSheetsIftttUrl, "", "", "")
                goto(NewQuestion)

            }
        }
    }
    onUserLeave {
        if (users.playing().isEmpty()) {
            furhat.say("Ok goodbye then. See you next time!")
            QuestionSet.shuffle()
            goto(Idle)
        }
        else{
                val nextUser = users.nextPlaying()
                furhat.attend(nextUser)
            furhat.say("You get to continue")

            }
        }

}


val NewQuestion = state(parent = Parent) {
    onEntry {
        /*
            If more than one player, we determine what user to target next here, based on the shouldChangeUser boolean
         */
        if (users.playing().count() > 1) {
            if (shouldChangeUser) {
                val nextUser = users.nextPlaying()
                furhat.attend(nextUser)
                random(
                        { furhat.say("The next one is for you") },
                        { furhat.say("For you now") },
                        { furhat.say("Now for you") }
                )
            } else {
                shouldChangeUser = true
                random(
                        { furhat.say("You get to continue") },
                        { furhat.say("Next one coming up") },
                        { furhat.say("Here's another one") }
                )
            }
        }
        if (!users.current.isAttendingFurhat) {
            furhat.say {
                random {
                    block {
                        +"But then I do want you to pay attention"
                        +Gestures.BigSmile
                    }
                    +"Look at me, I'm captain now"
                    +"Could you pay some attention to me"
                }
            }
        }
        // Ask new question
        if (QuestionSet.currenttopic.isNullOrEmpty()){
            furhat.say("Oh no, you have triggered a special event. Me not knowing any more questions!")
            goto(Idle)
        }
        QuestionSet.next()
        goto(AskQuestion)
    }
}