package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users
import furhatos.flow.kotlin.voice.AzureVoice
import furhatos.gestures.Gestures
import furhatos.records.User
import java.time.LocalDateTime

fun FlowControlRunner.greetUser(user: User = users.current, fromAfar: Boolean = false, isOtherGreet: Boolean = false) {
    if (fromAfar) {
        furhat.say {
            random {
                +voice!!.style("Hello over there", AzureVoice.Style.SHOUTING) // if too far away don’t goto interaction
                +voice!!.style("Hello you there", AzureVoice.Style.EXCITED) // if too far away don’t goto interaction
            }
        }
    } else if (isOtherGreet) {
        furhat.say {
            random {
                +"And hello there to you too"
                +"And hello to you"
            }
            +Gestures.BigSmile
        }
    } else {
        furhat.say {
            random {
                +"Happy ${LocalDateTime.now().dayOfWeek.name}!"
                +"Hello."
                +"Hi there."
                +"Greetings."
                +"Hey handsome."
                +"Hey beautiful."
            }
        }
    }
    user.hasBeenGreeted = true
}
fun FlowControlRunner.greetUserAttention(user: User = users.current, fromAfar: Boolean = false) {
    greetUser(user, fromAfar)
    user.isBeingEngaged = true
}

fun FlowControlRunner.complimentUser(user: User = users.current) {
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
    }
    user.hasBeenComplimented = true
}

fun FlowControlRunner.greetUserGoodbye(user: User = users.current) {
    furhat.say {
        +delay(800)
        random {
            +"That's it for now."
            +"That was all I wanted to say."
        }

        //TODO : should move out ?
        if (user.hasSmiled) {
            +"I’m happy I was able to put a smile on your face."
        }
        random {
            +"Goodbye."
            +"Have a nice day."
        }
    }
    user.hasBeenGreetedGoodbye = true
}