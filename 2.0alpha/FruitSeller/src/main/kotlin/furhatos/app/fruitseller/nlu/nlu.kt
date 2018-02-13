package furhatos.app.fruitseller.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.util.Language

class Fruit : EnumEntity() {

    override fun getEnum(lang: Language?): List<String> {
        return listOf("banana", "orange", "apple")
    }

}

class BuyFruit : Intent() {

    var fruit : Fruit? = null

    override fun getExamples(lang: Language?): List<String> {
        return listOf("banana", "I want a banana", "I would like a banana")
    }

}