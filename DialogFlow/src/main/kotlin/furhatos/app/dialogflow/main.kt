package furhatos.app.dialogflow

import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.QueryInput
import com.google.cloud.dialogflow.v2.SessionsClient
import com.google.cloud.dialogflow.v2.SessionsSettings
import com.google.cloud.dialogflow.v2.TextInput
import furhatos.app.dialogflow.flow.Idle
import furhatos.skills.Skill
import furhatos.flow.kotlin.*
import furhatos.skills.SingleUserEngagementPolicy
import furhatos.skills.UserManager
import java.io.File
import java.io.FileInputStream

val dialogFlowAgent = DialogFlowAgent("PROJECT_ID", "PATH_TO_CREDENTIALS_FILE")

class DialogFlowSkill : Skill() {
    override fun start() {
        UserManager.engagementPolicy = SingleUserEngagementPolicy()
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
