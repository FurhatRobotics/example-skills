package furhatos.app.dog.flow


import furhatos.flow.kotlin.*


val Parent: State = state {
    onButton("SLEEP", color = Color.Blue) {
        goto(Sleep)
    }
    onButton("WAKE", color = Color.Blue) {
        goto(WakeUp)
    }
    onButton("SHOW ALL GESTURES", color = Color.Blue) {
        goto(ShowAllGestures)
    }
}
