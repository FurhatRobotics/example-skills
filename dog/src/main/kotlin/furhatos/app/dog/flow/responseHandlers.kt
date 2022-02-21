package furhatos.app.dog.flow

import furhatos.app.dog.dog.CurrentState
import furhatos.app.dog.dog.Dog
import furhatos.app.dog.extensions.awakeIfAsleep
import furhatos.app.dog.extensions.randomBark
import furhatos.app.dog.extensions.randomGrowl
import furhatos.app.dog.extensions.randomWhimpering
import furhatos.app.dog.gestures.panting1
import furhatos.app.dog.gestures.shake1
import furhatos.app.dog.nlu.BadDog
import furhatos.app.dog.nlu.Fetch
import furhatos.app.dog.nlu.GoodDog
import furhatos.app.dog.nlu.ILoveYou
import furhatos.app.dog.nlu.LayDown
import furhatos.app.dog.nlu.RollAround
import furhatos.app.dog.nlu.SitDown
import furhatos.app.dog.nlu.YouAreCute
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