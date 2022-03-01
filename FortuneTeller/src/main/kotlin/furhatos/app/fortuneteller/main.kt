package furhatos.app.fortuneteller

import furhatos.app.fortuneteller.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class FortunetellerSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
