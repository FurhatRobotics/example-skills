package furhatos.app.dog.flow

import furhatos.app.dog.DogSkill
import furhatos.app.dog.dog.*
import furhatos.app.dog.extensions.randomBark
import furhatos.app.dog.extensions.randomGrowl
import furhatos.app.dog.extensions.randomWhimpering
import furhatos.app.dog.extensions.setDogCharacter
import furhatos.app.dog.gestures.*
import furhatos.app.dog.utils.SmileBackState
import furhatos.app.dog.utils.isVirtual
import furhatos.flow.kotlin.*
import furhatos.skills.UserManager
import furhatos.util.CommonUtils
import gestures.FallAsleep
import gestures.WakeUpWithHeadShake
import org.apache.logging.log4j.Level.INFO

val idleHeadMovements = partialState {
    onTime(2000..2500, 3200..6000, instant = true) {
        // Regulates when Furhat does idle head movements
        val gesture = idleHeadMovements(
            // these overrides the ones defined in idleHeadMovements, was also affecting other functions
            // that have been removed. This is kept if we want to expand this in the future.
            strength = 1.0,
            duration = 2.0, // Affect how fast the idle head movements will be
            amplitude = 5.0, // How big the head movements will be
            gazeAway = false // Function removed, but kept here if we re-introduce
        )
        furhat.gesture(gesture)
    }
}

// Set your run configuration to use "-DIntelliJ=true" as a JVM argument when running the skill.
// It will let the skill know you are running the skill from inside intelliJ, and will change the references to the audio files so they will play!
val runningFromIntelliJ = System.getProperty("IntelliJ")?.toBoolean() ?: false
val logger = CommonUtils.getLogger(DogSkill::class.java)

val Main: State = state(Parent) {

    init {
        // Init settings
        isVirtual = furhat.isVirtual()
        setDogCharacter()
        UserManager.engagementPolicy = DogEngagementPolicy(UserManager, 1.0, 2.0, 4)
        furhat.attendNobody()

        parallel(abortOnExit = false) {
            goto(SmileBackState)
        }

        if (users.count > 0) {
            Dog.decreaseExcitement(40)
            furhat.attend(users.random)
            goto(Interacting)
        }
    }

    onEntry {
        println("State: ${thisState.name}")
        furhat.listen(timeout = 8000)
    }

    onReentry {
        furhat.listen(timeout = 8000)
    }

    onTime(2000..2500, 2000..4000, instant = true) {
        println("CurrentState: ${Dog.currentState.name}")
        println("Tiredness ${Dog.tirednessLevel}")
        println("Excitement ${Dog.excitementLevel}")

        val prevState = Dog.currentState
        Dog.updateCurrentState()
        val currState = Dog.currentState

        if (prevState == CurrentState.AWAKE && currState == CurrentState.ASLEEP) {
            furhat.gesture(FallAsleep)
        }

        when (Dog.currentState) {
            CurrentState.AWAKE -> {
                // Idle Tick
                Dog.increaseTiredness(8)
                Dog.decreaseExcitement(10)
                Dog.increaseHunger(4)

                // Getting sleepy
                if (Dog.tirednessLevel > FALL_ASLEEP_TIREDNESS_THRESHOLD - 10) {
                    if (System.currentTimeMillis() - lastYawnTime > YAWN_INTERVAL_IN_MS) {
                        // TODO: Add more random stuff
                        random({
                            furhat.gesture(yawn1)
                        }, {
                            furhat.gesture(yawn1)
                        })
                        lastYawnTime = System.currentTimeMillis()
                    }
                }

                // Regulates when Furhat does idle head movements
                val gesture = idleHeadMovements(
                    // these overrides the ones defined in idleHeadMovements, was also affecting other functions
                    // that have been removed. This is kept if we want to expand this in the future.
                    strength = 1.0,
                    duration = 2.0, // Affect how fast the idle head movements will be
                    amplitude = 5.0, // How big the head movements will be
                    gazeAway = false // Function removed, but kept here if we re-introduce
                )
                furhat.gesture(gesture)
            }
            CurrentState.ASLEEP -> {
                Dog.decreaseTiredness(7)
                Dog.increaseHunger(3)

                // TODO: Add random sleep stuff

                if (Dog.tirednessLevel < WAKEUP_TIREDNESS_THRESHOLD) {
                    furhat.gesture(WakeUpWithHeadShake)
                    Dog.currentState = CurrentState.AWAKE
                }
            }
        }
    }

    include(ResponseHandlers)

    onResponse {
        reentry()
    }

    onNoResponse {
        reentry()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Interacting)
    }

    onUserLeave {
        randomWhimpering()
    }

    onEvent<SenseUserVisible>(instant = true) {
        if (Dog.excitementLevel < EXCITEMENT_LOWER_THRESHOLD) {
            furhat.attend(it.userId!!)
            randomBark()
        }
    }
}

