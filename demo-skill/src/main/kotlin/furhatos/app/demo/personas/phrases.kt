package furhatos.app.demo.personas

import furhatos.app.demo.util.getRandomString
import furhatos.flow.kotlin.Audio
import furhatos.flow.kotlin.Utterance
import furhatos.flow.kotlin.utterance
import furhatos.gestures.Gestures
import furhatos.util.joinToEnum

// A global variable used for convenience
var phrases : Phrases = DefaultPhrases()

abstract class Phrases {
    abstract val howCanIhelp : String
    abstract fun canIhelpWithAnythingElse() : String
    abstract val okay : String
    abstract val yes : String
    abstract val no : String
    abstract val greeting : String
    abstract val goodbye : String
    abstract fun presentOptions(options : List<String>) : String
    abstract fun presentAdditionalOptions(options : List<String>) : String
    abstract fun personaChange(name: String) : String
    abstract val pleaseTakeMaskOff : String
    abstract fun pleaseChangeMask(maskName: String) : String
    abstract fun gestureOrder(gestureName: String) : String
    abstract val hereAreRandomGestures : String
    abstract val canDoThat : String
    abstract val canNotDoThat : String
    abstract val alreadyDoingThat : String
    abstract val dontYouRecognizeMe : String
    abstract val parrotStart: String
    abstract val saySomething: String
    abstract val okayIWillStop: String
    abstract val noPersonaWantsThisIntent: String
    abstract fun personaPassLanguage(personas: List<Persona>) : String
    abstract fun personaPassIntent(personas: List<Persona>) : String
    abstract val presentation: List<Utterance>
    abstract fun bridgeToNewLanguage(lang: String): String
    abstract val exampleSentance: String
}

open class DefaultPhrases : Phrases() {
    override val okay: String = "okay"
    override val yes: String = getRandomString(listOf(
            "yes",
            "yeah"))
    override val no: String = "no"
    override val howCanIhelp = getRandomString(listOf(
            "how can I help?",
            "what can I do for you?",
            "What can I do to help?",
            "How can I help you?"))
    override fun presentOptions(options: List<String>) = getRandomString(listOf(
            "you can for example ask me ",
            "you can ask me "
    )) + options.joinToEnum("or")
    override fun presentAdditionalOptions(options: List<String>) = "you can also ask me " + options.joinToEnum("or")
    override fun canIhelpWithAnythingElse() = getRandomString(listOf(
            "Can I do anything else for you?",
            "What else can I do for you?",
            "Is there anything else I can do for you?"))
    override val greeting = getRandomString(listOf(
            "hello there!",
            "Hi there!",
            "Hello!"))
    override val goodbye = getRandomString(listOf(
            "Bye!",
            "Goodbye!",
            "Bye bye,"))
    override fun personaChange(name: String) = getRandomString(listOf(
            "ok, here comes my friend ${name}",
            "ok, here's my friend ${name}",
            "ok, make way for my buddy ${name}"))
    override val pleaseTakeMaskOff = "Can you please take off my mask? Trust me on this"
    override fun pleaseChangeMask(maskName: String) = "ok, please put on the ${maskName} mask"
    override fun gestureOrder(gestureName: String) = "ok, here comes a $gestureName"
    override val hereAreRandomGestures = getRandomString(listOf(
            "here are some random gestures",
            "here's a few gestures of mine",
            "some gestures coming up"
    ))
    override val canDoThat = getRandomString(listOf(
            "I can",
            "that, I can do",
            "absolutely"
    ))
    override val canNotDoThat = "sorry, I can't do that"
    override val alreadyDoingThat = "I'm already doing that"
    override val dontYouRecognizeMe: String = "But it's me, don't you recognize me?"
    override val parrotStart = "Okay, I'll repeat you like a parrot"
    override val saySomething = "Say something"
    override val okayIWillStop = "Okay, I will stop"
    override fun personaPassLanguage(personas: List<Persona>) =
            if (personas.size == 1) {
                val persona = personas.first()
                "Well I can't speak it, but I know my friend ${persona.name} can. Do you wanna talk to ${persona.grammarObject}?"
            }
            else {
                "Well I can't speak it, but I know my friends ${personas.map { it.name }.joinToEnum("and")} can. Do you wanna talk to one of them?"
            }
    override fun personaPassIntent(personas: List<Persona>) =
            if (personas.size == 1) {
                val persona = personas.first()
                "I don't feel like it, but I know my friend ${persona.name} does. Do you wanna talk to ${persona.grammarObject}?"
            }
            else {
                "I don't feel like it, but I know my friends ${personas.map { it.name }.joinToEnum("and")} do. Do you wanna talk to one of them?"
            }
    override val presentation = listOf(
            utterance {
                +"I'm a "
                Gestures.BrowRaise
                +"social robot "
                +Gestures.Smile
                +"that can "
                +Gestures.BrowRaise
                +"interact with people, "
                + "in real world situations. "},
            utterance {
                +"You might have seen me"
                +Gestures.BrowRaise
                +"guiding people "
                +"at Frankfurt Airport. "},
            utterance {
                +"Next week, "
                +"I will be "
                +Gestures.BrowRaise
                +"screening people, "
                +"for diabetes"
                +"and other conditions. "},
            utterance {
                +"I'm also learning to "
                Gestures.BrowRaise
                + "interview job candidates. "},
            utterance {
                +"There is "
                +Gestures.BrowRaise
                +"so much to do."
                +Gestures.Smile
                +"And I think I could do "
                +"more." },
            utterance {
                +"for example, I would like to work in a "
                +"store, "
                +"in a hotel "
                +"or maybe at a coffee shop?"}
    )
    override val exampleSentance = "I am a speaking robot"
    override val noPersonaWantsThisIntent: String = "Unfortunately, none of my personas wan't to do this"
    override fun bridgeToNewLanguage(lang: String): String = getRandomString(listOf(
            "I heard you wanted to hear some $lang?",
            "You wanted to hear me sport my $lang skills?"
    ))
}

