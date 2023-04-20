package furhatos.app.customasr.com

import furhatos.monitor.FurhatAudioFeedStreamer
import java.io.InputStream

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

    /**
     * Only store microphone data if active.
     * Converts the 2 channel (Robot + User) into Mono (User) data and stores it.
     */
    override fun audioStreamingData(data: ByteArray) {
        if (active) {
            val audioData = data.copyOf()
            for (i in 0 until audioData.size / 4) {
                audioData[i * 4 + 2] = audioData[i * 4]
                audioData[i * 4 + 3] = audioData[i * 4 + 1]
            }
            this.data += audioData
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
            logger.warn("Data is empty?")
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

