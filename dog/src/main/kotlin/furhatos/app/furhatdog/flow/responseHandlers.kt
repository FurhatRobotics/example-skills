package furhatos.app.furhatdog.flow

import furhatos.app.furhatdog.dog.CurrentState
import furhatos.app.furhatdog.dog.Dog
import furhatos.app.furhatdog.extensions.awakeIfAsleep
import furhatos.app.furhatdog.extensions.randomBark
import furhatos.app.furhatdog.extensions.randomGrowl
import furhatos.app.furhatdog.extensions.randomWhimpering
import furhatos.app.furhatdog.gestures.bark1
import furhatos.app.furhatdog.gestures.growl2
import furhatos.app.furhatdog.gestures.growl4
import furhatos.app.furhatdog.gestures.panting1
import furhatos.app.furhatdog.gestures.shake1
import furhatos.app.furhatdog.gestures.whimpering1
import furhatos.app.furhatdog.gestures.whimpering2
import furhatos.app.furhatdog.gestures.whimpering3
import furhatos.app.furhatdog.gestures.whimpering5
import furhatos.app.furhatdog.nlu.BadDog
import furhatos.app.furhatdog.nlu.Fetch
import furhatos.app.furhatdog.nlu.GoodDog
import furhatos.app.furhatdog.nlu.ILoveYou
import furhatos.app.furhatdog.nlu.LayDown
import furhatos.app.furhatdog.nlu.RollAround
import furhatos.app.furhatdog.nlu.SitDown
import furhatos.app.furhatdog.nlu.YouAreCute
import furhatos.flow.kotlin.RandomPolicy
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.partialState
import furhatos.gestures.Gestures.CloseEyes
import furhatos.gestures.Gestures.GazeAway
import furhatos.gestures.Gestures.OpenEyes
import furhatos.gestures.Gestures.Roll
import furhatos.gestures.Gestures.Shake
import furhatos.gestures.Gestures.Smile
import furhatos.gestures.Gestures.Thoughtful
import furhatos.nlu.common.AskName
import furhatos.nlu.common.Greeting
import furhatos.nlu.common.No
import gestures.WakeUpWithHeadShake

val ResponseHandlers = partialState {

    onResponse<Greeting> {
        Dog.increaseExcitement(10)
        Dog.increaseTiredness(8)

        when(Dog.currentState) {
            CurrentState.AWAKE -> {
                furhat.attend(it.userId)
                randomBark()
                goto(Interacting)
            }
            CurrentState.ASLEEP -> {
                furhat.gesture(WakeUpWithHeadShake)
                delay(500)
                // TODO: Look around
                furhat.attend(it.userId)
                goto(Interacting)
            }
        }

    }

    onResponse<GoodDog> {
        Dog.increaseExcitement(15)

        awakeIfAsleep(it.userId)

        random({
            furhat.gesture(Smile)
            delay(200)
            randomBark()
        }, {
            furhat.gesture(CloseEyes)
            delay(200)
            furhat.gesture(panting1)
            delay(200)
            furhat.gesture(OpenEyes)
            delay(200)
            randomBark()
        }, {
            furhat.gesture(shake1)
            delay(200)
            furhat.gesture(Smile)
            randomBark()
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<BadDog> {
        Dog.decreaseExcitement(15)

        awakeIfAsleep(it.userId)

        random({
            randomGrowl()
        }, {
            randomWhimpering()
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<No> {
        awakeIfAsleep(it.userId)

        Dog.decreaseExcitement(5)

        random({
            randomBark()
        }, {
            randomWhimpering()
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<ILoveYou> {
        awakeIfAsleep(it.userId)

        Dog.increaseExcitement(20)

        random({
            randomBark()
        }, {
            furhat.gesture(shake1)
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<YouAreCute> {
        awakeIfAsleep(it.userId)

        Dog.increaseExcitement(10)

        random({
            randomBark()
        }, {
            randomBark()
        }, {
            furhat.gesture(shake1)
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<Fetch> {
        awakeIfAsleep(it.userId)

        Dog.increaseExcitement(10)

        random({
            randomBark()
        }, {
            randomBark()
        }, {
            furhat.gesture(shake1)
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(15)
        goto(Interacting)
    }

    onResponse<AskName> {
        awakeIfAsleep(it.userId)

        Dog.increaseExcitement(10)

        random({
            randomBark()
        }, {
            furhat.gesture(shake1)
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<SitDown> {
        awakeIfAsleep(it.userId)

        Dog.increaseExcitement(10)

        random({
            furhat.gesture(Thoughtful)
            delay(100)
            randomBark()
        }, {
            furhat.gesture(shake1)
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<RollAround> {
        awakeIfAsleep(it.userId)

        Dog.increaseExcitement(10)

        random({
            furhat.gesture(Roll)
            delay(100)
            randomBark()
        }, {
            furhat.gesture(shake1)
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }

    onResponse<LayDown> {
        awakeIfAsleep(it.userId)

        Dog.increaseExcitement(10)

        random({
            furhat.gesture(GazeAway)
            delay(200)
            furhat.gesture(Shake)
            delay(200)
            randomBark()
        }, {
            furhat.gesture(shake1)
            delay(200)
            randomBark()
        }, policy = RandomPolicy.DECK_RESHUFFLE_NO_REPEAT)

        Dog.increaseTiredness(8)
        goto(Interacting)
    }
}