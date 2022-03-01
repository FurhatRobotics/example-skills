package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.flow.main.Idle
import furhatos.app.complimentbot.setting.activate
import furhatos.app.complimentbot.setting.mainPersona
import furhatos.app.complimentbot.setting.maxNumberOfUsers
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users


val Init = state {
    onEntry {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(0.5, 1.2, 1.2, 1.7, maxNumberOfUsers)

        /** Set our main character - defined in personas */
        activate(mainPersona)

        /** start the interaction */
        goto(Idle)
    }
}