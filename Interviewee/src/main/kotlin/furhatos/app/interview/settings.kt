package furhatos.app.interview

import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.records.Location
import furhatos.util.Gender
import furhatos.util.Language

val interviewerLocation = Location(0.0, 0.0, 1.0)
const val interviewerName = ", master human" // see the first question
const val confederateName = "human" // used to ask for a mask change
var maskChanging = false
val interval = 2000..4000
const val amplitudeUserPresent = 0.05
const val amplitudeUserAbsent = 0.1

val femaleVoice = PollyVoice.Joanna()
val maleVoice = PollyVoice.Matthew()
