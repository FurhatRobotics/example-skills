package furhatos.app.customasr.com

import furhatos.app.customasr.RMSResult
import furhatos.event.EventSystem
import furhatos.monitor.FurhatAudioFeedStreamer
import furhatos.util.CommonUtils
import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.math.sqrt

/**
 * Acts as a buffer to the AudioFeed.
 * Only when the stream is active will it store the data send from the audioFeed.
 */
class FurhatAudioStream : FurhatAudioFeedStreamer.AudioStreamingListener,
    InputStream() {

    var logger = CommonUtils.getLogger(FurhatAudioStream::class.java)
    var active = false // Whether this object should store microphoneData.
    internal var index = 0
    internal var data: ByteArray = byteArrayOf()

    init {
        FurhatAudioFeedStreamer.addListener(this)
    }

    override fun audioStreamingStarted() {}
    override fun audioStreamingStopped() {}

    fun resetForListen() {
        active = true
        index = 0
        data = byteArrayOf()
    }

    /**
     * Only store microphone data if active.
     * Converts the 2 channel (Robot + User) into Single channel (User) data and stores it.
     */
    override fun audioStreamingData(data: ByteArray) {
        if (active) {
            val audioData = data.copyOf()
            val buffer = ByteBuffer.allocate(audioData.size / 2)
            for (i in audioData.indices step 4) {
                buffer.put(audioData[i])
                buffer.put(audioData[i + 1])
            }
            this.data += buffer.array()
            calculateAndSendRMS()
        }
    }

    /**
     * Calculates the RMS of the sample for usage later.
     */
    fun calculateAndSendRMS() {
        val squaredSum = data.fold(0.0) { acc, byte -> acc + byte * byte }
        val meanSquared = squaredSum / data.size
        val rms = sqrt(meanSquared)
        EventSystem.send(RMSResult(rms))
    }

    override fun read(): Int {
        val returnArray = ByteArray(1)
        read(returnArray, 0, 1)
        return returnArray[0].toInt()
    }

    override fun read(newArr: ByteArray, offSet: Int, length: Int): Int {
        val bytesToRead = (data.size - index).coerceAtMost(length)
        if (bytesToRead == 0 || length == 0) {
            return 0
        }
        data.copyInto(
            destination = newArr,
            destinationOffset = offSet,
            startIndex = index,
            endIndex = index + bytesToRead
        )
        index += bytesToRead
        return bytesToRead
    }
}

