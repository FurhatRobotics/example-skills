package furhatos.app.jokebot.jokes


val listOfJokes = listOf(
        Joke("What do robots eat as snacks", "micro-chips"),
        Joke("What do robots do at lunchtime", "have a mega-byte"),
        Joke("What happened when they shut down the robot motorway?", "Everyone had to take the R2 detour."),
        Joke("Why was the robot bankrupt?", "He had used all his cache"),
        Joke("Why did the robot marry his partner?", "He couldn't resistor"),
        Joke("What does R2D2 use to open PDF files?", "Adobe wan kenobi"),
        Joke("Why do robots take holidays?", "To recharge their batteries"),
        Joke("Who's a robot's favourite author?", "Anne droid"),
        Joke("Why was the robot tired when it got home?", "it had a hard drive!"),
        Joke("What is most important when telling jokes", "timing")
)

data class Joke(val intro: String, val punchline: String)