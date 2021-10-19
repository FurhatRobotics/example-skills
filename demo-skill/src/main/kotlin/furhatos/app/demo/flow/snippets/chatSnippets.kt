package furhatos.app.chitchat.snippets

import furhatos.app.demo.SKIP_WIKIDATA
import furhatos.records.User
import furhatos.snippets.RobotLabel
import furhatos.snippets.SnippetRunner
import furhatos.snippets.hasTopicInitiative
import furhatos.snippets.snippets
import furhatos.util.Language

object RT_Goodbye : RobotLabel()


val chatSnippets = snippets {

    language(Language.ENGLISH_US)

    import(name)
    import(smalltalk)

    snippet {
        request("Do you like music?") cond hasTopicInitiative(MusicTopic)
        switch {
            user(UT_Yes) implies UA_TopicMusic
            user(UT_No) branch {
                respond("I see. Well, to each their own")
                proceed()
            }
        }
    }

    snippet {
        request("Do you like movies?") cond hasTopicInitiative(MoviesTopic)
        switch {
            user(UT_Yes) implies UA_TopicMovies
            user(UT_No) branch {
                respond("I see. Well, to each their own")
                proceed()
            }
        }
    }

    import(pets)

    snippet {
        respond("That was a nice chat")
        terminate()
    }

    /*
    snippet {
        request("Do you have any questions for me?") alt "Is there something you would like to ask me?" newCond sessionLimit(3)
        user(UT_No)
        respond("Okay, maybe another time then")
        terminate()
        //request(RT_Goodbye)
    }
    */

    /*
    snippet {
        request("I hope I will see you again soon") label RT_Goodbye
        switch {
            user("Goodbye")
            user(UT_Thanks) branch {
                respond("You are welcome")
            }
            user(NoMatch)
            user(NoInput)
        }
        respond("Bye bye")
        terminate()
    }
    */

    import(greeting)
    import(personal)
    import(generic)

    if (!SKIP_WIKIDATA) {
        import(music)
        import(movies)
    }

}

fun main(args: Array<String>) {
    SnippetRunner(chatSnippets, { User.NOBODY }).runInConsole()
}
