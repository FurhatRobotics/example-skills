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
					int count = getCount(1069020202) + 1;
					incrCount(1069020202);
					// Line: 18
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state0 = agent.new attendRandom();
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 18, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 20
						Request state1 = new Request();
						flowThread.gotoState(state1, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 20, 40)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 21
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state2 = agent.new attendNobody();
						if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 18, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 17, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 26
			try {
				count = getCount(508416458) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(508416458);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 27
						StringCreator string3 = new StringCreator();
						// Line: 27
						string3.append(event);
						log(string3.toString());
						iristk.situated.SystemAgentFlow.attend state4 = agent.new attend();
						state4.setTarget(event.get("user"));
						if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 26, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 29
						Request state5 = new Request();
						flowThread.gotoState(state5, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 29, 36)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 26, 42));
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
			// Line: 34
			try {
				EXECUTION: {
					int count = getCount(728619661) + 1;
					incrCount(728619661);
					// Line: 35
					if (count == 1) {
						iristk.situated.SystemAgentFlow.say state6 = agent.new say();
						StringCreator string7 = new StringCreator();
						string7.append("Hi, what do you want to know?");
						state6.setText(string7.toString());
						if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 35, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 37
					} else {
						iristk.situated.SystemAgentFlow.say state8 = agent.new say();
						StringCreator string9 = new StringCreator();
						string9.append("Anything else?");
						state8.setText(string9.toString());
						if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 35, 35)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.situated.SystemAgentFlow.listen state10 = agent.new listen();
					if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 34, 18)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 34, 18));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 42
			try {
				count = getCount(917216285) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no")) {
						incrCount(917216285);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state11 = agent.new say();
							StringCreator string12 = new StringCreator();
							string12.append("Ok, good bye");
							state11.setText(string12.toString());
							if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 42, 63)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 42, 63));
			}
			// Line: 47
			try {
				count = getCount(1943507447) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"), iristk.speech.RecResult.NOMATCH)) {
						incrCount(1943507447);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 48
							if (system.isAttendingAll()) {
								iristk.situated.SystemAgentFlow.attend state13 = agent.new attend();
								state13.setTarget(event.get("user"));
								if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 48, 48)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
							iristk.situated.SystemAgentFlow.say state14 = agent.new say();
							StringCreator string15 = new StringCreator();
							// Line: 48
							boolean chosen16 = false;
							boolean matching17 = true;
							while (!chosen16 && matching17) {
								int rand18 = random(330069759, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching17 = false;
								if (true) {
									matching17 = true;
									if (rand18 >= 0 && rand18 < 1) {
										chosen16 = true;
										string15.append("Let's see");
									}
								}
								if (true) {
									matching17 = true;
									if (rand18 >= 1 && rand18 < 2) {
										chosen16 = true;
										string15.append("One second");
									}
								}
							}
							state14.setAsync(true);
							state14.setText(string15.toString());
							if (!flowThread.callState(state14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 47, 98)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state19 = agent.new say();
							StringCreator string20 = new StringCreator();
							// Line: 48
							String answer = houndify.answer(asString(event.get("text")));
							// Line: 48
							if (!eq(answer, "")) {
								// Line: 48
								string20.append(answer);
								// Line: 48
							} else {
								string20.append("Sorry, I have some problems connecting to Houndify. Please contact my supervisor");
							}
							state19.setText(string20.toString());
							if (!flowThread.callState(state19, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 47, 98)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 65
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 65, 23)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 47, 98));
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
				count = getCount(1696111156) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1696111156);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state21 = agent.new gesture();
							state21.setName("smile");
							if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 73, 72)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 73, 72));
			}
			// Line: 77
			try {
				count = getCount(1694743330) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1694743330);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state22 = agent.new say();
						StringCreator string23 = new StringCreator();
						string23.append("Sorry, I didn't get that.");
						state22.setText(string23.toString());
						if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 77, 42)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 79
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 79, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 77, 42));
			}
			// Line: 83
			try {
				count = getCount(663884546) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(663884546);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 84
						Event sendEvent24 = new Event("sense.user.speak");
						sendEvent24.copyParams(event);
						flowRunner.sendEvent(sendEvent24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 84, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 83, 41));
			}
			// Line: 88
			try {
				count = getCount(556702637) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(556702637);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 89
						Event sendEvent25 = new Event("sense.user.speak");
						sendEvent25.copyParams(event);
						flowRunner.sendEvent(sendEvent25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 89, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 88, 42));
			}
			// Line: 92
			try {
				count = getCount(1203670194) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1203670194);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.say state26 = agent.new say();
						StringCreator string27 = new StringCreator();
						string27.append("Sorry, I didn't hear anything.");
						state26.setText(string27.toString());
						if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 92, 44)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 94
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 94, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 92, 44));
			}
			// Line: 97
			try {
				count = getCount(1313286832) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(1313286832);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 98
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state28 = agent.new attendRandom();
								if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 98, 42)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 100
								Request state29 = new Request();
								flowThread.gotoState(state29, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 100, 40)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 101
							} else {
								// Line: 102
								Idle state30 = new Idle();
								flowThread.gotoState(state30, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 102, 37)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 97, 75));
			}
			// Line: 106
			try {
				count = getCount(2043491488) + 1;
				if (event.triggers("repeat")) {
					incrCount(2043491488);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 107
						flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 107, 23)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\houndify\\src\\iristk\\app\\Houndify\\HoundifyFlow.xml"), 106, 32));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
