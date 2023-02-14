package furhatos.app.complimentbot.flow.main

import furhatos.app.complimentbot.utils.hasBeenComplimented
import furhatos.app.complimentbot.utils.hasBeenGreeted
import furhatos.app.complimentbot.utils.hasBeenGreetedGoodbye
import furhatos.app.complimentbot.utils.hasSmiled
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.gestures.Gestures
import furhatos.records.User
import java.time.LocalDateTime

fun FlowControlRunner.greetUser(user: User) {
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
    user.hasBeenGreeted = true
}

fun FlowControlRunner.complimentUser(user: User) {
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

fun FlowControlRunner.greetUserGoodbye(user: User) {
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