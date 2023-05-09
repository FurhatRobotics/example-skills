package furhatos.app.attentiongrabber.flow

import furhatos.app.attentiongrabber.flow.main.Idle
import furhatos.app.attentiongrabber.setting.activate
import furhatos.app.attentiongrabber.setting.mainPersona
import furhatos.app.attentiongrabber.setting.maxNumberOfUsers
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