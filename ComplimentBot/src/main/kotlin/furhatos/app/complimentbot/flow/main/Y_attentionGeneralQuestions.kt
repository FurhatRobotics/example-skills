package furhatos.app.complimentbot.flow.main

import furhat.libraries.standard.GesturesLib
import furhatos.app.complimentbot.nlu.CanIGetCompliment
import furhatos.app.complimentbot.utils.hasBeenGreeted
import furhatos.flow.kotlin.*
import furhatos.nlu.common.AskName

val attentionGeneralIntents = partialState {

    onResponse<AskName>{
        //goto interaction here seems risky
        furhat.say {
            +"I'm a furhat robot, "
            random {
                +"but you can call me Chloe. "
                +"but If you like, you can call me Chloe. "
                +"but some people just call me Chloe. "
            }
        }
        handleNext()
    }
    onResponse<CanIGetCompliment> {
        furhat.say {
            random {
                +"Sure!"
                +"For sure"
                +"Of course!"
            }
        }
        furhat.gesture(GesturesLib.SmileRandom())
        users.current.hasBeenGreeted = true
        goto(complimentNextGroup(users.current))
    }

}