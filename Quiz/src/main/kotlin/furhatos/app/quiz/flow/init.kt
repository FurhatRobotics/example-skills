package furhatos.app.quiz.flow

import furhatos.app.quiz.flow.main.Idle
import furhatos.app.quiz.setting.activate
import furhatos.app.quiz.setting.distanceToEngage
import furhatos.app.quiz.setting.maxNumberOfUsers
import furhatos.app.quiz.setting.quizPersona
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.util.Gender
import furhatos.util.Language

val Init: State = state() {
    init {
        /** Set our default interaction parameters
        By default, only two users can be actively engaged at the same time.
        By setting an engagement policy, the innerDistance (triggering entry),
        outerDistance (triggering exit), and maximum number of concurrent users
        can be set to values applicable to the skill's use case.
         */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)

        /** Set our main character - defined in personas */
        activate(quizPersona)

        /** start the interaction */
        goto(Idle)
    }
}
