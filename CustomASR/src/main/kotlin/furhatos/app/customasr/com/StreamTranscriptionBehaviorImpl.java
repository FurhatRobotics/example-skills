//snippet-sourcedescription:[StreamTranscriptionBehaviorImpl.java demonstrates how to implement StreamTranscriptionBehavior.]
// snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Amazon Transcribe]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/06/2020]
// snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package furhatos.app.customasr.com;

import furhatos.app.customasr.InterimResult;
import furhatos.app.customasr.ListenDone;
import furhatos.app.customasr.ListenStarted;
import furhatos.event.EventSystem;
import software.amazon.awssdk.services.transcribestreaming.model.Result;
import software.amazon.awssdk.services.transcribestreaming.model.StartStreamTranscriptionResponse;
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptEvent;
import software.amazon.awssdk.services.transcribestreaming.model.TranscriptResultStream;

import java.util.List;

/**
 * Listens to transcriptionEvents from AWS. Based off of the AWS example.
 */
public class StreamTranscriptionBehaviorImpl implements StreamTranscriptionBehavior {

    @Override
    public void onStream(TranscriptResultStream e) {
        List<Result> results = ((TranscriptEvent) e).transcript().results();
        if (results.size() > 0) {
            if (results.get(0).alternatives().size() > 0)
                if (!results.get(0).alternatives().get(0).transcript().isEmpty()) {
                    String message = results.get(0).alternatives().get(0).transcript();
                    System.out.println(message);
                    EventSystem.send(new InterimResult(message));
                }
        }
    }

    @Override
    public void onResponse(StartStreamTranscriptionResponse r) {
        EventSystem.send(new ListenStarted());
        System.out.println(String.format("=== Received initial response. Request Id: %s ===", r.requestId()));
    }


    @Override
    public void onError(Throwable e) {
        System.out.println("=== Failure encountered ===");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        EventSystem.send(new ListenDone());
        System.out.println("=== All records streamed successfully ===");
    }
}
