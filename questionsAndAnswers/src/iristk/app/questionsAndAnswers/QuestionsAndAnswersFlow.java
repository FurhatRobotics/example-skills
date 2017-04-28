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
					int count = getCount(492770444) + 1;
					incrCount(492770444);
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
				count = getCount(438148952) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(438148952);
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
					int count = getCount(1083460586) + 1;
					incrCount(1083460586);
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
				count = getCount(906357759) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(906357759);
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
				count = getCount(691554088) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(691554088);
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
				count = getCount(1594499026) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:question")) {
						incrCount(1594499026);
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
				count = getCount(343639609) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:questionstart")) {
						incrCount(343639609);
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
					int count = getCount(1098798122) + 1;
					incrCount(1098798122);
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
				count = getCount(2026208323) + 1;
				if (event.triggers("monitor.skill.query")) {
					incrCount(2026208323);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 110
						if (event.has("answer")) {
							// Line: 112
							StringCreator string39 = new StringCreator();
							string39.append("Answer:");
							// Line: 112
							string39.append(event.get("answer"));
							log(string39.toString());
							iristk.situated.SystemAgentFlow.say state40 = agent.new say();
							StringCreator string41 = new StringCreator();
							// Line: 112
							string41.append(event.get("answer"));
							state40.setText(string41.toString());
							if (!flowThread.callState(state40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 110, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state42 = agent.new say();
							StringCreator string43 = new StringCreator();
							string43.append("Anything else?");
							state42.setText(string43.toString());
							if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 110, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 116
						} else {
							iristk.situated.SystemAgentFlow.say state44 = agent.new say();
							StringCreator string45 = new StringCreator();
							string45.append("Sorry, I can't answer that. Please ask something else");
							state44.setText(string45.toString());
							if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 110, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 121
						flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 121, 19)));
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
			// Line: 129
			try {
				count = getCount(167215781) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(167215781);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state46 = agent.new gesture();
							state46.setName("smile");
							if (!flowThread.callState(state46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 129, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 129, 63));
			}
			// Line: 134
			try {
				count = getCount(1702316378) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"), iristk.speech.RecResult.NOMATCH)) {
						incrCount(1702316378);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state47 = agent.new say();
							StringCreator string48 = new StringCreator();
							// Line: 134
							boolean chosen49 = false;
							boolean matching50 = true;
							while (!chosen49 && matching50) {
								int rand51 = random(932821913, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching50 = false;
								if (true) {
									matching50 = true;
									if (rand51 >= 0 && rand51 < 1) {
										chosen49 = true;
										string48.append("Oh, you think so?");
									}
								}
								if (true) {
									matching50 = true;
									if (rand51 >= 1 && rand51 < 2) {
										chosen49 = true;
										string48.append("Okay");
									}
								}
								if (true) {
									matching50 = true;
									if (rand51 >= 2 && rand51 < 3) {
										chosen49 = true;
										string48.append("That doesn't sound like a question to me");
									}
								}
								if (true) {
									matching50 = true;
									if (rand51 >= 3 && rand51 < 4) {
										chosen49 = true;
										string48.append("Try to ask me a question");
									}
								}
							}
							state47.setText(string48.toString());
							if (!flowThread.callState(state47, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 134, 92)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state52 = agent.new listen();
							if (!flowThread.callState(state52, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 134, 92)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 134, 92));
			}
			// Line: 148
			try {
				count = getCount(1606719658) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1606719658);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state53 = agent.new say();
						StringCreator string54 = new StringCreator();
						string54.append("Sorry, I didn't get that.");
						state53.setText(string54.toString());
						if (!flowThread.callState(state53, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 148, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state55 = agent.new listen();
						if (!flowThread.callState(state55, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 148, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 148, 36));
			}
			// Line: 154
			try {
				count = getCount(742791577) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(742791577);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 155
						Event sendEvent56 = new Event("sense.user.speak");
						sendEvent56.copyParams(event);
						flowRunner.sendEvent(sendEvent56, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 155, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 154, 41));
			}
			// Line: 159
			try {
				count = getCount(1182004207) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(1182004207);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 160
						Event sendEvent57 = new Event("sense.user.speak");
						sendEvent57.copyParams(event);
						flowRunner.sendEvent(sendEvent57, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 160, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 159, 42));
			}
			// Line: 164
			try {
				count = getCount(564096682) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(564096682);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.listen state58 = agent.new listen();
						if (!flowThread.callState(state58, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 164, 38)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 164, 38));
			}
			// Line: 169
			try {
				count = getCount(842919715) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(842919715);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 170
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state59 = agent.new attendRandom();
								if (!flowThread.callState(state59, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 170, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 172
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 172, 16)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 173
							} else {
								// Line: 174
								Idle state60 = new Idle();
								flowThread.gotoState(state60, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 174, 26)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\questionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 169, 69));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
