
This is the README file for the Houndify Java SDK.


        COMPONENTS
        ----------

The Java SDK comes in a compressed tar file.  It unpacks into a
directory named HoundJava.  The following components are in HoundJava:

  * This README.txt file.

  * HoundifyJavaSDK.jar  This is a jar file containing the compiled
        library code of the Java SDK.

  * src  This is a directory containing the source code for everything
        in HoundifyJavaSDK.jar.

  * SampleJavaClient.java  This is a Java command-line client program
        that uses HoundifyJavaSDK.jar.  It is intended mainly as a
        demonstration of how to use the SDK.

  * SampleJavaClient.class  This is the Java .class file generated
        from SampleJavaClient.java.

        REQUIREMENTS
        ------------

The SDK uses only standard Java and standard Java libraries.  It
should run on any system that supports Java.  The sample client is
written to use a command-line interface, but the core SDK code doesn't
require a command line and can be used in any Java system.

        RUNNING
        -------

The sample client may be run on the command line with the standard
free Java runtime tools from Oracle.  Here's an example of running the
sample client with no arguments, which causes it to print its usage
message (assuming you are in the HoundJava directory):

    java -ea -cp HoundifyJavaSDK.jar:. SampleJavaClient

The message it prints is as follows:

    Usage: java SampleJavaClient <client-id> <client-key> <user-id>
        { <text-URL-base> { <voice-URL-base> }? }?

Note that the <text-URL-base> and <voice-URL-base> parameters are
optional.  If they are not provided on the command line, the defaults
will be used, which is normally what should be used.

The <client-id> and <client-key> parameters are the client ID and
corresponding client key obtained from the Houndify.com site.  They
are required to authenticate with the Houndify API.  If you don't have
them already, you will need to log in to Houndify.com and register a
client.  When you register the client, it will create the client ID
and client key for you.

The final argument to the sample client is the <user-id>.  You may use
anything you like for this parameter.  It represents the ID of a
particular user of your client.  You can decide for yourself what the
user ID is for each of your users.

When you have all the required parameters, you can start the client.
It will then sit in a loop and wait for you to type queries one line
at a time.  These may be text queries, such as

    What is the weather like in Chicago?

They may also be of the form

    -audio <file>

where <file> is an audio file.  Supported audio formats are .wav and
Speex.

Another alternative for audio files is to use

    -transcript-audio <file>

This will work as with -audio but with the additional feature that
partial transcriptions from the server will be printed.

A variant on that is

    -slow-transcript-audio <file>

This is the same as -transcript-audio but with some delays added in
when sending the audio file to the server.  This makes it clear that
the partial transcriptions are coming in real time while the audio
data is being sent to the server.

Note: Your Houndify account may have limits on your API access.  If
you submit a large number of queries in batch, you may exceed these
limits and find that further requests with your client ID are denied.
Please see http://houndify.com for details on the limits of your
account.

        WRITING YOUR OWN JAVA CLIENT
        ----------------------------

The file SampleJavaClient.java provides an example of how to use the
SDK.  The SampleJavaClient.java class provides a simple command-line
wrapper that uses the HoundCloudRequester and SampleHoundDriver
classes.

The HoundCloudRequester class (in
src/com/Hound/HoundRequester/HoundCloudRequester.java) is the main
interface to the Houndify API.  It extends the abstract HoundRequester
class (in src/com/Hound/HoundRequester/HoundRequester.java).  The
HoundRequester class provides an interface that can be used to access
a Houndify implementation wherever it is -- for now, only through the
Houndify API using the HoundCloudRequester subclass, but in the future
also a local embedded implementation or a dual-mode implementation
will be available through other subclasses of HoundRequester.

To create a HoundCloudRequester class, the following constructor is
normally used:

    public HoundCloudRequester(String init_client_id, String init_client_key,
                               String init_user_id)

There's an alternate constructor taking two additional arguments for
the API URL endpoints to hit, but normally you don't need to specify
them and you can just use the defaults.

The init_client_id, init_client_key, and init_user_id parameters are
the client ID, client key, and user ID parameters.  As mentioned
above, the client ID and client key you will get from Houndify.com
when you register a client.  The user ID should be different for each
user of your client and should be generated by your code to identifier
that user.  User-specific data is stored keyed to the user ID, so it
is important that each user of your client get a different user ID,
but that each user gets the same user ID every time he or she uses the
client.

Text requests use this method on the HoundRequester class:

    public abstract HoundServerJSON do_text_request(String query,
            ConversationStateJSON conversation_state,
            RequestInfoJSON request_info) throws Exception;

The query parameter is the text of the request.  The conversation
state should be null on a first request and on subsequent requests it
should come from the JSON returned by the previous request.  The
request_info is a class that has various fields that can optionally be
filled in by the client that help the server do a better job of
handling the request, such as location information.  The client should
fill in as many fields of this class as possible.

The conversation_state and request_info use classes
(ConversationStateJSON and RequestInfoJSON) that correspond directly
to JSON sent to the server.  The result of this method uses the
HoundServerJSON class, which corresponds directly to the JSON recieved
from the server.  The formats of these JSON values are documented on
the Houndify.com website.  The fields in the Java classes correspond
exactly to the fields as documented on Houndify.com.  In fact, both
the documentation and the Java code are automatically generated from
the same specification.

Voice requests use this method on the HoundRequester class:

    public abstract VoiceRequest start_voice_request(
            ConversationStateJSON conversation_state,
            RequestInfoJSON request_info, PartialHandler partial_handler)
                    throws Exception;

This is a little more complex than the text case.  Like the text case,
conversation_state and request_info parameters are passed in.  But an
additional parameter is passed in -- the partial_handler parameter.
This is a callback mechanism for your code to get notified when
partial transcript information comes back from the server while the
audio is still being sent.  Your code can choose to ignore it, or it
can use it, for example to display to the user what the system thinks
the user has said so far.

The PartialHandler abstract class is declared within the
HoundRequester class.  It has an abstract handle() method which your
code should implement:

    public abstract void handle(HoundPartialTranscriptJSON partial);

Note that the parameter it gets is of type
HoundPartialTranscriptJSON.  This is another type that corresponds
exactly to a JSON value and which is documented on Houndify.com.  It
contains a partial transcript and also a signal from the server-side
Voice Activity Detector (VAD) indicating whether the server thinks the
user has finished speaking.  The client may use this to decide when to
finish the request.

The start_voice_request() method returns an object of type
VoiceRequest.  This is another abstract class declared within the
HoundRequester class.

The VoiceRequest class has two methods that should be used by your
client code to communicate information about the audio to the server:

        public abstract void add_audio(int byte_count, byte[] data)
                throws Exception;
        public abstract HoundServerJSON finish() throws Exception;

The add_audio() method sends some bytes of audio data.  The finish()
method should be called when the audio is done.

Note that the data sent through add_audio() should form a valid WAV or
SPEEX data file.  Raw PCM audio without a WAV header is not acceptable
and will not work.  When sending raw data, the WAV header must be sent
first.  It doesn't matter how the data is split up among calls to
add_audio(), but any waiting done by the client to put the data into
larger blocks before sending it to the server will delay the response
by the server, so it's generally a good idea to send smaller blocks of
data as they become available from a microphone.

Note that the add_audio() method returns nothing but that the finish()
method returns an object of type HoundServerJSON, which is the same
type returned by the do_text_request() -- it directly represents the
JSON sent by the server in response to the query.  Please see the
documentation on Houndify.com for details of the fields of
HoundServerJSON.

        SUPPORT
        -------

If you have any issues with this Java SDK, please visit
http://houndify.com for support.
