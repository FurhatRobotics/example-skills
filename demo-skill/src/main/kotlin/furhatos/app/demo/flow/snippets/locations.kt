package furhatos.app.chitchat.snippets

import furhatos.flow.kotlin.UserDataDelegate
import furhatos.nlu.wikidata.City
import furhatos.records.User
import furhatos.snippets.RobotLabel
import furhatos.snippets.UserLabel
import furhatos.snippets.snippets

object UA_WhyFurhat : UserLabel()

object RA_Age : RobotLabel()

object RA_WhichTopic : RobotLabel()

var User.city : String? by UserDataDelegate()

val locations = snippets {

    val entities = object {
        var city : City? = null
    }

    entities(entities)

    snippet {
        request("In which city do you live?")
        user("@city", "I live in @city") effect {
            user.city = entities.city?.name
        }
        respond {+"${entities.city}, I have heard it's a very nice city"}
        respond {+"I don't think I have been to ${entities.city?.country}"}
    }



}

