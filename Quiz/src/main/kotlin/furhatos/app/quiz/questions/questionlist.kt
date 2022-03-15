package furhatos.app.quiz.questions

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer, followed by alternative pronounciations
 *  -A list of other answers followed by their alternatives
 */
val questionsEnglish = mutableListOf(

        Question("Which country won the 2016 Eurovision Song competition?",
                answer = listOf("Ukraine"),
                alternatives = listOf(listOf("Sweden"), listOf("France"), listOf("Finland"))),

        Question("When was the first circumnavigation of the world completed?",
                answer = listOf("15 22", "1522"),
                alternatives = listOf(listOf("14 99", "1499"), listOf("16 32", "1632"), listOf("15 78", "1578"))),

        Question("During which decade did Elvis Presley die?",
                answer = listOf("70s", "seventies", "70"),
                alternatives = listOf(listOf("50s", "fifties", "50"), listOf("60s", "sixties", "60"), listOf("80s", "eighties", "hades", "80"))),

        Question("What is the largest freshwater lake in the world?",
                answer = listOf("Lake Superior", "Superior"),
                alternatives = listOf(listOf("Lake Victoria", "Victoria"), listOf("Lake Michigan", "Michigan"), listOf("Great Bear Lake", "Great Bear"), listOf("Lake Ontario", "Ontario"))),

        Question("Where can you find the Sea of Tranquility?",
                answer = listOf("The moon", "moon"),
                alternatives = listOf(listOf("Turkey"), listOf("Germany"), listOf("The united states", "united states", "states"))),

        Question("What is the seventh planet from the Sun?",
                answer = listOf("Uranus", "anus"),
                alternatives = listOf(listOf("Pluto"), listOf("Neptune", "tune"), listOf("Saturn"))),

        Question("In what year did, ABBA,  win the Eurovision songfestival?",
                answer = listOf("19 74", "1974", "74"),
                alternatives = listOf(listOf("19 78", "1978", "78"), listOf("19 76", "1976", "76"), listOf("19 72", "1972", "72")),
                funfact = "They won it with their well known song Waterloo. Man, do I love that song"),

        Question("What is the title of the famous novel by George Orwell?",
                answer = listOf("19 84", "1984"),
                alternatives = listOf(listOf("The lord of the rings", "lord of the rings", "the rings"), listOf("The Great Gatsby", "great gatsby", "the great", "gatsby"), listOf("Of mice and men", "mice and men"))),

        Question("Chardonnay. Malaga. and Merlot. are all types of which fruit?",
                answer = listOf("Grape", "grapes"),
                alternatives = listOf(listOf("Berry", "berries"), listOf("Melon", "Melons"), listOf("Stone fruit", "stone"))),

        Question("What did the Wright Brothers invent in 19 02?",
                answer = listOf("Airplane", "aeroplane", "plane"),
                alternatives = listOf(listOf("car", "cars", "automobile"), listOf("motorbike", "motor", "bike"), listOf("Fighter jet", "jet", "fighter"))),

        Question("Who was the first man on the moon?",
                answer = listOf("Neil Armstrong", "Armstrong", "Neil"),
                alternatives = listOf(listOf("Buzz Aldrin", "Buzz", "Aldrin"), listOf("Michael Collins", "Michael", "Collins"), listOf("Yuri Gagarin", "Yuri", "Gagarin"))),

        Question("Which country has more inhabitants?",
                answer = listOf("Sweden", "Swedish"),
                alternatives = listOf(listOf("Switzerland", "Swiss"))),

        Question("Which volcano erupted in 1906 and devastated Naples?",
                answer = listOf("Mount Vesuvius", "Vesuvius"),
                alternatives = listOf(listOf("Etna"), listOf("Fuji"), listOf("Krakatoa"))),

        Question("Which famous tennis player is from Sweden?",
                answer = listOf("Bjorn Borg", "Borg", "Bjorn"),
                alternatives = listOf(listOf("Roger Federer", "Federer", "Roger"), listOf("Novak Djokovic", "Novak", "Djokovic"), listOf("Andy Murray", "Andy", "Murray"))),

        Question("Everbody knows what the Nobel Prize is, but what was Mr Nobel's first name?",
                answer = listOf("Alfred"),
                alternatives = listOf(listOf("Albert"), listOf("Isak"), listOf("Alexander")),
                funfact = "Did you know he named his invention dynamite after the Greek word for power? This sounds much better than the first name Nobel's Safety Powder"),

        Question("What is the national animal of Sweden?",
                answer = listOf("elk", "moose", "alces alces"),
                alternatives = listOf(listOf("reindeer"), listOf("arctic fox"), listOf("Brown Bear")),
                funfact = "They really look majestic, don't they?"),

        Question("One of Swedens most known Royals, Karl the 14th Johan, doesn't origin from Sweden. Previous to his new job, he was a general for which country?",
                answer = listOf("France", "French"),
                alternatives = listOf(listOf("Prussia", "Germany", "German"), listOf("Russia", "Russian"), listOf("Norway", "Norwegian")),
                funfact = "He was know as Jean Bernadotte and served in the French Army. Hopefully one day I'll get a promotion like that")


)

val questionsSwedenEnglish = mutableListOf(
        Question("One of Swedens most known Royals, Karl the 14th Johan, doesn't origin from Sweden. Previous to his new job, he was a general for which country?",
                answer = listOf("France", "French"),
                alternatives = listOf(listOf("Prussia", "Germany", "German"), listOf("Russia", "Russian"), listOf("Norway", "Norwegian")),
                funfact = "He was know as Jean Bernadotte and served in the French Army. Hopefully one day I'll get a promotion like that")
)

val questionsRobotEnglishReasonPhraseCatalog = mutableListOf(
        Question("One of Swedens most known Royals, Karl the 14th Johan, doesn't origin from Sweden. Previous to his new job, he was a general for which country?",
                answer = listOf("France", "French"),
                alternatives = listOf(listOf("Prussia", "Germany", "German"), listOf("Russia", "Russian"), listOf("Norway", "Norwegian")),
                funfact = "He was know as Jean Bernadotte and served in the French Army. Hopefully one day I'll get a promotion like that")
)

val questionsMusicEnglish = mutableListOf(
        Question("In what year did, ABBA,  win the Eurovision songfestival?",
                answer = listOf("19 74", "1974", "74"),
                alternatives = listOf(listOf("19 78", "1978", "78"), listOf("19 76", "1976", "76"), listOf("19 72", "1972", "72")),
                funfact = "They won it with their well known song Waterloo. Man, do I love that song")
)

val questionsScience = mutableListOf(
        Question("Where can you find the Sea of Tranquility?",
                answer = listOf("The moon", "moon"),
                alternatives = listOf(listOf("Turkey"), listOf("Germany"), listOf("The united states", "united states", "states")),
                funfact = "")
)
