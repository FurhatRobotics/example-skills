package iristk.app.Houndify;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;
import iristk.app.Houndify.HoundifySkill.HoundifyFlowClient;

public class HoundifyFlow extends iristk.flow.Flow {

	private iristk.situated.SystemAgentFlow agent;
	private HoundifyFlowClient houndify;
	private iristk.situated.SystemAgent system;
	private String query;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String value) {
		this.query = value;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	public HoundifyFlowClient getHoundify() {
		return this.houndify;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("query")) return this.query;
		if (name.equals("agent")) return this.agent;
		if (name.equals("houndify")) return this.houndify;
		return null;
	}


	public HoundifyFlow(iristk.situated.SystemAgentFlow agent, HoundifyFlowClient houndify) {
		this.agent = agent;
		this.houndify = houndify;
		initVariables();
	}

	@Override
	public State getInitialState() {return new Idle();}


	public class Idle extends Dialog implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 17
			try {
				EXECUTION: {
					int count = getCount(1607460018) + 1;
					incrCount(1607460018);
					// Line: 18
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state0 = agent.new attendRandom();
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 18, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 20
						Request state1 = new Request();
						flowThread.gotoState(state1, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 20, 40)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 21
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state2 = agent.new attendNobody();
						if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 18, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 17, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 25
			try {
				count = getCount(245565335) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(245565335);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state3 = agent.new attend();
						state3.setTarget(event.get("user"));
						if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 25, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 27
						Request state4 = new Request();
						flowThread.gotoState(state4, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 27, 36)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 25, 42));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Request extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 32
			try {
				EXECUTION: {
					int count = getCount(183264084) + 1;
					incrCount(183264084);
					// Line: 33
					if (count == 1) {
						iristk.situated.SystemAgentFlow.say state5 = agent.new say();
						StringCreator string6 = new StringCreator();
						string6.append("Hi, what do you want to know?");
						state5.setText(string6.toString());
						if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 33, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 35
					} else {
						iristk.situated.SystemAgentFlow.say state7 = agent.new say();
						StringCreator string8 = new StringCreator();
						string8.append("Anything else?");
						state7.setText(string8.toString());
						if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 33, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.situated.SystemAgentFlow.listen state9 = agent.new listen();
					if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 32, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 32, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 40
			try {
				count = getCount(460332449) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(460332449);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state10 = agent.new say();
							StringCreator string11 = new StringCreator();
							string11.append("Ok, good bye");
							state10.setText(string11.toString());
							if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 40, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 40, 63));
			}
			// Line: 45
			try {
				count = getCount(1919892312) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"), iristk.speech.RecResult.NOMATCH)) {
						incrCount(1919892312);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 46
							if (system.isAttendingAll()) {
								iristk.situated.SystemAgentFlow.attend state12 = agent.new attend();
								state12.setTarget(event.get("user"));
								if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 46, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
							iristk.situated.SystemAgentFlow.say state13 = agent.new say();
							StringCreator string14 = new StringCreator();
							// Line: 46
							boolean chosen15 = false;
							boolean matching16 = true;
							while (!chosen15 && matching16) {
								int rand17 = random(2104457164, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching16 = false;
								if (true) {
									matching16 = true;
									if (rand17 >= 0 && rand17 < 1) {
										chosen15 = true;
										string14.append("Let's see");
									}
								}
								if (true) {
									matching16 = true;
									if (rand17 >= 1 && rand17 < 2) {
										chosen15 = true;
										string14.append("One second");
									}
								}
							}
							state13.setAsync(true);
							state13.setText(string14.toString());
							if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 45, 98)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state18 = agent.new say();
							StringCreator string19 = new StringCreator();
							// Line: 46
							String answer = houndify.answer(asString(event.get("text")));
							// Line: 46
							if (!eq(answer, "")) {
								StringCreator string20 = new StringCreator();
								string20.append("<say>");
								// Line: 46
								string20.append(answer);
								string20.append("</say>");
								string19.append(string20.toString());
								// Line: 46
							} else {
								StringCreator string21 = new StringCreator();
								string21.append("<say>");
								string21.append("Sorry, I have some problems connecting to Houndify. Please contact my supervisor");
								string21.append("</say>");
								string19.append(string21.toString());
							}
							state18.setText(string19.toString());
							if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 45, 98)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 65
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 65, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 45, 98));
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
			// Line: 73
			try {
				count = getCount(517938326) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(517938326);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state22 = agent.new gesture();
							state22.setName("smile");
							if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 73, 72)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 73, 72));
			}
			// Line: 77
			try {
				count = getCount(110718392) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(110718392);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state23 = agent.new say();
						StringCreator string24 = new StringCreator();
						string24.append("Sorry, I didn't get that.");
						state23.setText(string24.toString());
						if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 77, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 79
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 79, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 77, 42));
			}
			// Line: 83
			try {
				count = getCount(2143192188) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(2143192188);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 84
						Event sendEvent25 = new Event("sense.user.speak");
						sendEvent25.copyParams(event);
						flowRunner.sendEvent(sendEvent25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 84, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 83, 41));
			}
			// Line: 88
			try {
				count = getCount(204349222) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(204349222);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 89
						Event sendEvent26 = new Event("sense.user.speak");
						sendEvent26.copyParams(event);
						flowRunner.sendEvent(sendEvent26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 89, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 88, 42));
			}
			// Line: 92
			try {
				count = getCount(114935352) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(114935352);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state27 = agent.new say();
						StringCreator string28 = new StringCreator();
						string28.append("Sorry, I didn't hear anything.");
						state27.setText(string28.toString());
						if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 92, 44)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 94
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 94, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 92, 44));
			}
			// Line: 97
			try {
				count = getCount(32374789) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(32374789);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 98
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state29 = agent.new attendRandom();
								if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 98, 42)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 100
								Request state30 = new Request();
								flowThread.gotoState(state30, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 100, 40)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 101
							} else {
								// Line: 102
								Idle state31 = new Idle();
								flowThread.gotoState(state31, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 102, 37)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 97, 75));
			}
			// Line: 106
			try {
				count = getCount(1694819250) + 1;
				if (event.triggers("repeat")) {
					incrCount(1694819250);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 107
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 107, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 106, 32));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
