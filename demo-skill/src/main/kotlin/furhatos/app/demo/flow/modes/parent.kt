package furhatos.app.demo.flow.modes

import furhatos.app.demo.flow.partials.conversationalHandlers
import furhatos.app.demo.flow.partials.functionalHandlers
import furhatos.app.demo.flow.wizardButtons
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.gestures.Gestures

val Parent : State = state {
    // New include syntax for partial states, see https://docs.furhat.io/flow/#partial-states-and-extending-states
    include(wizardButtons)
    include(functionalHandlers)
    include(conversationalHandlers)

    // Silent fallback since we don't want to use the build-in fallbacks, see https://docs.furhat.io/listening/#changing-default-responses
    onResponse {
        furhat.gesture(Gestures.BrowRaise)
        furhat.listen()
    }
}