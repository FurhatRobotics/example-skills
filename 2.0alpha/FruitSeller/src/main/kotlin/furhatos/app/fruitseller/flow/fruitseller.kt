package furhatos.app.fruitseller.flow

import furhatos.app.fruitseller.nlu.BuyFruit
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val Start = state(Interaction) {
    onEntry {
        furhat.say("Hi there")
        goto(TakingOrder)
    }
}

val TakingOrder = state(Interaction) {
    onEntry {
        random(
            { furhat.ask("What kind of fruit do you want?") },
            { furhat.ask("Do you want some fruits?") }
        )
    }

    onResponse<BuyFruit> {
        furhat.say("${it.intent.fruit?.text}, what a lovely choice!")
        furhat.ask("Anything else?")
    }

    onResponse<Yes> {
        reentry() // Since this (likely) is an answer to "Do you want some fruits?"
    }

    onResponse<No> {
        furhat.say("Okay, have a great day!")
        goto(Idle)
    }

}