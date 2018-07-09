package furhatos.app.parrot

import furhatos.app.parrot.flow.Idle
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class Parrot : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
