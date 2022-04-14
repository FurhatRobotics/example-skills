package furhatos.app.openaichat

import furhatos.app.openaichat.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*
import furhatos.nlu.LogisticMultiIntentClassifier

class OpenaichatSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    LogisticMultiIntentClassifier.setAsDefault()
    Skill.main(args)
}
