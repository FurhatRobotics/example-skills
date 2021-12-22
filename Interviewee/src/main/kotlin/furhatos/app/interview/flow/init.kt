package furhatos.app.interview.flow

import furhatos.app.interview.flow.main.Interview
import furhatos.app.interview.setting.distanceToEngage
import furhatos.app.interview.setting.maxNumberOfUsers
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

val Init: State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)

        /** start the interaction */
        goto(Interview)
    }
}
