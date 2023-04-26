package furhatos.app.customasr

import furhatos.app.customasr.flow.Basic
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class CustomasrSkill : Skill() {
    override fun start() {
        Flow().run(Basic)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
