package furhatos.app.pizzaorder.nlu

import furhatos.nlu.TextGenerator
import furhatos.util.Language
import furhatos.nlu.*
import furhatos.nlu.common.*
import furhatos.nlu.common.Number
import furhatos.records.GenericRecord

open class OrderPizzaIntent : Intent(), TextGenerator {
    var topping : ListOfTopping? = null
    var deliverTo : Place? = null
    var deliveryTime : Time? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I would like a pizza to my office at 3 pm",
                "I want a pizza",
                "I want to order a pizza with bacon and ham",
                "Deliver to my @deliverTo instead",
                "I want it to @deliverTo",
                "Deliver it at @deliveryTime",
                "I want it at @deliveryTime",
                "I want to add @topping",
                "I also want @topping",
                "I want @topping",
                "I want it @deliveryTime @deliverTo"
        )
    }

    override fun toText(lang : Language) : String {
        return generate(lang, "[with $topping] [$deliverTo] [delivered $deliveryTime]");
    }

    override fun toString(): String {
        return toText()
    }

    override fun adjoin(record: GenericRecord<Any>?) {
        super.adjoin(record)
        if (topping != null){
            topping?.list = topping?.list?.distinctBy { it.value }!!.toMutableList()
        }
    }
}

class TellPlaceIntent : Intent() {
    var deliverTo : Place? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf("home", "to my home", "I want it delivered to my home", "send it to my home")
    }
}

class TellTimeIntent(var time : Time? = null) : Intent() {

    override fun getExamples(lang: Language): List<String> {
        return listOf("@time", "at @time")
    }
}

class ToppingIntent : AddToppingIntent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "@topping",
                "yes @topping")
    }
}

open class AddToppingIntent : Intent() {
    var topping : ListOfTopping? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I want @topping",
                "I also want @topping",
                "I want to add @topping"
        )
    }
}

class RemoveToppingIntent : Intent() {
    var topping : ListOfTopping? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "I want to remove bacon",
            "no bacon",
            "I do not want bacon",
            "I don't want bacon",
            "remove bacon from my pizza")
    }
}

class RequestOptionsIntent : Intent()  {
    override fun getExamples(lang: Language): List<String> {
        return listOf("what options are there",
                "what are the options",
                "what can I choose from",
                "what do you have")
    }
}

class RequestDeliveryOptionsIntent : Intent()  {
    override fun getExamples(lang: Language): List<String> {
        return listOf("where can you deliver",
                "where can I get it")
    }
}

class RequestToppingOptionsIntent : Intent()  {
    override fun getExamples(lang: Language): List<String> {
        return listOf("what topping do you have",
                "what different toppings do you have?")
    }
}

class RequestOpeningHoursIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("what are your opening hours",
                "when do you open",
                "when do you close")
    }
}
