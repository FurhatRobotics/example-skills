package furhatos.app.demo.imported.quiz

import furhatos.nlu.EnumEntity
import furhatos.nlu.EnumItem
import furhatos.nlu.Intent
import furhatos.util.Language

class StopIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "stop",
            "stop playing",
            "end game",
            "I don't want to play",
            "let's stop this game",
            "stop the quiz",
            "goodbye",
            "bye",
            "bye bye",
            "byebye"
        )
    }
}

class DontKnowIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I don't know",
                "don't know",
                "no idea",
                "I have no idea"
        )
    }
}

class RequestRulesIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the rules",
                "how does it work"
        )
    }
}

class RequestRepeatQuestionIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what was the question",
                "can you repeat the question",
                "what was the question again"
        )
    }
}

class RequestRepeatOptionsIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the options",
                "can you repeat the options",
                "what was the options"
        )
    }
}

class AnswerOptionIntent : EnumEntity {

    var correct : Boolean = false

    // Every entity and intent needs an empty constructor.
    constructor() {
    }

    // Since we are overwriting the value, we need to use this custom constructor
    constructor(correct : Boolean, value : String) {
        this.correct = correct
        this.value = value
    }

    override fun getEnumItems(lang: Language): List<EnumItem> {
        return QuestionSet.current.options;
    }
}