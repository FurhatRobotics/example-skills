package furhatos.app.wolframalpha

import furhatos.app.wolframalpha.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class WolframalphaSkill : Skill() {
    override fun start() {
        Flow().run(idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
