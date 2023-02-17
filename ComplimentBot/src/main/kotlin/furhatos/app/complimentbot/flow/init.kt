package furhatos.app.complimentbot.flow

import furhatos.app.complimentbot.*
import furhatos.app.complimentbot.flow.main.ActiveIdle
import furhatos.app.complimentbot.gestures.SmileCheckState
import furhatos.app.complimentbot.utils.ComplexEngagementPolicy
import furhatos.app.complimentbot.utils.Zone
import furhatos.app.complimentbot.utils.activate
import furhatos.app.complimentbot.utils.mainPersona
import furhatos.flow.kotlin.*
import furhatos.skills.UserManager
import furhatos.util.CommonUtils
import java.io.File

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

fun FlowControlRunner.setEngagementTimings(enterBufferTime: Int, leaveBufferTime: Int) {
    val timingParameters = mapOf("enterBufferTime" to enterBufferTime, "leaveBufferTime" to leaveBufferTime)
    if (!furhat.isVirtual() && (System.getProperty("furhatos.skills.brokeraddress") == null)) { //Only applies to the real robot
        val visionProps = File("/usr/share/furhat/properties/", "vision.properties")
        try {
            val props = visionProps.readLines().associate { stringProp -> stringProp.split("=")[0] to stringProp.split("=")[1].toInt() }
            if (props["enterBufferTime"] != enterBufferTime || props["leaveBufferTime"] != leaveBufferTime) {
                throw Exception("New vision parameters detected")
            } else {
                logger.info("Vision parameters are correctly set up.")
            }
        } catch (e: Exception) {
            logger.warn("Rewriting the vision parameters : ${e.message}. Please restart your robot for them to take effect.")
            visionProps.printWriter().use { out ->
                timingParameters.forEach {
                    out.println("${it.key}=${it.value}")
                }
            }
        }
    } else {
        logger.warn("Did not rewrite the engagement timings, machine is not a robot.")
    }
}