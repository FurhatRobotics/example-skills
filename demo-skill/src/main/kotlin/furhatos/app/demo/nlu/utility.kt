package furhatos.app.demo.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class WakeupIntent : Intent() {
    fun getEnum(lang: Language): List<String> {
        return listOf(
                "furhat",
                "wake up",
                "wakeup",
                "are you there",
                "are you here")
    }

    override fun getExamples(lang: Language): List<String> {
        return getEnum(lang)
    }

    override fun getSpeechRecPhrases(lang: Language): List<String> {
        return getEnum(lang)
    }
}

class GoToPassiveIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "hold on",
                "wait a minute",
                "hold on for a minute",
                "wait a bit",
                "wait a little",
                "hold on for a while",
                "chill for a while",
                "chill a bit"
        )
    }
}

class WhateverIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "it does not matter",
                "it doesn't matter",
                "you decide for me",
                "you decide",
                "I don't care",
                "anyone",
                "anything",
                "whatever",
                "whatever you decide"
        )
    }
}

class DoneIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.SWEDISH -> listOf("Jag är klar", "klar", "färdigt", "färdig", "klart")
            else -> listOf(
                    "done",
                    "finished",
                    "it's done",
                    "I'm done",
                    "it is on",
                    "the mask is on",
                    "it is ready"
            )
        }
    }
}

class CancelIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("cancel", "stop", "abort", "stop changing mask")
    }
}

class ExitIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.GERMAN ->  listOf("Stop", "Zurück", "Exit", "Stille", "Halt die Klappe", "Auf Wiedersehen", "Tschüss")
            Language.SWEDISH -> listOf("Stopp", "Sluta", "tyst", "hejdå")
            else -> listOf("Stop", "Go back to sleep", "Exit", "Silence", "Goodbye", "Bye", "Go to sleep")
        }
    }
}

class NoIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("no",
                "nope",
                "never",
                "i don't think so",
                "no I don't",
                "nothing"
        )
    }
}