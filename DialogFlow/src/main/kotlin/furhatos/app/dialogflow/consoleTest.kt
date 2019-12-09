package furhatos.app.dialogflow

fun main() {
    val session = dialogFlowAgent.createSession()
    var resp = session.queryWelcomeEvent()
    println(resp.fulfillmentText)
    while (!resp.endConversation) {
        val text = readLine()
        if (text == null || text.isBlank()) {
            break
        }
        resp = session.queryText(text)
        println(resp.fulfillmentText)
    }
    println("---\nInteraction ended")
}