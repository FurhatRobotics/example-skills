package furhatos.app.quiz.questions

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer, followed by alternative pronunciations
 *  -A list of other answers followed by their alternatives
 *  -A funfact or remark that Furhat can say
 */
/*
val questionsEnglish = mutableListOf(

        Question("I bet, you have never heard, of a <prosody rate=\"85%\"> black sapote </prosody>. Its a fruit from Central America with a special feature. What does it taste like?",
                answer = listOf("chocolate pudding", "chocolate", "pudding", "choco", "shock"),
                alternatives = listOf(listOf("Fries", "fly", "fry", "fried"), listOf("berry salad", "berry"), listOf("meat", "eat")),
                funfact = "so yes, in theory, chocolate pudding grows on trees!"),

        Question("A cute question in between: Which of the following animals hold hands with each other, while sleeping?",
                answer = listOf("sea otter", "otter"),
                alternatives = listOf(listOf("dolphins"), listOf("spiders"), listOf("penguins")),
                funfact = "Of the animals mentioned, they are also the only ones, that actually have hands."),

        Question("Everyone hates getting charged for speeding. However, the first speeding ticket was given in 18 96.<prosody rate=\"85%\"> How many miles per hour was the first person too fast?</prosody>",
                answer = listOf("6"),
                alternatives = listOf(listOf("20"), listOf("1"), listOf("31")),
                funfact = "He was driving with astonishing 8 miles per hour and but the road said only 2... With these speed limits, i have to be carefully the next time i am driving."),

        Question("Many countries proudly boasts with their national animal. America with an eagle, or Russia with the bear. But what is the national animal of Scotland?",
                answer = listOf("A unicorn"),
                alternatives = listOf(listOf("The Loch Ness Monster", "Nessie", "Monster", "Loch Ness"), listOf("A phoenix"), listOf("A dinosaur")),
                funfact = "so majestic!"),

        Question("As a social robot, i, will talk a lot. Especially in English. By the way,<prosody rate=\"85%\"> what is the most common letter in the english language?</prosody>",
                answer = listOf("E"),
                alternatives = listOf(listOf("A"), listOf("I", "eye"), listOf("S")),
                funfact = "What begins with a P, ends with an E, and has a million letters in it? The Post Office"),

        Question("Brigadier Sir Nils Olav the third is a member of the norwegian army. He holds the title, that has been passed down by his grandfather. But what is so special about him?",
                answer = listOf("he is a penguin", "penguin", "animal"),
                alternatives = listOf(listOf("he is only 5 years old", "child", "5", "only", "age")),
                funfact = "He was adopted by the norwegian king in 19 61. The photo of the army saluting to him is really special."),

        Question("I am not a good dancer with my two left feet. And while many humans can move their body to music, there is one other species, who can also do this.<prosody rate=\"85%\"> Which one of the following animals can dance to music?</prosody>",
                answer = listOf("sea lions", "lions"),
                alternatives = listOf(listOf("bears"), listOf("dogs"), listOf("crabs", "crap")),
                funfact = "Some day i will enroll myself in a tango course and will be the main person on the dance-floor.")

)*/
/*
val questionsSwedenEnglish = mutableListOf(

        Question("One of Sweden's most known Royals, Karl the 14th Johan, doesn't origin from Sweden. Previous to his new job, he was a general for which country?",
                answer = listOf("France", "French"),
                alternatives = listOf(listOf("Prussia", "Germany", "German"), listOf("Russia", "Russian"), listOf("Norway", "Norwegian")),
                funfact = "He was know as Jean Bernadotte, and served in the French Army. Hopefully, one day, I'll get a promotion like that."),

        Question("Everybody knows, what the Nobel Prize is, but what was Mr Nobel's first name?",
                answer = listOf("Alfred", "Air France"),
                alternatives = listOf(listOf("Albert"), listOf("Isak"), listOf("Alexander")),
                funfact = "Did you know, he named his invention, dynamite, after the Greek word for power? This sounds much better than the first name, Nobel's Safety Powder"),

        Question("What is the national animal of Sweden?",
                answer = listOf("elk", "moose", "alces alces", "el"),
                alternatives = listOf(listOf("reindeer", "dear"), listOf("arctic fox", "arctic", "fox"), listOf("brown bear", "bear", "brown")),
                funfact = "They really look majestic, don't they?"),

        Question("The swedish language is a beautiful one. And apart from Sweden, an official language in which other country?",
                answer = listOf("Finland", "thin mint", "financed", "synonym"),
                alternatives = listOf(listOf("Russia"), listOf("Norway", "noway"), listOf("Denmark", "there now"))),

        Question("Which Statement about Volvo is true:",
                answer = listOf("A volvo engineer invented the seatbelt", "seatbelt", "engineer", "seagull"),
                alternatives = listOf(listOf("Their name is an acronym", "acronym", "name")),
                funfact = "North Korea once ordered 1000 Volvo's in 19 74. They haven't payed one of them until today."),

        Question("In what year did, ABBA,  win the Eurovision song festival?",
                answer = listOf("19 74", "1974", "74"),
                alternatives = listOf(listOf("19 78", "1978", "78"), listOf("19 76", "1976", "76"), listOf("19 72", "1972", "72")),
                funfact = "They won it with their well known song Waterloo. Man, i do love that song"),

        Question("What does a swedish person think of, when he hears: <prosody rate=\"85%\"> Dagen H Day?</prosody>",
                answer = listOf("The day they changed the traffic from left to right-sided", "change", "left to right", "traffic"),
                alternatives = listOf(listOf("The day that marks the beginning of summer", "summer", "begin")),
                funfact = "The rate of car accidents has subsequently dropped. Take that, Great Britain!")
)*/
/*
val questionsRobotEnglish = mutableListOf(

        Question("What is the name of the Robot, WALLY meets on Earth in the Movie, WALL-E",
                answer = listOf("EVE", "evening"),
                alternatives = listOf(listOf("SKY"), listOf("AMBER"), listOf("AMY")),
                funfact = ""),

        Question("In which film does Arnold Schwarzenegger have a role with the name, T-800??",
                answer = listOf("Terminator", "terminate"),
                alternatives = listOf(listOf("I Robot", "Robot"), listOf("Star Wars", "wars"), listOf("Star Trek", "trek")),
                funfact = "Hasta la vista, baby"),

        Question("How many languages can C3PO speak? According to himself in the Star Wars Universe",
                answer = listOf("over 6 million", "6"),
                alternatives = listOf(listOf("200 thousand", "200"), listOf("10"), listOf("1.5 Million", "1.5")),
                funfact = ""),

        Question("Have you seen a roomba? These cute little fellas have a history, which started with a robot from a Science-Fiction movie. Do you know which robot was the inspiration for it?",
                answer = listOf("R2D2", "R2", "D2", "R", "2"),
                alternatives = listOf(listOf("Baymax", "beatch", "bay", "max"), listOf("T-800", "800"), listOf("WALL-E", "Wally")),
                funfact = "These are the droids you are looking for. But yes, robots in Science-Fiction movies paved the way for many robots in today's world."),

        Question("The wonderful wizard of Oz was one of my favourite books, when i was a little robot. Especially the tin man. He was one of my personal heroes. What did he want from the Wizard?",
                answer = listOf("A heart", "heart"),
                alternatives = listOf(listOf("A new head", "head"), listOf("A friend", "friend", "french"), listOf("nothing")),
                funfact = ""),

        Question("Am i really intelligent, or just a program. That may be a good question, but, <prosody rate=\"85%\"> there is a test, that can check via conversation, if a machine behaves similarly to a human being. How is this test called? </prosody>",
                answer = listOf("The turing test", "turing", "imitation game"),
                alternatives = listOf(listOf("The intelligence test", "intelligence"), listOf("The T test", "T", "tea"), listOf("The oddy test", "oddy", "odd")),
                funfact = "I would certainly rock this test. I mean, look at me."),

        Question("What was the name of the first robot?",
                answer = listOf("Unimate", "unity", "ultimate", "unite", "duty", "mate", "anyways"),
                alternatives = listOf(listOf("Roby", "robbery", "rob"), listOf("Carl", "call", "car"), listOf("T-800", "800", "tea")),
                funfact = "And now 70 Years later, i am the best robot now!")
)*/

