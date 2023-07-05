package furhatos.app.attentionGrabber

import furhatos.app.attentiongrabber.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class AttentionGrabberSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
