package iristk.app.questionsAndAnswers

import furhatos.flow.FlowVariable
import furhatos.flow.dialog.DialogState
import furhatos.flow.dialog.Response
import furhatos.flow.dialog.Say

class QuestionsAndAnswersFlow extends DialogState {




	public QuestionsAndAnswersFlow() {
		this.agent = agent;
	}

	public QuestionsAndAnswersFlow() {
	}

}

class Idle extends State {


	void onEntry() {
		if (system.hasUsers()) {
attendRandom 

			trans QnA
		} else {
attendNobody 

		}
	}

	void onEvent("sense.user.enter") {
attend Target:event.get("user")

		trans QnA
	}

}


class QnA extends Dialog {


	void onEntry() {
say "Hi, do you have any question for me?"
listen 

	}

	void onResponse(Response reply) {
		if (reply.contains(yes)) {
say "What is it?"
listen 

		}
		else if (reply.contains(no)) {
say "Okay, bye"
		}
		else if (reply.contains(question)) {
			String question = asString(event.get("sem:question"))
			if (question.equals("whatsYourName")) {
say "My name is Fur hat"
			} else if (question.equals("whatsUp")) {
say "Nothing, just chilling here"
gesture Name:"smile"

			} else if (question.equals("howAreYou")) {
say "Good, thanks!"
gesture Name:"smile"

			} else if (question.equals("howOldAreYou")) {
say "I'm two years old"
			} else if (question.equals("whoMadeYou")) {
say "Fur hat Robotics made me"
			} else if (question.equals("whatCanYouDo")) {
say "I can answer questions"
			} else if (question.equals("whatAreYou")) {
say "I am a social robot"
			} else {
say "Sorry, I can't answer that."
			}
listen 

		}
		else if (reply.contains(questionstart)) {
say "Lets see", async:true

			Builder builder = new CustomEvent.Builder("action.gesture")
			builder.setField("name", "gaze_away")
			send builder.buildEvent()
call new PendingQueryAnswer(event			)
listen 

		}
		else {
			super.onResponse(reply)
		}
	}

}


class PendingQueryAnswer extends State {

	iristk.system.Event queryEvent = null

PendingQueryAnswer(iristk.system.Event queryEvent	) {
		this.queryEvent = queryEvent
	}

	void onEntry() {
		Builder builder = new CustomEvent.Builder("action.skill.query")
		builder.setField("text", queryEvent.get("text"))
		send builder.buildEvent()
print "Question: " + queryEvent.get("text")
	}

	void onEvent("monitor.skill.query") {
		if (event.has("answer")) {
print "Answer: " + event.get("answer")
say 			event.get("answer")

say "Anything else?"
		} else if (eq(event.get("status"), "MATCH")) {
call new QueryParser(asList(event.get("skills"))			)
		} else {
say "Sorry, I can't answer that. Please ask something else"
		}
		terminate()
	}

}


class QueryParser extends Dialog {

	List skills = null

QueryParser(List skills	) {
		this.skills = skills
	}
	public Record skill;
	public String message;

	void onEntry() {
		skill = asRecord(skills.remove(0));
		message = asString(skill.get("answer"));
print skill.get("name")
		if (eq(skill.get("name"), "Houndify")) {
say 			message

			terminate()
		} else {
			if (skills.isEmpty()) {
				terminate()
			} else {
trans new QueryParser(skills				)
			}
		}
	}


}


class Dialog extends State {


	void onEntry() {

		void onResponse(Response reply) {
			if (!eq(event.get("text"), iristk.speech.RecResult.NOMATCH)) {
say 				// You had an RANDOM here, it is not supported by the migrator. Please implement your own random.
				// START OF RANDOM
				// You had an BLOCK here, it is not supported by the migrator. Please implement your own IF/ELSE/SWITCH.
				// START OF BLOCK
"Oh, you think so?"				// END OF BLOCK
				// You had an BLOCK here, it is not supported by the migrator. Please implement your own IF/ELSE/SWITCH.
				// START OF BLOCK
"Okay"				// END OF BLOCK
				// You had an BLOCK here, it is not supported by the migrator. Please implement your own IF/ELSE/SWITCH.
				// START OF BLOCK
"That doesn't sound like a question to me"				// END OF BLOCK
				// You had an BLOCK here, it is not supported by the migrator. Please implement your own IF/ELSE/SWITCH.
				// START OF BLOCK
"Try to ask me a question"				// END OF BLOCK
				// END OF RANDOM

listen 

			}
			else if (reply.speech.type == SenseSpeechRec.Type.FINAL || reply.speech.type == SenseSpeechRec.Type.MAXSPEECH) {
say "Sorry, I didn't get that."
listen 

			}
			else if (reply.speech.type == SenseSpeechRec.Type.FINAL || reply.speech.type == SenseSpeechRec.Type.MAXSPEECH) {
				Builder builder = new CustomEvent.Builder("sense.user.speak")
				send builder.buildEvent()
			}
			else if (reply.speech.type == SenseSpeechRec.Type.FINAL || reply.speech.type == SenseSpeechRec.Type.MAXSPEECH) {
				Builder builder = new CustomEvent.Builder("sense.user.speak")
				send builder.buildEvent()
			}
			else {
listen 

			}
		}
		void onEvent("sense.user.speech.start") {
			if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
gesture Name:"smile"

			}
		}
		void onEvent("sense.user.leave") {
			if (system.isAttending(event)) {
				if (system.hasUsers()) {
attendRandom 

					reentry
				} else {
					trans Idle
				}
			}
		}

	}


