package furhatos.app.quiz.questions

import furhatos.app.quiz.AnswerOption
import furhatos.nlu.EnumItem
import furhatos.nlu.TextBuilder

import kotlinx.coroutines.currentCoroutineContext
import java.util.*

object QuestionSet {
    var currenttopic = mutableListOf<Question>()
    lateinit var current : Question
    var topicname : String = ""

    init {

        shuffle()
    }

    fun next() {
        currenttopic.removeAt(0)
        AnswerOption().forget()
        current = currenttopic.first()

    }

    fun shuffle(){
        questionsRandomEnglish.shuffle()
        questionsScienceEnglish.shuffle()
        questionsMusicEnglish.shuffle()
        questionsSwedenEnglish.shuffle()
        questionsRobotEnglish.shuffle()
    }

}

/**
 * The question class gets the following parameters:
 * @text : The question as a String
 * @answer : A list containing the correct answer to the question, followed by alternative pronunciations of the correct answer
 * @alternatives : A list, containing lists of other (wrong) answers. Every other answer is also followed by alternative pronunciations of the correct answer.
 * @funfact : A list, containing a funny thing to know or a funny answer that Furhat tells, if the user got the right answer
 */
class Question(val text: String, answer: List<String>, alternatives: List<List<String>>,val funfact: String = "") {
    //All options, used to prime the NLU
    var options : MutableList<EnumItem> = mutableListOf()
    //Only the first option of the answers, these are correctly spelled, and not alternative.
    var primeoptions : MutableList<EnumItem> = mutableListOf()
    var rightanswer : String = answer.first()


    //init loads the first item of the list into primeoptions
    //And loads everything into options
    init {
        primeoptions.add(EnumItem(AnswerOption(true, answer.first()), answer.first()))
        answer.forEach {
            options.add(EnumItem(AnswerOption(true, it), it))
        }

        alternatives.forEach {
            primeoptions.add(EnumItem(AnswerOption(false, it.first()), it.first()))
            it.forEach {
                options.add(EnumItem(AnswerOption(false, it), it))
            }
        }

        options.shuffle()
        primeoptions.shuffle()
    }

    //Returns the well formatted answer options
    fun getOptionsString() : String {
        var text = TextBuilder()
        text.appendList(primeoptions.map { it.wordString }, "or")
        return text.toString()
    }

    //Returns the well formatted answer options
    val speechPhrases : List<String>
        get() = primeoptions.map { it.wordString ?: "" }

}

