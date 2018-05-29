package furhatos.app.presentation

import furhatos.app.presentation.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class PresentationSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
