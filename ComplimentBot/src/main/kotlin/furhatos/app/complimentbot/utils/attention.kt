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

fun Furhat.attendCSlow(loc: Location) {
    attend(loc, gazeMode = gazeMode, slack = attentionSlack, speed = ActionGaze.Speed.SLOW)
}

fun Furhat.attendNobodyC() {
    attendC(User.NOBODY)
}