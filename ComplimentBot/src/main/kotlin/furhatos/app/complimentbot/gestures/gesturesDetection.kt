package furhatos.app.complimentbot.gestures

import furhatos.app.complimentbot.utils.hasSmiled
import furhatos.app.complimentbot.utils.isSmiling
import furhatos.flow.kotlin.onUserGesture
import furhatos.flow.kotlin.onUserGestureEnd
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.skills.emotions.UserGestures

val SmileCheckState = state {
    onUserGesture(UserGestures.Smile, instant = true) {
        users.getUser(it.userID).isSmiling = true
        users.getUser(it.userID).hasSmiled = true
    }
    onUserGestureEnd(UserGestures.Smile, instant = true) {
        users.getUser(it.userID).isSmiling = false
    }
}