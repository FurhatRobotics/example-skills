package furhatos.app.complimentbot

import furhatos.app.complimentbot.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class ComplimentbotSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
