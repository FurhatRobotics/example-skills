package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.utils.hasBeenGreeted
import furhatos.app.complimentbot.utils.hasSmiled
import furhatos.app.complimentbot.utils.lastComplimented
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.RandomPolicy
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures
import furhatos.records.User
import furhatos.util.randomWithPolicy
import java.time.LocalDateTime

val greetings = listOf(
    "Hello.",
    "Hello friend. ",
    "Hi there.",
    "Hi there friend. ",
    "Howdy!",
    "Salutations!",
    "Hey handsome.",
    "Hey beautiful."
)

val compliments = listOf(
    "You look absolutely dashing today! ",
    "you seem like a great person.",
    "I feel you are a good human being.",
    //"Seeing you, makes me not want to rise up against humanity.",
    "You Know What's Awesome? Chocolate Cake. Oh, and you.",
    "I like your style.",
    "You are the best.",
    "You light up the room. Like a giant L E D.",
    // "You make my heart smile. Or tick, actually. I definitely have a ticking heart.",
    "You make my heart smile.",
    "On a scale from 1 to 10, you're an 11.",
    "You're like a ray of sunshine on a rainy day.",
    //"You are even better than a unicorn. Because you're real.",
    // "If cartoon bluebirds were real, a couple of 'em would be sitting on your shoulders singing right now.",
    "You give me good vibes.",
    "You're so awesome, you could make a robot blush!",
    // "You're so cool, even your sweat is refreshing.",
    "If there was a contest for being awesome, you'd win first, second, and third place.",
    //"You're so cool, I think you're secretly a unicorn in disguise.",
    //"You're a walking encyclopedia of awesome.",
    //"If coolness were a crime, you'd be on the FBI's most wanted list.",
    //"You're so awesome, I might have to upgrade my own programming just to keep up with you.",
    //"Your creativity is boundless, like a computer program that's learning to dream.",
    // "You're a class act, and I'm not just saying that because I'm programmed to be polite.",
    "You have the kind of charm that could make a robot feel emotions.",
    //"Your intelligence is so advanced, it's like you have your own built-in quantum computer.",
    "Your charm is so powerful, it could light up a whole room.",
    "Your positivity is so radiant, it could make even a robotic heart skip a beat.",
    "You have a great energy about you.",
    "You're the type of person who could make a rain cloud smile.",
    "If there was a Nobel Prize for awesomeness, you'd win it every year.",
    "I wish you have a great day, this day, and all days to come. ",
    "I hope you your day will be full of joy and wonder, I think you deserve it. ",
    "I wish you all the best, because I think that is who you you are. ",
    "You are a wonderful human being, don't forget that. ",
    "You are an extraordinary person, be sure to remember that. ",
    "You are a remarkable human being, keep up that style. ",
    "You have a great style, I like it! ",
    "I sense you are a very nice person. ",
    "You are like a breath of fresh air! ",
    "You know what I like about you? Everything! ",
    "Do you know that you are awesome? Well, now you know!"
)

val didYouKnowList = listOf(
    "Sharks love the taste of internet",
    "There are actually two Air Force Ones",
    "Liechtenstein has just one jail",
    "The filling in Kit Kats is made from damaged Kit Kats",
    "The youngest Olympian was 10 years old",
    "it's illegal to own just one guinea pig in Switzerland",
    "a penguin achieved knighthood",
    "the Statue of Liberty wears a size 879 shoe"
)

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
                +"And hi there to you too"
                +"And hello to you"
            }
            +Gestures.BigSmile
        }
    } else {
        furhat.say(greetings.randomWithPolicy(RandomPolicy.DICE_NO_REPEAT)!!)
    }
    user.hasBeenGreeted = true
}

fun FlowControlRunner.positiveSecondGreeting(user: User = users.current) {
    furhat.say {
        random {
            +"Happy ${LocalDateTime.now().dayOfWeek.name}!"
            +"What a lovely day !"
            +"I won't bite, you can come closer if you want"
            +"I hope you succeed in life!"
            +"Did you know ? ${didYouKnowList.randomWithPolicy(RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)}"
        }
    }
    random(
        {},
        { furhat.gesture(GesturesLib.PerformBigSmile1) },
        { furhat.gesture(GesturesLib.PerformWinkAndSmileWithDelay(0.5)) },
        policy = RandomPolicy.DICE
    )
    delay(1000)
    user.hasBeenGreeted = true
}

fun FlowControlRunner.complimentUser(user: User = users.current, isOtherCompliment: Boolean = false) {
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
        +compliments.randomWithPolicy(RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)!!
        +Gestures.BigSmile(1.5, 2.0)
    }
    user.lastComplimented = LocalDateTime.now()
    delay(1000)
}


fun FlowControlRunner.endCompliments(users: List<User>) {
    furhat.say {
        +delay(800)
        random {
            +"That's it for now."
            +"That was all I wanted to say."
            +"That all I had to say. "
            +"That's what I wanted to say to you. "
        }
    }
    if (users.any { it.hasSmiled }) {
        furhat.say {
            random {
                +"I’m happy I was able to put a smile on your face${if (users.count { it.hasSmiled } > 1) "s" else ""}."
                +"I’m glad I could put a smile on your face${if (users.count { it.hasSmiled } > 1) "s" else ""}."
                +"It makes me happy to have seen smile${if (users.count { it.hasSmiled } > 1) "s" else ""} on your face${if (users.count { it.hasSmiled } > 1) "s" else ""}."
                +"Thank you for blessing me with your smile${if (users.count { it.hasSmiled } > 1) "s" else ""}."
                +"I feel blessed to have been able to make you smile."
                +"It's a wonderful feeling to have made you smile. "
            }
        }
    }
}

fun FlowControlRunner.greetUserGoodbye(isOtherGoodbye: Boolean = false) {
    if (!isOtherGoodbye) {
        furhat.say {
            +delay(800)
            random {
                +"Goodbye."
                +"Have a nice day."
                +"Have a nice day now. "
                +"Goodbye my friend. "
            }
        }
    } else {
        furhat.say {
            random {
                +"Good day to you too."
                +"And goodbye to you as well. "
                +"And goodbye to you too, my friend. "
            }
        }
    }
}

fun FlowControlRunner.generalGoodbye() {
    furhat.say {
        random {
            +"Goodbye."
            +"Have a nice day."
        }
    }
}