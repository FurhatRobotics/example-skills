package furhatos.app.demo.flow

import furhatos.app.demo.log
import furhatos.app.demo.nlu.*
import furhatos.app.demo.personas.Persona
import furhatos.app.demo.personas.phrases
import furhatos.app.demo.personas.setPersona
import furhatos.app.demo.util.AttendUsers
import furhatos.app.demo.util.getSpokenForm
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.raise
import furhatos.flow.kotlin.state
import furhatos.gestures.Gestures
import furhatos.nlu.Intent
import furhatos.nlu.Response
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.Language
import furhatos.util.joinToEnum

fun PersonaPass(intent: Intent? = null, language: Language? = null) = state {
    include(wizardButtons)

    var eligiblePersonas : List<Persona> = listOf()

    onEntry {
        send(AttendUsers(shouldAlterAttentionOnSpeech = false))
        if (intent != null) {
            eligiblePersonas = Persona.list.filter { it.topics.contains(intent) }
            if (eligiblePersonas.isEmpty()) { // This fallback should really never happen
                log.warn("You have an intent ${intent.intentName} that isn't assigned to a persona, you probably want to fix this")
                furhat.say(phrases.noPersonaWantsThisIntent)
                goto(Active(returning = true))
            }
            furhat.ask(phrases.personaPassIntent(eligiblePersonas))
        }
        else if (language != null) {
            eligiblePersonas = Persona.list.filter { it.canSpeak(language) }
            furhat.ask(phrases.personaPassLanguage(eligiblePersonas))
        }
    }

    onReentry {
        furhat.ask("who do you want to talk to?")
    }

    onResponse<RequestOptionsIntent> {
        furhat.ask(eligiblePersonas.map { it.name }.joinToEnum("and"))
    }

    onResponse<Yes> {
        if (eligiblePersonas.size == 1) {
            raise(it, PersonaEntity(persona = eligiblePersonas.first()))
        }
        reentry()
    }

    onResponse<ExitIntent> {
        raise(it, No())
    }

    onResponse<No> {
        furhat.say("Ok, no worries")
        goto(Active(returning = true))
    }

    onResponse<WhateverIntent> {
        raise(it, PersonaEntity(persona = eligiblePersonas.first()))
    }

    onResponse<PersonaEntity> {
        raise(it, BePersonaIntent(personaEntity = it.intent))
    }

    onResponse<BePersonaIntent> {
        val persona = it.intent.personaEntity!!.persona!!
        when {
            eligiblePersonas.contains(persona) -> {
                if (language != null) {
                    furhat.setPersona(persona)
                    furhat.say(phrases.bridgeToNewLanguage(language.getSpokenForm()))
                    persona.setSpeech(language)
                    furhat.setPersona(persona)
                    delay(300)
                    furhat.say(phrases.exampleSentance)
                    delay(1000)
                    persona.setSpeech(lang = persona.speech.first().language)
                    furhat.setPersona(persona)
                    delay(1000)
                    goto(Active(returning = true))
                }
                else {
                    val nextState = Active(Response.newResponse(intent, it.speech))
                    if (Persona.active.model == persona.model) {
                        furhat.say(phrases.personaChange(persona.name))
                        delay(1000)
                        furhat.setPersona(persona)
                        furhat.say(phrases.greeting)
                        goto(nextState)
                    }
                    else {
                        goto(ModelChange(persona, nextState))
                    }
                }
            }
            Persona.active == persona -> {
                furhat.ask {
                    +Gestures.ExpressSad
                    +"Aren't you listening? I don't want to"
                }
            }
            else -> {
                furhat.ask {
                    +"${persona.name} doesn't want it either"
                }
            }
        }
    }
}