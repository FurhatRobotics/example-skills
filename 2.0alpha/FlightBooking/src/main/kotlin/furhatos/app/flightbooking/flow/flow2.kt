package furhatos.app.flightbooking.flow.temp

import furhatos.app.flightbooking.nlu.*
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

var itinerary = Itinerary()

val Init = state {
    onEntry {
        furhat.say("Welcome to the flight booking system")
        furhat.ask("How may I help you?")
    }
    onReentry {
        if (itinerary.departure == null)
            itinerary.departure =
                    furhat.askFor<City>("From which city do you want to go?")
        if (itinerary.destination == null)
            itinerary.destination =
                    furhat.askFor<City>("To which city do you want to go?")
        if (itinerary.date == null)
            itinerary.date =
                    furhat.askFor<Date>("On which date do you want to go?")
        if (itinerary.time == null)
            itinerary.time =
                    furhat.askFor<Time>("At what time do you want to go?")
        if (itinerary.travelClass == null)
            itinerary.travelClass =
                    furhat.askFor<TravelClass>("Do you want business or economy class?")
        furhat.say("You have now booked a trip ${itinerary.toText()}")
        furhat.say("Thanks for your order. Goodbye")
        terminate()
    }
    onResponse<Itinerary> {
        furhat.say("Okay, ${it.intent.toText()}")
        itinerary.adjoin(it.intent)
        reentry()
    }
}
