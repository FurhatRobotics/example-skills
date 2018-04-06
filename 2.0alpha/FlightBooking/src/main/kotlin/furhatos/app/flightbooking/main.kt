package furhatos.app.flightbooking

import furhatos.app.flightbooking.flow.Idle
import furhatos.skills.Skill
import furhatos.flow.kotlin.*
import furhatos.util.Gender
import furhatos.util.Language

class FlightBookingSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
