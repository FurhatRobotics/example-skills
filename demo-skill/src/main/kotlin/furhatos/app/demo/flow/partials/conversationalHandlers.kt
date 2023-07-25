package furhatos.app.demo.flow.partials

import furhatos.app.demo.flow.*
import furhatos.app.demo.flow.actions.ChatState
import furhatos.app.demo.imported.quiz.Idle
import furhatos.app.demo.nlu.*
import furhatos.app.demo.personas.Persona
import furhatos.app.demo.personas.phrases
import furhatos.app.demo.personas.setPersona
import furhatos.app.demo.util.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.joinToEnum
import java.util.concurrent.TimeUnit

val conversationalHandlers = partialState {
    onResponse<RequestPersonasIntent> {
        val me = Persona.active
        val others = Persona.list.filter { it.name != me.name }

        furhat.say("Well, aside from me ${me.nickNames.first()}")
        furhat.ask("I can be ${others.map { "${it.name}, ${it.description}" }.joinToEnum("and")}")
    }

    onResponse<RequestLanguagesIntent> {
        val myLanguages = Persona.active.speech.map { it.language.getSpokenForm() }.distinct()
        val allLanguages = Persona.list.map { it.speech.map { it.language.getSpokenForm() } }.flatten().distinct()
        val otherLanguages = allLanguages.subtract(myLanguages).toList()

        furhat.say("I can speak ${myLanguages.joinToEnum("and")}")
        furhat.ask("my other personas can speak ${otherLanguages.joinToEnum("and")}")
    }

    onResponse<ChatIntent> {
        if (Persona.active.wantsToSpeakAbout(it.intent)) {
            furhat.say("okay, let's chat")
            goto(ChatState)
        }
        else {
            goto(PersonaPass(it.intent))
        }
    }

    onResponse<RequestOptionsIntent> {
        val topics = Persona.active.topics.subList(0,4).distinctBy { it.description }
        furhat.ask {
            +phrases.presentOptions(topics.map { it.description })
        }
    }

    onResponse<RequestOtherOptionsIntent> {
        val topics = Persona.active.topics.distinctBy { it.description }
        when {
            topics.size <= 4 -> {
                furhat.ask("Nothing else, right now. But maybe ask my other personas") // Add a session variable where it keeps track off if the RequestOptions has been asked, and if not presents the original set here..
            }
            else -> {
                furhat.ask {
                    +phrases.presentAdditionalOptions(topics.subList(4,topics.size).map { it.description })
                }
            }
        }
    }

    onResponse<ChangePersonaIntent> {
        raise(it, RequestPersonasIntent())
    }

    onResponse<BePersonaIntent> {
        val persona = it.intent.personaEntity?.persona
        if (persona != null) {
            if (Persona.active != persona) {
                if (Persona.active.model != persona.model) {
                    goto(ModelChange(persona, Active()))
                }
                else {
                    raise(PersonaChange(persona))
                }
            } else {
                //furhat.say(phrases.dontYouRecognizeMe) Since this intent seems to trigger alot
                reentry()
            }
        }
    }

    onEvent<PersonaChange> {
        val persona = it.persona
        if (persona != null) {
            furhat.say(phrases.personaChange(persona.name))
            delay(1000)
            furhat.setPersona(persona = persona)
            furhat.say(phrases.greeting)
        }
        goto(Active())
    }

    onResponse<SpeakLanguageIntent> {
        val language = it.intent.languageEntity?.language!!
        when {
            Persona.active.language.equalsIgnoreDialect(language) -> {
                furhat.say(phrases.alreadyDoingThat)
                reentry()
            }
            Persona.active.canSpeak(language) -> {
                furhat.say(phrases.canDoThat)
                raise(LanguageChange(it.intent.languageEntity?.language))
            }
            Persona.canSpeak(language) -> {
                goto(PersonaPass(language = language))
            }
            else -> {
                furhat.say(phrases.canNotDoThat)
                reentry()
            }
        }
    }

    onEvent<LanguageChange> {
        if (it.language != null) {
            val persona = Persona.active
            persona.setSpeech(lang = it.language)
            furhat.setPersona(persona = persona)
            furhat.say(phrases.exampleSentance)
            delay(1000)
            persona.setSpeech(lang = persona.speech.first().language)
            furhat.setPersona(persona)
        }
        reentry()
    }

    onResponse<ShowRandomGesture> {
        if (Persona.active.wantsToSpeakAbout(it.intent)) {
            furhat.say(phrases.hereAreRandomGestures)
            val numGestures = 3
            Gestures.getRandom(numGestures).forEachIndexed { index, gesture ->
                furhat.gesture(gesture, async = false)
                furhat.say {
                    if (index == numGestures - 1) {
                        +"and"
                    }
                    random {
                        +"that gesture is called"
                        +"that was my gesture"
                        +"that was me doing a"
                    }
                    +(gesture.name?.camelCaseToSpaces() ?: "")
                }
            }
            reentry()
        }
        else {
            goto(PersonaPass(it.intent))
        }
    }

    onResponse<ShowGestureIntent> {
        if (Persona.active.wantsToSpeakAbout(it.intent)) {
            val gestureName = it.intent.gestureEntity
            if (gestureName != null) {
                val gesture = Gestures.getByName(gestureName.value!!)
                if (gesture != null) {
                    furhat.say(phrases.gestureOrder(gestureName.text))
                    furhat.gesture(gesture, async = false)
                } else {
                    furhat.say(phrases.canNotDoThat)
                }
            }
            reentry()
        }
        else {
            goto(PersonaPass(it.intent))
        }
    }

    onResponse<ShowLEDIntent> {
        if (Persona.active.wantsToSpeakAbout(it.intent)) {
            val color = it.intent.color
            furhat.setLED("off")
            if (color != null) {
                furhat.say {
                    random {
                        +"$color is nice"
                        +"$color"
                        +"$color you say?"
                    }
                }
                if (furhat.ledColors.contains(color.text.toLowerCase())) {
                    furhat.setLED(color.text)
                    furhat.ask {
                        random {
                            +"here you go!"
                            +"why not!"
                            +"today is your lucky day!"
                        }
                        random {
                            +Gestures.Wink
                            +Gestures.BigSmile
                        }
                    }
                }
                else {
                    furhat.ask("unfortunately I can't show it yet though")
                }
            }
            else {
                val favoriteColor = Persona.active.favoriteColor
                furhat.ask{
                    +"sure! my favorite color is $favoriteColor"
                    +behavior { furhat.setLED(favoriteColor) }
                    +"Like this"
                    +Gestures.BigSmile
                }
            }
        }
        else {
            goto(PersonaPass(it.intent))
        }
    }

    onResponse<TurnOffLEDsIntent> {
        furhat.ask {
            +phrases.okay
            +behavior { furhat.setLED("White") }
        }
    }

    onResponse<BeParrotIntent> {
        if (Persona.active.wantsToSpeakAbout(it.intent)) {
            call(ParrotMode)
            delay(time = 300, timeUnit = TimeUnit.MILLISECONDS)
            reentry()
        }
        else {
            goto(PersonaPass(it.intent))
        }
    }

    onResponse<DoPresentationIntent> {
        if (Persona.active.wantsToSpeakAbout(it.intent)) {
            furhat.say(phrases.okay)
            call(Presentation)
            reentry()
        }
        else {
            goto(PersonaPass(it.intent))
        }
    }

    onResponse<PlayQuizIntent> {
        if (Persona.active.wantsToSpeakAbout(it.intent)) {
            furhat.say("oh, fun")
            goto(RequireUsers(Idle))
        }
        else {
            goto(PersonaPass(it.intent))
        }
    }
}