package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.*
import furhatos.app.complimentbot.flow.main.ActiveIdle
import furhatos.app.complimentbot.gestures.SmileCheckState
import furhatos.app.complimentbot.utils.ComplexEngagementPolicy
import furhatos.app.complimentbot.utils.Zone
import furhatos.app.complimentbot.utils.activate
import furhatos.app.complimentbot.utils.setEngagementTimings
import furhatos.flow.kotlin.*
import furhatos.skills.UserManager
import furhatos.util.CommonUtils

val logger = CommonUtils.getLogger(ComplimentbotSkill::class.java)

val Init: State = state(UniversalParent) {
    onEntry {

        /** Set our default interaction parameters */
        users.engagementPolicy = ComplexEngagementPolicy(UserManager, listOf(Zone.ZONE1, Zone.ZONE2, Zone.ZONE3))

        setEngagementTimings(enterBufferTime, leaveBufferTime)

        /** Set our main character - defined in personas */
        furhat.mask = "anime [legacy]"
        activate(mainPersona)

        parallel(abortOnExit = false) { goto(SmileCheckState) }

        /** start the interaction */
        goto(ActiveIdle)
    }
}