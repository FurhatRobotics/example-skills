package furhatos.app.interview.flow

import furhatos.app.interview.flow.main.Interview
import furhatos.app.interview.setting.maskChanging
import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

fun ChangeMask(model: String) = state(Interview) {
    onEntry {
        maskChanging = true
    }

    onButton("$model mask is on!", section = Section.RIGHT, color = Color.Red) {
        furhat.setModel(model)
        terminate()
    }

    onExit {
        maskChanging = false
    }
}