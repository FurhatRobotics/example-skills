package furhatos.app.flightbooking.nlu

import furhatos.util.Language
import furhatos.nlu.*
import furhatos.nlu.common.*

open class Itinerary : Intent(), TextGenerator {

    var departure : City? = null

    var destination : City? = null

    var time : Time? = null

    var date : Date? = null

    var travelClass : TravelClass? = null

    override fun toText(lang : Language) : String {
        return generate(lang,"{from @departure} {to @destination} @date @time {travelling @travelClass class}");
    }

    override fun toString(): String {
        return toText()
    }

    override fun getExamples(lang: Language): MutableList<String> {
        return mutableListOf<String>(
                "i want to go from @departure to @destination on @date",
                "i would like to go travel on @date",
                "i want to go to @destination",
                "i want to travel from @departure")
    }

}

class TravelClass : EnumEntity() {

    override fun getEnum(lang: Language): List<String> {
        return listOf("economy", "business")
    }

}
