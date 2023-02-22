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
            random {
                +"My name is Furhat"
                +"I am Furhat"
                +"You can call me Furhat"
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