val Interacting: State = state(Parent) {
    onEntry {
        println("State: ${thisState.name}")

        when (Dog.excitementLevel) {
            // Low Excitement
            in 0..30 -> {
                random(
                    { randomGrowl() },
                    { randomWhimpering() },
                    {
                        furhat.gesture(sniffing3)
                        delay(300)
                        randomWhimpering()
                    },
                    {
                        furhat.gesture(sniffing3)
                        delay(300)
                        randomWhimpering()
                    }
                )
            }

            // Medium Excitement
            in 31..60 -> {
                random(
                    { randomBark() },
                    { furhat.gesture(sniffing1) },
                    {
                        furhat.gesture(sniffing3)
                        delay(600)
                        randomBark()
                    })
            }

            // High Excitement
            in 61..100 -> {
                random(
                    {
                        randomBark()
                        delay(300)
                        randomBark()
                    },
                    {
                        randomBark()
                        delay(300)
                        furhat.gesture(sniffing3)
                        delay(300)
                        randomBark()
                    },
                    {
                        furhat.gesture(sniffing3)
                        delay(300)
                        randomBark()
                        delay(100)
                        randomBark()
                    })
            }
        }

        furhat.listen(timeout = 8000)
    }

    onReentry {
        furhat.listen(timeout = 8000)
    }

    onTime(3000..3500, 15000..18000, instant = true) {
        Dog.decreaseExcitement(8)

        println("CurrentState: ${Dog.currentState.name}")
        println("Tiredness ${Dog.tirednessLevel}")
        println("Excitement ${Dog.excitementLevel}")

        // TODO: Goto main logic
        if (Dog.tirednessLevel > FALL_ASLEEP_TIREDNESS_THRESHOLD) {
            furhat.gesture(yawn1)
            delay(500)
            furhat.attendNobody()
            Dog.currentState = CurrentState.ASLEEP
            goto(Main)
        }

        if (Dog.excitementLevel < 20) {
            randomWhimpering()
            furhat.attendNobody()
            goto(Main)
        }

        when (Dog.excitementLevel) {
            // Low Excitement
            in 0..30 -> {
                random({
                    randomGrowl()
                    randomWhimpering()
                }, {
                    randomWhimpering()
                }, {
                    randomBark()
                    delay(300)
                    randomWhimpering()
                })
            }

            // Medium Excitement
            in 31..60 -> {
                random({
                    randomBark()
                    delay(500)
                    randomBark()
                    delay(500)
                    furhat.gesture(sniffing1)
                }, {
                    delay(100)
                    randomBark()
                    delay(500)
                    randomBark()
                }, {
                    furhat.gesture(sniffing1)
                    delay(300)
                    randomBark()
                    randomBark()
                })
            }

            // High Excitement
            in 61..100 -> {
                random({
                    furhat.gesture(shake1)
                    delay(100)
                    furhat.gesture(sniffing1)
                    delay(100)
                    randomBark()
                    delay(500)
                }, {
                    delay(100)
                    randomBark()
                    delay(500)
                    randomBark()
                    delay(100)
                    furhat.gesture(sniffing3)
                }, {
                    furhat.gesture(sniffing1)
                })
            }

        }

        // Regulates when Furhat does idle head movements
        val gesture = idleHeadMovements(
            // these overrides the ones defined in idleHeadMovements, was also affecting other functions
            // that have been removed. This is kept if we want to expand this in the future.
            strength = 1.0,
            duration = 2.0, // Affect how fast the idle head movements will be
            amplitude = 5.0, // How big the head movements will be
            gazeAway = false // Function removed, but kept here if we re-introduce
        )
        furhat.gesture(gesture)

        furhat.listen(timeout = 8000)
    }

    include(ResponseHandlers)

    onResponse {
        reentry()
    }

    onNoResponse {
        reentry()
    }

    onUserEnter {

    }

    onUserLeave {
        when (Dog.excitementLevel) {
            // Low Excitement
            in 0..30 -> {
                random({
                    randomGrowl()
                    randomWhimpering()
                }, {
                    randomWhimpering()
                }, {
                    randomBark()
                    delay(300)
                    randomWhimpering()
                })
            }

            // Medium Excitement
            in 31..60 -> {
                random({
                    randomBark()
                    delay(500)
                    randomWhimpering()
                }, {
                    delay(100)
                    randomWhimpering()
                    delay(500)
                    randomBark()
                }, {
                    randomWhimpering()
                    delay(500)
                    randomBark()
                })
            }

            // High Excitement
            in 61..100 -> {
                random({
                    randomWhimpering()
                    randomBark()
                    delay(100)
                    randomWhimpering()
                }, {
                    delay(100)
                    randomWhimpering()
                    delay(500)
                    randomWhimpering()
                }, {
                    delay(300)
                    randomWhimpering()
                    randomBark()
                })
            }
        }
    }
}

var continueWithoutMask = false

val MissingMask: State = state(Parent) {
    onButton("continue without mask") {
        continueWithoutMask = true
        goto(Main)
    }
    // do nothing.
}