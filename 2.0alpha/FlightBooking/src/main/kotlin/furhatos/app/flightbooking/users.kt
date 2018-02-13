package furhatos.app.flightbooking

import furhatos.app.flightbooking.nlu.*
import furhatos.records.Record
import furhatos.records.User

val User.flight : Itinerary
    get() = data.getOrPut("flight", Itinerary())