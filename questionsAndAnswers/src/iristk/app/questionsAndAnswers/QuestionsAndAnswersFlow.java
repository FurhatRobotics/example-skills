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
					int count = getCount(1174290147) + 1;
					incrCount(1174290147);
					// Line: 12
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state0 = agent.new attendRandom();
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 12, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 14
						QnA state1 = new QnA();
						flowThread.gotoState(state1, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 14, 27)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 15
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state2 = agent.new attendNobody();
						if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 12, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 11, 13));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 21
			try {
				count = getCount(1588970020) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1588970020);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state3 = agent.new attend();
						state3.setTarget(event.get("user"));
						if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 21, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 23
						QnA state4 = new QnA();
						flowThread.gotoState(state4, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 23, 24)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 21, 36));
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
					int count = getCount(245565335) + 1;
					incrCount(245565335);
					iristk.situated.SystemAgentFlow.say state5 = agent.new say();
					StringCreator string6 = new StringCreator();
					string6.append("Hi, do you have any question for me?");
					state5.setText(string6.toString());
					if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.listen state7 = agent.new listen();
					if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 29, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 34
			try {
				count = getCount(2121744517) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(2121744517);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state8 = agent.new say();
							StringCreator string9 = new StringCreator();
							string9.append("What is it?");
							state8.setText(string9.toString());
							if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 34, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state10 = agent.new listen();
							if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 34, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 34, 58));
			}
			// Line: 41
			try {
				count = getCount(1066376662) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(1066376662);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state11 = agent.new say();
							StringCreator string12 = new StringCreator();
							string12.append("Okay, bye");
							state11.setText(string12.toString());
							if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 41, 57)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 41, 57));
			}
			// Line: 47
			try {
				count = getCount(183264084) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:answer")) {
						incrCount(183264084);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state13 = agent.new say();
							StringCreator string14 = new StringCreator();
							// Line: 47
							string14.append(event.get("sem:answer"));
							state13.setText(string14.toString());
							if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 47, 61)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state15 = agent.new say();
							StringCreator string16 = new StringCreator();
							string16.append("Anything else?");
							state15.setText(string16.toString());
							if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 47, 61)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state17 = agent.new listen();
							if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 47, 61)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 47, 61));
			}
			// Line: 56
			try {
				count = getCount(476402209) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:questionstart")) {
						incrCount(476402209);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 58
							Event sendEvent18 = new Event("action.skill.query");
							sendEvent18.putIfNotNull("text", event.get("text"));
							flowRunner.sendEvent(sendEvent18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 58, 64)));
							// Line: 61
							StringCreator string19 = new StringCreator();
							string19.append("Question:");
							// Line: 61
							string19.append(event.get("text"));
							log(string19.toString());
							// Line: 62
							PendingQueryAnswer state20 = new PendingQueryAnswer();
							if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 62, 47)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state21 = agent.new listen();
							if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 56, 71)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 56, 71));
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


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 69
			try {
				EXECUTION: {
					int count = getCount(358699161) + 1;
					incrCount(358699161);
					iristk.situated.SystemAgentFlow.say state22 = agent.new say();
					StringCreator string23 = new StringCreator();
					string23.append("Lets see");
					state22.setText(string23.toString());
					if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 69, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 72
					Event sendEvent24 = new Event("action.gesture");
					sendEvent24.putIfNotNull("name", "gaze_away");
					flowRunner.sendEvent(sendEvent24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 72, 62)));
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 69, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 76
			try {
				count = getCount(110718392) + 1;
				if (event.triggers("monitor.skill.query")) {
					incrCount(110718392);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 78
						if (event.has("answer")) {
							// Line: 80
							StringCreator string25 = new StringCreator();
							string25.append("Answer:");
							// Line: 80
							string25.append(event.get("answer"));
							log(string25.toString());
							iristk.situated.SystemAgentFlow.say state26 = agent.new say();
							StringCreator string27 = new StringCreator();
							// Line: 80
							string27.append(event.get("answer"));
							state26.setText(string27.toString());
							if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 78, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state28 = agent.new say();
							StringCreator string29 = new StringCreator();
							string29.append("Anything else?");
							state28.setText(string29.toString());
							if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 78, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 84
						} else {
							iristk.situated.SystemAgentFlow.say state30 = agent.new say();
							StringCreator string31 = new StringCreator();
							string31.append("Sorry, I can't answer that. Please ask something else");
							state30.setAsync(true);
							state30.setText(string31.toString());
							if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 78, 32)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 90
						flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 90, 19)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 76, 39));
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
			// Line: 98
			try {
				count = getCount(1973538135) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1973538135);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state32 = agent.new gesture();
							state32.setName("smile");
							if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 98, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 98, 63));
			}
			// Line: 103
			try {
				count = getCount(1023487453) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"), iristk.speech.RecResult.NOMATCH)) {
						incrCount(1023487453);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state33 = agent.new say();
							StringCreator string34 = new StringCreator();
							// Line: 103
							boolean chosen35 = false;
							boolean matching36 = true;
							while (!chosen35 && matching36) {
								int rand37 = random(1192108080, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching36 = false;
								if (true) {
									matching36 = true;
									if (rand37 >= 0 && rand37 < 1) {
										chosen35 = true;
										string34.append("Oh, you think so?");
									}
								}
								if (true) {
									matching36 = true;
									if (rand37 >= 1 && rand37 < 2) {
										chosen35 = true;
										string34.append("Okay");
									}
								}
								if (true) {
									matching36 = true;
									if (rand37 >= 2 && rand37 < 3) {
										chosen35 = true;
										string34.append("Interesting");
									}
								}
								if (true) {
									matching36 = true;
									if (rand37 >= 3 && rand37 < 4) {
										chosen35 = true;
										string34.append("Try to ask me a question");
									}
								}
							}
							state33.setText(string34.toString());
							if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 103, 92)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.listen state38 = agent.new listen();
							if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 103, 92)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 103, 92));
			}
			// Line: 117
			try {
				count = getCount(1865127310) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1865127310);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state39 = agent.new say();
						StringCreator string40 = new StringCreator();
						string40.append("Sorry, I didn't get that.");
						state39.setText(string40.toString());
						if (!flowThread.callState(state39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 117, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state41 = agent.new listen();
						if (!flowThread.callState(state41, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 117, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 117, 36));
			}
			// Line: 123
			try {
				count = getCount(515132998) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(515132998);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 124
						Event sendEvent42 = new Event("sense.user.speak");
						sendEvent42.copyParams(event);
						flowRunner.sendEvent(sendEvent42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 124, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 123, 41));
			}
			// Line: 128
			try {
				count = getCount(1365202186) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(1365202186);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 129
						Event sendEvent43 = new Event("sense.user.speak");
						sendEvent43.copyParams(event);
						flowRunner.sendEvent(sendEvent43, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 129, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 128, 42));
			}
			// Line: 133
			try {
				count = getCount(1586600255) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1586600255);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state44 = agent.new say();
						StringCreator string45 = new StringCreator();
						string45.append("Sorry, I didn't hear anything.");
						state44.setText(string45.toString());
						if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 133, 38)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state46 = agent.new listen();
						if (!flowThread.callState(state46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 133, 38)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 133, 38));
			}
			// Line: 139
			try {
				count = getCount(474675244) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(474675244);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 140
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state47 = agent.new attendRandom();
								if (!flowThread.callState(state47, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 140, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 142
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 142, 16)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 143
							} else {
								// Line: 144
								Idle state48 = new Idle();
								flowThread.gotoState(state48, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 144, 26)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\QuestionsAndAnswers\\src\\iristk\\app\\questionsAndAnswers\\QuestionsAndAnswersFlow.xml"), 139, 69));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
