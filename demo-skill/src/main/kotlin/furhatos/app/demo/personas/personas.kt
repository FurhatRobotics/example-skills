package furhatos.app.demo.personas

import furhatos.app.demo.nlu.*
import furhatos.flow.kotlin.Furhat
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.flow.kotlin.voice.Voice
import furhatos.nlu.Intent
import furhatos.nlu.IntentInstance
import furhatos.nlu.wikidata.Film
import furhatos.nlu.wikidata.MusicArtist
import furhatos.util.Gender
import furhatos.util.Language
import furhatos.util.Language.ENGLISH_GB
import furhatos.util.Language.ENGLISH_US
import java.util.*

data class Speech(
        val language: Language,
        val voice: Voice,
        val phrases: Phrases
)

data class Persona(
        val name: String,
        val nickNames: List<String> = listOf("default"),
        val gender : Gender,
        val description: String,
        val topics: List<ConversationalIntent>,
        val model: String = "bertil",
        val texture: String = "default",
        val speech: List<Speech>,
        val age: Int = 10,
        val favoriteColor: String = "red",
        val favoriteArtist : MusicArtist? = null, // MusicArtist.from("Kraftwerk"),
        val favoriteFilm : Film? = null, // Film.from("Robot and Frank"),
        var active: Boolean = false)
    {

    fun wantsToSpeakAbout(intent: Intent) : Boolean {
        return topics.any { it.intentName == intent.intentName }
    }

    fun canSpeak(language: Language) : Boolean {
        return speech.any { it.language.equalsIgnoreDialect(language) }
    }

    val grammarSubject : String
        get() {
            return when (gender) {
                Gender.MALE -> "he"
                Gender.FEMALE -> "she"
                Gender.NEUTRAL -> "it"
            }
    }

    val grammarObject : String
        get() {
            return when (gender) {
                Gender.MALE -> "him"
                Gender.FEMALE -> "her"
                Gender.NEUTRAL -> "it"
            }
        }

    fun setSpeech(lang: Language) {
        language = lang
        val _speech = speech.first { it.language.equalsIgnoreDialect(lang) }
        phrases = _speech.phrases
        voice = _speech.voice
    }

    val conversationStack = mutableListOf<IntentInstance>()
    var voice = speech.first().voice
    var phrases = speech.first().phrases
    var language = speech.first().language

    companion object {
        fun canSpeak(language : Language) : Boolean {
            return list.any { it.speech.any { language.equalsIgnoreDialect(language) } }
        }

        // Get the active persona
        val active : Persona
            get() {
                val activePersona = list.firstOrNull { it.active }
                return if (activePersona != null) {
                    activePersona
                }
                else {
                    // Default personaEntity
                    list.first()
                }
            }

        // Get a random, non-active persona
        val other : Persona
            get() {
                val inactivePersonas = list.filter { !it.active }
                return inactivePersonas.elementAt(
                        Random().nextInt(inactivePersonas.size)
                )
            }

        // Get all personas
        val list = mutableListOf(
                Persona(
                        name = "Bill",
                        gender = Gender.MALE,
                        description = "Your robot tourguide",
                        topics = listOf(
                                DoPresentationIntent(),
                                SpeakLanguageIntent(),
                                BePersonaIntent(),
                                ChatIntent(),
                                ShowRandomGesture(),
                                ShowGestureIntent(),
                                ShowLEDIntent(),
                                BeParrotIntent()
                        ),
                        nickNames = listOf("Billy"),
                        favoriteColor = "blue",
                        texture = "male",
                        speech = listOf(
                                Speech(
                                        ENGLISH_US,
                                        PollyVoice(name = "Matthew", language = ENGLISH_US, gender = Gender.MALE),
                                        DefaultPhrases()
                                ),
                                Speech(
                                        Language.GERMAN,
                                        PollyVoice(name = "Hans", gender = Gender.MALE, language = Language.GERMAN),
                                        GermanPhrases()
                                ),
                                Speech(
                                        Language.SPANISH_ES,
                                        PollyVoice(name = "Enrique", gender = Gender.MALE, language = Language.SPANISH_ES),
                                        SpanishPhrases()
                                ),
                                Speech(
                                        Language.FRENCH,
                                        PollyVoice(name = "Mathieu", gender = Gender.MALE, language = Language.FRENCH),
                                        FrenchPhrases()
                                ),
                                Speech(
                                        Language.PORTUGUESE_PT,
                                        PollyVoice(name = "Cristiano", gender = Gender.MALE, language = Language.PORTUGUESE_PT),
                                        PortuguesePhrases()
                                )
                        )
                ),
                Persona(
                        name = "Monica",
                        gender = Gender.FEMALE,
                        description = "A quiz master",
                        topics = listOf(
                                DoPresentationIntent(),
                                ChatIntent(),
                                PlayQuizIntent(),
                                BePersonaIntent()),
                        nickNames = listOf("Monica", "girl", "woman", "women"),
                        texture = "female",
                        age = 26,
                        favoriteColor = "yellow",
//                        favoriteArtist = MusicArtist.from("Lady Gaga"),
//                        favoriteFilm = Film.from("Pulp Fiction"),
                        speech = listOf(
                                Speech(
                                        language = ENGLISH_GB,
                                        voice = PollyVoice(name = "Joanna", language = ENGLISH_US, gender = Gender.FEMALE),
                                        phrases = MonicaPhrases()
                                ),
                                Speech(
                                        language = Language.SWEDISH,
                                        voice = PollyVoice(name = "Astrid", gender = Gender.FEMALE, language = Language.SWEDISH),
                                        phrases = SwedishPhrases()
                                ),
                                Speech(
                                        language = Language.MANDARIN,
                                        voice = PollyVoice(name = "Zhiyu", gender = Gender.MALE, language = Language.MANDARIN),
                                        phrases = ChinesePhrases()
                                )
                        )
                ),
                Persona(
                        name = "Doug",
                        gender = Gender.NEUTRAL,
                        description = "A semi-sweet puppy",
                        age = 3,
                        favoriteColor = "green",
                        topics = listOf(DoPresentationIntent(), ShowRandomGesture(), ShowGestureIntent(), BePersonaIntent()),
                        nickNames = listOf("Dougie", "puppy", "dog", "doug", "doggie", "hound", "the dog", "the sweet puppy"),
                        model = "pugz",
                        speech = listOf(
                                Speech(
                                        language = ENGLISH_GB,
                                        voice = PollyVoice(gender = Gender.MALE, language = ENGLISH_GB),
                                        phrases = DogPhrases()
                                )
                        )
                )
        )
    }
}

fun Furhat.setPersona(persona: Persona, initial : Boolean = false) {
    val oldPersona = Persona.active

    // Model, texture and active setting (if we changed persona)
    if (initial || oldPersona.name != persona.name) {
        if (oldPersona.model != persona.model) {
            setModel(persona.model, persona.texture)
        }
        else {
            setTexture(persona.texture)
        }

        // Set all personas as inactive
        Persona.list.forEach { it.active = false }

        // Set current personaEntity as active
        persona.active = true
    }

    // Voice, language & phrases settings, we always change this
    voice = persona.voice
    setInputLanguage(ENGLISH_US)
    phrases = persona.phrases
}