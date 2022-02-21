package furhatos.app.furhatdog.extensions

import furhatos.app.furhatdog.dog.CurrentState
import furhatos.app.furhatdog.dog.Dog
import furhatos.app.furhatdog.gestures.*
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.RandomPolicy
import furhatos.flow.kotlin.furhat
import gestures.WakeUpWithHeadShake

fun FlowControlRunner.setDogCharacter() {
    furhat.setModel("pug", "default")
}

fun FlowControlRunner.randomBark() {
    random(
            { furhat.gesture(bark1, async = false) },
            { furhat.gesture(bark2, async = false) },
            { furhat.gesture(bark3, async = false) },
            policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT
    )
}

fun FlowControlRunner.randomWhimpering() {
    random({
        furhat.gesture(whimpering1, async = false)
    }, {
        furhat.gesture(whimpering2, async = false)
    }, {
        furhat.gesture(whimpering3, async = false)
    }, {
        furhat.gesture(whimpering5, async = false)
    }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)
}

fun FlowControlRunner.randomGrowl() {
    random({
        furhat.gesture(growl2, async = false)
    }, {
        furhat.gesture(growl4, async = false)
    }, {

    }, {

    }, {

    }, {

    }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)
}

fun FlowControlRunner.awakeIfAsleep(userId: String) {
    when(Dog.currentState) {
        CurrentState.AWAKE -> {
            furhat.attend(userId)
        }
        CurrentState.ASLEEP -> {
            furhat.gesture(WakeUpWithHeadShake)
            delay(500)
            furhat.attend(userId)

        }
    }
}