package furhatos.app.demo.nlu

import furhatos.nlu.Intent
import furhatos.nlu.common.Color
import furhatos.util.Language

open class ConversationalIntent : Intent() {
    // To be used for the sentance "you can ask me $description"
    open val description : String = ""
}

class RequestOptionsIntent : ConversationalIntent() {
    override val description : String = "what I can do"

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what can you do",
                "what options do I have?",
                "what can I do?",
                "what can you say?",
                "what can we talk about?",
                "what can you help me with?")
    }
}

class RequestOtherOptionsIntent : ConversationalIntent() {
    override val description : String = "what else I can do"

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what else can you do?",
                "what other options do I have?",
                "what else can I do?",
                "what else can you say?",
                "what else can we talk about?",
                "what else can you help me with?"
        )
    }
}

class RequestPersonasIntent : ConversationalIntent() {
    override val description : String = "who I can be"

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "who can you be",
                "what personas can you be",
                "what are your personas",
                "what personalities do you have",
                "what are your personas",
                "can you be another person"
        )
    }
}

class ChatIntent(val chat: ChatEntity? = null) : ConversationalIntent() {
    override val description : String = "to chat a little"

    override fun getExamples(lang: Language): List<String> {
        return listOf("I wanna @chat", "I want to @chat", "Can we @chat a bit", "can we @chat a little", "can we @chat", "can we @chat about something", "how about @chat something", "how about @chat", "can we have a little @chat")
    }
}

class ChangePersonaIntent() : ConversationalIntent() {
    override val description : String = "to change persona"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.GERMAN -> listOf("Kannst du mir eine andere Person zeigen", "Persona ändern", "wie jemand anders aussehen", "Persönlichkeit ändern")
            Language.SWEDISH -> listOf("Var en annan person", "visa mig en annan person", "se ut som någon annan", "visa en annan personlighet")
            else -> listOf(
                    "Can you show me another persona",
                    "change your voice",
                    "can you change your voice",
                    "be someone else",
                    "Change your persona",
                    "be another person",
                    "change personality"
            )
        }
    }
}

class ShowLEDIntent(val color : Color? = null) : ConversationalIntent() {
    override val description : String = "to show my LED lights"

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "turn on my lights",
                "show me @color leds",
                "turn on leds in @color",
                "turn on leds",
                "turn on lights",
                "turn on @color lights"
        )
    }
}

class TurnOffLEDsIntent : ConversationalIntent() {
    override val description : String = "to turn off my LEDs"

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "can you turn off my lights",
                "turn off leds",
                "turn off the color",
                "can you turn off the LEDS"
        )
    }
}

class BePersonaIntent(val personaEntity : PersonaEntity? = null) : ConversationalIntent() {
    override val description : String = "to be a different persona"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.GERMAN -> listOf("kannst du mir zeigen @personaEntity", "sehe aus wie @personaEntity", "sei persona @personaEntity", "sei @personaEntity")
            Language.SWEDISH -> listOf("Var @personaEntity", "visa mig @personaEntity", "se ut som @personaEntity", "var personen @personaEntity")
            else -> listOf(
                    "can you show me @personaEntity",
                    "can you be @personaEntity",
                    "can you look like @personaEntity",
                    "can you look like a @personaEntity",
                    "can you be the persona @personaEntity",
                    "can I speak to @personaEntity",
                    "I want to speak to @personaEntity",
                    "can I talk to @personaEntity",
                    "give me @personaEntity"
            )
        }
    }
}

class RequestLanguagesIntent : ConversationalIntent() {
    override val description : String = "what languages I speak"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.GERMAN -> listOf("Können wir eine andere Sprache sprechen", "kannst du eine andere sprache sprechen")
            Language.SWEDISH -> listOf("kan du prata ett annat språk", "kan du byta till ett annat språk", "vilka språk kan du prata")
            else -> listOf(
                    "can you speak another language",
                    "can you switch to a different language",
                    "what languages do you speak",
                    "what languages do you know"
            )
        }
    }
}

