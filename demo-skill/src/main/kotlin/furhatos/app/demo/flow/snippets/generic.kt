package furhatos.app.chitchat.snippets

import furhatos.flow.kotlin.UserDataDelegate
import furhatos.nlu.common.No
import furhatos.nlu.common.RequestRepeat
import furhatos.nlu.common.Thanks
import furhatos.nlu.common.Yes
import furhatos.records.User
import furhatos.snippets.*
import furhatos.snippets.UtteranceType.Request
import furhatos.snippets.UtteranceType.Response

object UA_Repeat : UserLabel(RequestRepeat())
object UT_Thanks : UserLabel(Thanks())
object UT_Yes : UserLabel(Yes())
object UT_No : UserLabel(No())

object UA_Why : UserLabel(listOf("why", "how come", "why is that"))

object RA_Repeat_1 : RobotLabel()
object RA_Repeat_2 : RobotLabel()

var User.age : Int? by UserDataDelegate()

val generic = snippets {

    snippet {
        context(Request)
        user(NoMatch)
        choice {
            respond("Sorry, I didn't understand that") label RA_Repeat_1 cond noRepeat(RA_Repeat_2) branch {
                repeat(2)
            }
            respond("Sorry, I still didn't understand that") label RA_Repeat_2 cond noRepeat() branch {
                repeat(2)
            }
            respond("Well, anyway") branch {
                proceed()
            }
        }
    }

    snippet {
        context(Request)
        user(NoInput)
        choice {
            repeat(1) cond noRepeat()
            proceed()
        }
    }

    snippet {
        context(Response)
        user(NoInput)
        proceed()
    }

    snippet {
        context(Response)
        user(NoMatch)
        respond("Yeah")
        proceed()
    }

    snippet {
        context(Response)
        user(UT_Thanks)
        respond("You are welcome")
        proceed()
    }

    snippet {
        context(Response)
        user("that is cool", "that is great")
        respond("Yeah, isn't it")
        proceed()
    }

    snippet {
        user(UA_Repeat)
        repeat(1)
    }


}