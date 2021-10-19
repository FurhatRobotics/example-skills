package furhatos.app.chitchat.snippets

import furhatos.snippets.NoMatch
import furhatos.snippets.RobotLabel
import furhatos.snippets.snippets

object RT_HasntSeenUser : RobotLabel()
object RA_ComeHereOften : RobotLabel()

val smalltalk = snippets {

    snippet {
        request("Do you come here often?") label RA_ComeHereOften
        switch {
            user(UT_Yes) branch {
                respond("Really? I haven't seen you before") label RT_HasntSeenUser
            }
            user(UT_No) branch {
                respond("You should, it's a great place")
            }
        }
    }

    snippet {
        context(RT_HasntSeenUser)
        switch {
            user("I haven't seen you either")
            user(NoMatch)
        }
        respond("Well, I am glad we are both here now")
        proceed()
    }

    snippet {
        context(RT_HasntSeenUser)
        user(UA_Why)
        respond("Maybe we come at different times")
        bridge("Well anyway")
        proceed()
    }

}

