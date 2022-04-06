package furhatos.app.quiz.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures


// End of game, announcing results
val EndGame: State = state(parent = Parent) {
    var skipNext = false

    onEntry {
        playing = false

        // If multiple players, we rank the players on points and congratulate the winner. A special case is when we have a tie.
        if (users.playing().count() > 1) {
            // Sort users by score
            users.playing().sortedByDescending {
                it.quiz.score
            }.forEachIndexed { index, user ->
                // The winner(s)
                if (index == 0) {
                    val highestScore = user.quiz.score
                    val usersWithHighestScore = users.playing().filter {
                        it.quiz.score == highestScore
                    }
                    // Check if we have more than one user with the highest score and if so announce a tie
                    if (usersWithHighestScore.count() > 1) {
                        furhat.say("Wow, we have a tie! You each have $highestScore points", async = true)
                        delay(500)
                        usersWithHighestScore.forEach {
                            furhat.attend(it)
                            delay(700)
                        }
                        // If a tie, we skip the next user since we've already adressed them above
                        skipNext = true
                    }
                    // A single winner exists
                    else {
                        furhat.attend(user)
                        furhat.say("Congratulations! You won with ${user.quiz.score} points")
                    }
                }
                // Every other subsequent player
                else {
                    if (!skipNext) {
                        skipNext = false
                        furhat.attend(user)
                        furhat.say("And you got ${user.quiz.score} points")
                    }
                }
                delay(300)
            }
        }
        // Only one player, we simply announce the score
        else {
            furhat.say("You got ${users.current.quiz.score} points.")

        }
        if (users.current.quiz.played){
            if (users.current.quiz.score < users.current.quiz.lastScore){
                furhat.say{
                    random {
                        +"looks like these questions were too hard. Maybe next time, your score can be higher again!"
                        +"You held back, didn't you?"
                        +"Oh no. Next time, i will go easy on you. Then your score will look good again"
                        +"I know, you can do better than this."
                        // TODO: GESTURES
                    }
                }
            }
            else if (users.current.quiz.score == users.current.quiz.lastScore){
                furhat.say("Well... There was no improvement this time. Same score again")
                // TODO: Gestures
            }
            else if (users.current.quiz.score > users.current.quiz.lastScore){
                furhat.say{
                    random {
                        +"good job, beating your old score!"
                        +"I knew, you could do this. You have cracked your old score!"
                        +"Next time i won't go easy on you. Your score is a little bit too high for my taste."
                    }
                }
                furhat.gesture(Gestures.Wink)
            }

        }

        furhat.say("Thanks for playing!")

        // Resetting user state variables
        users.playing().forEach {
            it.quiz.playing = false
            it.quiz.played = true
            it.quiz.lastScore = it.quiz.score
            it.quiz.score = 0
        }

        delay(1000)

        goto(Idle)
    }
}