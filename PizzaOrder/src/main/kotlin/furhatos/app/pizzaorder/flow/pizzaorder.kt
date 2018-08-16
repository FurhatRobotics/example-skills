package furhatos.app.pizzaorder.flow

import furhatos.app.pizzaorder.*
import furhatos.app.pizzaorder.nlu.*
import furhatos.event.Event
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Number
import furhatos.nlu.common.Time
import furhatos.nlu.common.Yes
import java.time.LocalTime

class TellToppingOptions : Event()
class TellDeliveryOptions : Event()
class TellOpeningHours : Event()
val orderChanged = false
/*
    General enquiries that we want to be able to handle. The reason why we define events above
    and then raise() events that we catch is that we want to be able to reuse these answers
    in substates. For example, the user might as "what are your opening hours" both as an initial
    question and after getting then question "when do you want it delivered?".
 */
val General: State = state(Interaction) {
    onResponse<RequestDeliveryOptions> {
        raise(TellDeliveryOptions())
    }

    onEvent<TellDeliveryOptions> {
        furhat.say("We can deliver to your home and to your office")
        reentry()
    }
    onResponse<RequestOpeningHours> {
        raise(TellOpeningHours())
    }

    onEvent<TellOpeningHours> {
        furhat.say("We are open between 7 am and 8 pm")
        reentry()
    }

    onResponse<RequestToppingOptions> {
        raise(TellToppingOptions())
    }

    onEvent<TellToppingOptions> {
        furhat.say("We have " + Topping().optionsToText())
        reentry()
    }

    onResponse<OrderPizzaIntent> {
        users.current.order.adjoin(it.intent)
        furhat.say("Okay, you want ${it.intent}")
        goto(CheckOrder)
    }

}

val OrderHandling: State = state(parent = General) {
    onResponse<AddToppingIntent> {
        users.current.order.adjoin(it.intent)
        furhat.say("Okay, added ${it.intent.topping}")
        reentry()
    }

    onResponse<RemoveToppingIntent> {
        users.current.order.topping?.removeFromList(it.intent?.topping)
        furhat.say("Okay, we remove ${it.intent?.topping} from your pizza")
        reentry()
    }
}

// Form-filling state
val CheckOrder = state {
    onEntry {
        val order = users.current.order
        when {
            order.topping == null -> goto(RequestTopping)
            order.deliverTo == null -> goto(RequestDelivery)
            order.deliveryTime == null -> goto(RequestTime)
            else -> {
                goto(ConfirmOrder)
            }
        }
    }
}

// Start of interaction
val Start = state(parent = General) {
    onEntry {
        furhat.ask("Welcome to Pizza house. How may I help you?")
    }
}

// Request toppings
val RequestTopping : State = state(parent = OrderHandling) {
    onEntry() {
        furhat.ask("All our pizzas come with tomato and cheese. Do you want any extra topping?")
    }

    onReentry() {
        furhat.ask("Do you want any extra topping?")
    }

    onResponse<RequestOptionsIntent> {
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

    onResponse<ToppingIntent> {
        furhat.say("Okay, ${it.intent.topping}")
        users.current.order.topping = it.intent.topping
        goto(CheckOrder)
    }
}

// Request delivery point
val RequestDelivery : State = state(parent = OrderHandling) {
    onEntry() {
        furhat.ask("Where do you want it delivered?")
    }

    onResponse<RequestOptionsIntent> {
        raise(TellDeliveryOptions())
    }

    onResponse<TellPlaceIntent> {
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

    onResponse<RequestOptionsIntent> {
        raise(TellOpeningHours())
    }

    class TellTime(val intent: TellTimeIntent) : Event()

    onResponse<Number> {
        println(it)
        println(LocalTime.of(it.intent.value, 0))
        raise(TellTime(intent = TellTimeIntent(time = Time(LocalTime.of(it.intent.value, 0)))))
    }

    onResponse<TellTimeIntent> {
        println(it.intent.time)
        raise(TellTime(it.intent))
    }

    onEvent<TellTime> {
        println(it)
        furhat.say("Okay, ${it.intent.time}")
        users.current.order.deliveryTime = it.intent.time
        goto(CheckOrder)
    }
}

// Confirming and verifying order
val ConfirmOrder : State = state(parent = OrderHandling) {
    onEntry() {
        furhat.say("You want to order ${users.current.order}")
        furhat.ask("Is that correct?")
    }

    onResponse<OrderPizzaIntent> {
        val order = users.current.order
        order.adjoin(it.intent)
        furhat.say("Okay, let's change that. So,")
        reentry()
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
    onEntry {
        furhat.ask("Anything you would like to change?")
    }

    onReentry {
        furhat.ask("Anything else you want to change?")
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

