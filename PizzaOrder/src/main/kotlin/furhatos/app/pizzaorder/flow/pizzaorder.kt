package furhatos.app.pizzaorder.flow

import furhatos.app.pizzaorder.*
import furhatos.app.pizzaorder.nlu.*
import furhatos.event.Event
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

class TellToppingOptions : Event()
class TellDeliveryOptions : Event()
class TellOpeningHours : Event()

/*
    General enquiries that we want to be able to handle. The reason why we define events above
    and then raise() events that we catch is that we want to be able to reuse these answers
    in substates. For example, the user might as "what are your opening hours" both as an initial
    question and after getting then question "when do you want it delivered?".
 */
val General: State = state(Interaction) {
    onResponse<RequestOpen> {
        raise(TellOpeningHours())
    }

    onEvent<TellOpeningHours> {
        furhat.say("We are open between 7 am and 8 pm")
        reentry()
    }

    onResponse<RequestPlace> {
        raise(TellDeliveryOptions())
    }

    onEvent<TellDeliveryOptions> {
        furhat.say("We can deliver to your home and to your office")
        reentry()
    }

    onResponse<RequestToppingOptions> {
        raise(TellToppingOptions())
    }

    onEvent<TellToppingOptions> {
        furhat.say("We have " + Topping().optionsToText())
        reentry()
    }

    onResponse<OrderPizza> {
        furhat.say("Okay, you want ${it.intent}")
        users.current.order.adjoin(it.intent)
        goto(CheckOrder)
    }

}

// State with handlers only applicable once an order has been made.
val OrderHandling: State = state(parent = General) {
    onResponse<RemoveTopping> {
        users.current.order.topping?.removeFromList(it.intent?.topping)
        furhat.say("Okay, we remove ${it.intent?.topping} from your pizza")
        reentry()
    }
}

// Form-filling state
val CheckOrder = state {
    onEntry {
        if (users.current.order.deliverTo == null) {
            goto(RequestDelivery)
        } else if (users.current.order.deliveryTime == null) {
            goto(RequestTime)
        } else if (users.current.order.topping == null) {
            goto(RequestTopping)
        } else {
            goto(ConfirmOrder)
        }
    }

}

// Start of interaction
val Start = state(parent = General) {
    onEntry {
        furhat.ask("Welcome to Pizza house. How may I help you?")
    }
}

// Request delivery point
val RequestDelivery : State = state(parent = OrderHandling) {
    onEntry() {
        furhat.ask("Where do you want it delivered?")
    }

    onResponse<RequestOptions> {
        raise(TellDeliveryOptions())
    }

    onResponse<TellPlace> {
        furhat.say("Okay, ${it.intent.deliverTo}")
        users.current.order.deliverTo = it.intent.deliverTo
        goto(CheckOrder)
    }

}

// Request delivery time
val RequestTime : State = state(parent = OrderHandling) {

    onEntry() {
        furhat.ask("At what time do you want it delivered?")
    }

    onResponse<RequestOptions> {
        raise(TellOpeningHours())
    }

    onResponse<TellTime> {
        furhat.say("Okay, ${it.intent.time}")
        users.current.order.deliveryTime = it.intent.time
        goto(CheckOrder)
    }

}

// Request toppings
val RequestTopping : State = state(parent = OrderHandling) {

    onEntry() {
        furhat.ask("Any extra topping?")
    }

    onResponse<RequestOptions> {
        raise(TellToppingOptions())
    }

    onResponse<Yes> {
        furhat.ask("What kind of topping do you want?")
    }

    onResponse<No> {
        furhat.say("Okay, no extra topping")
        users.current.order.topping = ListOfTopping()
        goto(CheckOrder)
    }

    onResponse<AddTopping> {
        furhat.say("Okay, ${it.intent.topping}")
        users.current.order.topping = it.intent.topping
        goto(CheckOrder)
    }

}

// Confirming and verifying order
val ConfirmOrder = state(parent = OrderHandling) {
    onEntry() {
        furhat.say("You want to order ${users.current.order}")
        furhat.ask("Is that correct?")
    }
    onResponse<Yes> {
        furhat.say("Okay")
        goto(EndOrder)
    }
    onResponse<No> {
        goto(ChangeOrder)
    }
}

// Changing order
val ChangeOrder = state(parent = OrderHandling) {
    onEntry() {
        furhat.ask("Anything you would like to change?")
    }
    onResponse<Yes> {
        reentry()
    }
    onResponse<No> {
        goto(EndOrder)
    }
}

// Order completed
val EndOrder = state {
    onEntry {
        furhat.say("Thanks for your order. Goodbye")
        goto(Idle)
    }
}

