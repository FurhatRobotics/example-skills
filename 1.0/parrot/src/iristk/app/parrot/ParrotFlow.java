package iristk.app.parrot;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;

public class ParrotFlow extends iristk.flow.Flow {

	private iristk.situated.SystemAgentFlow agent;
	private iristk.app.parrot.ParrotSkill.API api;
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

	public iristk.app.parrot.ParrotSkill.API getApi() {
		return this.api;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("agent")) return this.agent;
		if (name.equals("api")) return this.api;
		return null;
	}


	public ParrotFlow(iristk.situated.SystemAgentFlow agent, iristk.app.parrot.ParrotSkill.API api) {
		this.agent = agent;
		this.api = api;
		initVariables();
	}

	@Override
	public State getInitialState() {return new Init();}


	public class Init extends State implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 13
			try {
				EXECUTION: {
					int count = getCount(1838180135) + 1;
					incrCount(1838180135);
					iristk.situated.SystemAgentFlow.say state0 = agent.new say();
					StringCreator string1 = new StringCreator();
					string1.append("my API query is");
					// Line: 13
					string1.append(api.query());
					state0.setText(string1.toString());
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 13, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 16
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state2 = agent.new attendRandom();
						if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 16, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.say state3 = agent.new say();
						StringCreator string4 = new StringCreator();
						string4.append("Hi there");
						state3.setText(string4.toString());
						if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 16, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 19
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state5 = agent.new attendNobody();
						if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 16, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 22
					Dialog state6 = new Dialog();
					flowThread.gotoState(state6, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 22, 29)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 13, 12));
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
			// Line: 27
			try {
				EXECUTION: {
					int count = getCount(206081683) + 1;
					incrCount(206081683);
					iristk.situated.SystemAgentFlow.listen state7 = agent.new listen();
					if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 27, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 29
					flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 29, 14)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 27, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 32
			try {
				count = getCount(1560584546) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1560584546);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state8 = agent.new gesture();
							state8.setName("smile");
							if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 32, 102)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 32, 102));
			}
			// Line: 36
			try {
				count = getCount(1278751622) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(1278751622);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 37
						boolean chosen9 = false;
						boolean matching10 = true;
						while (!chosen9 && matching10) {
							int rand11 = random(72093828, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching10 = false;
							if (true) {
								matching10 = true;
								if (rand11 >= 0 && rand11 < 1) {
									chosen9 = true;
									iristk.situated.SystemAgentFlow.say state12 = agent.new say();
									StringCreator string13 = new StringCreator();
									string13.append("Hey humans! one at a time");
									state12.setText(string13.toString());
									if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 37, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching10 = true;
								if (rand11 >= 1 && rand11 < 2) {
									chosen9 = true;
									iristk.situated.SystemAgentFlow.say state14 = agent.new say();
									StringCreator string15 = new StringCreator();
									string15.append("Ah! Shut up you guys!");
									state14.setText(string15.toString());
									if (!flowThread.callState(state14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 37, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching10 = true;
								if (rand11 >= 2 && rand11 < 3) {
									chosen9 = true;
									iristk.situated.SystemAgentFlow.say state16 = agent.new say();
									StringCreator string17 = new StringCreator();
									string17.append("Too Loud! You are making it too loud!");
									state16.setText(string17.toString());
									if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 37, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching10 = true;
								if (rand11 >= 3 && rand11 < 4) {
									chosen9 = true;
									iristk.situated.SystemAgentFlow.say state18 = agent.new say();
									StringCreator string19 = new StringCreator();
									string19.append("Don't speak at the same time");
									state18.setText(string19.toString());
									if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 37, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 36, 42));
			}
			// Line: 46
			try {
				count = getCount(34499641) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(34499641);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 47
						Event sendEvent20 = new Event("sense.user.speak");
						sendEvent20.copyParams(event);
						flowRunner.sendEvent(sendEvent20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 47, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 46, 41));
			}
			// Line: 50
			try {
				count = getCount(2001762913) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:goodbye")) {
						incrCount(2001762913);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 51
							Exit state21 = new Exit();
							flowThread.gotoState(state21, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 51, 33)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 50, 68));
			}
			// Line: 54
			try {
				count = getCount(1650607581) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"),"NO_MATCH")) {
						incrCount(1650607581);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state22 = agent.new say();
							StringCreator string23 = new StringCreator();
							// Line: 54
							string23.append(event.get("text"));
							state22.setText(string23.toString());
							if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 54, 70)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 56
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 56, 17)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 54, 70));
			}
			// Line: 59
			try {
				count = getCount(575489140) + 1;
				if (event.triggers("sense.user.silence")) {
					if (!system.getCurrentUser().isNobody()) {
						incrCount(575489140);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 60
							boolean chosen24 = false;
							boolean matching25 = true;
							while (!chosen24 && matching25) {
								int rand26 = random(1428662646, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching25 = false;
								if (true) {
									matching25 = true;
									if (rand26 >= 0 && rand26 < 1) {
										chosen24 = true;
										iristk.situated.SystemAgentFlow.say state27 = agent.new say();
										StringCreator string28 = new StringCreator();
										string28.append("Speak or I run away");
										state27.setText(string28.toString());
										if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 60, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching25 = true;
									if (rand26 >= 1 && rand26 < 2) {
										chosen24 = true;
										iristk.situated.SystemAgentFlow.say state29 = agent.new say();
										StringCreator string30 = new StringCreator();
										string30.append("I have all day to wait!");
										state29.setText(string30.toString());
										if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 60, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching25 = true;
									if (rand26 >= 2 && rand26 < 3) {
										chosen24 = true;
										iristk.situated.SystemAgentFlow.say state31 = agent.new say();
										StringCreator string32 = new StringCreator();
										string32.append("Are you going to talk or what?");
										state31.setText(string32.toString());
										if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 60, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching25 = true;
									if (rand26 >= 3 && rand26 < 4) {
										chosen24 = true;
										iristk.situated.SystemAgentFlow.say state33 = agent.new say();
										StringCreator string34 = new StringCreator();
										string34.append("Speak or I will forever hold my peace");
										state33.setText(string34.toString());
										if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 60, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 66
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 66, 17)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 59, 81));
			}
			// Line: 69
			try {
				count = getCount(1861824597) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(1861824597);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 70
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state35 = agent.new attendRandom();
								if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 70, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 72
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 72, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 73
							} else {
								// Line: 74
								Exit state36 = new Exit();
								flowThread.gotoState(state36, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 74, 25)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 69, 69));
			}
			// Line: 78
			try {
				count = getCount(1891742888) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1891742888);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state37 = agent.new attend();
						state37.setTarget(event.get("user"));
						if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 78, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state38 = agent.new listen();
						if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 78, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 78, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Exit extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 85
			try {
				EXECUTION: {
					int count = getCount(820068340) + 1;
					incrCount(820068340);
					iristk.situated.SystemAgentFlow.say state39 = agent.new say();
					StringCreator string40 = new StringCreator();
					string40.append("Okay, goodbye.");
					state39.setText(string40.toString());
					if (!flowThread.callState(state39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 85, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 85, 12));
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


}
