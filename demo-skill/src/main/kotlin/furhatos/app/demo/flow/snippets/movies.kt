package furhatos.app.chitchat.snippets

import furhatos.app.demo.personas.Persona
import furhatos.flow.kotlin.future
import furhatos.nlu.wikidata.Film
import furhatos.snippets.*

object MoviesTopic : Topic()

object UA_TopicMovies : UserLabel()
object RA_LikesMoviesFromCountry : RobotLabel()
object RT_FavoriteMovie : RobotLabel()

val movies = snippets(MoviesTopic) {

    val entities = object {
        var film: Film? = null
    }

    entities(entities)

    snippet {
        context(RA_WhichTopic)
        user("film", "movies") implies UA_TopicMovies
    }

    snippet {
        user("Let's chat about movies") label UA_TopicMovies
        choice {
            respond("Okay, let's chat about movies") cond hasTopicInitiative()
            respond("Sorry, I don't have anything more to say about movies")
        }
        proceed()
    }

    snippet {
        request("What is your favorite movie?")
        user("@film", "I like @film", "my favorite movie is @film")
        respond("Yes")
        respond{+future { "I really like ${entities.film?.cast?.get(0)?.name} in that movie"}}
        respond{+future { "Are you into ${entities.film?.countryOfOrigin?.demonym} films in general?"}} label RA_LikesMoviesFromCountry
    }

    snippet {
        user("What is your favorite movie")
        respond { +"My favorite movie is ${Persona.active.favoriteFilm?.name}" } label RT_FavoriteMovie
        respond{+future { "I really like ${Persona.active.favoriteFilm?.cast?.get(0)?.name} in that movie"}}
    }

    snippet {
        context(RA_LikesMoviesFromCountry)
        user(UT_Yes)
        respond("Me too")
    }

    snippet {
        context(RA_LikesMoviesFromCountry)
        user(UT_No)
        respond("Well, me neither actually")
    }

}