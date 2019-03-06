package furhatos.app.furgui

// Buttons
val buttons = listOf("A button", "Another button")

/*
 Input fields, each with a answer to be spoken. The answer is defined as a lambda
 function since we want to have different answers depending on what favorite robot the
 user inputs
  */
val inputFieldData = mutableMapOf<String, (String) -> String>(
    "Name" to { name -> "Nice to meet you $name" },
    "Age" to { _ -> "That's a nice age. I'm 5 years old " },
    "Favorite robot" to { robotName ->
        when (robotName.toLowerCase()) {
            "furhat" -> "I love you too!"
            "pepper" -> "I prefer salt to be honest"
            "jibo" -> "Jibo was cute. I miss him"
            else -> "Really? I don't know $robotName. I'd like to meet it"
        }
    }
)

