package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.*
import furhatos.app.complimentbot.flow.main.ActiveIdle
import furhatos.app.complimentbot.gestures.SmileCheckState
import furhatos.app.complimentbot.utils.ComplexEngagementPolicy
import furhatos.app.complimentbot.utils.Zone
import furhatos.app.complimentbot.utils.activate
import furhatos.app.complimentbot.utils.mainPersona
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.skills.UserManager
import furhatos.util.CommonUtils
import java.io.File

val logger = CommonUtils.getLogger(ComplimentbotSkill::class.java)

val Init: State = state(UniversalParent) {
    onEntry {

        /** Set our default interaction parameters */
        users.engagementPolicy = ComplexEngagementPolicy(UserManager, listOf(Zone.ZONE1, Zone.ZONE2, Zone.ZONE3))

        if (!furhat.isVirtual()) {
            val visionProps = File("/usr/share/furhat/properties/", "vision.properties")
            println(visionProps)
//            visionProps.printWriter().use { out ->
//                timingParameters.forEach {
//                    out.println("${it.key}=${it.value}")
//                }
//            }
        }

        /** Set our main character - defined in personas */
        furhat.mask = "anime [legacy]"
        activate(mainPersona)

        parallel(abortOnExit = false) { goto(SmileCheckState) }

        /** start the interaction */
        goto(ActiveIdle)
    }
}