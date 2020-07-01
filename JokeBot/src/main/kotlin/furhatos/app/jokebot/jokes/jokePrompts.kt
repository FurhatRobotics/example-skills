package furhatos.app.jokebot.jokes

/**
 * Returns a comment on the joke, based on joke score. This could be
 * -> A new joke
 * -> A mixed reception joke
 * -> A positive reception joke
 * -> A negative reception joke.
 */
fun getJokeComment(score: Double?): String {
    return when {
        score == null -> newIntros.random()
        score > 0.0 -> positiveIntros.random()
        score < 0.0 -> negativeIntros.random()
        else -> neutralIntros.random()
    }
}

private val newIntros = listOf(
        "I haven't tried this one before actually.",
        "This is a new one."
)

private val neutralIntros = listOf(
        "Let’s see what you think of this one.",
        "Mixed reactions on this one so far."
)

private val positiveIntros = listOf(
        "People usually like this one.",
        "This one has been a success so far."
)

private val negativeIntros = listOf(
        "This one is not liked by many, but maybe it's your kind of humor.",
        "I haven’t had much luck with this one.",
        "I’ll give this one another chance"
)

/**
 * Gives a comment based on the users reaction to the joke.
 *  -> User smiled or not
 *  -> User said it was a good/bad joke.
 */
fun getResponseOnUser(didUserLaugh: Boolean, badJoke: Boolean, goodJoke: Boolean): String {
    return if (didUserLaugh) {
        when {
            badJoke -> negativeCommentSmileResponse.random()
            goodJoke -> positiveCommentSmileResponse.random()
            else -> smileResponse.random()
        }
    } else {
        when {
            badJoke -> negativeCommentNoSmileResponse.random()
            goodJoke -> positiveCommentNoSmileResponse.random()
            else -> noSmileResponse.random()
        }
    }
}

private val negativeCommentSmileResponse = listOf(
        "At least it made you smile.",
        "Hey you smiled, so it can't have been that bad."
)

private val negativeCommentNoSmileResponse = listOf(
        "This is not your kind of joke, noted.",
        "Terrible joke, I am sorry"
)

private val positiveCommentNoSmileResponse = listOf(
        "But it didn't make you smile.",
        "I'm not sure if you're being sarcastic right now."
)

private val positiveCommentSmileResponse = listOf(
        "Wow! You really liked this joke!",
        "This is a very enjoyable joke",
        "We have the same sense of humor, amazing!"
)

private val noSmileResponse = listOf(
        "Not that funny? Noted.",
        "No? Alright.",
        "Not your style? I see.."
)

private val smileResponse = listOf(
        "I'm glad you liked it.",
        "Not bad right?",
        "Yay, I'm getting the hang of this."
)

val continuePrompt = listOf(
        "Should I go on?",
        "Would you like another one?"
)