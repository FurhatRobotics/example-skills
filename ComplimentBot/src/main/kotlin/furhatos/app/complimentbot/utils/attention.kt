package furhatos.app.complimentbot.utils

import furhatos.event.actions.ActionGaze
import furhatos.flow.kotlin.Furhat
import furhatos.records.Location
import furhatos.records.User

val gazeMode = ActionGaze.Mode.DEADZONE
const val attentionSlack = 5

fun Furhat.attendC(user: User) {
    attend(user, gazeMode = gazeMode, slack = attentionSlack)
}

fun Furhat.attendC(loc: Location) {
    attend(loc, gazeMode = gazeMode, slack = attentionSlack)
}

fun Furhat.attendNobodyC() {
    attendC(User.NOBODY)
}