package furhatos.app.pizzaorder

import furhatos.app.pizzaorder.flow.Idle
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class PizzaOrderSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
