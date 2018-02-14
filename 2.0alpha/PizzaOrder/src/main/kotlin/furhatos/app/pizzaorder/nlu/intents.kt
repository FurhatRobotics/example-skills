package furhatos.app.pizzaorder.nlu

import furhatos.nlu.TextGenerator
import furhatos.util.Language
import furhatos.nlu.*
import furhatos.nlu.common.*
import furhatos.nlu.common.Number

class OrderPizza : Intent(), TextGenerator {

    var topping : ListOfTopping? = null

    var deliverTo : Place? = null

    var count : Number = Number(1)

    var deliveryTime : Time? = null

    var deliveryDate : Date? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "I would like a pizza to my office at 3 pm",
                "I would like to order a pizza with bacon and ham")
    }

    override fun toText(lang : Language) : String {
        return fromPattern(lang, "${if (count.value?:1>1) "${count.value} pizzas" else "a pizza"} {with @topping} {delivered @deliverTo} @deliveryDate @deliveryTime");
    }

    override fun toString(): String {
        return toText()
    }
}


class TellPlace : Intent() {

    var deliverTo : Place? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf("home", "to my home")
    }

}

class TellTime : Intent() {

    var time : Time? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf("@time")
    }

}

class AddTopping : Intent() {

    var topping : ListOfTopping? = null

    override fun getExamples(lang: Language?): MutableList<String> {
        return mutableListOf("bacon and ham", "yes bacon and ham")
    }

}


class RemoveTopping : Intent() {

    var topping : ListOfTopping? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf(
                "I do not want bacon",
                "I don't want bacon",
                "remove bacon from my pizza")
    }

}

class RequestPlace : Intent()  {

    override fun getExamples(lang: Language?): List<String> {
        return listOf("where can you deliver")
    }

}

class RequestToppingOptions : Intent()  {

    override fun getExamples(lang: Language?): List<String> {
        return listOf("what topping do you have")
    }

}

class RequestOpen : Intent() {

    override fun getExamples(lang: Language?): List<String> {
        return listOf("what are your opening hours",
                "when do you open",
                "when do you close")
    }

}

class RequestOptions : Intent()  {

    override fun getExamples(lang: Language?): List<String> {
        return listOf("what options are there",
                "what are the options",
                "what can I choose from",
                "what do you have")
    }

}
