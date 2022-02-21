package furhatos.app.furhatdog

import furhatos.app.furhatdog.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class FurhatdogSkill : Skill() {
    override fun start() {
        Flow().run(Main)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
