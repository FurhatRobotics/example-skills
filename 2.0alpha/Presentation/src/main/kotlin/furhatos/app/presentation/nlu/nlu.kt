package furhatos.app.presentation.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class StartIntent : Intent() {
    override fun getExamples(lang: Language?): List<String> {
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