package furhatos.app.quiz

import furhatos.nlu.EnumEntity
import furhatos.nlu.EnumItem
import furhatos.nlu.Intent
import furhatos.util.Language

class DontKnow : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I don't know",
                "don't know",
                "no idea",
                "I have no idea"
        )
    }
}

class RequestRules : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the rules",
                "how does it work"
        )
    }
}

class RequestRepeatQuestion : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what was the question",
                "can you repeat the question",
                "what was the question again"
        )
    }
}

class RequestRepeatOptions : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the options",
                "can you repeat the options",
                "what was the options"
        )
    }
}

class AnswerOption : EnumEntity {

    @RecordField
    var correct : Boolean = false

    constructor() {
    }

    constructor(correct : Boolean, value : String) {
        this.correct = correct
        this.value = value
    }

    override fun getEnumItems(lang: Language): List<EnumItem> {
        return QuestionSet.current!!.options;
    }

}