open class DogPhrases : DefaultPhrases() {
    override val okay: String = "okay"
    override val yes: String = "yep"
    override val no: String = "nope"
    override val howCanIhelp = "what can I do for you, human?"
    override fun canIhelpWithAnythingElse() = getRandomString(listOf(
            "Anything else I can do, friend?",
            "Anything more I can help with?"))
    override val greeting = "hello there!"
    override val goodbye = "take care!"
    override fun personaChange(name: String) = "ok, here comes my buddy ${name}"
    override fun gestureOrder(gestureName: String) = "ok, here's a $gestureName"
    override val hereAreRandomGestures = "here are some random gestures"
    override val canDoThat = "I can"
    override val canNotDoThat = "sorry, I can't do that"
    override val alreadyDoingThat = "I'm already doing that"
    override val dontYouRecognizeMe: String = "But it's me, don't you recognize me?"
    override val parrotStart = "Okay, I'll repeat you like a dog. I mean parrot"
    override val saySomething = "Say something"
    override val okayIWillStop = "Okay, I will stop"
    override val presentation = listOf(
        utterance {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/ffb5bb0d-22c3-4500-b491-6cdfebb671f8/audio/dougi1.wav",
                    "Hello, my name is Dougie the dog.")
        },
        utterance {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/ffb5bb0d-22c3-4500-b491-6cdfebb671f8/audio/Dougie2.wav",
                    "My favortie things t do are eat. Num num num num num.")
        },
        utterance {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/ffb5bb0d-22c3-4500-b491-6cdfebb671f8/audio/Dougie3.wav",
                    "Sniff, and lie around the house")
        },
        utterance {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/ffb5bb0d-22c3-4500-b491-6cdfebb671f8/audio/Dougie4.wav",
                    "Some people think I'm lazy. Huh!")
        },
        utterance {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/ffb5bb0d-22c3-4500-b491-6cdfebb671f8/audio/Dougie5.wav",
                    "every now and then I like to go chasing after a ball or a cat rof rof rof rof rof.")
        },
        utterance {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/ffb5bb0d-22c3-4500-b491-6cdfebb671f8/audio/Dougie6.wav",
                    "well, anyway, its been nice talking to you I got to go take a nap now. rof rof..")
        }
    )
}

