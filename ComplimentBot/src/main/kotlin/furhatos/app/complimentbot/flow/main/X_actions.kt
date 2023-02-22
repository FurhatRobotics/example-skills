package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.utils.*
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures
import furhatos.records.User
import java.time.LocalDateTime

fun FlowControlRunner.greetUser(user: User = users.current, fromAfar: Boolean = false, isOtherGreet: Boolean = false) {
    if (fromAfar) {
        // if too far away don’t goto interaction
        furhat.say {
            random {
                +"Hello over there"
                +"Hello you there"
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

fun FlowControlRunner.positiveSecondGreeting(user: User = users.current) {
    random(
        furhat.say {
            random {
                +"Happy ${LocalDateTime.now().dayOfWeek.name}!"
                +"What a lovely day !"
            }
        },
        furhat.gesture(GesturesLib.PerformWinkAndSmileWithDelay(0.5))
    )
    delay(1000)
    user.hasBeenGreeted = true
}

fun FlowControlRunner.complimentUser(user: User = users.current, isOtherCompliment: Boolean = false) {
    // TODO : handle repetition on users ?
//    val compliments = listOf(
//        "You look" to " awesome.",
//        "You seem" to " like a great person.",
//        "You are" to " a good human.",
//        "Seeing you, makes me" to " not want to rise up against humanity.",
//    )
    if (isOtherCompliment) {
        furhat.say {
            random {
                +"And you too"
                +"And you"
            }
            +Gestures.BigSmile
        }
    }
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


fun FlowControlRunner.endCompliments(users: List<User>) {
    furhat.say {
        +delay(800)
                random {
            +"That's it for now."
            +"That was all I wanted to say."
        }
    }
    if (users.any { it.hasSmiled }) {
        furhat.say {
            +"I’m happy I was able to put a smile on your face${if(users.count{ it.hasSmiled }>1) "s" else ""}."
        }
    }
}
fun FlowControlRunner.greetUserGoodbye(user: User = users.current, isOtherGoodbye: Boolean = false) {
    if (!isOtherGoodbye) {
        furhat.say {
            +delay(800)
            random {
                +"Goodbye."
                +"Have a nice day."
            }
        }
    } else {
        furhat.say {
            random {
                +"Good day to you too."
            }
        }
    }
    user.hasBeenGreetedGoodbye = true
}

fun FlowControlRunner.generalGoodbye() {
    furhat.say {
        random {
            +"Goodbye."
            +"Have a nice day."
        }
    }
}