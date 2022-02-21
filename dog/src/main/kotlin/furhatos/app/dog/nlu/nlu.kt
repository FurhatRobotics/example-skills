package furhatos.app.dog.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class GoodDog : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Good boy",
            "You're such a good boy",
            "What a good boy",
            "Who is a good boy?",
            "you're a good boy",
            "good dog",
            "good doggie",
            "good girl",
            "Who's a goodbye?",
            "goodbye",
        "Good lucky")
    }
}

class BadDog : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Bad dog",
            "bad doggie",
            "No, bad dog",
            "bad boy",
            "bad girl",
        "Bad lucky",
        "No, bad lucky. Stop.")
    }
}

class ILoveYou : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I love you",
            "love you dog",
        "I love you lucky")
    }
}

class YouAreCute : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("You're so cure",
            "such a cute dog",
        "aren't you a cute dog",
        "who's a cute dog",
        "you're the cutest dog I've every seen",
        "You're so cute lucky",
        "What a cutie you are lucky",
        "Cute lucky")
    }
}

class Fetch : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Go fetch",
            "go fish",
            "fetch lucky",
            "go get it",
            "go get it lucky",
        "Fetch")
    }
}

class SitDown : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Sit",
            "lucky sit",
            "set lucky",
            "set",
            "Sit down")
    }
}

class RollAround : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Roll",
            "lucky roll",
            "Roll around",
        "rode around")
    }
}

class LayDown : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Lay down",
            "lay down lucky",
            "lay")
    }
}