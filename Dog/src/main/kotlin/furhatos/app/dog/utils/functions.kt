package furhatos.app.dog.utils

import furhatos.app.dog.DogSkill
import furhatos.app.dog.flow.runningFromIntelliJ
import furhatos.gestures.*
import furhatos.records.Pixel
import furhatos.records.Record
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

var isVirtual = false

// Random double in given range
fun getRandomInRange(startInterval: Double = 18.0, interval: Double = 3.0) = startInterval + Math.random() * interval

fun getResourceGesture(filePath: String): Gesture {
    val resource = DogSkill::class.java.getResourceAsStream(filePath)
    return if (resource != null) {
        Record.fromJSON(BufferedReader(InputStreamReader(resource)).readText()) as Gesture
    } else {
        println("Failed to get resource : $filePath")
        Gestures.Blink
    }
}

fun getAudioURL(path: String) : String {
    return if(isVirtual && runningFromIntelliJ) {
        "file:${File(".").canonicalPath + "/src/main/resources/sounds/"}$path"
    } else {
        //"file:/home/furnix/dog/sounds/$path"
        "classpath:sounds/$path"
        //"classpath:$path"
    }
}

fun _defineGesture(name: String? = null,
                   strength: Double = 1.0,
                   duration: Double = 1.0,
                   defaultPriority: Int = 0,
                   frameTimes: List<Double>? = null,
                   audioURL: String? = null,
                   texture: String? = null,
                   ledPixel: Pixel? = null,
                   definition: GestureBuilder.() -> Unit): Gesture {
    val gesture = defineGesture(name, strength, duration, defaultPriority, definition)

    if(frameTimes != null) {
        gesture.frames.add(Frame(frameTimes, false, audioURL, texture, texture, ledPixel))
    }

    return gesture
}
