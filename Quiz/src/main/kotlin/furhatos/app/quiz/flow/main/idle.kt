package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.interested
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.User

val maxRounds = 3
var rounds = 0
var shouldChangeUser = true
var playing = false
var reentering = false

val Idle: State = state {
    onEntry {
        if (users.count > 0){
            if (users.list[0].quiz.interested && !users.list[0].quiz.played){
                furhat.say {
                    random{
                        +"Hello there"
                        +"Oh hi there"
                    }}
                furhat.say("It's nice to finally have someone visit me")
                if (users.count > 1){
                    furhat.attend(users.list[1])
                    furhat.say("And now i have even more than one before me")
                    furhat.gesture(Gestures.Smile)
                    delay(400)
                    furhat.attend(users.list[0])

                }
                goto(QueryPerson(users.list[0]))

            }
            else if (users.list[0].quiz.interested && users.list[0].quiz.played){
                goto(QueryPerson(users.list[0]))
            }


    }}
    onUserEnter{
            reentry()
    }

    onUserLeave(instant = true) {
        if (users.count > 0) {
            furhat.attend(users.other)
        } else {
            furhat.attendNobody()
        }
    }
}

fun QueryPerson(user: User) = state(parent = Parent) {
            onEntry {
        if (!user.quiz.played) {
            furhat.say("I hope you are doing fine. I am in the mood for a quiz.")
            delay(900)
            furhat.ask("Do you want to play one?")
        } else {
            println(user.quiz.lastScore)
            furhat.ask{random {
                +"Do you want to play again? Maybe you can beat your old score of ${user.quiz.lastScore}"
                +"Are you up for another round?  Maybe you can beat your old score of ${user.quiz.lastScore}"
            }}
        }
    }

    onResponse<Yes> {
        user.quiz.playing = true
        furhat.say("great!")
        goto(NewGame)
    }

    onResponse<No> {
        user.quiz.interested = false
        furhat.say("oh well")
        goto(Idle)
    }
    onResponse {

        furhat.say("I am sorry, i did not catch that.")
        furhat.ask("Do you want to play a quiz?")
    }
}
