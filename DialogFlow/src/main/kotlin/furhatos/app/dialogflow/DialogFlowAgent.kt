package furhatos.app.dialogflow

import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.*
import com.google.protobuf.Value
import furhatos.util.Language
import java.io.File
import java.io.FileInputStream
import java.util.*

class DialogFlowAgent(val projectId: String, val credentialsFile: String) {

    val sessionsClient: SessionsClient

    init {
        val credentialsPath = File(credentialsFile)
        val credentials = FileInputStream(credentialsPath).use { serviceAccountStream ->
            ServiceAccountCredentials.fromStream(serviceAccountStream)
        }
        val sessionsSettings = SessionsSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build()
        sessionsClient = SessionsClient.create(sessionsSettings)
    }

    fun createSession(language: Language = Language.ENGLISH_US): Session {
        return Session(language)
    }

    inner class Session(val language: Language) {

        val session = SessionName.of(projectId, UUID.randomUUID().toString());

        fun queryWelcomeEvent(): QueryResult {
            return queryEvent("WELCOME")
        }

        fun queryEvent(eventName: String): QueryResult {
            return queryInput(QueryInput.newBuilder().setEvent(EventInput.newBuilder().setName(eventName).setLanguageCode(language.code)).build())
        }

        fun queryText(text: String): QueryResult {
            return queryInput(QueryInput.newBuilder().setText(TextInput.newBuilder().setText(text).setLanguageCode(language.code)).build())
        }

        fun queryInput(input: QueryInput): QueryResult {
            return sessionsClient.detectIntent(session, input).queryResult
        }

    }

}

val QueryResult.endConversation
    get() = diagnosticInfo.getFieldsOrDefault("end_conversation", Value.getDefaultInstance()).boolValue

fun QueryResult.getPayload(name: String) =
    fulfillmentMessagesList.map { it.payload.getFieldsOrDefault(name, Value.getDefaultInstance()).stringValue }.find { it.isNotEmpty() }