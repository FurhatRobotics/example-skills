package furhatos.app.customasr.aws

import furhatos.app.customasr.com.params
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.services.transcribestreaming.model.AudioEvent
import software.amazon.awssdk.services.transcribestreaming.model.AudioStream
import java.io.IOException
import java.io.InputStream
import java.io.UncheckedIOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

/**
 * Sends data from the FurhatAudioStream to AWS for transcription
 */
class SubscriptionImpl internal constructor(
    private val subscriber: Subscriber<in AudioStream?>,
    private val inputStream: InputStream
) : Subscription {
    private val executor = Executors.newFixedThreadPool(1)
    private val demand = AtomicLong(0)
    internal var data: ByteArray = byteArrayOf()

    override fun request(n: Long) {
        if (n <= 0) {
            subscriber.onError(IllegalArgumentException("Demand must be positive"))
        }
        demand.getAndAdd(n)
        executor.submit {
            try {
                do {
                    val audioBuffer: ByteBuffer = getNextEvent()
                    if (audioBuffer.remaining() > 0) {
                        val audioEvent = audioEventFromBuffer(audioBuffer)
                        subscriber.onNext(audioEvent)
                    } else {
                        subscriber.onComplete()
                        break
                    }
                } while (demand.decrementAndGet() > 0)
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }

    private fun getNextEvent(): ByteBuffer {

        val audioBytes = ByteArray(CHUNK_SIZE_IN_BYTES)
        var len = 0
        try {
            Thread.sleep(params.microphoneTimeoutInMillis) //Microphone data is a bit slow
            len = inputStream.read(audioBytes)
            return if (len <= 0) {
                ByteBuffer.allocate(0)
            } else {
                ByteBuffer
                    .wrap(audioBytes, 0, len)
                    .order(ByteOrder.LITTLE_ENDIAN)
            }
        } catch (e: IOException) {
            throw UncheckedIOException(e)
        }
    }

    override fun cancel() {
        executor.shutdown()
    }

    private fun audioEventFromBuffer(bb: ByteBuffer?): AudioEvent {
        return AudioEvent.builder()
            .audioChunk(SdkBytes.fromByteBuffer(bb))
            .build()
    }

    companion object {
        private const val CHUNK_SIZE_IN_BYTES = 6400
    }
}
