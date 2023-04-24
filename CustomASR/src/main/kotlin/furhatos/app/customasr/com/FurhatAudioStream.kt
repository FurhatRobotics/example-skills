package furhatos.app.customasr.com

import furhatos.monitor.FurhatAudioFeedStreamer
import java.io.InputStream
import java.nio.ByteBuffer

class FurhatAudioStream : FurhatAudioFeedStreamer.AudioStreamingListener,
    InputStream() {

    var active = true // Whether this object should store microphoneData.
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
        data = byteArrayOf(0x00, 0x00, 0x00, 0x00)
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
        }
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
