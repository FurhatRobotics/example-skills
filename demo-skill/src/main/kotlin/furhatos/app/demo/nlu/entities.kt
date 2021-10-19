package furhatos.app.demo.nlu

import furhatos.app.demo.personas.Persona
import furhatos.app.demo.util.camelCaseToSpaces
import furhatos.gestures.Gestures
import furhatos.nlu.EnumEntity
import furhatos.nlu.EnumItem
import furhatos.util.Language

class LanguageEntity(val language: Language? = null) : EnumEntity() {
    override fun getEnumItems(lang: Language): List<EnumItem> {
        // All distinct languages, for now I have to use reflection
        val langs = Language().javaClass.fields.filter {
            it.name != "sampleText" // Get all the language field names
        }.map {
            it.get(it) as Language // Cast them to Language class instances
        }.filter {
            it.name != null // Filter out anyone that lacks a name
        }.map {
            // Create EnumItems with the language variable set
            EnumItem(LanguageEntity(language = it),
                    it.name.substringBefore("(", it.name).trim().toLowerCase())
        }
        return langs
    }
}

class PersonaEntity(val persona: Persona? = null) : EnumEntity(speechRecPhrases = true) {
    override fun getEnumItems(lang: Language): List<EnumItem> {
        return Persona.list.flatMap { persona ->
            (listOf(persona.name) + persona.nickNames).map {name ->
                EnumItem(PersonaEntity(persona = persona), name)
            }
        }
    }
}

class GestureEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return Gestures.getGestureNames().map {
            it + ":" + it.camelCaseToSpaces()
        }
    }
}

class ChatEntity : EnumEntity() {
    override fun getEnum(lang: Language): List<String> {
        return listOf(
                "chat:chat,chatting", "talk:talk,talking", "discuss:discuss,discussing"
        )
    }
}