open class SpanishPhrases : DefaultPhrases() {
    override val okay: String = "bien"
    override val yes: String = "si"
    override val no: String = "no"
    override val howCanIhelp = "¿Cómo puedo ayudar?"
    override fun canIhelpWithAnythingElse() = getRandomString(listOf(
            "¿Algo más?",
            "¿Qué más puedo hacer por ti?",
            "¿Que tal ahora?"))
    override val greeting = "¡Hola!"
    override val goodbye = "¡adiós!"
    override fun personaChange(name: String) = "Ok, aquí viene mi amigo $name"
    override fun gestureOrder(gestureName: String) = "ok, aquí viene un $gestureName"
    override val hereAreRandomGestures = "aquí hay gestos al azar"
    override val canDoThat = "puedo"
    override val canNotDoThat = "lo siento, no puedo hacer eso"
    override val alreadyDoingThat = "pero ya lo estoy haciendo"
    override val dontYouRecognizeMe: String = "Pero soy yo, ¿no me reconoces?"
    override val parrotStart = "De acuerdo, te repetiré como un loro"
    override val saySomething = "Di algo"
    override val okayIWillStop = "de acuerdo, me detendré"
    override val exampleSentance = "Me gusta el español, creo que es uno de mis idiomas favoritos"
}

open class SwedishPhrases : DefaultPhrases() {
    override val okay: String = "okej"
    override val yes: String = "ja"
    override val no: String = "nej"
    override val howCanIhelp = "kan jag göra något för dig?"
    override fun canIhelpWithAnythingElse() = "kan jag hjälpa dig med något mer?"
    override val greeting = "Hallå där!"
    override val goodbye = "hejdå!"
    override fun personaChange(name: String) = "okej, här kommer $name"
    override fun gestureOrder(gestureName: String) = "okej, här kommer $gestureName"
    override val hereAreRandomGestures = "Här är några exempel på gester"
    override val canDoThat = "Det kan jag"
    override val canNotDoThat = "Tyvärr kan jag inte göra det"
    override val alreadyDoingThat = "Jag gör ju redan det"
    override val dontYouRecognizeMe: String = "Men det är ju jag, känner du inte igen mig?"
    override val parrotStart = "Okej, jag härmar dig som en papegoja"
    override val saySomething = "Säg någonting"
    override val okayIWillStop = "Okej, jag slutar"
    override val presentation = listOf(
            utterance {
                +"I'm Elin a store assistant from sweden. "
                +Gestures.BigSmile
            },
            utterance {
                +"Det verkar inte som vi har den varan i lager, men jag kan se att den finns i vår andra butik, ska jag reservera den åt dig?"
                +Gestures.BrowRaise
            },
            utterance {
                +"that was me explaining to a customer that the item they were looking at was not in stock, but I could reserve it for them at our other branch"
                +Gestures.Smile
            }
    )
    override val exampleSentance = "Svenska är mitt modersmål och är därför ett av mina favoritspråk"
}

open class GermanPhrases : DefaultPhrases() {
    override val okay: String = "in Ordnung"
    override val yes: String = "ja"
    override val no: String = "keine"
    override val howCanIhelp = "kann ich etwas für Sie tun?"
    override fun canIhelpWithAnythingElse() = "Kann ich Ihnen bei etwas mehr helfen?"
    override val greeting = "hallo!"
    override val goodbye = "Auf Wiedersehen!"
    override fun personaChange(name: String) = "Okay, hier kommt $name"
    override fun gestureOrder(gestureName: String) = "Okay, hier kommt $gestureName"
    override val hereAreRandomGestures = "Hier sind zufällige Gesten"
    override val canDoThat = "Ich kann das"
    override val canNotDoThat = "Leider kann ich das nicht"
    override val alreadyDoingThat = "Ich mache das schon"
    override val dontYouRecognizeMe = "Aber ich bin es, erkennst du mich nicht?"
    override val parrotStart = "Okay, ich ahme dich wie ein Papagei nach"
    override val saySomething = "Sag etwas"
    override val okayIWillStop = "Okay, ich höre auf"
    override val presentation = listOf(
            utterance {
                + "I'm Klaus a german tour guide here in Lisbon."
                +Gestures.Smile
            },
            utterance{
                +"Die Tram Nummer 28 ist eine klassische gelbe Tram, die durch die engen Straßen Lissabons rattert und knattert. Und kein Besuch ist komplett ohne eine Fahrt in einer dieser wunderlichen Trams."
            },
            utterance {
                +"That was me recommending the classic yellow tram line number 28 here in Lisbon. "
                +Gestures.Smile
            }
    )
    override val exampleSentance = "Deutsch ist eigentlich eine meiner Lieblingssprachen."
}

