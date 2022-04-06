package furhatos.app.quiz.setting

import furhatos.app.quiz.questions.Question
import kotlinx.coroutines.runBlocking
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.json.JSONObject
import java.io.File
import java.io.InputStreamReader
import java.net.URL

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.cio.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*
import java.io.*
import java.net.*

var googleSheetsIftttUrl = "https://maker.ifttt.com/trigger/furLogRow/with/key/gLV85oSCtirKda3D1KuSDLQ3V9NLQM_DeotSUokHUeH"


fun getQuestions(sheetLink : String, topic : String, Questionlist : MutableList<Question>) {
    val csvParser = run {
        val formUrl =
            "https://docs.google.com/spreadsheets/d/$sheetLink/gviz/tq?tqx=out:csv&sheet=questions$topic"
        CSVParser(InputStreamReader(URL(formUrl).openStream()), CSVFormat.EXCEL.withFirstRecordAsHeader())
    }
    for (line in csvParser){
        var templist = mutableListOf<List<String>>()
        templist.add(line[2].split(";"))
        if (!line[3].isNullOrEmpty()){templist.add(line[3].split(";"))}
        Questionlist.add(Question(text = line.get(0), answer = line[1].split(";"), alternatives = templist, funfact= if (line.last().isNullOrEmpty()) "" else line.last()))

    }
}


val client = HttpClient(Apache) {
    followRedirects = true
}

data class HttpClientException(val response: HttpResponse) : IOException("HTTP Error ${response.status}")

suspend fun HttpClient.getAsTempFile(url: String, callback: suspend (file: File) -> Unit) {
    val file = getAsTempFile(url)
    try {
        callback(file)
    } finally {
        file.delete()
    }
}

suspend fun HttpClient.getAsTempFile(url: String): File {
    val file = File.createTempFile("ktor", "http-client")
    val response = request<HttpResponse> {
        url(URL(url))
        method = HttpMethod.Get
    }
    if (!response.status.isSuccess()) {
        throw HttpClientException(response)
    }
    response.content.copyAndClose(file.writeChannel())
    return file
}


fun googleSheetsLog(url: String, value1: String, value2: String = "", value3: String = "") {
    val rootObject= JSONObject()
    rootObject.put("value1",value1)
    if (value2.isNotEmpty()) rootObject.put("value2",value2)
    if (value3.isNotEmpty()) rootObject.put("value3",value3)
    println(rootObject.toString())
    println(rootObject)

    runBlocking {

        val file = File.createTempFile("ktor", "http-client")
        try {
            val response = client.request<HttpResponse> {
                url(URL(url))
                contentType(ContentType.Application.Json)
                body = rootObject.toString()
                method = HttpMethod.Post
            }
            if (!response.status.isSuccess()) {
                throw HttpClientException(response)
            } else {
                println(response.content.toString())
            }
            response.content.copyAndClose(file.writeChannel())
        } catch (e: Exception) {
            println(e.stackTrace)
        }
    }
}