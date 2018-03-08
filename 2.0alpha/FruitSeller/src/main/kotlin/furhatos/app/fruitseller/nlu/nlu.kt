package furhatos.app.fruitseller.nlu

import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language

class RequestOptions: Intent() {
    override fun getExamples(lang: Language?): List<String> {
        return listOf("What options do you have?", "What fruits do you have?", "What are the alternatives?", "What do you have?")
    }
}

class FruitList : ListEntity<QuantifiedFruit>()

class QuantifiedFruit : ComplexEnumEntity() {
    var count : Number? = Number(1)
    var fruit : Fruit? = null

    override fun getEnum(lang: Language): List<String> {
        return listOf("@count @fruit", "@fruit")
    }
}

class Fruit : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("banana", "orange", "apple", "cherimoya")
    }
}

class BuyFruit : Intent() {

    var fruits : FruitList? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf("@fruits", "I want @fruits", "I would like @fruits", "I want to buy @fruits")
    }

}