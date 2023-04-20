package furhatos.app.customasr.com;

import furhatos.util.Language;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.transcribestreaming.TranscribeStreamingAsyncClient;
import software.amazon.awssdk.services.transcribestreaming.model.AudioStream;
import software.amazon.awssdk.services.transcribestreaming.model.StartStreamTranscriptionRequest;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static furhatos.app.customasr.com.TranscriptBehaviorKt.getTranscriptor;


public class TranscribeApp {
    private Region region = Region.EU_WEST_1;
    private String encoding = "pcm";
    private String pollyKey = "XXX";
    private String pollySecret = "XXX";

    private TranscribeStreamingRetryClient client;

    public TranscribeApp() throws URISyntaxException {
        client = new TranscribeStreamingRetryClient(
                new CredProvider(pollyKey, pollySecret),
                region
        );
    }

    public String startListen(
            Language lang, FurhatAudioStream furhatStream, Long maxSpeech
    ) throws InterruptedException, ExecutionException {
        TranscribeStreamingAsyncClient client = TranscribeStreamingAsyncClient
                .builder()
                .credentialsProvider(new CredProvider(pollyKey, pollySecret))
                .region(region)
                .build();
        StartStreamTranscriptionRequest request = StartStreamTranscriptionRequest.builder()
                .languageCode(lang.getCode())
                .mediaEncoding(encoding)
                .mediaSampleRateHertz(16000)
                .build();
        CompletableFuture<Void> result = client.startStreamTranscription(
                request,
                new AudioStreamPublisher(furhatStream),
                getTranscriptor()
        );
        result.get();
        client.close();
        return "DONE";
    }

    private class CredProvider implements AwsCredentialsProvider {

        private String key;
        private String secret;

        CredProvider(String key, String secret) {
            this.key = key;
            this.secret = secret;
        }

        private class Creds implements AwsCredentials {

            @Override
            public String accessKeyId() {
                return key;
            }

            @Override
            public String secretAccessKey() {
                return secret;
            }
        }

        @Override
        public AwsCredentials resolveCredentials() {
            return new CredProvider.Creds();
        }
    }

    private class AudioStreamPublisher implements Publisher<AudioStream> {
        private final InputStream inputStream;
        private Subscription currentSubscription;

        private AudioStreamPublisher(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void subscribe(Subscriber<? super AudioStream> s) {
            if (currentSubscription != null) {
                currentSubscription.cancel();
            }
            currentSubscription = new SubscriptionImpl(s, inputStream);
            s.onSubscribe(currentSubscription);
        }
    }
}
