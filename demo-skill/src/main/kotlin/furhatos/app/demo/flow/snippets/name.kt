package furhatos.app.chitchat.snippets

import furhatos.app.demo.personas.Persona
import furhatos.flow.kotlin.UserDataDelegate
import furhatos.nlu.common.PersonName
import furhatos.records.User
import furhatos.snippets.RobotLabel
import furhatos.snippets.UserLabel
import furhatos.snippets.sessionLimit
import furhatos.snippets.snippets

object UT_Name : UserLabel()
object UA_Name : UserLabel()

object RA_Name : RobotLabel({user.name == null})
object RT_Name : RobotLabel()

var User.name : String? by UserDataDelegate()

val name = snippets {

    val entities = object {
        var name : PersonName? = null
    }

    entities(entities)

    snippet {
        request("What is your name?") label RA_Name
        user("@name") implies UT_Name
    }

    snippet {
        context(RA_Name)
        user("First or last?")
        respond("First")
    }

    snippet {
        context(RA_Name)
        user("I don't want to tell you")
        respond("Okay, let's remain anonymous")
    }

    snippet {
        user("My name is @name") label UT_Name effect {
            user.name = entities.name?.value
        }
        respond { +"Nice talking to you ${user.name}" }
        respond(RT_Name) cond sessionLimit(1)
    }

    snippet {
        user("What is your name") label UA_Name
        respond {+"My name is ${Persona.active.name}"} label RT_Name
        request("Could you tell me your name") label RA_Name
    }

    snippet {
        context(RT_Name)
        user(UA_Why) implies UA_WhyFurhat
    }

}