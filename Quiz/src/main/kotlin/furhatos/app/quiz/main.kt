package furhatos.app.quiz

import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class Quiz : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
