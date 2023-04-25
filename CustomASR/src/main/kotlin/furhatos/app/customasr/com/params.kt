package furhatos.app.customasr.com

object params {
    const val endSil = 1000L
    const val maxSpeech = 15000L
    const val timeout = 5000L
    // AWS consumes audioData faster than we feed it, so we need to slow it down
    const val microphoneTimeoutInMillis = 50L
    const val ROBOT_IP_ADDRESS = "127.0.0.1" // 127.0.0.1 for SDK/Local, or RobotIP(192.168.x.x etc) for running remotely.
    const val API_KEY =  ""
    const val API_SECRET = ""
}