package furhatos.app.dog

import furhatos.app.dog.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class DogSkill : Skill() {
    override fun start() {
        Flow().run(Main)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
