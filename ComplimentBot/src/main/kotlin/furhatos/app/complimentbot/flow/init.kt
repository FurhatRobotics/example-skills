package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.ComplimentbotSkill
import furhatos.app.complimentbot.flow.main.ActiveIdle
import furhatos.app.complimentbot.gestures.SmileCheckState
import furhatos.app.complimentbot.setting.activate
import furhatos.app.complimentbot.setting.mainPersona
import furhatos.app.complimentbot.setting.maxNumberOfUsers
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.util.CommonUtils

val logger = CommonUtils.getLogger(ComplimentbotSkill::class.java)

val Init: State = state(UniversalParent) {
    onEntry {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(0.5, 1.2, 1.2, 1.7, maxNumberOfUsers)

        /** Set our main character - defined in personas */
        activate(mainPersona)

        parallel(abortOnExit = false) { goto(SmileCheckState) }

        /** start the interaction */
        goto(ActiveIdle)
    }
}