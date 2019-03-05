package furhatos.app.quiz

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer, followed by alternative pronounciations
 *  -A list of other answers followed by their alternatives
 */
val questionsEnglish = mutableListOf(
        Question("How many wives did Henry VIII have?",
                listOf("6"),
                listOf(listOf("5"),listOf("8"),listOf("4"))),

        Question("Which country won the 2016 Eurovision Song competition?",
                listOf("Ukraine"),
                listOf(listOf("Sweden"),listOf("France"),listOf("Finland"))),

        Question("When was the first circumnavigation of the world completed?",
                listOf("15 22", "1522"),
                listOf(listOf("14 99", "1499"),listOf("16 32", "1632"),listOf("15 78", "1578"))),

        Question("Which language is Afrikaans derived from?",
                listOf("Dutch", "touch"),
                listOf(listOf("German"),listOf("Spanish"),listOf("English"))),

        Question("The Strip is a famous street in which American city?",
                listOf("Las Vegas", "vegas"),
                listOf(listOf("Washington DC", "washington"),listOf("Los Angeles", "hollywood", "angeles", "la"),listOf("New York", "York", "New"))),

        Question("During which decade did Elvis Presley die?",
                listOf("70s", "seventies", "70"),
                listOf(listOf("50s", "fifties", "50"), listOf("60s", "sixties", "60"), listOf("80s", "eighties", "hades", "80"))),

        Question("As of 2016, which athlete had won the most Olympic medals?",
                listOf("Michael Phelps", "Phelps", "Michael"),
                listOf(listOf("Usain Bolt", "Bolt", "Usain"))),

        Question("Who is the author of the Game of Thrones books?",
                listOf("George RR Martin", "George Martin", "George", "martin"),
                listOf(listOf("JK Rowling", "Rowling", "Rolling"), listOf("Suzanne Collins", "Collins", "Suzanne"), listOf("JRR Tolkien", "Tolkien", "Talking"))),

        Question("Which nation won the most gold medals at the 2014 Olympics in Brazil?",
                listOf("USA", "America"),
                listOf(listOf("Great Britain", "United Kingdom", "Britain"), listOf("China"), listOf("Russia"))),

        Question("What is the largest freshwater lake in the world?",
                listOf("Lake Superior", "Superior"),
                listOf(listOf("Lake Victoria", "Victoria"), listOf("Lake Michigan", "Michigan"), listOf("Great Bear Lake", "Great Bear"), listOf("Lake Ontario", "Ontario"))),

        Question("Where can you find the Sea of Tranquility?",
                listOf("The moon", "moon"),
                listOf(listOf("Turkey"), listOf("Germany"), listOf("The united states", "united states", "states"))),

        Question("What is the seventh planet from the Sun?",
                listOf("Uranus", "anus"),
                listOf(listOf("Pluto"), listOf("Neptune", "tune"), listOf("Saturn"))),

        Question("In what year did, ABBA,  win the Eurovision songfestival?",
                listOf("19 74", "1974", "74"),
                listOf(listOf("19 78", "1978", "78"), listOf("19 76", "1976", "76"), listOf("19 72",  "1972", "72"))),

        Question("What is the title of the famous novel by George Orwell?",
                listOf("19 84", "1984"),
                listOf(listOf("The lord of the rings", "lord of the rings", "the rings"),listOf("The Great Gatsby", "great gatsby", "the great", "gatsby"),listOf("Of mice and men", "mice and men"))),

        Question("Chardonnay. Malaga. and Merlot. are all types of which fruit?",
                listOf("Grape", "grapes"),
                listOf(listOf("Berry", "berries"),listOf("Melon", "Melons"),listOf("Stone fruit", "stone"))),

        Question("What did the Wright Brothers invent in 19 02?",
                listOf("Airplane", "aeroplane", "plane"),
                listOf(listOf("car", "cars", "automobile"),listOf("motorbike", "motor", "bike"),listOf("Fighter jet", "jet", "fighter"))),

        Question("Who was the first man on the moon?",
                listOf("Neil Armstrong", "Armstrong", "Neil"),
                listOf(listOf("Buzz Aldrin", "Buzz", "Aldrin"),listOf("Michael Collins", "Michael", "Collins"),listOf("Yuri Gagarin", "Yuri", "Gagarin"))),

        Question("Which country has more inhabitants?",
                listOf("Sweden"),
                listOf(listOf("Switserland"))),

        Question("Which volcano erupted in 1906 and devastated Naples?",
                listOf("Mount Vesuvius", "Vesuvius"),
                listOf(listOf("Etna"),listOf("Fuji"),listOf("Krakatoa"))),

        Question("Which famous tennis player is from Sweden?",
                listOf("Bjorn Borg", "Borg", "Bjorn"),
                listOf(listOf("Roger Federer", "Federer", "Roger"),listOf("Novak Djokovic", "Novak", "Djokovic"),listOf("Andy Murray", "Andy", "Murray")))
)