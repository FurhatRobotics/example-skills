package furhatos.app.flightbooking.flow

import furhatos.app.flightbooking.*
import furhatos.app.flightbooking.nlu.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.City
import furhatos.nlu.common.Date
import furhatos.nlu.common.Time

val Start : State = state(Interaction) {
    onEntry {
        furhat.say("Welcome to the flight booking system")
        goto(RequestStart)
    }
}

val CheckItinerary : State = state(Interaction) {
    onEntry {
        if (users.current.flight.departure == null) {
            goto(RequestDeparture)
        } else if (users.current.flight.destination == null) {
            goto(RequestDestination)
        } else if (users.current.flight.date == null) {
            goto(RequestDate)
        } else if (users.current.flight.time == null) {
            goto(RequestTime)
        } else if (users.current.flight.travelClass == null) {
            goto(RequestTravelClass)
        } else {
            goto(EndOrder)
        }
    }

}

val GeneralRequest = state(Interaction) {

    onResponse<Itinerary> {
        furhat.say("Okay, ${it.intent}")
        users.current.flight.adjoin(it.intent)
        goto(CheckItinerary)
    }
}

val RequestStart : State = state(GeneralRequest) {

    onEntry {
        furhat.ask("How may I help you?")
    }

}

val RequestDeparture : State = state(GeneralRequest) {
    onEntry() {
        furhat.ask("From which city do you want to go?")
    }
    onResponse<City> {
        furhat.say("Okay, from ${it.intent}")
        users.current.flight.departure = it.intent
        goto(CheckItinerary)
    }
}

val RequestDestination : State = state(GeneralRequest) {
    onEntry() {
        furhat.ask("To which city do you want to go?")
    }
    onResponse<City> {
        furhat.say("Okay, to ${it.intent}")
        users.current.flight.destination = it.intent
        goto(CheckItinerary)
    }
}

val RequestDate : State = state(GeneralRequest) {
    onEntry() {
        furhat.ask("Which date do you want to go?")
    }
    onResponse<Date> {
        furhat.say("Okay, ${it.intent}")
        users.current.flight.date = it.intent
        goto(CheckItinerary)
    }
}

val RequestTime : State = state(GeneralRequest) {
    onEntry() {
        furhat.ask("Which time do you want to go?")
    }
    onResponse<Time> {
        furhat.say("Okay, ${it.intent}")
        users.current.flight.time = it.intent
        goto(CheckItinerary)
    }
}

val RequestTravelClass : State = state(GeneralRequest) {
    onEntry() {
        furhat.ask("Do you want business or economy class?")
    }
    onResponse<TravelClass> {
        furhat.say("Okay, ${it.intent}")
        users.current.flight.travelClass = it.intent
        goto(CheckItinerary)
    }
}

val EndOrder: State  = state(Interaction) {
    onEntry() {
        furhat.say("You have now booked a trip ${users.current.flight}")
        furhat.say("Thanks for your order. Goodbye")
        terminate()
    }
}

