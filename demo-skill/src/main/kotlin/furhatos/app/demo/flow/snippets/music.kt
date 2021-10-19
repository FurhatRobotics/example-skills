package furhatos.app.chitchat.snippets

import furhatos.app.demo.personas.Persona
import furhatos.flow.kotlin.UserDataDelegate
import furhatos.flow.kotlin.future
import furhatos.nlu.wikidata.MusicArtist
import furhatos.nlu.wikidata.MusicGenre
import furhatos.records.User
import furhatos.snippets.*
import furhatos.util.random

object MusicTopic : Topic()

object RT_FavoriteArtist : RobotLabel()
object RA_LikeGenre : RobotLabel()
object UA_TopicMusic : UserLabel()
object RT_LikeGenre : RobotLabel()

var User.favoriteArtist : MusicArtist? by UserDataDelegate()

val music = snippets(MusicTopic) {

    val entities = object {
        var artist : MusicArtist? = null
        var genre : MusicGenre? = null
    }

    entities(entities)

    snippet {
        context(RA_WhichTopic)
        user("music") implies UA_TopicMusic
    }

    snippet {
        user("Let's chat about music") label UA_TopicMusic
        choice {
            respond("Okay, let's chat about music") cond hasTopicInitiative()
            respond("Sorry, I don't have anything more to say about music")
        }
        proceed()
    }

    snippet {
        user("Do you like music")
        respond("Sure, I love music")
        respond(RT_FavoriteArtist)
    }

    snippet {
        user("What is your favorite artist")
        respond { +"My favorite artist is ${Persona.active.favoriteArtist?.name}" } label RT_FavoriteArtist
        respond { +future{"I really like the album ${Persona.active.favoriteArtist?.albums?.random()}" }}
    }

    snippet {
        request("What is your favorite artist?")
        user("@artist", "I like @artist") effect {
            user.favoriteArtist = entities.artist
        }
        respond("Yes")
        respond { +future{"${entities.artist?.itIs} very good"}}
        respond { +future{"I really like the album ${entities.artist?.albums?.random()}" }}
        request { +future{"Are you into ${entities.artist?.genre} in general?" }} label RA_LikeGenre
    }

    snippet {
        context(RA_LikeGenre)
        user(UT_Yes)
        respond{+future{"I like ${entities.artist?.genre} too, but I mostly listen to ${Persona.active.favoriteArtist?.genre}"}} implies RT_LikeGenre
    }

    snippet {
        context(RA_LikeGenre)
        user(UT_No)
        respond{+future{"I see. I mostly listen to ${Persona.active.favoriteArtist?.genre}"}} implies RT_LikeGenre
    }

    snippet {
        context(RA_LikeGenre)
        user("I like @genre", "no I like @genre", "no I listen to @genre", "I mostly listen to @genre")
        respond{+future{"Yes, I like ${entities.genre} too, but I mostly listen to ${Persona.active.favoriteArtist?.genre}"}} implies RT_LikeGenre
    }

}

