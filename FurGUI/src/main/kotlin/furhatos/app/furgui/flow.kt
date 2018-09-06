package furhatos.app.furgui

import furhatos.event.senses.SenseSkillGUIConnected
import furhatos.flow.kotlin.*
import furhatos.records.Record
import furhatos.skills.HostedGUI

// Our GUI declaration
val GUI = HostedGUI("ExampleGUI", "assets/exampleGui", PORT)

// Starting state, before our GUI has connected.
val NoGUI: State = state(null) {
    onEvent<SenseSkillGUIConnected> {
        goto(GUIConnected)
    }
}

/*
    Here we know our GUI has connected. Since the user might close down the GUI and then reopen
    again, we inherit our handler from the NoGUI state. An edge case might be that a second GUI
    is opened, but this is not accounted for here.

 */
val GUIConnected = state(NoGUI) {
    onEntry {
        // Pass data to GUI
        send(DataDelivery(buttons = buttons, inputFields = inputFieldData.keys.toList()))
    }

    // Users clicked any of our buttons
    onEvent("ClickButton") {
        // Directly respond with the value we get from the event, with a fallback
        furhat.say("You pressed ${it.get("data") ?: "something I'm not aware of" }")

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)
    }

    // Users saved some input
    onEvent("VariableSet") {
        // Get data from event
        println(it)
        val data = it.get("data") as Record
        println(data)
        val variable = data.getString("variable")
        println(variable)
        val value = data.getString("value")
        println(value)

        // Get answer depending on what variable we changed and what the new value is, and speak it out
        val answer = inputFieldData[variable]?.invoke(value)
        furhat.say(answer ?: "Something went wrong")

        // Let the GUI know we're done speaking, to unlock buttons
        send(SPEECH_DONE)
    }
}