package furhatos.app.jokebot

import furhatos.app.jokebot.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class JokebotSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
