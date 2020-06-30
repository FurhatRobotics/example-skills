package furhatos.app.jokebot.jokes

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import furhatos.util.CommonUtils

val BASIC_LIST_OF_JOKES = listOf(
        Joke("What do robots eat as snacks", "micro-chips"),
        Joke("What do robots do at lunchtime", "have a mega-byte"),
        Joke("What happened when they shut down the robot motorway?", "Everyone had to take the R2 detour.", 1.0),
        Joke("Why was the robot bankrupt?", "He had used all his cache"),
        Joke("Why did the robot marry his partner?", "He couldn't resistor"),
        Joke("What does R2D2 use to open PDF files?", "Adobe. wan. kenobi."),
        Joke("Why do robots take holidays?", "To recharge their batteries"),
        Joke("Who's a robot's favourite author?", "Anne. droid."),
        Joke("Why was the robot tired when it got home?", "it had a hard drive!"),
        Joke("What is most important when telling jokes", "timing")
)

data class Joke(val intro: String, val punchline: String, var score: Double? = null)


object JokeHandler {

    private val jokeFile = CommonUtils.getAppDataDir("jokes.json") //File to read/save jokes to
    private val gson = Gson().newBuilder().setPrettyPrinting().create() //JSON parser/serializer
    private val listOfJokes = getJokes() //List of jokes
    private lateinit var currentJoke: Joke //Will be initialized when we request the first joke.

    /**
     * Returns a joke, and saves that joke to the current joke variable.
     */
    fun getJoke(): Joke {
        currentJoke = listOfJokes.random()
        return currentJoke
    }

    /**
     * Changes the current joke score with the change provided.
     */
    fun changeJokeScore(scoreChange: Double) {
        if (currentJoke.score == null) {
            currentJoke.score = 0.0
        }
        currentJoke.score = currentJoke.score!! + scoreChange
        writeToFile()
    }

    /**
     * Tries to read the jokes from file. If that file does not exist, or the JSON cannot be parsed, we return a basic
     * list of jokes.
     */
    private fun getJokes(): List<Joke> {
        return if (jokeFile.exists()) {
             try {
                 gson.fromJson(jokeFile.readText(), Array<Joke>::class.java).toList()
            } catch (_: JsonSyntaxException) {
                BASIC_LIST_OF_JOKES
            }
        } else {
            BASIC_LIST_OF_JOKES
        }
    }

    /**
     * Writes the current list of jokes to a file
     */
    private fun writeToFile() {
        if (!jokeFile.exists()) {
            jokeFile.createNewFile()
        }
        jokeFile.writeText(gson.toJson(listOfJokes))
    }

}

fun main() {
    println("Testing")
    val thing = JokeHandler.getJoke()
    println("Score now:" + thing.score)
    JokeHandler.changeJokeScore(0.25)
    println("New score:" + thing.score)
}