package furhatos.app.pizzaorder.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.ListEntity
import furhatos.util.Language

class ListOfTopping : ListEntity<Topping>()

class Topping : EnumEntity() {

    override fun getEnum(lang: Language): List<String> {
        return listOf("onion", "tomato", "ham", "bacon", "rocket salad", "pepper")
    }

}

class Place : EnumEntity() {

    override fun getEnum(lang: Language): List<String> {
        return listOf("home", "office")
    }

    // Method overriden to produce a spoken utterancce of the place
    override fun toText(lang: Language): String {
        return generate(lang,"to your $value");
    }
}
