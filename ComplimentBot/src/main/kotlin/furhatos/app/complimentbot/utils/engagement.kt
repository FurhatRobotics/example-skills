package furhatos.app.complimentbot.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.GsonBuilder
import furhatos.app.complimentbot.flow.logger
import furhatos.app.complimentbot.origin
import furhatos.app.complimentbot.zone1Params
import furhatos.app.complimentbot.zone2Params
import furhatos.app.complimentbot.zone3Params
import furhatos.event.EventSystem
import furhatos.event.senses.SenseInteractionSpaces
import furhatos.event.senses.SenseUserEnter
import furhatos.event.senses.SenseUserLeave
import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.records.Ellipse
import furhatos.records.Space
import furhatos.records.User
import furhatos.skills.EngagementPolicy
import furhatos.skills.UserManager
import java.io.File
import java.io.FileWriter

enum class Zone(val space: Space) {
    ZONE1(Ellipse("zone1", origin, zone1Params.first, zone1Params.second)),
    ZONE2(Ellipse("zone2", origin, zone2Params.first, zone2Params.second)),
    ZONE3(Ellipse("zone3", origin, zone3Params.first, zone3Params.second)),
    OUT(Ellipse());

    fun isCloser(other: Zone): Boolean {
        return ordinal < other.ordinal
    }
    fun isFurther(other: Zone): Boolean {
        return ordinal > other.ordinal
    }

    override fun toString(): String {
        return "Zone(name=$name, space=$space)"
    }
}

/**
 * Complex engagement policy definition.
 * Note that the zone have to be defined from the smallest to the largest !
 *
 * No max users for this engagement policy at the moment
 */
class ComplexEngagementPolicy(private val userManager: UserManager, private var zones : List<Zone>) : EngagementPolicy {

    override fun checkEngagement() {
        val engagedUsers = userManager.list.map { user -> user.id }

        userManager.all.forEach { user ->
            updateActiveAttention(user)
            val activeZone = findUserZone(user)

            // 1. Check engagement
            if (!user.isEngaged && user.isVisible && zones.last().space.contains(user.head.location)) {
                // User entered
                user.isEngaged = true
            }

            // 2. Send zone events
            if (user.isEngaged && user.isVisible) {
                if (activeZone.isCloser(user.zone)) {
                    user.zone = activeZone
                    sendSenseEnter(user.id)
                }
                else if (activeZone.isFurther(user.zone)) {
                    user.zone = activeZone
                    sendSenseLeave(user.id)
                    if (activeZone == Zone.OUT) {
                        user.isEngaged = false
                    }
                }
            } else if (user.isEngaged) {
                // Engaged user left
                user.isEngaged = false
                user.zone = Zone.OUT
                sendSenseLeave(user.id)
            }
        }

        val newEngagedUsers = userManager.list.map { user -> user.id }

        if (engagedUsers != newEngagedUsers) {
            userManager.sendUserStatus()
        }
    }

    override fun requestInteractionSpaces() {
        EventSystem.send(SenseInteractionSpaces.Builder().spaces(zones.map { it.space }).buildEvent())
    }

    private fun sendSenseEnter(userId: String) {
        EventSystem.send(SenseUserEnter.Builder().userId(userId).buildEvent())
    }

    private fun sendSenseLeave(userId: String) {
        EventSystem.send(SenseUserLeave.Builder().userId(userId).buildEvent())
    }

    private fun findUserZone(user: User): Zone {
        for (zone in zones) {
            // From the smallest zone to the largest, the user belongs to the smallest one
            if (zone.space.contains(user.head.location)) {
                return zone
            }
        }
        return Zone.OUT
    }
}

fun updateActiveAttention(user: User) {
    user.attentionAverage.add(user.isAttendingFurhat)
}



/**
 * Checks/sets the vision.properties file on the robot.
 *
 * @param enterBufferTime the entering buffer time to detect a user
 * @param leaveBufferTime the leaving buffer time to detect a leaving user
 */
fun FlowControlRunner.setEngagementTimings(enterBufferTime: Int = 500, leaveBufferTime: Int = 2500) {
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

/**
 * Sets the camcore config.json file on the robot.
 */
fun FlowControlRunner.setCamcoreFaceDetectionThreshold(faceDetectionThreshold: Double = 0.9) {
    if (!furhat.isVirtual() && (System.getProperty("furhatos.skills.brokeraddress") == null)) { //Only applies to the real robot
        val configFile = File("/usr/local/furhatcamcore/", "config.json")

        val root = ObjectMapper().readValue(configFile, CamcoreConfig::class.java)
        val previousThreshold = root.faceDetectionThreshold

        //Compare values
        if (faceDetectionThreshold != previousThreshold) {
            //Update value in object
            root.faceDetectionThreshold = faceDetectionThreshold

            FileWriter("/usr/local/furhatcamcore/config.json").use { file ->
                file.write(GsonBuilder().setPrettyPrinting().create().toJson(root))
                logger.info("Successfully updated faceDetectionThreshold. Please restart your robot to take the changes into account")
            }
        } else {
            logger.info("faceDetectionThreshold is correctly set up")
        }
    } else {
        logger.warn("Did not rewrite camcore detection threshold, machine is not a robot.")
    }
}

/**
 * Class used for JSON serialization.
 * The class default values are overridden by existing ones, but if the file is lacking a field it's going to be added.
 * All the fields must be present if we don't want a serialization exception.
 */
class CamcoreConfig {
    var frameIntervalToProcess = 0.0
    var faceDetectionDevice = ""
    var faceDetectionBBEnlargeCoeff = 0.0
    var faceDetectionThreshold = 0.0
}