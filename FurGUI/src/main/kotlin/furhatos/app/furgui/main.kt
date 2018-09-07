package furhatos.app.furgui

import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class FurguiSkill : Skill() {
    override fun start() {
        Flow().run(NoGUI)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
