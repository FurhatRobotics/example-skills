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

/*
    General enquiries that we want to be able to handle as well as an OrderPizzaIntent that is used for initial orders.
 */
val General: State = state(Interaction) {
    onResponse<RequestDeliveryOptionsIntent> {
        furhat.say("We can deliver to your home and to your office")
        reentry()
    }

    onResponse<RequestOpeningHoursIntent> {
        furhat.say("We are open between 7 am and 8 pm")
        reentry()
    }

    onResponse<RequestToppingOptionsIntent> {
        furhat.say("We have " + Topping().optionsToText())
        reentry()
    }
}

// Start of interaction
val Start = state(parent = General) {
    onEntry {
        furhat.ask("Welcome to Pizza house. How may I help you?")
    }

    onResponse<OrderPizzaIntent> {
        users.current.order.adjoin(it.intent)
        furhat.say("Ok, you want a pizza ${it.intent}")
        goto(CheckOrder)
    }
}

// Form-filling state that checks any missing slots and if so, goes to specific slot-filling states.
val CheckOrder = state {
    onEntry {
        val order = users.current.order
        when {
            order.topping == null -> goto(RequestTopping)
            order.deliverTo == null -> goto(RequestDelivery)
            order.deliveryTime == null -> goto(RequestTime)
            else -> {
                furhat.say("Alright, so you want to order a pizza $order")
                goto(ConfirmOrder)
            }
        }
    }
}

/*
    State for handling changes to an existing order
 */
val OrderHandling: State = state(parent = General) {

    // Handler that re-uses our pizza intent but has a more intelligent response handling depending on what new information we get
    onResponse<OrderPizzaIntent> {
        val order = users.current.order

        // Message to be constructed based on what data points we get from the user
        var message = "Okay"

        // Adding topping(s) if we get any new
        if (it.intent.topping != null) message += ", adding }"

        // Adding or changing delivery option and time
        if (it.intent.deliverTo != null || it.intent.deliveryTime != null) {

            /* We are constructing a specific message depending on if we
            get a delivery place and/or time and if this slot already had a value
             */
            when {
                it.intent.deliverTo != null && it.intent.deliveryTime != null -> { // We get both a delivery place and time
                    message += ", delivering ${it.intent.deliverTo} ${it.intent.deliveryTime} "
                    if (order.deliverTo != null || order.deliveryTime != null) message += "instead " // Add an "instead" if we are overwriting any of the slots
                }
                it.intent.deliverTo != null -> { // We get only a delivery place
                    message += ", delivering ${it.intent.deliverTo} "
                    if (order.deliverTo != null) message += "instead " // Add an "instead" if we are overwriting the slot
                }
                it.intent.deliveryTime != null -> { // We get only a delivery time
                    message += ", delivering ${it.intent.deliveryTime} "
                    if (order.deliveryTime != null) message += "instead " // Add an "instead" if we are overwriting the slot
                }
            }
        }

        // Deliver our message
        furhat.say(message)

        // Finally we join the existing order with the new one
        order.adjoin(it.intent)

        reentry()
    }

    // Specific handler for removing toppings since this is to complex to include in our OrderPizzaIntent (due to the ambiguity of adding vs removing toppings)
    onResponse<RemoveToppingIntent> {
        users.current.order.topping?.removeFromList(it.intent?.topping)
        furhat.say("Okay, we remove ${it.intent?.topping} from your pizza")
        reentry()
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

    onResponse<Yes> {
        furhat.ask("What kind of topping do you want?")
    }

    onResponse<RequestOptionsIntent> {
        transform(it, RequestToppingOptionsIntent())
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
        transform(it, RequestDeliveryOptionsIntent())
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
        transform(it, RequestOpeningHoursIntent())
    }

    onResponse<Number> {
        var hour = it.intent.value

        // We're assuming we want an afternoon delivery, so if the user says "at 5", we assume it's 5pm.
        if (hour <= 12) hour += 12
        transform(it, TellTimeIntent(Time(LocalTime.of(hour, 0))))
    }

    onResponse<TellTimeIntent> {
        furhat.say("Okay, ${it.intent.time}")
        users.current.order.deliveryTime = it.intent.time
        goto(CheckOrder)
    }
}

// Confirming order
val ConfirmOrder : State = state(parent = OrderHandling) {
    onEntry {
        furhat.ask("Does that sound good?")
    }

    onResponse<Yes> {
        furhat.say("Great")
        goto(EndOrder)
    }

    onResponse<No> {
        goto(ChangeOrder)
    }
}

// Changing order
val ChangeOrder = state(parent = OrderHandling) {
    onEntry {
        furhat.ask("Anything that you like to change?")
    }

    onReentry {
        furhat.ask("I currently have a pizza ${users.current.order}. Anything that you like to change?")
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
        furhat.say("Great, thanks for your order. Goodbye")
        goto(Idle)
    }
}

