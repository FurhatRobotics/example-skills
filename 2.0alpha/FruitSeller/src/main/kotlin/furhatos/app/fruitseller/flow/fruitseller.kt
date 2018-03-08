package furhatos.app.fruitseller.flow

import furhatos.app.fruitseller.nlu.BuyFruit
import furhatos.app.fruitseller.nlu.Fruit
import furhatos.app.fruitseller.nlu.RequestOptions
import furhatos.app.fruitseller.order
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*
import furhatos.util.Language

val Start = state(Interaction) {
    onEntry {
        random(
            {   furhat.say("Hi there") },
            {   furhat.say("Oh, hello there") }
        )

        goto(TakingOrder)
    }
}

val TakingOrder = state(Interaction) {
    onEntry {
        random(
            { furhat.ask("How about some fruits?") },
            { furhat.ask("Do you want some fruits?") }
        )
    }

    onReentry {
        random(
                { furhat.ask("We have very good fruits today") },
                { furhat.ask("Want some?") }
        )
    }

    onResponse<RequestOptions> {
        furhat.say("We have ${Fruit().getEnum(Language.ENGLISH_US).joinToString(", ")}")
        furhat.ask("Do you want some?")
    }

    onResponse<BuyFruit> {
        furhat.say("${it.intent.fruits?.text}, what a lovely choice!")
        it.intent.fruits?.list?.forEach {
            users.current.order.fruits.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onResponse<Yes> {
        random(
            { furhat.ask("What kind of fruit do you want?") },
            { furhat.ask("What type of fruit?") }
        )
    }

    onResponse<No> {
        furhat.say("Okay, here is your order of ${users.current.order.fruits}. Have a great day!")
        goto(Idle)
    }
}