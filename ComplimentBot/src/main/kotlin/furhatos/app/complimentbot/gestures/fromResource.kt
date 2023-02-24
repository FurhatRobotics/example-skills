package furhatos.app.complimentbot.gestures

import furhatos.app.complimentbot.ComplimentbotSkill
import furhatos.gestures.Gesture
import furhatos.gestures.Gestures
import furhatos.records.Record
import java.io.BufferedReader
import java.io.InputStreamReader

fun getResourceGesture(filePath: String): Gesture {
    val resource = ComplimentbotSkill::class.java.getResourceAsStream(filePath)
    return if (resource != null) {
        Record.fromJSON(BufferedReader(InputStreamReader(resource)).readText()) as Gesture
    } else {
        println("Failed to get resource : $filePath")
        Gestures.Blink
    }
}

val sleep1 = getResourceGesture("/Sleeping_1.json")
val sleep2 = getResourceGesture("/Sleeping_2.json")
val fallAsleep = getResourceGesture("/FallAsleep_1.json")
val wakeUp = getResourceGesture("/WakeUp_1.json")