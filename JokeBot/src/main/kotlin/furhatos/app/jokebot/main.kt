package furhatos.app.jokebot

import furhatos.app.jokebot.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class JokebotSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
