package furhatos.app.jokebot.util

/**
 * Calculates a score based on time spent laughing, and if the user said if it was a good/bad joke.
 * @param timeSpentLaughing The amount in ms that the user spent with a smile.
 * @param maxTimeLaughing The amount in ms that the user could have spent smiling.
 * @param badJoke Boolean depicting if the the user said it was a bad joke.
 * @param goodJoke Boolean depicting if the user said it was a good joke.
 * @return value between 0.75 and -0.5 depicting how well the joke worked.
 */
fun calculateJokeScore(timeSpentLaughing: Long, maxTimeLaughing: Int, badJoke: Boolean, goodJoke: Boolean): Double {
    val smileModifier = when {
        badJoke -> -0.25
        goodJoke -> 0.25
        else -> 0.0
    }

    val gestureModifier = if (timeSpentLaughing != 0L) {
        (maxTimeLaughing / timeSpentLaughing) * 0.5
    } else {
        -0.25
    }

    return gestureModifier + smileModifier
}
