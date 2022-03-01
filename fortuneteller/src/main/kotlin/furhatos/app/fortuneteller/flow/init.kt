package furhatos.app.fortuneteller.flow

import furhatos.app.fortuneteller.flow.main.Idle
import furhatos.app.fortuneteller.setting.activate
import furhatos.app.fortuneteller.setting.fortuneTellerPersona
import furhatos.app.fortuneteller.setting.maxNumberOfUsers
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.flow.kotlin.voice.Voice
import furhatos.util.Language

val defaultVoice = "WillBadGuy22k_HQ"
val theVoice = Voice(name = defaultVoice, language = Language.ENGLISH_US)

val Init = state {
    onEntry {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(0.5, 1.2, 1.2, 1.7, maxNumberOfUsers)


        /** Set our main character - defined in personas */
        activate(fortuneTellerPersona)

        /** start the interaction */
        goto(Idle)
    }
}