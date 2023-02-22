package furhatos.app.complimentbot.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class CanIGetCompliment : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Can I get a compliment",
            "I would like to interact",
            "Can we have a chat",
            "Can you say something to me",
            "I want to hear something",
            "Tell me something",
            "May I get some attention",
            "Could I have a compliment",
            "Can you do your thing"
        )
    }
}