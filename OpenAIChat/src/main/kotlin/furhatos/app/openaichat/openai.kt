package furhatos.app.openaichat

import com.theokanning.openai.OpenAiService
import com.theokanning.openai.completion.CompletionRequest
import furhatos.flow.kotlin.DialogHistory
import furhatos.flow.kotlin.Furhat

/** API Key to GPT3 language model. Get access to the API and genereate your key from: https://openai.com/api/ **/
val serviceKey = ""

class OpenAIChatbot(val description: String, val userName: String, val agentName: String) {

    val service = OpenAiService(serviceKey)

    var temperature = 0.9
    var maxTokens = 50
    var topP = 1.0
    var frequencyPenalty = 0.0
    var presencePenalty = 0.6

    fun getNextResponse(): String {
        /** The prompt for the chatbot includes a context of ten "lines" of dialogue. **/
        val history = Furhat.dialogHistory.all.takeLast(10).mapNotNull {
            when (it) {
                is DialogHistory.ResponseItem -> {
                    "$userName: ${it.response.text}"
                }
                is DialogHistory.UtteranceItem -> {
                    "$agentName: ${it.toText()}"
                }
                else -> null
            }
        }.joinToString(separator = "\n")
        val prompt = "$description\n\n$history\n$agentName:"
        println("-----")
        println(prompt)
        println("-----")
        val completionRequest = CompletionRequest.builder()
            .temperature(temperature)
            .maxTokens(maxTokens)
            .topP(topP)
            .frequencyPenalty(frequencyPenalty)
            .presencePenalty(presencePenalty)
            .prompt(prompt)
            .stop(listOf("\n", " $userName:", " $agentName:"))
            .echo(true)
            .build();

        try {
            val completion = service.createCompletion("davinci", completionRequest).getChoices().first().text
            val response = completion.drop(prompt.length).trim()
            return response
        } catch (e: Exception) {
            println("Problem with connection to OpenAI")
        }
        return "I am not sure what to say"

    }

}
