package furhatos.app.presentation

import furhatos.nlu.Intent
import furhatos.util.Language

class StartIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Start",
            "Presentation",
            "Do your presentation",
            "Do the presentation",
            "Present",
            "Who are you",
            "Start your presentation"
        )
    }
}

class ShowEmotionIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Show me some emotions",
                "Show me emotions",
                "show me your emotions"
        )
    }
}

class ShowLEDIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Show me some LEDs",
                "Show me lights",
                "show me your LED"
        )
    }
}

class ShowPersonalitiesIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Show me your personalities",
                "Show me personalities",
                "show me your personas",
                "show me your other personas"
        )
    }
}
