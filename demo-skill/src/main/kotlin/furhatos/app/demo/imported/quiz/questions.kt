package furhatos.app.demo.imported.quiz

import furhatos.nlu.EnumItem
import furhatos.nlu.TextBuilder
import java.util.*

val questions = mutableListOf(
    Question("Which country won the 2016 Eurovision Song competition?", "Ukraine", listOf("Sweden","France","Finland"), "Female"),
    Question("How many Popes were there during the Papal schism?", "3", listOf("2","4","10"), "Female"),
    Question("When was the first circumnavigation of the world completed?", "15 22", listOf("14 99","16 32", "15 78"), "Female"),
    Question("Which language is Afrikaans derived from?", "Dutch", listOf("German","Spanish","English"), "Female"),
    Question("The Royal Mile is a famous street in which capital city?", "Edinburgh", listOf("Ottawa","London","Dublin"), "Female"),
    Question("How many wives did Henry VIII have?", "6", listOf("5","8","4"), "Female"),
    Question("During which decade did Elvis Presley die?", "70s", listOf("50s", "60s", "80s")),
    Question("As of 2016, which athlete had won the most Olympic medals?", "Michael Phelps", listOf("Usain Bolt", "Chris Hoy", "Simone Biles")),
    Question("Who is the author of the Game of Thrones books?", "George RR Martin", listOf("JK Rowling", "Suzanne Collins", "JRR Tolkien")),
    Question("Which nation won the most gold medals at the 2014 Olympics in Brazil?", "USA", listOf("Great Britain", "China", "Russia"))
)

object QuestionSet {

    var count : Int = 0;
    var current: Question = questions[Random().nextInt(questions.lastIndex)]

    init {
        Collections.shuffle(questions)
    }

    fun next() {
        count++;
        if (count >= questions.size)
            count = 0
        current = questions[count]
        AnswerOptionIntent().forget()
    }

}


class Question(val text: String, val answer: String, val alternatives: List<String>, val persona: String = "Furhat") {
    var options : MutableList<EnumItem> = mutableListOf()

    init {
        options.add(EnumItem(AnswerOptionIntent(true, answer), answer))
        alternatives.forEach {
            options.add(EnumItem(AnswerOptionIntent(false, it), it))
        }
        Collections.shuffle(options)
    }

    fun getOptionsString() : String {
        var text = TextBuilder()
        text.appendList(options.map { it.wordString }, "or")
        return text.toString()
    }

    val phrases : List<String>
        get() = options.map { it.wordString ?: "" }

}

