package iristk.app.questionsAndAnswers;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;

public class QuestionsAndAnswersFlow extends iristk.flow.Flow {

	private iristk.situated.SystemAgentFlow agent;
	private iristk.situated.SystemAgent system;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("agent")) return this.agent;
		return null;
	}


	public QuestionsAndAnswersFlow(iristk.situated.SystemAgentFlow agent) {
		this.agent = agent;
		initVariables();
	}

	@Override
	public State getInitialState() {return new Idle();}


	public class Idle extends State implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 11
			try {
				EXECUTION: {
					int count = getCount(414641417) + 1;
					incrCount(414641417);
					// Line: 12
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state0 = agent.new attendRandom();
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 12, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 14
						QnA state1 = new QnA();
						flowThread.gotoState(state1, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 14, 27)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 15
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state2 = agent.new attendNobody();
						if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 12, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 11, 13));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 21
			try {
				count = getCount(1452763915) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1452763915);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state3 = agent.new attend();
						state3.setTarget(event.get("user"));
						if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 21, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 23
						QnA state4 = new QnA();
						flowThread.gotoState(state4, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 23, 24)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 21, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class QnA extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 29
			try {
				EXECUTION: {
					int count = getCount(1292556171) + 1;
					incrCount(1292556171);
					iristk.situated.SystemAgentFlow.say state5 = agent.new say();
					StringCreator string6 = new StringCreator();
					string6.append("Hi, do you have any question for me?");
					state5.setText(string6.toString());
					if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.listen state7 = agent.new listen();
					if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 29, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 34
			try {
				count = getCount(547889682) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(547889682);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state8 = agent.new say();
							StringCreator string9 = new StringCreator();
							string9.append("What is it?");
							state8.setText(string9.toString());
							if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 34, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state10 = agent.new listen();
							if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 34, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 34, 58));
			}
			// Line: 41
			try {
				count = getCount(1598531794) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(1598531794);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state11 = agent.new say();
							StringCreator string12 = new StringCreator();
							string12.append("Okay, bye");
							state11.setText(string12.toString());
							if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 41, 57)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 41, 57));
			}
			// Line: 47
			try {
				count = getCount(2033711381) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:question")) {
						incrCount(2033711381);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 49
							String question = asString(asString(event.get("sem:question")));
							// Line: 52
							if (question.equals("whatsYourName")) {
								iristk.situated.SystemAgentFlow.say state13 = agent.new say();
								StringCreator string14 = new StringCreator();
								string14.append("My name is Fur hat");
								state13.setText(string14.toString());
								if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 55
							} else if (question.equals("whatsUp")) {
								iristk.situated.SystemAgentFlow.say state15 = agent.new say();
								StringCreator string16 = new StringCreator();
								string16.append("Nothing, just chilling here");
								state15.setText(string16.toString());
								if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								iristk.situated.SystemAgentFlow.gesture state17 = agent.new gesture();
								state17.setName("smile");
								if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 59
							} else if (question.equals("howAreYou")) {
								iristk.situated.SystemAgentFlow.say state18 = agent.new say();
								StringCreator string19 = new StringCreator();
								string19.append("Good, thanks!");
								state18.setText(string19.toString());
								if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								iristk.situated.SystemAgentFlow.gesture state20 = agent.new gesture();
								state20.setName("smile");
								if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 63
							} else if (question.equals("howOldAreYou")) {
								iristk.situated.SystemAgentFlow.say state21 = agent.new say();
								StringCreator string22 = new StringCreator();
								string22.append("I'm two years old");
								state21.setText(string22.toString());
								if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 66
							} else if (question.equals("whoMadeYou")) {
								iristk.situated.SystemAgentFlow.say state23 = agent.new say();
								StringCreator string24 = new StringCreator();
								string24.append("Fur hat Robotics made me");
								state23.setText(string24.toString());
								if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 69
							} else if (question.equals("whatCanYouDo")) {
								iristk.situated.SystemAgentFlow.say state25 = agent.new say();
								StringCreator string26 = new StringCreator();
								string26.append("I can answer questions");
								state25.setText(string26.toString());
								if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 72
							} else if (question.equals("whatAreYou")) {
								iristk.situated.SystemAgentFlow.say state27 = agent.new say();
								StringCreator string28 = new StringCreator();
								string28.append("I am a social robot");
								state27.setText(string28.toString());
								if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 75
							} else {
								iristk.situated.SystemAgentFlow.say state29 = agent.new say();
								StringCreator string30 = new StringCreator();
								string30.append("Sorry, I can't answer that.");
								state29.setText(string30.toString());
								if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 52, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
							iristk.situated.SystemAgentFlow.listen state31 = agent.new listen();
							if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 47, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 47, 63));
			}
			// Line: 84
			try {
				count = getCount(1499122613) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:questionstart")) {
						incrCount(1499122613);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state32 = agent.new say();
							StringCreator string33 = new StringCreator();
							string33.append("Lets see");
							state32.setAsync(true);
							state32.setText(string33.toString());
							if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 84, 71)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 88
							Event sendEvent34 = new Event("action.gesture");
							sendEvent34.putIfNotNull("name", "gaze_away");
							flowRunner.sendEvent(sendEvent34, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 88, 65)));
							// Line: 90
							PendingQueryAnswer state35 = new PendingQueryAnswer();
							state35.setQueryEvent(event);
							if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 90, 68)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state36 = agent.new listen();
							if (!flowThread.callState(state36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 84, 71)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 84, 71));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class PendingQueryAnswer extends State {

		final State currentState = this;
		public iristk.system.Event queryEvent = null;

		public void setQueryEvent(Object value) {
			if (value != null) {
				queryEvent = (iristk.system.Event) value;
				params.put("queryEvent", value);
			}
		}


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 100
			try {
				EXECUTION: {
					int count = getCount(55940606) + 1;
					incrCount(55940606);
					// Line: 102
					Event sendEvent37 = new Event("action.skill.query");
					sendEvent37.putIfNotNull("text", queryEvent.get("text"));
					flowRunner.sendEvent(sendEvent37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 102, 69)));
					// Line: 104
					StringCreator string38 = new StringCreator();
					string38.append("Question:");
					// Line: 104
					string38.append(queryEvent.get("text"));
					log(string38.toString());
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 100, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 108
			try {
				count = getCount(597730606) + 1;
				if (event.triggers("monitor.skill.query")) {
					incrCount(597730606);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 111
						if (event.has("answer")) {
							// Line: 113
							StringCreator string39 = new StringCreator();
							string39.append("Answer:");
							// Line: 113
							string39.append(event.get("answer"));
							log(string39.toString());
							iristk.situated.SystemAgentFlow.say state40 = agent.new say();
							StringCreator string41 = new StringCreator();
							// Line: 113
							string41.append(event.get("answer"));
							state40.setText(string41.toString());
							if (!flowThread.callState(state40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 111, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state42 = agent.new say();
							StringCreator string43 = new StringCreator();
							string43.append("Anything else?");
							state42.setText(string43.toString());
							if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 111, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 117
						} else if (eq(event.get("status"), "MATCH")) {
							// Line: 118
							QueryParser state44 = new QueryParser();
							state44.setSkills(asList(event.get("skills")));
							if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 118, 83)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 119
						} else {
							iristk.situated.SystemAgentFlow.say state45 = agent.new say();
							StringCreator string46 = new StringCreator();
							string46.append("Sorry, I can't answer that. Please ask something else");
							state45.setText(string46.toString());
							if (!flowThread.callState(state45, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 111, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 124
						flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 124, 19)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 108, 39));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class QueryParser extends Dialog {

		final State currentState = this;
		public List skills = null;

		public void setSkills(Object value) {
			if (value != null) {
				skills = asList(value);
				params.put("skills", value);
			}
		}

		public Record skill;
		public String message;

		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 133
			try {
				EXECUTION: {
					int count = getCount(1433361711) + 1;
					incrCount(1433361711);
					// Line: 134
					skill = asRecord(skills.remove(0));
					message = asString(skill.get("answer"));
					// Line: 138
					StringCreator string47 = new StringCreator();
					// Line: 138
					string47.append(skill.get("name"));
					log(string47.toString());
					// Line: 139
					if (eq(skill.get("name"), "Houndify")) {
						iristk.situated.SystemAgentFlow.say state48 = agent.new say();
						StringCreator string49 = new StringCreator();
						// Line: 139
						string49.append(message);
						state48.setText(string49.toString());
						if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 139, 51)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 141
						flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 141, 26)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 142
					} else {
						// Line: 143
						if (skills.isEmpty()) {
							// Line: 144
							flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 144, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 145
						} else {
							// Line: 146
							QueryParser state50 = new QueryParser();
							state50.setSkills(skills);
							flowThread.gotoState(state50, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 146, 66)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 133, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Dialog extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 156
			try {
				count = getCount(1831924809) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1831924809);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state51 = agent.new gesture();
							state51.setName("smile");
							if (!flowThread.callState(state51, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 156, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 156, 63));
			}
			// Line: 161
			try {
				count = getCount(1033739133) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"), iristk.speech.RecResult.NOMATCH)) {
						incrCount(1033739133);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state52 = agent.new say();
							StringCreator string53 = new StringCreator();
							// Line: 161
							boolean chosen54 = false;
							boolean matching55 = true;
							while (!chosen54 && matching55) {
								int rand56 = random(1528177151, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching55 = false;
								if (true) {
									matching55 = true;
									if (rand56 >= 0 && rand56 < 1) {
										chosen54 = true;
										string53.append("Oh, you think so?");
									}
								}
								if (true) {
									matching55 = true;
									if (rand56 >= 1 && rand56 < 2) {
										chosen54 = true;
										string53.append("Okay");
									}
								}
								if (true) {
									matching55 = true;
									if (rand56 >= 2 && rand56 < 3) {
										chosen54 = true;
										string53.append("That doesn't sound like a question to me");
									}
								}
								if (true) {
									matching55 = true;
									if (rand56 >= 3 && rand56 < 4) {
										chosen54 = true;
										string53.append("Try to ask me a question");
									}
								}
							}
							state52.setText(string53.toString());
							if (!flowThread.callState(state52, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 161, 92)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state57 = agent.new listen();
							if (!flowThread.callState(state57, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 161, 92)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 161, 92));
			}
			// Line: 175
			try {
				count = getCount(1885113518) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1885113518);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state58 = agent.new say();
						StringCreator string59 = new StringCreator();
						string59.append("Sorry, I didn't get that.");
						state58.setText(string59.toString());
						if (!flowThread.callState(state58, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 175, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state60 = agent.new listen();
						if (!flowThread.callState(state60, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 175, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 175, 36));
			}
			// Line: 181
			try {
				count = getCount(1947381066) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(1947381066);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 182
						Event sendEvent61 = new Event("sense.user.speak");
						sendEvent61.copyParams(event);
						flowRunner.sendEvent(sendEvent61, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 182, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 181, 41));
			}
			// Line: 186
			try {
				count = getCount(1333636094) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(1333636094);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 187
						Event sendEvent62 = new Event("sense.user.speak");
						sendEvent62.copyParams(event);
						flowRunner.sendEvent(sendEvent62, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 187, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 186, 42));
			}
			// Line: 191
			try {
				count = getCount(986311070) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(986311070);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.listen state63 = agent.new listen();
						if (!flowThread.callState(state63, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 191, 38)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 191, 38));
			}
			// Line: 196
			try {
				count = getCount(1879083167) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(1879083167);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 197
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state64 = agent.new attendRandom();
								if (!flowThread.callState(state64, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 197, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 199
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 199, 16)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 200
							} else {
								// Line: 201
								Idle state65 = new Idle();
								flowThread.gotoState(state65, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 201, 26)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 196, 69));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
