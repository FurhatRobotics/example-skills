package furhatos.app.interview

import furhatos.app.interview.flow.Init
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class InterviewSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
