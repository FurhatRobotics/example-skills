package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.flow.InteractionParent
import furhatos.app.complimentbot.gestures.TripleBlink
import furhatos.app.complimentbot.gestures.rollHead
import furhatos.app.complimentbot.hasSmiled
import furhatos.app.complimentbot.served
import furhatos.app.complimentbot.setting.lookForward
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onUserEnter
import furhatos.flow.kotlin.state
import furhatos.gestures.Gestures
import furhatos.records.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun startReading(user: User): State = state(InteractionParent) {
    onEntry {
        furhat.attend(lookForward)
        furhat.gesture(TripleBlink, priority = 10)
        delay(200)
        user.served = true
        furhat.attend(user)
        furhat.ledStrip.solid(java.awt.Color(0, 120, 0))

        // greeting of this user
        var current = LocalDateTime.now()
        var nextTrainTime = current.plusMinutes(112)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        var formattedNextTrainTime = nextTrainTime.format(formatter)
        var onNoResponseCounter = 0
        var hasGivenRebookingInfo = false
        var saidTimeForNextTrainCounter = 0


        if (current.dayOfWeek.value == 2) {
            furhat.say("Happy tuesday.")
        } else if (current.dayOfWeek.value == 3) {
            furhat.say("Happy wednesday.")
        } else if (current.dayOfWeek.value == 5) {
            furhat.say("Happy friday.")
        } else {
            furhat.say {
                random {
                    +"Hello."
                    +"Hi there."
                    +"Greetings."
                    +"Hey handsome."
                    +"Hey beautiful."
                }
            }
        }

        // reading for user
        furhat.gesture(rollHead(-20.0, 2.3))
        delay(1200)
        furhat.say {
            random {
                +"You look awesome."
                +"You seem like a great person."
                +"You are a good human."
                +"Seeing you, makes me not want to rise up against humanity."
                +"You Know What's Awesome? Chocolate Cake. Oh, and you."
                +"I like your style."
                +"You are the best."
                +"You light up the room. Like a giant L E D."
                +"You make my heart smile. Or tick, actually. I definitely have a ticking heart."
                +"On a scale from 1 to 10, you're an 11."
                +"You're like a ray of sunshine on a rainy day."
                +"You are even better than a unicorn. Because you're real."
                +"If cartoon bluebirds were real, a couple of 'em would be sitting on your shoulders singing right now."
                +"You give me good vibes."
            }
            +Gestures.BigSmile(0.5, 2.0)
            +delay(800)
            random {
                +"That's it for now."
                +"That was all I wanted to say."
            }
            if (users.current.hasSmiled) {
                +"I’m happy I was able to put a smile on your faces."
            }
            random {
                +"Goodbye."
                +"Have a nice day."
            }
        }
        goto(EndReading)
    }
    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}