open class MonicaPhrases : DefaultPhrases() {
    override val okay: String = "okay"
    override val yes: String = "yes"
    override val no: String = "no"
    override val howCanIhelp = getRandomString(listOf(
            "how can I help?",
            "What can I do to help?",
            "How can I help you?"))
    override fun presentOptions(options: List<String>) = getRandomString(listOf(
            "you can for example ask me ",
            "you can ask me "
    )) + options.joinToEnum("or")
    override fun presentAdditionalOptions(options: List<String>) = "you can also ask me " + options.joinToEnum("or")
    override fun canIhelpWithAnythingElse() = getRandomString(listOf(
            "May I do anything else for you?",
            "What else can I do for you?"))
    override val greeting = getRandomString(listOf(
            "hello",
            "Hello there",
            "Hello!"))
    override val goodbye = getRandomString(listOf(
            "Bye!",
            "Goodbye!"))
    override fun personaChange(name: String) =  getRandomString(listOf(
            "ok, here is ${name}",
            "ok, here's my friend ${name}"))
    override val pleaseTakeMaskOff = "Can you remove my mask, please. I'll need you to trust me on this"
    override fun pleaseChangeMask(maskName: String) = "ok, now put on the $maskName mask, please"
    override fun gestureOrder(gestureName: String) = "ok, here is a $gestureName"
    override val hereAreRandomGestures = "here are some examples of expressions"
    override val canDoThat = "I can"
    override val canNotDoThat = "sorry, I can't do that"
    override val alreadyDoingThat = "I'm already doing that"
    override val dontYouRecognizeMe: String = "I'm here, can't you recognize me?"
    override val parrotStart = "Okay, I'll repeat you like a parrot"
    override val saySomething = "Say something"
    override val okayIWillStop = "Okay, I will stop"
    override fun personaPassLanguage(personas: List<Persona>) =
            if (personas.size == 1) {
                val persona = personas.first()
                "I can't speak it, but I know my friend ${persona.name} can. Do you wanna talk to ${persona.grammarObject}?"
            }
            else {
                "I can't speak it, but I know my friends ${personas.map { it.name }.joinToEnum("and")} can. Do you wanna talk to one of them?"
            }
    override fun personaPassIntent(personas: List<Persona>) =
            if (personas.size == 1) {
                val persona = personas.first()
                "I don't feel like it, but I know my friend ${persona.name} does. Do you wanna talk to ${persona.grammarObject}?"
            }
            else {
                "I don't feel like it, but I know my friends ${personas.map { it.name }.joinToEnum("and")} does. Do you wanna talk to one of them?"
            }
    override val presentation = listOf(
            utterance {
                +"Hi there! I'm Monica,"
                Gestures.BrowRaise
                +"I love playing quizzes"
                +Gestures.Smile
                +"and knows a few exotic languages."
                +Gestures.BrowRaise
                +"Maybe we can play a game later?"
            }
    )
    override val exampleSentance = "I am a speaking robot"
}

open class PortuguesePhrases : DefaultPhrases() {
    override val exampleSentance = "Português é uma boa língua, uma das minhas línguas favoritas."
}

open class ChinesePhrases : DefaultPhrases() {
    override val exampleSentance = "我爱讲中文, 我对中国很感兴趣"
}

open class FrenchPhrases : DefaultPhrases() {
    override val exampleSentance = "Le français, est définitivement une de mes langues préférées"
}

