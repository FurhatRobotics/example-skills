package furhatos.app.customasr.com

object params {
    const val endSil = 1000L
    const val maxSpeech = 15000L
    const val timeout = 5000L
    // AWS consumes audioData faster than we feed it, so we need to slow it down
    const val microphoneTimeoutInMillis = 50L
    const val API_KEY =  ""
    const val API_SECRET = ""
}