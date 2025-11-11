package furhatos.app.openaichat.flow.chatbot

import furhatos.flow.kotlin.DialogHistory
import furhatos.flow.kotlin.Furhat
import io.github.sashirestela.openai.SimpleOpenAI
import io.github.sashirestela.openai.domain.chat.ChatMessage
import io.github.sashirestela.openai.domain.chat.ChatRequest

/** Open AI API Key **/
val serviceKey = ""

val openAI = SimpleOpenAI.builder()
    .apiKey(serviceKey)
    .build();

class OpenAIChatbot(val systemPrompt: String) {

    fun getResponse(): String {
        val chatRequestBuilder = ChatRequest.builder()
            .model("gpt-4o-mini")
            .message(ChatMessage.SystemMessage.of(systemPrompt))

        Furhat.dialogHistory.all.takeLast(10).forEach {
            when (it) {
                is DialogHistory.ResponseItem -> {
                    chatRequestBuilder.message(ChatMessage.UserMessage.of(it.response.text))
                }
                is DialogHistory.UtteranceItem -> {
                    chatRequestBuilder.message(ChatMessage.AssistantMessage.of(it.toText()))
                }
            }
        }

        var futureChat = openAI.chatCompletions().create(chatRequestBuilder.build())
        var chatResponse = futureChat.join()
        return chatResponse.firstContent().toString()
    }

}