class SpeakLanguageIntent(val languageEntity : LanguageEntity? = null) : ConversationalIntent() {
    override val description : String = "to speak a different language"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.SPANISH_ES -> listOf("languages @languageEntity")
            Language.GERMAN -> listOf("Können wir @languageEntity sprechen", "kannst du @languageEntity sprechen", "Kennst du @languageEntity","können wir auf @languageEntity wechseln")
            Language.SWEDISH -> listOf("", "kan du prata @languageEntity", "kan vi byta till @languageEntity", "kan vi prata @languageEntity")
            else -> listOf(
                    "can you speak @languageEntity",
                    "can you switch to @languageEntity",
                    "can we speak @languageEntity",
                    "do you know @languageEntity",
                    "speak some @languageEntity for us"
            )
        }
    }
}

class PlayQuizIntent : ConversationalIntent() {
    override val description : String = "to play a quiz with you"

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Play a quiz",
                "I want to play a quiz",
                "Do a quiz",
                "Can we play a quiz",
                "can you play a quiz"
        )
    }
}

class ShowAllGesturesIntent : ConversationalIntent() {
    override val description : String = "to show all my gestures"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.SWEDISH -> listOf("Visa dina gester", "Visa några gester", "kan du gestikulera", "visa alla gester")
            else -> listOf(
                    "Show your gestures",
                    "Show me your gestures",
                    "Do your gestures",
                    "All gestures",
                    "Show me some gestures"
            )
        }
    }
}

class ShowRandomGesture : ConversationalIntent() {
    override val description : String = "to show some gestures"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.GERMAN -> listOf("eine zufällige Geste machen", "eine Geste machen", "eine Geste zeigen")
            Language.SWEDISH -> listOf("gör en slumpvis gest", "Visa en gest", "kan du göra en gest", "gör en gestureEntity")
            else -> listOf(
                    "do a random gestureEntity",
                    "do some expressions",
                    "show some expressions",
                    "show some emotions",
                    "do some emotions",
                    "do some gestures",
                    "show a gestureEntity",
                    "show some gestures",
                    "can you do some gestures"
            )
        }
    }
}

class ShowGestureIntent(val gestureEntity: GestureEntity? = null) : ConversationalIntent() {
    override val description : String = "to show some gestures"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.GERMAN -> listOf("mach ein @gestureEntity", "zeige mir ein @gestureEntity", "@gestureEntity")
            Language.SWEDISH -> listOf("gör en @gestureEntity", "Visa en @gestureEntity", "kan du göra en @gestureEntity", "gör en @gestureEntity", "@gestureEntity")
            else -> listOf(
                    "do a @gestureEntity",
                    "can you do a @gestureEntity",
                    "do a couple of @gestureEntity"
            )
        }
    }
}

class BeParrotIntent : ConversationalIntent() {
    override val description : String = "to be a parrot"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.GERMAN -> listOf("Sei ein Papagei", "Wiederhole nach mir", "Wiederhole mich", "Sei der Papagei", "Sei bitte ein Papagei", "Wiederhole mich bitte")
            Language.SWEDISH -> listOf("Var en papegoja", "Säg efter mig", "Repetera mig", "repetera efter mig")
            else -> listOf(
                    "Be a parrot",
                    "Repeat after me",
                    "Repeat me",
                    "Be the parrot",
                    "Be a parrot please",
                    "Repeat me please"
            )
        }
    }
}

class DoPresentationIntent : ConversationalIntent() {
    override val description : String = "to introduce myself"

    override fun getExamples(lang: Language): List<String> {
        return when(lang) {
            Language.SPANISH_ES -> listOf("haga su presentación", "preséntese", "puede hacer su presentación", "puede presentarse")
            Language.GERMAN -> listOf("Mach deine Präsentation", "präsentiere dich", "kannst du deine Präsentation machen", "kannst du dich präsentieren")
            Language.SWEDISH -> listOf("gör din presentation", "presentera dig", "kan du göra din presentation", "kan du presentera dig själv")
            else -> listOf(
                    "tell us who you are",
                    "who are you",
                    "do your presentation",
                    "present yourself",
                    "can you do your presentation",
                    "can you present yourself",
                    "introduce yourself",
                    "can you introduce yourself",
                    "tell us about yourself"
            )
        }
    }
}
