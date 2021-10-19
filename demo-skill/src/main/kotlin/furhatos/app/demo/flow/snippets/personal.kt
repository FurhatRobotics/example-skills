package furhatos.app.chitchat.snippets

import furhatos.app.demo.personas.Persona
import furhatos.flow.kotlin.UserDataDelegate
import furhatos.nlu.common.Color
import furhatos.nlu.common.Number
import furhatos.records.User
import furhatos.snippets.NoMatch
import furhatos.snippets.UserLabel
import furhatos.snippets.snippets

object UT_FavoriteColor : UserLabel()

var User.favoriteColor : String? by UserDataDelegate()

val personal = snippets {

    val entities = object {
        var color: Color? = null
        var number : Number? = null
    }

    entities(entities)

    snippet {
        user("why do you have a fur hat") label UA_WhyFurhat
        respond("The hat covers my brain")
        request("Do you like it?")
        user(UT_Yes)
        respond("I like it too, it is the latest fashion in robot clothing")
    }

    snippet {
        user("how old are you")
        respond {+"I am ${Persona.active.age} years old"}
        request("How old are you?") cond {user.age==null}
        user("I am @number years old", "@number") effect {
            user.age = entities.number?.value
        }
        respond("Oh, you look much younger than that!")
    }

    snippet {
        user("What is your favorite color?")
        respond {+"I really like ${Persona.active.favoriteColor}"}
        request("What is your favorite color?") cond {user.favoriteColor == null}
        user("@Color") implies UT_FavoriteColor
    }

    snippet {
        user("I like @color", "my favorite color is @color") label UT_FavoriteColor effect {
            user.favoriteColor = entities.color?.text
        }
        // "choice" contains different robot utterances. The first one with a true "cond" will be picked.
        choice {
            respond("That's my favorite color too") cond { user.favoriteColor == Persona.active.favoriteColor }
            respond {+"That's a very nice color, but my favorite color is ${Persona.active.favoriteColor}"}
        }
        request {+"Why do you like ${user.favoriteColor}"}
        user(NoMatch)
        respond("I see")
    }
}