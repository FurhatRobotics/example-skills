package furhatos.app.chitchat.snippets

import furhatos.nlu.common.PersonName
import furhatos.snippets.RobotLabel
import furhatos.snippets.Topic
import furhatos.snippets.snippets

object RA_PetName : RobotLabel()
object RA_HasPet : RobotLabel()
object RT_HasNoPet : RobotLabel()

object PetsTopic : Topic()

val pets = snippets(PetsTopic) {

    val entities = object {
        var name: PersonName? = null
    }

    entities(entities)

    snippet {
        // You can associate labels to robot and user utterances like this
        request("Do you have any pets?") label RA_HasPet
        switch {
            user(UT_Yes) branch {
                respond("How nice")
                request("What is the name of your pet?") implies RA_PetName
            }
            user(UT_No) branch {
                respond("I see")
                request("Would you like to have a pet?")
                switch {
                    user(UT_Yes) branch {
                        request("What name would you give it?") implies RA_PetName
                    }
                    user(UT_No) branch {
                        respond("I understand, pets require a lot of attention")
                    }
                }
            }
        }
    }

    // Snippets that start with a user utterance can always be triggered
    snippet {
        user("Do you have any pets?")
        respond("No, unfortunately not, I would love a robot dog") label RT_HasNoPet
        // Bridges are used before an ask
        // It will not be used if the ask is not used (remember that the robot never asks the same question twice)
        bridge("How about you")
        request(RA_HasPet)
    }

    snippet {
        // context allow you to handle multiple possible user responses to a robot utterance
        context(RT_HasNoPet)
        user("why")
        respond("I could go out and walk with it")
    }

    snippet {
        context(RA_PetName)
        user("@name")
        respond {+"${entities.name}, That's a very nice name"}
    }


}