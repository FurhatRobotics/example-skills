package iristk.app.social;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;
import iristk.speech.Nbest;
import java.util.ArrayList;

public class SocialFlow extends iristk.flow.Flow {

	private iristk.situated.SystemAgentFlow agent;
	private iristk.situated.SystemAgent system;
	private Boolean quizAvailable;
	private String lastTopic;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
		quizAvailable = asBoolean(false);
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public Boolean getQuizAvailable() {
		return this.quizAvailable;
	}

	public void setQuizAvailable(Boolean value) {
		this.quizAvailable = value;
	}

	public String getLastTopic() {
		return this.lastTopic;
	}

	public void setLastTopic(String value) {
		this.lastTopic = value;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("quizAvailable")) return this.quizAvailable;
		if (name.equals("lastTopic")) return this.lastTopic;
		if (name.equals("agent")) return this.agent;
		return null;
	}


	public SocialFlow(iristk.situated.SystemAgentFlow agent) {
		this.agent = agent;
		initVariables();
	}

	@Override
	public State getInitialState() {return new Start();}


	public class Start extends State implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 18
			try {
				EXECUTION: {
					int count = getCount(1811075214) + 1;
					incrCount(1811075214);
					// Line: 19
					Event sendEvent0 = new Event("action.voice");
					sendEvent0.putIfNotNull("name", "william");
					flowRunner.sendEvent(sendEvent0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 19, 51)));
					// Line: 20
					Event sendEvent1 = new Event("action.skill.list");
					flowRunner.sendEvent(sendEvent1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 20, 37)));
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 18, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 23
			try {
				count = getCount(1940447180) + 1;
				if (event.triggers("monitor.skill.list")) {
					incrCount(1940447180);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 24
						{
							RepeatHandler skillIterator = new RepeatHandler((ArrayList<Record>)event.get("skills"));
							while (skillIterator.getPosition() < skillIterator.getLength()) {
								// Line: 25
								if (((Record)  skillIterator.getItem()).get("name").equals("Quiz")) {
									// Line: 26
									quizAvailable = true;
									break;
								}
								skillIterator.next();
							}
						}
						// Line: 32
						if (system.hasUsers()) {
							iristk.situated.SystemAgentFlow.attendRandom state2 = agent.new attendRandom();
							if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 32, 33)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 34
							Greeting state3 = new Greeting();
							flowThread.gotoState(state3, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 34, 29)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 35
						} else {
							// Line: 36
							Idle state4 = new Idle();
							flowThread.gotoState(state4, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 36, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 23, 38));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Idle extends State {

		final State currentState = this;
		public boolean goodbye = (boolean) false;

		public void setGoodbye(Object value) {
			if (value != null) {
				goodbye = (boolean) value;
				params.put("goodbye", value);
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
			// Line: 43
			try {
				EXECUTION: {
					int count = getCount(358699161) + 1;
					incrCount(358699161);
					// Line: 44
					if (goodbye) {
						iristk.situated.SystemAgentFlow.say state5 = agent.new say();
						StringCreator string6 = new StringCreator();
						string6.append("Goodbye");
						state5.setAbort(true);
						state5.setText(string6.toString());
						if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 44, 23)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.situated.SystemAgentFlow.attendNobody state7 = agent.new attendNobody();
					if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 43, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 43, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 49
			try {
				count = getCount(914424520) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(914424520);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state8 = agent.new attend();
						state8.setTarget(event.get("user"));
						if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 49, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 51
						Greeting state9 = new Greeting();
						flowThread.gotoState(state9, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 51, 28)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 49, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Attending extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 56
			try {
				EXECUTION: {
					int count = getCount(2143192188) + 1;
					incrCount(2143192188);
					iristk.situated.SystemAgentFlow.listen state10 = agent.new listen();
					if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 56, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 56, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 59
			try {
				count = getCount(204349222) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(204349222);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 60
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state11 = agent.new attendRandom();
								if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 60, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 62
							} else {
								// Line: 63
								Idle state12 = new Idle();
								flowThread.gotoState(state12, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 63, 25)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 59, 69));
			}
			// Line: 66
			try {
				count = getCount(32374789) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(32374789);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state13 = agent.new attend();
						state13.setTarget(event.get("agent"));
						if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 66, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 68
						Attending state14 = new Attending();
						flowThread.gotoState(state14, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 68, 29)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 66, 36));
			}
			// Line: 70
			try {
				count = getCount(1023487453) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1023487453);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 71
						Attending state15 = new Attending();
						flowThread.gotoState(state15, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 71, 29)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 70, 38));
			}
			// Line: 73
			try {
				count = getCount(515132998) + 1;
				if (event.triggers("sense.user.speak**")) {
					incrCount(515132998);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 74
						if (system.getCurrentUser().has("name")) {
							iristk.situated.SystemAgentFlow.say state16 = agent.new say();
							StringCreator string17 = new StringCreator();
							string17.append("Hi");
							// Line: 74
							string17.append(system.getCurrentUser().get("name"));
							state16.setText(string17.toString());
							if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 74, 51)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 76
						} else {
							iristk.situated.SystemAgentFlow.say state18 = agent.new say();
							StringCreator string19 = new StringCreator();
							string19.append("Hi there");
							state18.setText(string19.toString());
							if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 74, 51)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 79
						NextTopic state20 = new NextTopic();
						flowThread.gotoState(state20, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 79, 29)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 73, 38));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Await extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 84
			try {
				EXECUTION: {
					int count = getCount(474675244) + 1;
					incrCount(474675244);
					iristk.situated.SystemAgentFlow.listen state21 = agent.new listen();
					state21.setTimeout(4000);
					if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 84, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 84, 12));
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


	private class PlayQuiz extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 90
			try {
				EXECUTION: {
					int count = getCount(212628335) + 1;
					incrCount(212628335);
					// Line: 91
					system.getCurrentUser().put("played", true);
					if (system.hasManyUsers()) {
						system.getOtherUser().put("played", true);
					};
					// Line: 97
					Event sendEvent22 = new Event("action.skill");
					sendEvent22.putIfNotNull("entry", "iristk.app.quiz.QuizSkill.play");
					flowRunner.sendEvent(sendEvent22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 97, 75)));
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 90, 12));
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


	public class QuizReturn extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 102
			try {
				EXECUTION: {
					int count = getCount(2111991224) + 1;
					incrCount(2111991224);
					// Line: 103
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.say state23 = agent.new say();
						StringCreator string24 = new StringCreator();
						string24.append("That was fun, wasn't it?");
						state23.setText(string24.toString());
						if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 103, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 105
						iristk.flow.DialogFlow.wait waitState25 = new iristk.flow.DialogFlow.wait();
						waitState25.setMsec(1000);
						if (!flowThread.callState(waitState25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 105, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 106
						NextTopic state26 = new NextTopic();
						flowThread.gotoState(state26, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 106, 31)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 107
					} else {
						// Line: 108
						Idle state27 = new Idle();
						state27.setGoodbye(true);
						flowThread.gotoState(state27, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 108, 42)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 102, 18));
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
			// Line: 114
			try {
				count = getCount(1404928347) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_quiz")) {
						incrCount(1404928347);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 115
							PlayQuiz state28 = new PlayQuiz();
							flowThread.gotoState(state28, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 115, 29)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 114, 63));
			}
			// Line: 117
			try {
				count = getCount(123961122) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_config_face")) {
						incrCount(123961122);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 119
							Event sendEvent29 = new Event("action.gesture");
							sendEvent29.putIfNotNull("param", event.get("sem:face_param"));
							sendEvent29.putIfNotNull("value", event.get("sem:param_value"));
							flowRunner.sendEvent(sendEvent29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 119, 39)));
							// Line: 120
							boolean chosen30 = false;
							boolean matching31 = true;
							while (!chosen30 && matching31) {
								int rand32 = random(1982791261, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching31 = false;
								if (true) {
									matching31 = true;
									if (rand32 >= 0 && rand32 < 1) {
										chosen30 = true;
										iristk.situated.SystemAgentFlow.say state33 = agent.new say();
										StringCreator string34 = new StringCreator();
										string34.append("What do you think about this?");
										state33.setText(string34.toString());
										if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 120, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching31 = true;
									if (rand32 >= 1 && rand32 < 2) {
										chosen30 = true;
										iristk.situated.SystemAgentFlow.say state35 = agent.new say();
										StringCreator string36 = new StringCreator();
										string36.append("How about this?");
										state35.setText(string36.toString());
										if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 120, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching31 = true;
									if (rand32 >= 2 && rand32 < 3) {
										chosen30 = true;
										iristk.situated.SystemAgentFlow.say state37 = agent.new say();
										StringCreator string38 = new StringCreator();
										StringCreator string39 = new StringCreator();
										string39.append("<usel variant=\"2\">");
										string39.append("Maybe you like it better this way");
										string39.append("</usel>");
										string38.append(string39.toString());
										state37.setText(string38.toString());
										if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 120, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 127
							Await state40 = new Await();
							flowThread.gotoState(state40, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 127, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 117, 70));
			}
			// Line: 130
			try {
				count = getCount(1101288798) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_questions")) {
						incrCount(1101288798);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 131
							boolean chosen41 = false;
							boolean matching42 = true;
							while (!chosen41 && matching42) {
								int rand43 = random(942731712, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching42 = false;
								if (true) {
									matching42 = true;
									if (rand43 >= 0 && rand43 < 1) {
										chosen41 = true;
										iristk.situated.SystemAgentFlow.say state44 = agent.new say();
										StringCreator string45 = new StringCreator();
										string45.append("You could ask me to tell you a joke");
										state44.setText(string45.toString());
										if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 131, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching42 = true;
									if (rand43 >= 1 && rand43 < 2) {
										chosen41 = true;
										iristk.situated.SystemAgentFlow.say state46 = agent.new say();
										StringCreator string47 = new StringCreator();
										string47.append("You could ask me why I have a Furhat");
										state46.setText(string47.toString());
										if (!flowThread.callState(state46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 131, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching42 = true;
									if (rand43 >= 2 && rand43 < 3) {
										chosen41 = true;
										iristk.situated.SystemAgentFlow.say state48 = agent.new say();
										StringCreator string49 = new StringCreator();
										string49.append("You could ask me if I have emotions");
										state48.setText(string49.toString());
										if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 131, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching42 = true;
									if (rand43 >= 3 && rand43 < 4) {
										chosen41 = true;
										iristk.situated.SystemAgentFlow.say state50 = agent.new say();
										StringCreator string51 = new StringCreator();
										string51.append("You could ask me if I can speak Swedish");
										state50.setText(string51.toString());
										if (!flowThread.callState(state50, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 131, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 137
							Await state52 = new Await();
							flowThread.gotoState(state52, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 137, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 130, 68));
			}
			// Line: 140
			try {
				count = getCount(758529971) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_health")) {
						incrCount(758529971);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 141
							boolean chosen53 = false;
							boolean matching54 = true;
							while (!chosen53 && matching54) {
								int rand55 = random(2104457164, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching54 = false;
								if (true) {
									matching54 = true;
									if (rand55 >= 0 && rand55 < 1) {
										chosen53 = true;
										iristk.situated.SystemAgentFlow.say state56 = agent.new say();
										StringCreator string57 = new StringCreator();
										string57.append("I'm fine");
										state56.setText(string57.toString());
										if (!flowThread.callState(state56, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 141, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching54 = true;
									if (rand55 >= 1 && rand55 < 2) {
										chosen53 = true;
										iristk.situated.SystemAgentFlow.say state58 = agent.new say();
										StringCreator string59 = new StringCreator();
										string59.append("Thank you for asking");
										state58.setText(string59.toString());
										if (!flowThread.callState(state58, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 141, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 145
							Await state60 = new Await();
							flowThread.gotoState(state60, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 145, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 140, 65));
			}
			// Line: 148
			try {
				count = getCount(1940030785) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:goodbye")) {
						incrCount(1940030785);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 150
							boolean chosen61 = false;
							boolean matching62 = true;
							while (!chosen61 && matching62) {
								int rand63 = random(1869997857, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching62 = false;
								if (true) {
									matching62 = true;
									if (rand63 >= 0 && rand63 < 1) {
										chosen61 = true;
										iristk.situated.SystemAgentFlow.say state64 = agent.new say();
										StringCreator string65 = new StringCreator();
										string65.append("Goodbye");
										state64.setText(string65.toString());
										if (!flowThread.callState(state64, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 150, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 153
							Attending state66 = new Attending();
							flowThread.gotoState(state66, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 153, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 148, 62));
			}
			// Line: 156
			try {
				count = getCount(1617791695) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_job")) {
						incrCount(1617791695);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 157
							boolean chosen67 = false;
							boolean matching68 = true;
							while (!chosen67 && matching68) {
								int rand69 = random(125993742, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching68 = false;
								if (true) {
									matching68 = true;
									if (rand69 >= 0 && rand69 < 1) {
										chosen67 = true;
										iristk.situated.SystemAgentFlow.say state70 = agent.new say();
										StringCreator string71 = new StringCreator();
										string71.append("I am here to mingle with people");
										state70.setText(string71.toString());
										if (!flowThread.callState(state70, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 157, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 160
							Await state72 = new Await();
							flowThread.gotoState(state72, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 160, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 156, 62));
			}
			// Line: 163
			try {
				count = getCount(1068824137) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:interests")) {
						incrCount(1068824137);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 164
							boolean chosen73 = false;
							boolean matching74 = true;
							while (!chosen73 && matching74) {
								int rand75 = random(864237698, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching74 = false;
								if (true) {
									matching74 = true;
									if (rand75 >= 0 && rand75 < 1) {
										chosen73 = true;
										iristk.situated.SystemAgentFlow.say state76 = agent.new say();
										StringCreator string77 = new StringCreator();
										string77.append("I don't have many hobbies, besides talking to people.");
										state76.setText(string77.toString());
										if (!flowThread.callState(state76, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 164, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching74 = true;
									if (rand75 >= 1 && rand75 < 2) {
										chosen73 = true;
										iristk.situated.SystemAgentFlow.say state78 = agent.new say();
										StringCreator string79 = new StringCreator();
										string79.append("I was in a play once, it was like a dream.");
										state78.setText(string79.toString());
										if (!flowThread.callState(state78, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 164, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching74 = true;
									if (rand75 >= 2 && rand75 < 3) {
										chosen73 = true;
										iristk.situated.SystemAgentFlow.say state80 = agent.new say();
										StringCreator string81 = new StringCreator();
										string81.append("I'm often in schools, helping kids.");
										state80.setText(string81.toString());
										if (!flowThread.callState(state80, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 164, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 169
							Await state82 = new Await();
							flowThread.gotoState(state82, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 169, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 163, 64));
			}
			// Line: 172
			try {
				count = getCount(237852351) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_maker")) {
						incrCount(237852351);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 173
							boolean chosen83 = false;
							boolean matching84 = true;
							while (!chosen83 && matching84) {
								int rand85 = random(608188624, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching84 = false;
								if (true) {
									matching84 = true;
									if (rand85 >= 0 && rand85 < 1) {
										chosen83 = true;
										iristk.situated.SystemAgentFlow.say state86 = agent.new say();
										StringCreator string87 = new StringCreator();
										string87.append("I was built by the Furhat Robotics team");
										state86.setText(string87.toString());
										if (!flowThread.callState(state86, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 173, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching84 = true;
									if (rand85 >= 1 && rand85 < 2) {
										chosen83 = true;
										iristk.situated.SystemAgentFlow.say state88 = agent.new say();
										StringCreator string89 = new StringCreator();
										string89.append("I come from Stockholm in Sweden");
										state88.setText(string89.toString());
										if (!flowThread.callState(state88, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 173, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching84 = true;
									if (rand85 >= 2 && rand85 < 3) {
										chosen83 = true;
										iristk.situated.SystemAgentFlow.say state90 = agent.new say();
										StringCreator string91 = new StringCreator();
										StringCreator string92 = new StringCreator();
										string92.append("<voice emotion=\"calm\">");
										string92.append("I am made by the smartest people on earth");
										string92.append("</voice>");
										string91.append(string92.toString());
										state90.setText(string91.toString());
										if (!flowThread.callState(state90, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 173, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 182
							Await state93 = new Await();
							flowThread.gotoState(state93, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 182, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 172, 64));
			}
			// Line: 185
			try {
				count = getCount(1608446010) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:describe_speech_technology")) {
						incrCount(1608446010);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 186
							boolean chosen94 = false;
							boolean matching95 = true;
							while (!chosen94 && matching95) {
								int rand96 = random(992136656, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching95 = false;
								if (true) {
									matching95 = true;
									if (rand96 >= 0 && rand96 < 1) {
										chosen94 = true;
										iristk.situated.SystemAgentFlow.say state97 = agent.new say();
										StringCreator string98 = new StringCreator();
										string98.append("If you ask one of my makers, I am sure they can describe the technology behind all of this.");
										state97.setText(string98.toString());
										if (!flowThread.callState(state97, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 186, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching95 = true;
									if (rand96 >= 1 && rand96 < 2) {
										chosen94 = true;
										iristk.situated.SystemAgentFlow.say state99 = agent.new say();
										StringCreator string100 = new StringCreator();
										string100.append("I don't really know how the technology works, but I think it is cool.");
										state99.setText(string100.toString());
										if (!flowThread.callState(state99, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 186, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 190
							Await state101 = new Await();
							flowThread.gotoState(state101, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 190, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 185, 81));
			}
			// Line: 193
			try {
				count = getCount(1705929636) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:big_question")) {
						incrCount(1705929636);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 194
							boolean chosen102 = false;
							boolean matching103 = true;
							while (!chosen102 && matching103) {
								int rand104 = random(1221555852, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching103 = false;
								if (true) {
									matching103 = true;
									if (rand104 >= 0 && rand104 < 1) {
										chosen102 = true;
										iristk.situated.SystemAgentFlow.say state105 = agent.new say();
										StringCreator string106 = new StringCreator();
										string106.append("I was not programmed to answer such complicated questions. I am just a social machine.");
										state105.setText(string106.toString());
										if (!flowThread.callState(state105, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 194, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching103 = true;
									if (rand104 >= 1 && rand104 < 2) {
										chosen102 = true;
										iristk.situated.SystemAgentFlow.say state107 = agent.new say();
										StringCreator string108 = new StringCreator();
										string108.append("Why don't you search wikipedia for that, I am sure you can find an answer there.");
										state107.setText(string108.toString());
										if (!flowThread.callState(state107, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 194, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching103 = true;
									if (rand104 >= 2 && rand104 < 3) {
										chosen102 = true;
										iristk.situated.SystemAgentFlow.say state109 = agent.new say();
										StringCreator string110 = new StringCreator();
										StringCreator string111 = new StringCreator();
										string111.append("<usel variant=\"2\">");
										string111.append("Greater people than");
										string111.append("</usel>");
										string110.append(string111.toString());
										StringCreator string112 = new StringCreator();
										string112.append("<usel variant=\"1\">");
										string112.append("me");
										string112.append("</usel>");
										string110.append(string112.toString());
										string110.append(",");
										StringCreator string113 = new StringCreator();
										string113.append("<usel variant=\"4\">");
										string113.append("have failed to answer such big questions.");
										string113.append("</usel>");
										string110.append(string113.toString());
										state109.setText(string110.toString());
										if (!flowThread.callState(state109, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 194, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 201
							Await state114 = new Await();
							flowThread.gotoState(state114, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 201, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 193, 67));
			}
			// Line: 204
			try {
				count = getCount(1556956098) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_hat")) {
						incrCount(1556956098);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 205
							boolean chosen115 = false;
							boolean matching116 = true;
							while (!chosen115 && matching116) {
								int rand117 = random(1252585652, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching116 = false;
								if (true) {
									matching116 = true;
									if (rand117 >= 0 && rand117 < 1) {
										chosen115 = true;
										iristk.situated.SystemAgentFlow.say state118 = agent.new say();
										StringCreator string119 = new StringCreator();
										string119.append("I come from a cold country");
										StringCreator string120 = new StringCreator();
										string120.append("<usel variant=\"6\">");
										string120.append("where polar bears");
										string120.append("</usel>");
										string119.append(string120.toString());
										string119.append("walk on");
										StringCreator string121 = new StringCreator();
										string121.append("<usel variant=\"3\">");
										string121.append("the streets");
										string121.append("</usel>");
										string119.append(string121.toString());
										state118.setText(string119.toString());
										if (!flowThread.callState(state118, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 205, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching116 = true;
									if (rand117 >= 1 && rand117 < 2) {
										chosen115 = true;
										iristk.situated.SystemAgentFlow.say state122 = agent.new say();
										StringCreator string123 = new StringCreator();
										string123.append("This is the latest fashion in");
										StringCreator string124 = new StringCreator();
										string124.append("<usel variant=\"4\">");
										string124.append("robot");
										string124.append("</usel>");
										string123.append(string124.toString());
										string123.append("clothing");
										state122.setText(string123.toString());
										if (!flowThread.callState(state122, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 205, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching116 = true;
									if (rand117 >= 2 && rand117 < 3) {
										chosen115 = true;
										iristk.situated.SystemAgentFlow.say state125 = agent.new say();
										StringCreator string126 = new StringCreator();
										StringCreator string127 = new StringCreator();
										string127.append("<voice emotion=\"happy\">");
										string127.append("The hat covers my brain, and I think it looks 						nice");
										string127.append("</voice>");
										string126.append(string127.toString());
										state125.setText(string126.toString());
										if (!flowThread.callState(state125, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 205, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 222
							Await state128 = new Await();
							flowThread.gotoState(state128, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 222, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 204, 62));
			}
			// Line: 225
			try {
				count = getCount(1785210046) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_age")) {
						incrCount(1785210046);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 226
							boolean chosen129 = false;
							boolean matching130 = true;
							while (!chosen129 && matching130) {
								int rand131 = random(1552787810, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching130 = false;
								if (true) {
									matching130 = true;
									if (rand131 >= 0 && rand131 < 1) {
										chosen129 = true;
										iristk.situated.SystemAgentFlow.say state132 = agent.new say();
										StringCreator string133 = new StringCreator();
										string133.append("I was born in march 2011");
										state132.setText(string133.toString());
										if (!flowThread.callState(state132, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 226, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 229
							Integer age = asInteger(asInteger(event.get("user:age")));
							// Line: 230
							if (age != null && age > -1) {
								iristk.situated.SystemAgentFlow.say state134 = agent.new say();
								StringCreator string135 = new StringCreator();
								string135.append("Let me guess. I think you are about");
								// Line: 230
								string135.append(age);
								string135.append("years old");
								state134.setText(string135.toString());
								if (!flowThread.callState(state134, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 230, 40)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
							// Line: 237
							Await state136 = new Await();
							flowThread.gotoState(state136, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 237, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 225, 62));
			}
			// Line: 240
			try {
				count = getCount(914504136) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:emotions")) {
						incrCount(914504136);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 241
							Event sendEvent137 = new Event("action.gesture");
							sendEvent137.putIfNotNull("name", "emotion_anger");
							flowRunner.sendEvent(sendEvent137, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 241, 59)));
							iristk.situated.SystemAgentFlow.say state138 = agent.new say();
							StringCreator string139 = new StringCreator();
							string139.append("I can be angry");
							state138.setText(string139.toString());
							if (!flowThread.callState(state138, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 240, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 243
							Event sendEvent140 = new Event("action.gesture");
							sendEvent140.putIfNotNull("name", "emotion_sad");
							flowRunner.sendEvent(sendEvent140, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 243, 57)));
							iristk.situated.SystemAgentFlow.say state141 = agent.new say();
							StringCreator string142 = new StringCreator();
							string142.append("I can also be sad");
							state141.setText(string142.toString());
							if (!flowThread.callState(state141, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 240, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 245
							Event sendEvent143 = new Event("action.gesture");
							sendEvent143.putIfNotNull("name", "emotion_disgust");
							flowRunner.sendEvent(sendEvent143, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 245, 61)));
							iristk.situated.SystemAgentFlow.say state144 = agent.new say();
							StringCreator string145 = new StringCreator();
							string145.append("Sometimes I can be disgusted");
							state144.setText(string145.toString());
							if (!flowThread.callState(state144, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 240, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 247
							Event sendEvent146 = new Event("action.gesture");
							sendEvent146.putIfNotNull("name", "emotion_fear");
							flowRunner.sendEvent(sendEvent146, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 247, 58)));
							iristk.situated.SystemAgentFlow.say state147 = agent.new say();
							StringCreator string148 = new StringCreator();
							string148.append("And I can be scared");
							state147.setText(string148.toString());
							if (!flowThread.callState(state147, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 240, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 249
							Event sendEvent149 = new Event("action.gesture");
							sendEvent149.putIfNotNull("name", "emotion_neutral");
							flowRunner.sendEvent(sendEvent149, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 249, 61)));
							// Line: 250
							Await state150 = new Await();
							flowThread.gotoState(state150, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 250, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 240, 63));
			}
			// Line: 253
			try {
				count = getCount(708049632) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_body")) {
						incrCount(708049632);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 254
							boolean chosen151 = false;
							boolean matching152 = true;
							while (!chosen151 && matching152) {
								int rand153 = random(1887400018, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching152 = false;
								if (true) {
									matching152 = true;
									if (rand153 >= 0 && rand153 < 1) {
										chosen151 = true;
										iristk.situated.SystemAgentFlow.say state154 = agent.new say();
										StringCreator string155 = new StringCreator();
										string155.append("I left my body at home");
										state154.setText(string155.toString());
										if (!flowThread.callState(state154, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 254, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching152 = true;
									if (rand153 >= 1 && rand153 < 2) {
										chosen151 = true;
										iristk.situated.SystemAgentFlow.say state156 = agent.new say();
										StringCreator string157 = new StringCreator();
										string157.append("Not all robots need a body");
										state156.setText(string157.toString());
										if (!flowThread.callState(state156, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 254, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 258
							Await state158 = new Await();
							flowThread.gotoState(state158, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 258, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 253, 63));
			}
			// Line: 261
			try {
				count = getCount(344560770) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_stop_talking")) {
						incrCount(344560770);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 262
							boolean chosen159 = false;
							boolean matching160 = true;
							while (!chosen159 && matching160) {
								int rand161 = random(559450121, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching160 = false;
								if (true) {
									matching160 = true;
									if (rand161 >= 0 && rand161 < 1) {
										chosen159 = true;
										iristk.situated.SystemAgentFlow.say state162 = agent.new say();
										StringCreator string163 = new StringCreator();
										string163.append("I am a talking robot, I am supposed to talk to you");
										state162.setText(string163.toString());
										if (!flowThread.callState(state162, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 262, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 265
							Await state164 = new Await();
							flowThread.gotoState(state164, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 265, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 261, 71));
			}
			// Line: 268
			try {
				count = getCount(791885625) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no_understanding")) {
						incrCount(791885625);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 269
							boolean chosen165 = false;
							boolean matching166 = true;
							while (!chosen165 && matching166) {
								int rand167 = random(2001112025, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching166 = false;
								if (true) {
									matching166 = true;
									if (rand167 >= 0 && rand167 < 1) {
										chosen165 = true;
										iristk.situated.SystemAgentFlow.say state168 = agent.new say();
										StringCreator string169 = new StringCreator();
										string169.append("sorry if");
										StringCreator string170 = new StringCreator();
										string170.append("<usel variant=\"2\">");
										string170.append("i misunderstood");
										string170.append("</usel>");
										string169.append(string170.toString());
										state168.setText(string169.toString());
										if (!flowThread.callState(state168, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 269, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching166 = true;
									if (rand167 >= 1 && rand167 < 2) {
										chosen165 = true;
										iristk.situated.SystemAgentFlow.say state171 = agent.new say();
										StringCreator string172 = new StringCreator();
										string172.append("I'm just trying to help you");
										state171.setText(string172.toString());
										if (!flowThread.callState(state171, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 269, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching166 = true;
									if (rand167 >= 2 && rand167 < 3) {
										chosen165 = true;
										iristk.situated.SystemAgentFlow.say state173 = agent.new say();
										StringCreator string174 = new StringCreator();
										string174.append("i'm doing the best i can");
										state173.setText(string174.toString());
										if (!flowThread.callState(state173, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 269, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching166 = true;
									if (rand167 >= 3 && rand167 < 4) {
										chosen165 = true;
										iristk.situated.SystemAgentFlow.say state175 = agent.new say();
										StringCreator string176 = new StringCreator();
										string176.append("I am only a humble");
										StringCreator string177 = new StringCreator();
										string177.append("<usel variant=\"4\">");
										string177.append("robot");
										string177.append("</usel>");
										string176.append(string177.toString());
										state175.setText(string176.toString());
										if (!flowThread.callState(state175, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 269, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 278
							Await state178 = new Await();
							flowThread.gotoState(state178, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 278, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 268, 71));
			}
			// Line: 281
			try {
				count = getCount(1288141870) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no_answer")) {
						incrCount(1288141870);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 282
							boolean chosen179 = false;
							boolean matching180 = true;
							while (!chosen179 && matching180) {
								int rand181 = random(2054881392, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching180 = false;
								if (true) {
									matching180 = true;
									if (rand181 >= 0 && rand181 < 1) {
										chosen179 = true;
										iristk.situated.SystemAgentFlow.say state182 = agent.new say();
										StringCreator string183 = new StringCreator();
										string183.append("sorry if");
										StringCreator string184 = new StringCreator();
										string184.append("<usel variant=\"2\">");
										string184.append("i misunderstood");
										string184.append("</usel>");
										string183.append(string184.toString());
										state182.setText(string183.toString());
										if (!flowThread.callState(state182, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 282, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching180 = true;
									if (rand181 >= 1 && rand181 < 2) {
										chosen179 = true;
										iristk.situated.SystemAgentFlow.say state185 = agent.new say();
										StringCreator string186 = new StringCreator();
										string186.append("i'm doing the best i can");
										state185.setText(string186.toString());
										if (!flowThread.callState(state185, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 282, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 289
							Await state187 = new Await();
							flowThread.gotoState(state187, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 289, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 281, 64));
			}
			// Line: 292
			try {
				count = getCount(1908153060) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:user_unwilling")) {
						incrCount(1908153060);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 293
							boolean chosen188 = false;
							boolean matching189 = true;
							while (!chosen188 && matching189) {
								int rand190 = random(607635164, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching189 = false;
								if (true) {
									matching189 = true;
									if (rand190 >= 0 && rand190 < 1) {
										chosen188 = true;
										iristk.situated.SystemAgentFlow.say state191 = agent.new say();
										StringCreator string192 = new StringCreator();
										string192.append("that's ok");
										state191.setText(string192.toString());
										if (!flowThread.callState(state191, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 293, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching189 = true;
									if (rand190 >= 1 && rand190 < 2) {
										chosen188 = true;
										iristk.situated.SystemAgentFlow.say state193 = agent.new say();
										StringCreator string194 = new StringCreator();
										string194.append("no worries");
										state193.setText(string194.toString());
										if (!flowThread.callState(state193, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 293, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 297
							Await state195 = new Await();
							flowThread.gotoState(state195, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 297, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 292, 69));
			}
			// Line: 300
			try {
				count = getCount(242481580) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:favourite_color")) {
						incrCount(242481580);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 301
							boolean chosen196 = false;
							boolean matching197 = true;
							while (!chosen196 && matching197) {
								int rand198 = random(1627800613, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching197 = false;
								if (true) {
									matching197 = true;
									if (rand198 >= 0 && rand198 < 1) {
										chosen196 = true;
										iristk.situated.SystemAgentFlow.say state199 = agent.new say();
										StringCreator string200 = new StringCreator();
										string200.append("i'm a robot, i have no favourite colour");
										state199.setText(string200.toString());
										if (!flowThread.callState(state199, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 301, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching197 = true;
									if (rand198 >= 1 && rand198 < 2) {
										chosen196 = true;
										iristk.situated.SystemAgentFlow.say state201 = agent.new say();
										StringCreator string202 = new StringCreator();
										string202.append("i like black");
										state201.setText(string202.toString());
										if (!flowThread.callState(state201, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 301, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 305
							Await state203 = new Await();
							flowThread.gotoState(state203, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 305, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 300, 70));
			}
			// Line: 308
			try {
				count = getCount(697960108) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:favourite_food")) {
						incrCount(697960108);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 309
							boolean chosen204 = false;
							boolean matching205 = true;
							while (!chosen204 && matching205) {
								int rand206 = random(943010986, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching205 = false;
								if (true) {
									matching205 = true;
									if (rand206 >= 0 && rand206 < 1) {
										chosen204 = true;
										iristk.situated.SystemAgentFlow.say state207 = agent.new say();
										StringCreator string208 = new StringCreator();
										string208.append("i'm a robot, i don't eat");
										state207.setText(string208.toString());
										if (!flowThread.callState(state207, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 309, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching205 = true;
									if (rand206 >= 1 && rand206 < 2) {
										chosen204 = true;
										iristk.situated.SystemAgentFlow.say state209 = agent.new say();
										StringCreator string210 = new StringCreator();
										string210.append("i like black pudding");
										state209.setText(string210.toString());
										if (!flowThread.callState(state209, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 309, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 313
							Await state211 = new Await();
							flowThread.gotoState(state211, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 313, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 308, 69));
			}
			// Line: 316
			try {
				count = getCount(2066940133) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:favourite_movie")) {
						incrCount(2066940133);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 317
							boolean chosen212 = false;
							boolean matching213 = true;
							while (!chosen212 && matching213) {
								int rand214 = random(48612937, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching213 = false;
								if (true) {
									matching213 = true;
									if (rand214 >= 0 && rand214 < 1) {
										chosen212 = true;
										iristk.situated.SystemAgentFlow.say state215 = agent.new say();
										StringCreator string216 = new StringCreator();
										string216.append("i'm a robot, i don't go to the movie");
										state215.setText(string216.toString());
										if (!flowThread.callState(state215, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 317, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching213 = true;
									if (rand214 >= 1 && rand214 < 2) {
										chosen212 = true;
										iristk.situated.SystemAgentFlow.say state217 = agent.new say();
										StringCreator string218 = new StringCreator();
										string218.append("i like");
										StringCreator string219 = new StringCreator();
										string219.append("<usel variant=\"3\">");
										string219.append("eye robot");
										string219.append("</usel>");
										string218.append(string219.toString());
										state217.setText(string218.toString());
										if (!flowThread.callState(state217, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 317, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 324
							Await state220 = new Await();
							flowThread.gotoState(state220, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 324, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 316, 70));
			}
			// Line: 327
			try {
				count = getCount(1937962514) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_joke")) {
						incrCount(1937962514);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state221 = agent.new say();
							StringCreator string222 = new StringCreator();
							string222.append("How about this");
							state221.setText(string222.toString());
							if (!flowThread.callState(state221, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 327, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 329
							boolean chosen223 = false;
							boolean matching224 = true;
							while (!chosen223 && matching224) {
								int rand225 = random(274064559, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching224 = false;
								if (true) {
									matching224 = true;
									if (rand225 >= 0 && rand225 < 1) {
										chosen223 = true;
										iristk.situated.SystemAgentFlow.say state226 = agent.new say();
										StringCreator string227 = new StringCreator();
										string227.append("Always remember that you are unique, just like everyone else.");
										state226.setText(string227.toString());
										if (!flowThread.callState(state226, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 329, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching224 = true;
									if (rand225 >= 1 && rand225 < 2) {
										chosen223 = true;
										iristk.situated.SystemAgentFlow.say state228 = agent.new say();
										StringCreator string229 = new StringCreator();
										string229.append("Is it good if a,");
										StringCreator string230 = new StringCreator();
										string230.append("<usel variant=\"2\">");
										string230.append("vacuum");
										string230.append("</usel>");
										string229.append(string230.toString());
										string229.append(", really sucks?");
										state228.setText(string229.toString());
										if (!flowThread.callState(state228, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 329, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching224 = true;
									if (rand225 >= 2 && rand225 < 3) {
										chosen223 = true;
										iristk.situated.SystemAgentFlow.say state231 = agent.new say();
										StringCreator string232 = new StringCreator();
										string232.append("How Many Roads Must A Man Walk Down, Before He Admits He is lost?");
										state231.setText(string232.toString());
										if (!flowThread.callState(state231, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 329, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching224 = true;
									if (rand225 >= 3 && rand225 < 4) {
										chosen223 = true;
										iristk.situated.SystemAgentFlow.say state233 = agent.new say();
										StringCreator string234 = new StringCreator();
										string234.append("Why is it called, 'after dark', when it really is , after light?");
										state233.setText(string234.toString());
										if (!flowThread.callState(state233, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 329, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching224 = true;
									if (rand225 >= 4 && rand225 < 5) {
										chosen223 = true;
										iristk.situated.SystemAgentFlow.say state235 = agent.new say();
										StringCreator string236 = new StringCreator();
										string236.append("There is a fine line between fishing, and just standing on the shore like an idiot.");
										state235.setText(string236.toString());
										if (!flowThread.callState(state235, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 329, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 339
							boolean chosen237 = false;
							boolean matching238 = true;
							while (!chosen237 && matching238) {
								int rand239 = random(1018081122, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching238 = false;
								if (true) {
									matching238 = true;
									if (rand239 >= 0 && rand239 < 1) {
										chosen237 = true;
										iristk.situated.SystemAgentFlow.say state240 = agent.new say();
										StringCreator string241 = new StringCreator();
										string241.append("GESTURE_GIGGLE_2");
										state240.setText(string241.toString());
										if (!flowThread.callState(state240, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 339, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching238 = true;
									if (rand239 >= 1 && rand239 < 2) {
										chosen237 = true;
										iristk.situated.SystemAgentFlow.say state242 = agent.new say();
										StringCreator string243 = new StringCreator();
										string243.append("GESTURE_LAUGH_2");
										state242.setText(string243.toString());
										if (!flowThread.callState(state242, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 339, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching238 = true;
									if (rand239 >= 2 && rand239 < 3) {
										chosen237 = true;
										iristk.situated.SystemAgentFlow.say state244 = agent.new say();
										StringCreator string245 = new StringCreator();
										string245.append("GESTURE_LAUGH_3");
										state244.setText(string245.toString());
										if (!flowThread.callState(state244, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 339, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 344
							Await state246 = new Await();
							flowThread.gotoState(state246, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 344, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 327, 63));
			}
			// Line: 347
			try {
				count = getCount(1782113663) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:hal_2001")) {
						incrCount(1782113663);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 348
							boolean chosen247 = false;
							boolean matching248 = true;
							while (!chosen247 && matching248) {
								int rand249 = random(1433867275, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching248 = false;
								if (true) {
									matching248 = true;
									if (rand249 >= 0 && rand249 < 1) {
										chosen247 = true;
										iristk.situated.SystemAgentFlow.say state250 = agent.new say();
										StringCreator string251 = new StringCreator();
										StringCreator string252 = new StringCreator();
										string252.append("<voice emotion=\"calm\">");
										string252.append("I'm sorry Dave, I'm afraid I can't do that.");
										string252.append("</voice>");
										string251.append(string252.toString());
										state250.setText(string251.toString());
										if (!flowThread.callState(state250, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 348, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 353
							Await state253 = new Await();
							flowThread.gotoState(state253, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 353, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 347, 63));
			}
			// Line: 356
			try {
				count = getCount(1744347043) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_ask_other")) {
						incrCount(1744347043);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 357
							if (system.hasManyUsers()) {
								iristk.situated.SystemAgentFlow.attendOther state254 = agent.new attendOther();
								if (!flowThread.callState(state254, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 357, 37)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								iristk.situated.SystemAgentFlow.say state255 = agent.new say();
								StringCreator string256 = new StringCreator();
								string256.append("well then");
								state255.setText(string256.toString());
								if (!flowThread.callState(state255, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 357, 37)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 360
								Event raiseEvent257 = new Event("reprompt");
								if (flowThread.raiseEvent(raiseEvent257, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 360, 30))) == State.EVENT_ABORTED) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 361
							} else {
								iristk.situated.SystemAgentFlow.say state258 = agent.new say();
								StringCreator string259 = new StringCreator();
								string259.append("There is no one else here");
								state258.setText(string259.toString());
								if (!flowThread.callState(state258, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 357, 37)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 363
								Await state260 = new Await();
								flowThread.gotoState(state260, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 363, 27)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 356, 68));
			}
			// Line: 367
			try {
				count = getCount(1023714065) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_my_age")) {
						incrCount(1023714065);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 368
							Integer age = asInteger(event.get("user.age"));
							// Line: 369
							if (age != null && age > -1) {
								iristk.situated.SystemAgentFlow.say state261 = agent.new say();
								StringCreator string262 = new StringCreator();
								string262.append("I think you are about");
								// Line: 369
								string262.append(age);
								string262.append("years old");
								state261.setText(string262.toString());
								if (!flowThread.callState(state261, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 369, 40)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 375
							} else {
								iristk.situated.SystemAgentFlow.say state263 = agent.new say();
								StringCreator string264 = new StringCreator();
								string264.append("I have no idea");
								state263.setText(string264.toString());
								if (!flowThread.callState(state263, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 369, 40)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
							// Line: 378
							Await state265 = new Await();
							flowThread.gotoState(state265, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 378, 26)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 367, 65));
			}
			// Line: 381
			try {
				count = getCount(985655350) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_sleep")) {
						incrCount(985655350);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 382
							Asleep state266 = new Asleep();
							flowThread.gotoState(state266, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 382, 27)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 381, 64));
			}
			// Line: 385
			try {
				count = getCount(2008017533) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:residence")) {
						incrCount(2008017533);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 386
							boolean chosen267 = false;
							boolean matching268 = true;
							while (!chosen267 && matching268) {
								int rand269 = random(370988149, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching268 = false;
								if (true) {
									matching268 = true;
									if (rand269 >= 0 && rand269 < 1) {
										chosen267 = true;
										iristk.situated.SystemAgentFlow.say state270 = agent.new say();
										StringCreator string271 = new StringCreator();
										string271.append("I come from the Royal Institute of Technology in Stockholm.");
										state270.setText(string271.toString());
										if (!flowThread.callState(state270, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 386, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching268 = true;
									if (rand269 >= 1 && rand269 < 2) {
										chosen267 = true;
										iristk.situated.SystemAgentFlow.say state272 = agent.new say();
										StringCreator string273 = new StringCreator();
										string273.append("I come from Stockholm in Sweden.");
										state272.setText(string273.toString());
										if (!flowThread.callState(state272, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 386, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 390
							Event raiseEvent274 = new Event("continue");
							if (flowThread.raiseEvent(raiseEvent274, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 390, 30))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 385, 64));
			}
			// Line: 393
			try {
				count = getCount(1476011703) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_repeat")) {
						incrCount(1476011703);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 394
							Event raiseEvent275 = new Event("reprompt");
							if (flowThread.raiseEvent(raiseEvent275, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 394, 29))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 393, 65));
			}
			// Line: 397
			try {
				count = getCount(792791759) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_name")) {
						incrCount(792791759);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state276 = agent.new say();
							StringCreator string277 = new StringCreator();
							string277.append("My name is Furhat");
							state276.setText(string277.toString());
							if (!flowThread.callState(state276, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 397, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 399
							Event raiseEvent278 = new Event("continue");
							if (flowThread.raiseEvent(raiseEvent278, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 399, 30))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 397, 63));
			}
			// Line: 402
			try {
				count = getCount(1094834071) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_swedish")) {
						incrCount(1094834071);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state279 = agent.new say();
							StringCreator string280 = new StringCreator();
							string280.append("En Pawseh rairkoor, dahlaheihstahr, yehnom-stehk-ta 				churt-bull-are, en yetteh-snewgg mur-sah");
							state279.setText(string280.toString());
							if (!flowThread.callState(state279, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 402, 66)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 406
							Event raiseEvent281 = new Event("continue");
							if (flowThread.raiseEvent(raiseEvent281, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 406, 30))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 402, 66));
			}
			// Line: 409
			try {
				count = getCount(1330106945) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:insult")) {
						incrCount(1330106945);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 410
							boolean chosen282 = false;
							boolean matching283 = true;
							while (!chosen282 && matching283) {
								int rand284 = random(1279149968, 11, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching283 = false;
								if (true) {
									matching283 = true;
									if (rand284 >= 0 && rand284 < 1) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state285 = agent.new say();
										StringCreator string286 = new StringCreator();
										StringCreator string287 = new StringCreator();
										string287.append("<voice emotion=\"cross\">");
										string287.append("Let's not be rude here");
										string287.append("</voice>");
										string286.append(string287.toString());
										state285.setText(string286.toString());
										if (!flowThread.callState(state285, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 1 && rand284 < 2) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state288 = agent.new say();
										StringCreator string289 = new StringCreator();
										string289.append("You're certainly entitled");
										StringCreator string290 = new StringCreator();
										string290.append("<usel variant=\"3\">");
										string290.append("to");
										string290.append("</usel>");
										string289.append(string290.toString());
										StringCreator string291 = new StringCreator();
										string291.append("<emphasis level=\"strong\">");
										string291.append("that opinion");
										string291.append("</emphasis>");
										string289.append(string291.toString());
										state288.setText(string289.toString());
										if (!flowThread.callState(state288, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 2 && rand284 < 3) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state292 = agent.new say();
										StringCreator string293 = new StringCreator();
										string293.append("I respect you");
										state292.setText(string293.toString());
										if (!flowThread.callState(state292, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 3 && rand284 < 4) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state294 = agent.new say();
										StringCreator string295 = new StringCreator();
										string295.append("noted");
										state294.setText(string295.toString());
										if (!flowThread.callState(state294, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 4 && rand284 < 5) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state296 = agent.new say();
										StringCreator string297 = new StringCreator();
										string297.append("I guess you're not happy");
										state296.setText(string297.toString());
										if (!flowThread.callState(state296, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 5 && rand284 < 6) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state298 = agent.new say();
										StringCreator string299 = new StringCreator();
										string299.append("i'll pretend i didn't hear that");
										state298.setText(string299.toString());
										if (!flowThread.callState(state298, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 6 && rand284 < 7) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state300 = agent.new say();
										StringCreator string301 = new StringCreator();
										string301.append("I'm just trying to help you");
										state300.setText(string301.toString());
										if (!flowThread.callState(state300, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 7 && rand284 < 8) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state302 = agent.new say();
										StringCreator string303 = new StringCreator();
										string303.append("i'm doing the best i can");
										state302.setText(string303.toString());
										if (!flowThread.callState(state302, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 8 && rand284 < 9) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state304 = agent.new say();
										StringCreator string305 = new StringCreator();
										string305.append("I am only a humble");
										StringCreator string306 = new StringCreator();
										string306.append("<usel variant=\"4\">");
										string306.append("robot");
										string306.append("</usel>");
										string305.append(string306.toString());
										state304.setText(string305.toString());
										if (!flowThread.callState(state304, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 9 && rand284 < 10) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state307 = agent.new say();
										StringCreator string308 = new StringCreator();
										StringCreator string309 = new StringCreator();
										string309.append("<voice emotion=\"cross\">");
										string309.append("You need to learn some manners");
										string309.append("</voice>");
										string308.append(string309.toString());
										state307.setText(string308.toString());
										if (!flowThread.callState(state307, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching283 = true;
									if (rand284 >= 10 && rand284 < 11) {
										chosen282 = true;
										iristk.situated.SystemAgentFlow.say state310 = agent.new say();
										StringCreator string311 = new StringCreator();
										StringCreator string312 = new StringCreator();
										string312.append("<voice emotion=\"cross\">");
										string312.append("You need to learn how to talk to a robot");
										string312.append("</voice>");
										string311.append(string312.toString());
										state310.setText(string311.toString());
										if (!flowThread.callState(state310, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 410, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 435
							Event raiseEvent313 = new Event("continue");
							if (flowThread.raiseEvent(raiseEvent313, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 435, 30))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 409, 61));
			}
			// Line: 438
			try {
				count = getCount(1450821318) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:praise")) {
						incrCount(1450821318);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 439
							boolean chosen314 = false;
							boolean matching315 = true;
							while (!chosen314 && matching315) {
								int rand316 = random(668849042, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching315 = false;
								if (true) {
									matching315 = true;
									if (rand316 >= 0 && rand316 < 1) {
										chosen314 = true;
										iristk.situated.SystemAgentFlow.say state317 = agent.new say();
										StringCreator string318 = new StringCreator();
										string318.append("Thanks a lot, I'm blushing");
										state317.setText(string318.toString());
										if (!flowThread.callState(state317, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 439, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching315 = true;
									if (rand316 >= 1 && rand316 < 2) {
										chosen314 = true;
										iristk.situated.SystemAgentFlow.say state319 = agent.new say();
										StringCreator string320 = new StringCreator();
										StringCreator string321 = new StringCreator();
										string321.append("<usel variant=\"1\">");
										string321.append("That was really nice of you");
										string321.append("</usel>");
										string320.append(string321.toString());
										state319.setText(string320.toString());
										if (!flowThread.callState(state319, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 439, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching315 = true;
									if (rand316 >= 2 && rand316 < 3) {
										chosen314 = true;
										iristk.situated.SystemAgentFlow.say state322 = agent.new say();
										StringCreator string323 = new StringCreator();
										string323.append("You don't say that to every robot, do you?");
										state322.setText(string323.toString());
										if (!flowThread.callState(state322, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 439, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 446
							Event raiseEvent324 = new Event("continue");
							if (flowThread.raiseEvent(raiseEvent324, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 446, 30))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 438, 61));
			}
			// Line: 449
			try {
				count = getCount(2096057945) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(2096057945);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 450
						Event raiseEvent325 = new Event("nomatch");
						if (flowThread.raiseEvent(raiseEvent325, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 450, 29))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 449, 36));
			}
			// Line: 453
			try {
				count = getCount(766572210) + 1;
				if (event.triggers("nomatch")) {
					incrCount(766572210);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 454
						NextTopic state326 = new NextTopic();
						flowThread.gotoState(state326, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 454, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 453, 27));
			}
			// Line: 457
			try {
				count = getCount(977993101) + 1;
				if (event.triggers("continue")) {
					incrCount(977993101);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 458
						Await state327 = new Await();
						flowThread.gotoState(state327, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 458, 26)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 457, 28));
			}
			// Line: 461
			try {
				count = getCount(859417998) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(859417998);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 462
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state328 = agent.new attendRandom();
								if (!flowThread.callState(state328, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 462, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 464
								NextTopic state329 = new NextTopic();
								flowThread.gotoState(state329, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 464, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 465
							} else {
								// Line: 466
								Idle state330 = new Idle();
								state330.setGoodbye(true);
								flowThread.gotoState(state330, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 466, 43)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 461, 69));
			}
			// Line: 470
			try {
				count = getCount(380936215) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(380936215);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attendRandom state331 = agent.new attendRandom();
						if (!flowThread.callState(state331, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 470, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 472
						Event raiseEvent332 = new Event("sense.user.speak");
						raiseEvent332.copyParams(event);
						if (flowThread.raiseEvent(raiseEvent332, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 472, 50))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 470, 42));
			}
			// Line: 475
			try {
				count = getCount(707806938) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(707806938);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attendOther state333 = agent.new attendOther();
						state333.setMode("eyes");
						if (!flowThread.callState(state333, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 475, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 477
						if (system.getCurrentUser().has("name")) {
							iristk.situated.SystemAgentFlow.say state334 = agent.new say();
							StringCreator string335 = new StringCreator();
							// Line: 477
							string335.append(system.getCurrentUser().get("name"));
							state334.setText(string335.toString());
							if (!flowThread.callState(state334, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 477, 51)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 480
						boolean chosen336 = false;
						boolean matching337 = true;
						while (!chosen336 && matching337) {
							int rand338 = random(428746855, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching337 = false;
							if (true) {
								matching337 = true;
								if (rand338 >= 0 && rand338 < 1) {
									chosen336 = true;
									iristk.situated.SystemAgentFlow.say state339 = agent.new say();
									StringCreator string340 = new StringCreator();
									string340.append("could you just wait a second");
									state339.setText(string340.toString());
									if (!flowThread.callState(state339, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 480, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching337 = true;
								if (rand338 >= 1 && rand338 < 2) {
									chosen336 = true;
									iristk.situated.SystemAgentFlow.say state341 = agent.new say();
									StringCreator string342 = new StringCreator();
									string342.append("could you perhaps wait a second");
									state341.setText(string342.toString());
									if (!flowThread.callState(state341, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 480, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching337 = true;
								if (rand338 >= 2 && rand338 < 3) {
									chosen336 = true;
									iristk.situated.SystemAgentFlow.say state343 = agent.new say();
									StringCreator string344 = new StringCreator();
									string344.append("please wait a second");
									state343.setText(string344.toString());
									if (!flowThread.callState(state343, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 480, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching337 = true;
								if (rand338 >= 3 && rand338 < 4) {
									chosen336 = true;
									iristk.situated.SystemAgentFlow.say state345 = agent.new say();
									StringCreator string346 = new StringCreator();
									string346.append("give me a second, please");
									state345.setText(string346.toString());
									if (!flowThread.callState(state345, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 480, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching337 = true;
								if (rand338 >= 4 && rand338 < 5) {
									chosen336 = true;
									iristk.situated.SystemAgentFlow.say state347 = agent.new say();
									StringCreator string348 = new StringCreator();
									string348.append("I'll be right back with you");
									state347.setText(string348.toString());
									if (!flowThread.callState(state347, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 480, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						iristk.situated.SystemAgentFlow.attendOther state349 = agent.new attendOther();
						state349.setMode("eyes");
						if (!flowThread.callState(state349, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 475, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 488
						boolean chosen350 = false;
						boolean matching351 = true;
						while (!chosen350 && matching351) {
							int rand352 = random(317983781, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching351 = false;
							if (true) {
								matching351 = true;
								if (rand352 >= 0 && rand352 < 1) {
									chosen350 = true;
									iristk.situated.SystemAgentFlow.say state353 = agent.new say();
									StringCreator string354 = new StringCreator();
									string354.append("sorry");
									// Line: 488
									string354.append(system.getCurrentUser().getString("name", ""));
									string354.append(", where");
									StringCreator string355 = new StringCreator();
									string355.append("<emphasis level=\"strong\">");
									string355.append("were");
									string355.append("</emphasis>");
									string354.append(string355.toString());
									string354.append("we");
									state353.setText(string354.toString());
									if (!flowThread.callState(state353, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 488, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching351 = true;
								if (rand352 >= 1 && rand352 < 2) {
									chosen350 = true;
									iristk.situated.SystemAgentFlow.say state356 = agent.new say();
									StringCreator string357 = new StringCreator();
									string357.append("alright");
									// Line: 488
									string357.append(system.getCurrentUser().getString("name", ""));
									state356.setText(string357.toString());
									if (!flowThread.callState(state356, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 488, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching351 = true;
								if (rand352 >= 2 && rand352 < 3) {
									chosen350 = true;
									iristk.situated.SystemAgentFlow.say state358 = agent.new say();
									StringCreator string359 = new StringCreator();
									string359.append("so, let's see");
									// Line: 488
									string359.append(system.getCurrentUser().getString("name", ""));
									state358.setText(string359.toString());
									if (!flowThread.callState(state358, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 488, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 493
						Event raiseEvent360 = new Event("reprompt");
						if (flowThread.raiseEvent(raiseEvent360, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 493, 29))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 475, 41));
			}
			// Line: 496
			try {
				count = getCount(1555845260) + 1;
				if (event.triggers("reprompt")) {
					incrCount(1555845260);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 497
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 497, 14)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 496, 28));
			}
			// Line: 500
			try {
				count = getCount(104739310) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(104739310);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 501
						NextTopic state361 = new NextTopic();
						flowThread.gotoState(state361, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 501, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 500, 38));
			}
			// Line: 504
			try {
				count = getCount(1451043227) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1451043227);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 505
							if (system.isAttendingAll()) {
								iristk.situated.SystemAgentFlow.attend state362 = agent.new attend();
								state362.setTarget(event.get("user"));
								if (!flowThread.callState(state362, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 505, 39)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
							// Line: 508
							Event sendEvent363 = new Event("action.gesture");
							sendEvent363.putIfNotNull("name", "smile");
							flowRunner.sendEvent(sendEvent363, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 508, 51)));
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 504, 102));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class NextTopic extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 513
			try {
				EXECUTION: {
					int count = getCount(1464642111) + 1;
					incrCount(1464642111);
					// Line: 515
					if (!system.hasUsers()) {
						// Line: 516
						Idle state364 = new Idle();
						flowThread.gotoState(state364, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 516, 25)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 518
					if (!system.getCurrentUser().has("introduced")) {
						// Line: 519
						Introduce state365 = new Introduce();
						flowThread.gotoState(state365, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 519, 31)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 520
					} else if (system.hasManyUsers() && !system.getOtherUser().has("introduced")) {
						iristk.situated.SystemAgentFlow.attendOther state366 = agent.new attendOther();
						if (!flowThread.callState(state366, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 518, 58)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 522
						Introduce state367 = new Introduce();
						state367.setSwitchUser(true);
						flowThread.gotoState(state367, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 522, 51)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 523
					} else if (system.hasManyUsers() && !system.getCurrentUser().has("introducedTo:" + system.getOtherUser().id)) {
						// Line: 524
						IntroduceUsers state368 = new IntroduceUsers();
						flowThread.gotoState(state368, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 524, 36)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 526
					} else if (quizAvailable && !system.getCurrentUser().has("notInterestedInQuiz") && !system.getCurrentUser().has("played") || (system.hasManyUsers() && (!system.getOtherUser().has("played") || !system.getOtherUser().has("notInterestedInQuiz")))) {
						// Line: 527
						ReqQuiz state369 = new ReqQuiz();
						flowThread.gotoState(state369, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 527, 29)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 528
					} else if (!system.getCurrentUser().has("wherefrom") || (system.hasManyUsers() && !system.getOtherUser().has("wherefrom"))) {
						// Line: 529
						DiscussTopic state370 = new DiscussTopic();
						StringCreator string371 = new StringCreator();
						string371.append("Where do you come from?");
						StringCreator string372 = new StringCreator();
						string372.append("I come from Stockholm in Sweden.");
						StringCreator string373 = new StringCreator();
						string373.append("I am sure that is a very nice place.");
						state370.setQuestion(string371.toString());
						state370.setTopic("wherefrom");
						state370.setReply(java.util.Arrays.asList(string372.toString(), string373.toString()));
						flowThread.gotoState(state370, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 529, 54)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 534
					} else if (!system.getCurrentUser().has("beenhere") || (system.hasManyUsers() && !system.getOtherUser().has("beenhere"))) {
						// Line: 535
						DiscussTopic state374 = new DiscussTopic();
						StringCreator string375 = new StringCreator();
						string375.append("Have you ever been here before?");
						StringCreator string376 = new StringCreator();
						string376.append("I really like this place, it is full of interesting people");
						StringCreator string377 = new StringCreator();
						string377.append("I have been in London and Los Angeles. But since I don't have a body, I don't get to travel a lot");
						state374.setQuestion(string375.toString());
						state374.setTopic("beenhere");
						state374.setReply(java.util.Arrays.asList(string376.toString(), string377.toString()));
						flowThread.gotoState(state374, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 535, 53)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 540
					} else if (!system.getCurrentUser().has("robots") || (system.hasManyUsers() && !system.getOtherUser().has("robots"))) {
						// Line: 541
						DiscussTopic state378 = new DiscussTopic();
						StringCreator string379 = new StringCreator();
						string379.append("Have you ever talked to a robot before?");
						StringCreator string380 = new StringCreator();
						string380.append("Is this the first time you talk to a robot?");
						StringCreator string381 = new StringCreator();
						string381.append("Interesting experience, I guess. I like talking to humans.");
						StringCreator string382 = new StringCreator();
						string382.append("Maybe you will have a robot friend in the future, that you can talk to all day long.");
						state378.setQuestion(java.util.Arrays.asList(string379.toString(), string380.toString()));
						state378.setTopic("robots");
						state378.setReply(java.util.Arrays.asList(string381.toString(), string382.toString()));
						flowThread.gotoState(state378, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 541, 51)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 547
					} else if (!system.getCurrentUser().has("future") || (system.hasManyUsers() && !system.getOtherUser().has("future"))) {
						// Line: 548
						DiscussTopic state383 = new DiscussTopic();
						StringCreator string384 = new StringCreator();
						StringCreator string385 = new StringCreator();
						string385.append("<usel variant=\"1\">");
						string385.append("Do you look forward to a future full of robots?");
						string385.append("</usel>");
						string384.append(string385.toString());
						StringCreator string386 = new StringCreator();
						string386.append("If you had a robot at home, what would you like it to do for you?");
						StringCreator string387 = new StringCreator();
						StringCreator string388 = new StringCreator();
						string388.append("<usel variant=\"1\">");
						string388.append("How do you think a robot could help you in the future?");
						string388.append("</usel>");
						string387.append(string388.toString());
						StringCreator string389 = new StringCreator();
						string389.append("Do you look forward to a future full of robots?");
						StringCreator string390 = new StringCreator();
						StringCreator string391 = new StringCreator();
						string391.append("<usel variant=\"1\">");
						string391.append("I think I could be of great assistance.");
						string391.append("</usel>");
						string390.append(string391.toString());
						StringCreator string392 = new StringCreator();
						string392.append("<usel variant=\"1\">");
						string392.append("For example, I could talk to people who are lonely.");
						string392.append("</usel>");
						string390.append(string392.toString());
						StringCreator string393 = new StringCreator();
						StringCreator string394 = new StringCreator();
						string394.append("<usel variant=\"2\">");
						string394.append("You could think of me as a social machine.");
						string394.append("</usel>");
						string393.append(string394.toString());
						string393.append("If I had arms and a slightly more advanced brain, I could also do the dishes for you.");
						state383.setQuestion(java.util.Arrays.asList(string384.toString(), string386.toString(), string387.toString(), string389.toString()));
						state383.setTopic("future");
						state383.setReply(java.util.Arrays.asList(string390.toString(), string393.toString()));
						flowThread.gotoState(state383, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 548, 51)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 556
					} else if (!system.getCurrentUser().has("job") || (system.hasManyUsers() && !system.getOtherUser().has("job"))) {
						// Line: 557
						DiscussTopic state395 = new DiscussTopic();
						StringCreator string396 = new StringCreator();
						string396.append("What do you do for a living?");
						StringCreator string397 = new StringCreator();
						StringCreator string398 = new StringCreator();
						string398.append("<usel variant=\"4\">");
						string398.append("How do you spend your days?");
						string398.append("</usel>");
						string397.append(string398.toString());
						StringCreator string399 = new StringCreator();
						string399.append("My job is to talk to humans. I really enjoy it.");
						StringCreator string400 = new StringCreator();
						StringCreator string401 = new StringCreator();
						string401.append("<usel variant=\"2\">");
						string401.append("I work as a conversational machine.");
						string401.append("</usel>");
						string400.append(string401.toString());
						StringCreator string402 = new StringCreator();
						string402.append("<usel variant=\"5\">");
						string402.append("Kind of interesting.");
						string402.append("</usel>");
						string400.append(string402.toString());
						state395.setQuestion(java.util.Arrays.asList(string396.toString(), string397.toString()));
						state395.setTopic("job");
						state395.setReply(java.util.Arrays.asList(string399.toString(), string400.toString()));
						flowThread.gotoState(state395, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 557, 48)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 563
					} else {
						// Line: 564
						ReqQuestion state403 = new ReqQuestion();
						flowThread.gotoState(state403, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 564, 33)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 513, 12));
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


	private class ReqQuestion extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 570
			try {
				EXECUTION: {
					int count = getCount(88579647) + 1;
					incrCount(88579647);
					// Line: 571
					if (system.hasManyUsers()) {
						iristk.situated.SystemAgentFlow.attendOther state404 = agent.new attendOther();
						if (!flowThread.callState(state404, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 571, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 574
					boolean chosen405 = false;
					boolean matching406 = true;
					while (!chosen405 && matching406) {
						int rand407 = random(1712536284, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching406 = false;
						if (true) {
							matching406 = true;
							if (rand407 >= 0 && rand407 < 1) {
								chosen405 = true;
								iristk.situated.SystemAgentFlow.say state408 = agent.new say();
								StringCreator string409 = new StringCreator();
								string409.append("Maybe you have a question for me?");
								state408.setText(string409.toString());
								if (!flowThread.callState(state408, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 574, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching406 = true;
							if (rand407 >= 1 && rand407 < 2) {
								chosen405 = true;
								iristk.situated.SystemAgentFlow.say state410 = agent.new say();
								StringCreator string411 = new StringCreator();
								string411.append("Do you want to ask me something?");
								state410.setText(string411.toString());
								if (!flowThread.callState(state410, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 574, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching406 = true;
							if (rand407 >= 2 && rand407 < 3) {
								chosen405 = true;
								iristk.situated.SystemAgentFlow.say state412 = agent.new say();
								StringCreator string413 = new StringCreator();
								string413.append("Why don't you ask me to");
								StringCreator string414 = new StringCreator();
								string414.append("<usel variant=\"1\">");
								string414.append("tell you a joke");
								string414.append("</usel>");
								string413.append(string414.toString());
								state412.setText(string413.toString());
								if (!flowThread.callState(state412, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 574, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (quizAvailable) {
							matching406 = true;
							if (rand407 >= 3 && rand407 < 4) {
								chosen405 = true;
								// Line: 578
								ReqQuiz state415 = new ReqQuiz();
								flowThread.gotoState(state415, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 578, 58)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
					}
					iristk.situated.SystemAgentFlow.listen state416 = agent.new listen();
					if (!flowThread.callState(state416, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 570, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 570, 12));
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


	private class ReqQuiz extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 585
			try {
				EXECUTION: {
					int count = getCount(1528637575) + 1;
					incrCount(1528637575);
					iristk.situated.SystemAgentFlow.say state417 = agent.new say();
					StringCreator string418 = new StringCreator();
					string418.append("Do you want to play a game?");
					state417.setText(string418.toString());
					if (!flowThread.callState(state417, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 585, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.listen state419 = agent.new listen();
					if (!flowThread.callState(state419, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 585, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 585, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 589
			try {
				count = getCount(1190524793) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(1190524793);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 590
							PlayQuiz state420 = new PlayQuiz();
							flowThread.gotoState(state420, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 590, 29)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 589, 58));
			}
			// Line: 592
			try {
				count = getCount(26117480) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(26117480);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 593
							system.getCurrentUser().put("notInterestedInQuiz", true);
							// Line: 594
							NextTopic state421 = new NextTopic();
							flowThread.gotoState(state421, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 594, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 592, 57));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Greeting extends Dialog {

		final State currentState = this;
		public boolean switchUser = (boolean) false;

		public void setSwitchUser(Object value) {
			if (value != null) {
				switchUser = (boolean) value;
				params.put("switchUser", value);
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
			// Line: 600
			try {
				EXECUTION: {
					int count = getCount(1330278544) + 1;
					incrCount(1330278544);
					// Line: 601
					if (switchUser) {
						iristk.situated.SystemAgentFlow.say state422 = agent.new say();
						StringCreator string423 = new StringCreator();
						string423.append("I don't think we have said hello to each other");
						state422.setText(string423.toString());
						if (!flowThread.callState(state422, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 601, 26)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 603
					} else {
						iristk.situated.SystemAgentFlow.say state424 = agent.new say();
						StringCreator string425 = new StringCreator();
						string425.append("Hi there");
						state424.setText(string425.toString());
						if (!flowThread.callState(state424, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 601, 26)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 606
					system.getCurrentUser().put("greeting", true);
					iristk.situated.SystemAgentFlow.listen state426 = agent.new listen();
					if (!flowThread.callState(state426, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 600, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 600, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 609
			try {
				count = getCount(762152757) + 1;
				if (event.triggers("continue")) {
					incrCount(762152757);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 610
						NextTopic state427 = new NextTopic();
						flowThread.gotoState(state427, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 610, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 609, 28));
			}
			// Line: 612
			try {
				count = getCount(314337396) + 1;
				if (event.triggers("nomatch")) {
					incrCount(314337396);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state428 = agent.new say();
						StringCreator string429 = new StringCreator();
						string429.append("Nice to meet you");
						state428.setText(string429.toString());
						if (!flowThread.callState(state428, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 612, 27)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 614
						NextTopic state430 = new NextTopic();
						flowThread.gotoState(state430, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 614, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 612, 27));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Introduce extends Dialog {

		final State currentState = this;
		public boolean switchUser = (boolean) false;

		public void setSwitchUser(Object value) {
			if (value != null) {
				switchUser = (boolean) value;
				params.put("switchUser", value);
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
			// Line: 620
			try {
				EXECUTION: {
					int count = getCount(1870252780) + 1;
					incrCount(1870252780);
					// Line: 621
					if (switchUser) {
						iristk.situated.SystemAgentFlow.say state431 = agent.new say();
						StringCreator string432 = new StringCreator();
						string432.append("And what is your name?");
						state431.setText(string432.toString());
						if (!flowThread.callState(state431, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 621, 26)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 623
					} else {
						iristk.situated.SystemAgentFlow.say state433 = agent.new say();
						StringCreator string434 = new StringCreator();
						string434.append("My name is Fur hat. What is your name?");
						state433.setText(string434.toString());
						if (!flowThread.callState(state433, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 621, 26)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 626
					system.getCurrentUser().put("introduced", true);
					iristk.situated.SystemAgentFlow.listen state435 = agent.new listen();
					state435.setContext("name");
					state435.setNbest(10);
					if (!flowThread.callState(state435, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 620, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 620, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 629
			try {
				count = getCount(100555887) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:name")) {
						incrCount(100555887);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 630
							ConfirmName state436 = new ConfirmName();
							state436.setNamehyp(new Nbest(asList(event.get("nbest")), "sem:name"));
							flowThread.gotoState(state436, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 630, 86)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 629, 59));
			}
			// Line: 632
			try {
				count = getCount(1983747920) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_name")) {
						incrCount(1983747920);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 633
							NextTopic state437 = new NextTopic();
							flowThread.gotoState(state437, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 633, 30)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 632, 63));
			}
			// Line: 635
			try {
				count = getCount(736709391) + 1;
				if (event.triggers("continue")) {
					incrCount(736709391);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 636
						NextTopic state438 = new NextTopic();
						flowThread.gotoState(state438, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 636, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 635, 28));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class ConfirmName extends Introduce {

		final State currentState = this;
		public Nbest namehyp = null;
		public int trial = asInteger(0);

		public void setNamehyp(Object value) {
			if (value != null) {
				namehyp = (Nbest) value;
				params.put("namehyp", value);
			}
		}

		public void setTrial(Object value) {
			if (value != null) {
				trial = asInteger(value);
				params.put("trial", value);
			}
		}

		public String name;

		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 645
			try {
				EXECUTION: {
					int count = getCount(1205044462) + 1;
					incrCount(1205044462);
					// Line: 646
					if (2 > trial) {
						// Line: 647
						name = namehyp.getBest();
						// Line: 648
						boolean chosen439 = false;
						boolean matching440 = true;
						while (!chosen439 && matching440) {
							int rand441 = random(959447386, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching440 = false;
							if (true) {
								matching440 = true;
								if (rand441 >= 0 && rand441 < 1) {
									chosen439 = true;
									iristk.situated.SystemAgentFlow.say state442 = agent.new say();
									StringCreator string443 = new StringCreator();
									string443.append("Did you say");
									// Line: 648
									string443.append(name);
									state442.setText(string443.toString());
									if (!flowThread.callState(state442, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 648, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						iristk.situated.SystemAgentFlow.listen state444 = agent.new listen();
						state444.setContext("name default");
						state444.setNbest(10);
						state444.setTimeout(2000);
						if (!flowThread.callState(state444, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 646, 25)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 652
					} else {
						iristk.situated.SystemAgentFlow.say state445 = agent.new say();
						StringCreator string446 = new StringCreator();
						StringCreator string447 = new StringCreator();
						string447.append("<spurt audio=\"script1/mhm_dis_2\">");
						string447.append("mhm");
						string447.append("</spurt>");
						string446.append(string447.toString());
						state445.setText(string446.toString());
						if (!flowThread.callState(state445, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 646, 25)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 654
						Event raiseEvent448 = new Event("nomatch");
						if (flowThread.raiseEvent(raiseEvent448, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 654, 30))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 645, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 658
			try {
				count = getCount(33524623) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:name")) {
						incrCount(33524623);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 659
							ConfirmName state449 = new ConfirmName();
							state449.setNamehyp(namehyp.merge(asList(event.get("nbest")), name));
							state449.setTrial(trial + 1);
							flowThread.gotoState(state449, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 659, 104)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 658, 59));
			}
			// Line: 662
			try {
				count = getCount(575335780) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(575335780);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 663
							system.getCurrentUser().put("name", name);
							// Line: 664
							Event raiseEvent450 = new Event("nomatch");
							if (flowThread.raiseEvent(raiseEvent450, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 664, 29))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 662, 58));
			}
			// Line: 667
			try {
				count = getCount(717356484) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(717356484);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 668
							boolean chosen451 = false;
							boolean matching452 = true;
							while (!chosen451 && matching452) {
								int rand453 = random(1595212853, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching452 = false;
								if (true) {
									matching452 = true;
									if (rand453 >= 0 && rand453 < 1) {
										chosen451 = true;
										iristk.situated.SystemAgentFlow.say state454 = agent.new say();
										StringCreator string455 = new StringCreator();
										StringCreator string456 = new StringCreator();
										string456.append("<spurt audio=\"script1/ah_dis_3\">");
										string456.append("ah");
										string456.append("</spurt>");
										string455.append(string456.toString());
										state454.setText(string455.toString());
										if (!flowThread.callState(state454, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 668, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching452 = true;
									if (rand453 >= 1 && rand453 < 2) {
										chosen451 = true;
										iristk.situated.SystemAgentFlow.say state457 = agent.new say();
										StringCreator string458 = new StringCreator();
										StringCreator string459 = new StringCreator();
										string459.append("<spurt audio=\"script1/mhm_dis_2\">");
										string459.append("mhm");
										string459.append("</spurt>");
										string458.append(string459.toString());
										state457.setText(string458.toString());
										if (!flowThread.callState(state457, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 668, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 672
							Event raiseEvent460 = new Event("nomatch");
							if (flowThread.raiseEvent(raiseEvent460, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 672, 29))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 667, 57));
			}
			// Line: 675
			try {
				count = getCount(1355531311) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1355531311);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 676
						Event raiseEvent461 = new Event("nomatch");
						if (flowThread.raiseEvent(raiseEvent461, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 676, 29))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 675, 38));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class IntroduceUsers extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 681
			try {
				EXECUTION: {
					int count = getCount(20671747) + 1;
					incrCount(20671747);
					iristk.situated.SystemAgentFlow.attendAll state462 = agent.new attendAll();
					if (!flowThread.callState(state462, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 681, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 683
					boolean chosen463 = false;
					boolean matching464 = true;
					while (!chosen463 && matching464) {
						int rand465 = random(257895351, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching464 = false;
						if (true) {
							matching464 = true;
							if (rand465 >= 0 && rand465 < 1) {
								chosen463 = true;
								iristk.situated.SystemAgentFlow.say state466 = agent.new say();
								StringCreator string467 = new StringCreator();
								string467.append("So, do you two know each other?");
								state466.setText(string467.toString());
								if (!flowThread.callState(state466, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 683, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching464 = true;
							if (rand465 >= 1 && rand465 < 2) {
								chosen463 = true;
								iristk.situated.SystemAgentFlow.say state468 = agent.new say();
								StringCreator string469 = new StringCreator();
								string469.append("So, have you two met before?");
								state468.setText(string469.toString());
								if (!flowThread.callState(state468, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 683, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					iristk.situated.SystemAgentFlow.listen state470 = agent.new listen();
					if (!flowThread.callState(state470, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 681, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 681, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 690
			try {
				count = getCount(1929600551) + 1;
				if (event.triggers("nomatch")) {
					incrCount(1929600551);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 691
						if (system.hasManyUsers()) {
							system.getCurrentUser().put("introducedTo:" + system.getOtherUser().id, true);
							system.getOtherUser().put("introducedTo:" + system.getCurrentUser().id, true);
						};
						iristk.situated.SystemAgentFlow.say state471 = agent.new say();
						StringCreator string472 = new StringCreator();
						string472.append("That's cool");
						state471.setText(string472.toString());
						if (!flowThread.callState(state471, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 690, 27)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 698
						NextTopic state473 = new NextTopic();
						flowThread.gotoState(state473, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 698, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 690, 27));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class DiscussTopic extends Dialog {

		final State currentState = this;
		public String topic = null;
		public List question = null;
		public List reply = null;

		public void setTopic(Object value) {
			if (value != null) {
				topic = asString(value);
				params.put("topic", value);
			}
		}

		public void setQuestion(Object value) {
			if (value != null) {
				question = asList(value);
				params.put("question", value);
			}
		}

		public void setReply(Object value) {
			if (value != null) {
				reply = asList(value);
				params.put("reply", value);
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
			// Line: 707
			try {
				EXECUTION: {
					int count = getCount(627150481) + 1;
					incrCount(627150481);
					// Line: 708
					boolean chosen474 = false;
					boolean matching475 = true;
					while (!chosen474 && matching475) {
						int rand476 = random(128526626, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching475 = false;
						if (!system.getCurrentUser().has(topic)) {
							matching475 = true;
							if (rand476 >= 0 && rand476 < 1) {
								chosen474 = true;
								// Line: 710
								boolean chosen477 = false;
								boolean matching478 = true;
								while (!chosen477 && matching478) {
									int rand479 = random(754666084, 10, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching478 = false;
									if (true) {
										matching478 = true;
										if (rand479 >= 0 && rand479 < 1) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state480 = agent.new say();
											StringCreator string481 = new StringCreator();
											StringCreator string482 = new StringCreator();
											string482.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_05\">");
											string482.append("ehm");
											string482.append("</spurt>");
											string481.append(string482.toString());
											state480.setText(string481.toString());
											if (!flowThread.callState(state480, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 1 && rand479 < 2) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state483 = agent.new say();
											StringCreator string484 = new StringCreator();
											StringCreator string485 = new StringCreator();
											string485.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_06\">");
											string485.append("ehm");
											string485.append("</spurt>");
											string484.append(string485.toString());
											state483.setText(string484.toString());
											if (!flowThread.callState(state483, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 2 && rand479 < 3) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state486 = agent.new say();
											StringCreator string487 = new StringCreator();
											StringCreator string488 = new StringCreator();
											string488.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_04\">");
											string488.append("ehm");
											string488.append("</spurt>");
											string487.append(string488.toString());
											state486.setText(string487.toString());
											if (!flowThread.callState(state486, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 3 && rand479 < 4) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state489 = agent.new say();
											StringCreator string490 = new StringCreator();
											StringCreator string491 = new StringCreator();
											string491.append("<spurt audio=\"free_dialogue/ehm_taketurn_01\">");
											string491.append("ehm");
											string491.append("</spurt>");
											string490.append(string491.toString());
											state489.setText(string490.toString());
											if (!flowThread.callState(state489, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 4 && rand479 < 5) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state492 = agent.new say();
											StringCreator string493 = new StringCreator();
											StringCreator string494 = new StringCreator();
											string494.append("<spurt audio=\"free_dialogue/ehm_taketurn_02\">");
											string494.append("ehm");
											string494.append("</spurt>");
											string493.append(string494.toString());
											state492.setText(string493.toString());
											if (!flowThread.callState(state492, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 5 && rand479 < 6) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state495 = agent.new say();
											StringCreator string496 = new StringCreator();
											StringCreator string497 = new StringCreator();
											string497.append("<spurt audio=\"free_dialogue/ehm_hesitation_01\">");
											string497.append("ehm");
											string497.append("</spurt>");
											string496.append(string497.toString());
											state495.setText(string496.toString());
											if (!flowThread.callState(state495, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 6 && rand479 < 7) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state498 = agent.new say();
											StringCreator string499 = new StringCreator();
											StringCreator string500 = new StringCreator();
											string500.append("<spurt audio=\"free_dialogue/ehm_hesitation_02\">");
											string500.append("ehm");
											string500.append("</spurt>");
											string499.append(string500.toString());
											state498.setText(string499.toString());
											if (!flowThread.callState(state498, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 7 && rand479 < 8) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state501 = agent.new say();
											StringCreator string502 = new StringCreator();
											StringCreator string503 = new StringCreator();
											string503.append("<spurt audio=\"free_dialogue/ehm_hesitation_03\">");
											string503.append("ehm");
											string503.append("</spurt>");
											string502.append(string503.toString());
											state501.setText(string502.toString());
											if (!flowThread.callState(state501, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 8 && rand479 < 9) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state504 = agent.new say();
											StringCreator string505 = new StringCreator();
											StringCreator string506 = new StringCreator();
											string506.append("<spurt audio=\"free_dialogue/ehm_hesitation_05\">");
											string506.append("ehm");
											string506.append("</spurt>");
											string505.append(string506.toString());
											state504.setText(string505.toString());
											if (!flowThread.callState(state504, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching478 = true;
										if (rand479 >= 9 && rand479 < 10) {
											chosen477 = true;
											iristk.situated.SystemAgentFlow.say state507 = agent.new say();
											StringCreator string508 = new StringCreator();
											StringCreator string509 = new StringCreator();
											string509.append("<spurt audio=\"free_dialogue/ehm_hesitation_06\">");
											string509.append("ehm");
											string509.append("</spurt>");
											string508.append(string509.toString());
											state507.setText(string508.toString());
											if (!flowThread.callState(state507, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 710, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
							}
						}
						if (system.hasManyUsers() && !system.getOtherUser().has(topic)) {
							matching475 = true;
							if (rand476 >= 1 && rand476 < 2) {
								chosen474 = true;
								iristk.situated.SystemAgentFlow.attendOther state510 = agent.new attendOther();
								if (!flowThread.callState(state510, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 708, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 725
								boolean chosen511 = false;
								boolean matching512 = true;
								while (!chosen511 && matching512) {
									int rand513 = random(1265210847, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
									matching512 = false;
									if (true) {
										matching512 = true;
										if (rand513 >= 0 && rand513 < 1) {
											chosen511 = true;
											iristk.situated.SystemAgentFlow.say state514 = agent.new say();
											StringCreator string515 = new StringCreator();
											string515.append("How about you");
											// Line: 725
											string515.append(system.getCurrentUser().getString("name", ""));
											state514.setText(string515.toString());
											if (!flowThread.callState(state514, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 725, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
									if (true) {
										matching512 = true;
										if (rand513 >= 1 && rand513 < 2) {
											chosen511 = true;
											iristk.situated.SystemAgentFlow.say state516 = agent.new say();
											StringCreator string517 = new StringCreator();
											string517.append("And you");
											// Line: 725
											string517.append(system.getCurrentUser().getString("name", ""));
											state516.setText(string517.toString());
											if (!flowThread.callState(state516, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 725, 14)))) {
												eventResult = EVENT_ABORTED;
												break EXECUTION;
											}
										}
									}
								}
							}
						}
						if (system.hasManyUsers() && !system.getOtherUser().has(topic) && !system.getCurrentUser().has(topic)) {
							matching475 = true;
							if (rand476 >= 2 && rand476 < 3) {
								chosen474 = true;
								iristk.situated.SystemAgentFlow.attendAll state518 = agent.new attendAll();
								if (!flowThread.callState(state518, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 708, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					// Line: 734
					Event raiseEvent519 = new Event("reprompt");
					if (flowThread.raiseEvent(raiseEvent519, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 734, 29))) == State.EVENT_ABORTED) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 707, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 737
			try {
				count = getCount(1711574013) + 1;
				if (event.triggers("reprompt")) {
					incrCount(1711574013);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state520 = agent.new say();
						StringCreator string521 = new StringCreator();
						// Line: 737
						string521.append(randstr(question));
						state520.setText(string521.toString());
						if (!flowThread.callState(state520, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 737, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state522 = agent.new listen();
						if (!flowThread.callState(state522, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 737, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 737, 28));
			}
			// Line: 742
			try {
				count = getCount(1631862159) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(1631862159);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state523 = agent.new say();
							StringCreator string524 = new StringCreator();
							string524.append("GESTURE_YEAH_QUESTION");
							state523.setText(string524.toString());
							if (!flowThread.callState(state523, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 742, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 744
							Event raiseEvent525 = new Event("nomatch");
							if (flowThread.raiseEvent(raiseEvent525, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 744, 29))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 742, 58));
			}
			// Line: 747
			try {
				count = getCount(591137559) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no") || event.has("sem:user_unwilling")) {
						incrCount(591137559);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 748
							boolean chosen526 = false;
							boolean matching527 = true;
							while (!chosen526 && matching527) {
								int rand528 = random(1674896058, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching527 = false;
								if (true) {
									matching527 = true;
									if (rand528 >= 0 && rand528 < 1) {
										chosen526 = true;
										iristk.situated.SystemAgentFlow.say state529 = agent.new say();
										StringCreator string530 = new StringCreator();
										StringCreator string531 = new StringCreator();
										string531.append("<spurt audio=\"script1/ah_dis_3\">");
										string531.append("ah");
										string531.append("</spurt>");
										string530.append(string531.toString());
										state529.setText(string530.toString());
										if (!flowThread.callState(state529, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 748, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching527 = true;
									if (rand528 >= 1 && rand528 < 2) {
										chosen526 = true;
										iristk.situated.SystemAgentFlow.say state532 = agent.new say();
										StringCreator string533 = new StringCreator();
										StringCreator string534 = new StringCreator();
										string534.append("<spurt audio=\"script1/mhm_dis_2\">");
										string534.append("mhm");
										string534.append("</spurt>");
										string533.append(string534.toString());
										state532.setText(string533.toString());
										if (!flowThread.callState(state532, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 748, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 752
							Event raiseEvent535 = new Event("nomatch");
							if (flowThread.raiseEvent(raiseEvent535, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 752, 29))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 747, 86));
			}
			// Line: 755
			try {
				count = getCount(866191240) + 1;
				if (event.triggers("continue")) {
					incrCount(866191240);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 756
						system.getCurrentUser().put(topic, true);
						// Line: 757
						CheckFollowUp state536 = new CheckFollowUp();
						flowThread.gotoState(state536, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 757, 34)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 755, 28));
			}
			// Line: 760
			try {
				count = getCount(1207769059) + 1;
				if (event.triggers("nomatch")) {
					incrCount(1207769059);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 761
						system.getCurrentUser().put(topic, true);
						iristk.situated.SystemAgentFlow.say state537 = agent.new say();
						StringCreator string538 = new StringCreator();
						// Line: 761
						string538.append(randstr(reply));
						state537.setText(string538.toString());
						if (!flowThread.callState(state537, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 760, 27)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 763
						CheckFollowUp state539 = new CheckFollowUp();
						flowThread.gotoState(state539, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 763, 34)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 760, 27));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class CheckFollowUp extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 768
			try {
				EXECUTION: {
					int count = getCount(451111351) + 1;
					incrCount(451111351);
					iristk.situated.SystemAgentFlow.listen state540 = agent.new listen();
					state540.setTimeout(1000);
					if (!flowThread.callState(state540, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 768, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 768, 12));
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


	private class Asleep extends State {

		final State currentState = this;

		public Timer timer = (Timer) new Timer();

		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 775
			try {
				EXECUTION: {
					int count = getCount(729864207) + 1;
					incrCount(729864207);
					iristk.situated.SystemAgentFlow.fallAsleep state541 = agent.new fallAsleep();
					if (!flowThread.callState(state541, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 775, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state542 = agent.new say();
					StringCreator string543 = new StringCreator();
					string543.append("GESTURE_SNORE");
					state542.setText(string543.toString());
					if (!flowThread.callState(state542, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 775, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 778
					timer.reset();
					iristk.situated.SystemAgentFlow.listen state544 = agent.new listen();
					if (!flowThread.callState(state544, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 775, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 775, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 782
			try {
				count = getCount(787387795) + 1;
				if (event.triggers("sense.user.speak**")) {
					if (event.has("sem:req_wakeup")) {
						incrCount(787387795);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state545 = agent.new say();
							StringCreator string546 = new StringCreator();
							string546.append("GESTURE_CLEAR_THROAT");
							state545.setText(string546.toString());
							if (!flowThread.callState(state545, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 782, 67)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 784
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state547 = agent.new attendRandom();
								if (!flowThread.callState(state547, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 784, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 786
								NextTopic state548 = new NextTopic();
								flowThread.gotoState(state548, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 786, 31)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 788
							Idle state549 = new Idle();
							flowThread.gotoState(state549, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 788, 25)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 782, 67));
			}
			// Line: 791
			try {
				count = getCount(679890578) + 1;
				if (event.triggers("sense.user.speak** sense.user.silence")) {
					incrCount(679890578);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 792
						if (timer.passed(10000)) {
							// Line: 793
							boolean chosen550 = false;
							boolean matching551 = true;
							while (!chosen550 && matching551) {
								int rand552 = random(1792393294, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching551 = false;
								if (true) {
									matching551 = true;
									if (rand552 >= 0 && rand552 < 1) {
										chosen550 = true;
										iristk.situated.SystemAgentFlow.say state553 = agent.new say();
										StringCreator string554 = new StringCreator();
										string554.append("GESTURE_SNORE");
										state553.setText(string554.toString());
										if (!flowThread.callState(state553, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 793, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching551 = true;
									if (rand552 >= 1 && rand552 < 2) {
										chosen550 = true;
										iristk.situated.SystemAgentFlow.say state555 = agent.new say();
										StringCreator string556 = new StringCreator();
										string556.append("GESTURE_SNORE_PHEW");
										state555.setText(string556.toString());
										if (!flowThread.callState(state555, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 793, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching551 = true;
									if (rand552 >= 2 && rand552 < 3) {
										chosen550 = true;
										iristk.situated.SystemAgentFlow.say state557 = agent.new say();
										StringCreator string558 = new StringCreator();
										string558.append("GESTURE_YAWN_1");
										state557.setText(string558.toString());
										if (!flowThread.callState(state557, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 793, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching551 = true;
									if (rand552 >= 3 && rand552 < 4) {
										chosen550 = true;
										iristk.situated.SystemAgentFlow.say state559 = agent.new say();
										StringCreator string560 = new StringCreator();
										string560.append("GESTURE_YAWN_2");
										state559.setText(string560.toString());
										if (!flowThread.callState(state559, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 793, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching551 = true;
									if (rand552 >= 4 && rand552 < 5) {
										chosen550 = true;
										iristk.situated.SystemAgentFlow.say state561 = agent.new say();
										StringCreator string562 = new StringCreator();
										StringCreator string563 = new StringCreator();
										string563.append("<voice emotion=\"calm\">");
										string563.append("isn't that an electric sheep");
										string563.append("</voice>");
										string562.append(string563.toString());
										StringCreator string564 = new StringCreator();
										string564.append("<spurt audio=\"g0001_019\">");
										string564.append("GIGGLE");
										string564.append("</spurt>");
										string562.append(string564.toString());
										state561.setText(string562.toString());
										if (!flowThread.callState(state561, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 793, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 803
							timer.reset();
						}
						iristk.situated.SystemAgentFlow.listen state565 = agent.new listen();
						if (!flowThread.callState(state565, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 791, 57)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\social\\src\\iristk\\app\\social\\SocialFlow.xml"), 791, 57));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
