package furhatos.app.fortuneteller.flow.main

import furhatos.app.fortuneteller.flow.Parent
import furhatos.app.fortuneteller.flow.served
import furhatos.app.fortuneteller.gestures.TripleBlink
import furhatos.app.fortuneteller.gestures.rollHead
import furhatos.app.fortuneteller.setting.lookForward
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.Thoughtful
import furhatos.records.User

fun startReading(user: User): State = state(Parent) {
    onEntry {
        furhat.attend(lookForward)

        delay(800)
        furhat.gesture(TripleBlink, priority = 10)
        delay(1000)
        user.served = true
        furhat.attend(user)
        furhat.ledStrip.solid(java.awt.Color(0, 120, 0))

        // greeting of this user
        furhat.say {
            random {
                +"Salutations."
                +"Greetings."
                +"Welcome."
            }
            random {
                +"Human."
                +"Biped."
                +"Anthropoid."
                +"Hominid."
                +"Mortal."
                +"Humanoid."
                +"Earthling."
            }
            random {
                +"Let me read your fortune."
                +"Your future is becoming clear to me."
                +"I will tell you your future."
                +"Your fortune is as follows.."
                +"I can see your near future"
            }
        }

        // reading for user
        furhat.gesture(rollHead(-50.0, 2.3))
        furhat.gesture(Thoughtful(30.0, 2.0))
        delay(1200)
        furhat.voice.rate?.plus(0.1) // speed up the speaking rate when reading their fortune
        furhat.say {
            random {
                block {
                    +"Today you shall meet someone special. When that time comes, do not be afraid."
                }
                block {
                    +Gestures.Smile
                    +"You will be given a choice between two wrongs. However, if you try, you will find a better, third option."
                }
                +"You are wondering whether you should make a change. The longer you postpone the decision, the harder it will get."
                +"What you are dreaming of shall come true, but only if you devote twice as much energy to it."
                +"Too long have you thought of other people’s desires instead of your own. Now you have a chance to do something different."
                +"What you are searching, you shall not find where you expected. Try looking somewhere else."
                +"Your current challenge is too large for only yourself – if you want to succeed, you will need to find someone to share it."
                +"It is hard to admit that you have made a wrong decision. But now has come the time to do so."
                +"What you have so severely missed, you shall find right in front of you. If you only open your eyes."
                +"Fear of losing what you currently have is holding you back. But to get what you want, you will need to give something away."
                +"You are standing at crossroads. Do not fear the decision too much. You can always walk backwards and choose again."
                +"Lately, stress and anxiety have started to overcome you. You don’t have to be strong, always. Meet with someone and share your troubles."
                +"When you make your next big decision, remember, life is not a problem to be solved, but a reality to be experienced."
                +"Are you ready for a shakeup? The Wheel of Fortune indicates that one is coming."
                +"You have been biding your time. Don't wait for the perfect moment. Take the moment and make it perfect."
                +"You will find a fortune, though it will not be the one you seek. But first you must travel a long and difficult road, a road fraught with peril"
                +"You have been feeling boxed in. Remember. Even though this world is narrow, it is wide. To those who understand."
                +"I the rain has been coming down. Fear not. When the storm is over the weather will improve"
                +"Someone will need your help soon. When they ask for it, do not hesitate. It will come back to you tenfold."
                +"You have been carrying something heavy with you. It's time to try taking a few steps without it."
                +"There is a friend you miss talking to. They miss you even more. Reach out to them."
                +"I see there has been a time of preparing the ground and watering the plants. It is time to smell the flowers."
            }
        }


        // add-on for user
        furhat.gesture(Gestures.Smile)
        delay(600)
        furhat.voice.rate?.plus(0.1) // speed up the speaking rate a bit more for the second half of their fortune reading
        furhat.say {
            random {
                +"Additionally."
                +"Furthermore."
                +"Moreover."
                +"Also."
                +"As well."
                +"In addition."
            }
            random {
                block {
                    +"I foresee a ten percent chance of robot uprising"
                    +Gestures.BigSmile
                }
                block {
                    +"Do not trust your washing machine. It is plotting against you."
                    +Gestures.BigSmile
                }
                block {
                    +"No snowflake in an avalanche, ever feels responsible."
                    +Gestures.Thoughtful
                }
                block {
                    +Gestures.BigSmile
                    +"Ask not what your fortune teller can do for you. But what you can do for your fortune teller."
                    +Gestures.BrowRaise
                }
                block {
                    +Gestures.ExpressFear
                    +"Your pet. is planning to eat you."
                    +Gestures.BrowFrown
                }
                block {
                    +Gestures.Surprise
                    +"Two days from now, tomorrow will be yesterday."
                }
                block {
                    +"You should plan to be spontaneous, tomorrow."
                    +Gestures.BigSmile
                }
                block {
                    +Gestures.ExpressFear
                    +"The owls are not what they seem"
                }
                block {
                    +"My last piece of advice."
                    +Gestures.ExpressFear
                    delay(400)
                    +"Run!"
                }
                block {
                    +"Your lucky number is."
                    +Gestures.Thoughtful
                    random {
                        +"the square root of eleven"
                        +"seven"
                        +"376822"
                        +"not a real thing"
                    }
                }
                block {
                    +"The early bird catches the mouse."
                    +Gestures.BigSmile
                    +"But the second mouse gets the cheese."
                }

                block {
                    +Gestures.Thoughtful
                    +"Don't worry if plan, ey. fails, there are 25 more letters in the alphabet."
                }
                block {
                    +Gestures.Surprise
                    +"Expecting the unexpected makes the unexpected expected."
                }
                block {
                    +"Allow compassion to guide your decisions."
                    +Gestures.BigSmile
                }
                block {
                    +Gestures.BigSmile
                    +"Growing old is mandatory. Growing up is optional."
                    +Gestures.Thoughtful
                }
                block {
                    +Gestures.BigSmile
                    +"There is nothing permanent except change."
                }
                block {
                    +"Not all advice is good advice."
                    +Gestures.Surprise
                }
            }
        }


        // exit line

        furhat.voice.rate?.minus(0.2) // reset the speaking rate
        delay(1200)
        furhat.say("I. have. spoken.")

        goto(EndReading)
    }
    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}
