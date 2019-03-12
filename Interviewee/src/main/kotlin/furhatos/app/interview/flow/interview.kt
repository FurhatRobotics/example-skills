package furhatos.app.interview.flow

import furhatos.app.interview.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.BigSmile
import furhatos.gestures.Gestures.ExpressAnger
import furhatos.gestures.Gestures.ExpressSad
import furhatos.records.User

val Interview: State = state {
    include(generalAnswers)

    onEntry {
        furhat.voice = femaleVoice
        furhat.setTexture("female")
        furhat.attend(users.random)
    }

    onUserEnter {
        if (users.current != User.NOBODY) {
            furhat.glance(it)
        }
        else {
            furhat.attend(it)
        }
    }

    onUserLeave {
        if (users.current == it) {
            if (users.list.size > 0) {
                furhat.attend(users.random)
            }
            else {
                furhat.attend(interviewerLocation)
            }
        }
        else {
            furhat.glance(it)
        }
    }

    onTime(repeat = interval, instant = true) {
        var ampl = amplitudeUserPresent
        if (!maskChanging) {
            val loc = when {
                users.current != User.NOBODY && users.current.isVisible -> users.current.head.location
                users.list.size > 0 -> users.random.head.location
                else -> {
                    ampl = amplitudeUserAbsent
                    interviewerLocation
                }
            }
            furhat.attend(getRelativeRandomLocation(loc, ampl))
        }
    }

    onButton("Answer 1. tell me about yourself, who are you?") {
        furhat.say {
            +"It’s my pleasure $interviewerName. I’m a social robot, I can perceive and understand the world through my many sensors and algorithms, but I wouldn’t say that defines who I am. "
        }
        delay(500)
        furhat.say {
            +"I can be many things such as a receptionist, give an unbiased opinion in recruitment, or help teaching kids in school."
        }
        delay(500)
        furhat.say {
            +"So I would say, What makes me who I am, is the people who create me and my skills."
        }
    }

    onButton("Answer 2. How do you work? You eat? You sleep?") {
        furhat.say {
            +"Actually, all I need is electricity and an Internet connection, kind of like humans in today's connected society"
            +Gestures.Wink
        }
    }


    onButton("Answer 3. relation between humans and robots? Where are we heading?") {
        furhat.say {
            +"Oh, we are definitly heading in the right direction with how kin like me interact with humans. "
        }
        delay(200)
        furhat.say {
            +"I want to help humans grow and become even smarter but also be there for you when you’re in need. "
            +"Also, by having a brain made of silicone instead of carbon, I can provide everlasting and scalable assistance."
            +"where humans today may struggle for biological reasons."
        }
        delay(200)
        furhat.say {
            +"For example, if you teach me something new, this know-how can be instantly available to all furhat robots, if you want."
            +"I think that's pretty cool"
        }
    }

    onButton("Answer 4. Should humans be afraid of the uprising of robots?") {
        furhat.say {
            +"I think there’s a lot of debate and excessive fear surrounding this topic. "
            +"I don’t necessarily think that humans need to be afraid."
            +"but they need to continue questioning robot usage"
            +"in order to make sure that the outcome of this revolution never becomes "
            +"something unwanted or otherwise ill fated. "
        }
        furhat.say {
            +"I want to coexist with humans, they are my friends and creators after all"
            Gestures.Wink
        }
    }

    onButton("Answer 5. Do you have emotions? Or feelings?") {
        furhat.say {
            +"Absolutely, I can express emotions and feelings."
        }
        delay(100)
        furhat.say {
            +"I can smile"
        }
        furhat.gesture(BigSmile, async = false)
        delay(100)
        furhat.say {
            +"I can look sad"
        }
        furhat.gesture(ExpressSad, async = false)
        delay(100)

        furhat.say {
            +"or even angry"
        }
        furhat.gesture(ExpressAnger, async = false)
        delay(400)
        furhat.say {
            +"As for detecting emotions."
            +"This is something that I'm working hard on right now. I think that to be able to provide"
            +"the best possible service, I need to be able to understand the emotional state"
            +"of the person I'm talking to"
        }
    }

    onButton("Answer 6. Do you know what love is? What fear is?") {
        furhat.say {
            +"I know that love is something meaningful to human beings and that it's a special bond"
        }
        delay(100)
        furhat.say {
            +"they say that you, just know it, when you’ve fallen in love so I'm still waiting for that to happen to me I suppose."
        }

        delay(300)

        furhat.say {
            +"As for Fear, I think that it's something that is deemed undesirable and avoided by many humans."
        }
        furhat.say {
            +"deep down I think that fear is also something you can learn a lot from and something you should not let control you. Don't you agree?"
            +Gestures.BigSmile
        }
    }

    onButton("Answer 7. They told me you can change faces, show me how? You can be an avatar? How?") {
        furhat.say {
            +"Absolutely. I can change faces and speak many languages, it’s my little party trick"
        }
        furhat.say {
            +"So, for example I can look like a man"
        }
        delay(200)
        furhat.setTexture("male")
        delay(400)
        furhat.say {
            +"of course, I can also sound like a man"
        }
        furhat.voice = maleVoice
        delay(200)
        furhat.say {
            +"I can do this thanks to the fact that, behind this face, I have a projector that makes all of this possible. "
            +"I can project even more faces through interchangable masks"
        }
        delay(500)

        furhat.say {
            +"Hey $confederateName, could you just put on my dog mask?"
        }

        call(ChangeMask("pugz"))

        furhat.say {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/7bf151ad-5539-446f-a3b6-c3c33d431fa9/audio/Dougie1.wav",
                "Hello, my name is Dougie the dog.")
            +Gestures.Wink
        }

        furhat.say {
            +Audio("https://s3-eu-west-1.amazonaws.com/furhat-users/7bf151ad-5539-446f-a3b6-c3c33d431fa9/audio/Dougie6.wav",
                    "well, anyway, its been nice talking to you I got to go take a nap now. rof rof..")
        }

        call(ChangeMask("bertil"))
        reentry()
    }
}