/*
val questionsMusicEnglish = mutableListOf(

        Question("It is an open secret, that Taylor Swift had some inspiration for some songs, from her ex-boyfriends. How many songs has she dedicated to them, in total?",
                answer = listOf("21"),
                alternatives = listOf(listOf("3", "free"), listOf("8", "ate", "state"), listOf("14")),
                funfact = "That's also a way of getting over someone. But not my first choice."),

        Question("Which classic composer was deaf?",
                answer = listOf("Beethoven", "Ludwig van Beethoven"),
                alternatives = listOf(listOf("Bach", "Johann Sebastian Bach"), listOf("Mozart", "Wolfgang Amadeus Mozart"), listOf("Vivaldi", "Antonio Vivaldi", "Uvaldi")),
                funfact = "Some music genres make me wish, I, was deaf too."),

        Question("The Rock band, ACDC, originates from which country?",
                answer = listOf("Australia"),
                alternatives = listOf(listOf("US", "USA", "America"), listOf("Mexico"), listOf("Japan")),
                funfact = "Did you know, they use their song, Thunderstruck, to help beat cancer? Don't believe me? Look it up."),

        Question("Who sang a duet with Ed Sheeran on his song Perfect?",
                answer = listOf("Beyonce"),
                alternatives = listOf(listOf("Rihanna"), listOf("Madonna"), listOf("Eminem")),
                funfact = "I think, she was the perfect choice."),

        Question("Whose career as a solo performer was launched with the song, Careless Whisper?",
                answer = listOf("George Michael", "Michael", "George"),
                alternatives = listOf(listOf("Ed Sheeran", "Sheeran"), listOf("Freddie Mercury", "Freddie", "Mercury"), listOf("Katy Perry", "Katy")),
                funfact = "I will never forget this saxophone solo."),

        Question("There was a very famous singer, who was John Lennon’s hero. But John was disappointed, when they finally met. Who was it?",
                answer = listOf("Elvis Presley", "Elvis", "Presley"),
                alternatives = listOf(listOf("Michael Jackson", "Jackson"), listOf("Arianna Grande", "Grande", "Ari"), listOf("Frank Sinatra", "Sinatra")),
                funfact = "Did you know, Elvis only daughter, married Michael Jackson as well as Nicholas Cage?"),

        Question("Which Korean Artist's Video became the first, to reach one billion views on Youtube?",
                answer = listOf("Psy", "Y", "Psi"),
                alternatives = listOf(listOf("BTS", "BT","PJ", "PJs")),
                funfact = "")
)
*/
/*
val questionsScienceEnglish = mutableListOf(

        Question("Where can you find the Sea of Tranquility?",
                answer = listOf("The moon", "moon", "afternoon", "mood"),
                alternatives = listOf(listOf("Turkey"), listOf("Germany", "German"), listOf("The united states", "united states", "states", "US", "USA")),
                funfact = ""),

        Question("Who was the first man on the moon?",
                answer = listOf("Neil Armstrong", "Armstrong", "Neil"),
                alternatives = listOf(listOf("Buzz Aldrin", "Buzz", "Aldrin"), listOf("Michael Collins", "Michael", "Collins"), listOf("Yuri Gagarin", "Yuri", "Gagarin")),
                funfact = ""),

        Question("Your body can't survive, without a small amount of iron. But where in the human body, is the most iron located?",
                answer = listOf("the blood", "blood", "in the blood"),
                alternatives = listOf(listOf("the brain", "brain"), listOf("the skin", "skin"), listOf("the bones", "bones", "town")),
                funfact = "This answer is pretty, iron-ic, isn't it?"),

        Question("Have you ever seen a periodic table? Did you know, there is one letter missing from the alphabet? Which one is it?",
                answer = listOf("J", "Jay"),
                alternatives = listOf(listOf("X", "ex"), listOf("Y", "Why"), listOf("Q", "queue")),
                funfact = "To be fair, that was a hard question."),

        Question("Dynamite is known as something very explosive. Which of the following ingredients can be used in its manufacture?",
                answer = listOf("peanuts"),
                alternatives = listOf(listOf("banana"), listOf("coconut", "how can i"), listOf("rice", "ice","dries", "dyes")),
                funfact = "Peanut oil can be processed to produce glycerol, which can be used for nitroglycerin. However, there are also other options. Which means, more peanut butter for us."),

        Question("We all know, the biggest bone of the human body is the femur in your thigh.<prosody rate=\"85%\"> But in which part of your body can you find the smallest one?</prosody> ",
                answer = listOf("in the ear", "middle ear"),
                alternatives = listOf(listOf("in the hand", "handy"), listOf("in the feet", "fee"), listOf("in the chest", "chess")),
                funfact = "My best friend told me, he had my back. I'd been wondering, where that went."),

        Question("As one of the most famous physicist, Schrödinger is well known for his pet, he used for an example. Do you know what animal it was?",
                answer = listOf("a cat", "catch", "cats"),
                alternatives = listOf(listOf("a dog"), listOf("a bird"), listOf("a guinea pig", "pig")),
                funfact = "Schrödinger made this thought experiment in 1935, which means, his cat would be very old by now")
)
*/
val questionsRobotEnglish = mutableListOf<Question>()
val questionsRandomEnglish = mutableListOf<Question>()
val questionsMusicEnglish = mutableListOf<Question>()
val questionsScienceEnglish = mutableListOf<Question>()
val questionsSwedenEnglish = mutableListOf<Question>()