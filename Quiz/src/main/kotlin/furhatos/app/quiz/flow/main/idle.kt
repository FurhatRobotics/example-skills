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
        /*
            Loop through all (potentially) interested users.
            Goto calls are used since users may enter and leave
            while we are querying other users and we want to
            ask all users before moving on. I.e we want to update the
            users.interested() list of users.
          */
        /*users.interested().forEach {
            furhat.attend(it)
            goto(QueryPerson(it))
        }
        // Once no more user, start the game with all interested users
        if (users.playing().isNotEmpty()) {
            furhat.attendAll()
            goto(NewGame)
        }*(
    }

    onUserEnter(instant = true) {
        /* Say hi and query a new user if it's the only interested user.
            Other interested users would already be greeted at this point.
            If not, we glance at the user and keep doing whatever we were doing.
         */
        if (users.interested().count() == 1) {
            furhat.attend(it.id)
            furhat.say {
                random{
                    +"Hello there"
                    +"Oh hi there"
                }
            }
            furhat.say("It's nice to finally have someone visit me")
            goto(QueryPerson(it))

        } else {
            furhat.glance(it.id, async=true)
        }
    }

    onUserLeave(instant = true) {
        if (users.count > 0) {
            furhat.attend(users.other)
        } else {
            furhat.attendNobody()
        }
    }

    onResponse{
        reentry()
    }

    onNoResponse {
        reentry()
    }
}

// Variables


*/
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
