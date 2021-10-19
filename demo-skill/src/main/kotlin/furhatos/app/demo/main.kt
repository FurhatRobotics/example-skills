package furhatos.app.demo

import furhatos.app.demo.flow.autoBehavior.autoBehavior
import furhatos.app.demo.flow.init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill
import furhatos.util.CommonUtils

val log = CommonUtils.getLogger(DemoSkill::class.java)

class DemoSkill : Skill() {
    override fun start() {
        // Start a flow for automatic behavior
        Flow().runAsync(autoBehavior())
        // ... and our normal flow
        Flow().run(init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}