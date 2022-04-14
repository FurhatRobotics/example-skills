package furhatos.app.jokebot.flow

import furhatos.app.jokebot.flow.main.Idle
import furhatos.app.jokebot.setting.activate
import furhatos.app.jokebot.setting.distanceToEngage
import furhatos.app.jokebot.setting.mainPersona
import furhatos.app.jokebot.setting.maxNumberOfUsers
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val Init: State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)

        /** Set our main character - defined in personas */
        activate(mainPersona)

        /** start the interaction */
        goto(Idle)
    }
}
