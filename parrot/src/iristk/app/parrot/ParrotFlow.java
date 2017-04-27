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


	public ParrotFlow(iristk.situated.SystemAgentFlow agent) {
		this.agent = agent;
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
			// Line: 11
			try {
				EXECUTION: {
					int count = getCount(1174290147) + 1;
					incrCount(1174290147);
					// Line: 12
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state0 = agent.new attendRandom();
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 12, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 14
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state1 = agent.new attendNobody();
						if (!flowThread.callState(state1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 12, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 17
					Dialog state2 = new Dialog();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 17, 29)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 11, 12));
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
			// Line: 22
			try {
				EXECUTION: {
					int count = getCount(1407343478) + 1;
					incrCount(1407343478);
					iristk.situated.SystemAgentFlow.listen state3 = agent.new listen();
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 22, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 24
					flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 24, 14)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 22, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 27
			try {
				count = getCount(245565335) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(245565335);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state4 = agent.new gesture();
							state4.setName("smile");
							if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 27, 102)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 27, 102));
			}
			// Line: 31
			try {
				count = getCount(2121744517) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(2121744517);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 32
						boolean chosen5 = false;
						boolean matching6 = true;
						while (!chosen5 && matching6) {
							int rand7 = random(1066376662, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching6 = false;
							if (true) {
								matching6 = true;
								if (rand7 >= 0 && rand7 < 1) {
									chosen5 = true;
									iristk.situated.SystemAgentFlow.say state8 = agent.new say();
									StringCreator string9 = new StringCreator();
									string9.append("Hey humans! one at a time");
									state8.setText(string9.toString());
									if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 32, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching6 = true;
								if (rand7 >= 1 && rand7 < 2) {
									chosen5 = true;
									iristk.situated.SystemAgentFlow.say state10 = agent.new say();
									StringCreator string11 = new StringCreator();
									string11.append("Ah! Shut up you guys!");
									state10.setText(string11.toString());
									if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 32, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching6 = true;
								if (rand7 >= 2 && rand7 < 3) {
									chosen5 = true;
									iristk.situated.SystemAgentFlow.say state12 = agent.new say();
									StringCreator string13 = new StringCreator();
									string13.append("Too Loud! You are making it too loud!");
									state12.setText(string13.toString());
									if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 32, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching6 = true;
								if (rand7 >= 3 && rand7 < 4) {
									chosen5 = true;
									iristk.situated.SystemAgentFlow.say state14 = agent.new say();
									StringCreator string15 = new StringCreator();
									string15.append("Don't speak at the same time");
									state14.setText(string15.toString());
									if (!flowThread.callState(state14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 32, 12)))) {
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
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 31, 42));
			}
			// Line: 41
			try {
				count = getCount(183264084) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(183264084);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 42
						Event sendEvent16 = new Event("sense.user.speak");
						sendEvent16.copyParams(event);
						flowRunner.sendEvent(sendEvent16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 42, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 41, 41));
			}
			// Line: 45
			try {
				count = getCount(1490180672) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"),"NO_MATCH")) {
						incrCount(1490180672);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state17 = agent.new say();
							StringCreator string18 = new StringCreator();
							// Line: 45
							string18.append(event.get("text"));
							state17.setText(string18.toString());
							if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 70)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 70));
			}
			// Line: 49
			try {
				count = getCount(460332449) + 1;
				if (event.triggers("sense.user.silence")) {
					if (!system.getCurrentUser().isNobody()) {
						incrCount(460332449);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 50
							boolean chosen19 = false;
							boolean matching20 = true;
							while (!chosen19 && matching20) {
								int rand21 = random(1919892312, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching20 = false;
								if (true) {
									matching20 = true;
									if (rand21 >= 0 && rand21 < 1) {
										chosen19 = true;
										iristk.situated.SystemAgentFlow.say state22 = agent.new say();
										StringCreator string23 = new StringCreator();
										string23.append("Speak or I run away");
										state22.setText(string23.toString());
										if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 50, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching20 = true;
									if (rand21 >= 1 && rand21 < 2) {
										chosen19 = true;
										iristk.situated.SystemAgentFlow.say state24 = agent.new say();
										StringCreator string25 = new StringCreator();
										string25.append("I have all day to wait!");
										state24.setText(string25.toString());
										if (!flowThread.callState(state24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 50, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching20 = true;
									if (rand21 >= 2 && rand21 < 3) {
										chosen19 = true;
										iristk.situated.SystemAgentFlow.say state26 = agent.new say();
										StringCreator string27 = new StringCreator();
										string27.append("Are you going to talk or what?");
										state26.setText(string27.toString());
										if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 50, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching20 = true;
									if (rand21 >= 3 && rand21 < 4) {
										chosen19 = true;
										iristk.situated.SystemAgentFlow.say state28 = agent.new say();
										StringCreator string29 = new StringCreator();
										string29.append("Speak or I will forever hold my peace");
										state28.setText(string29.toString());
										if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 50, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 49, 81));
			}
			// Line: 58
			try {
				count = getCount(1143839598) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(1143839598);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 59
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state30 = agent.new attendRandom();
								if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 59, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 61
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 61, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 62
							} else {
								iristk.situated.SystemAgentFlow.say state31 = agent.new say();
								StringCreator string32 = new StringCreator();
								string32.append("Bye.");
								state31.setText(string32.toString());
								if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 59, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 64
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 64, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 58, 69));
			}
			// Line: 68
			try {
				count = getCount(2143192188) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(2143192188);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state33 = agent.new attend();
						state33.setTarget(event.get("user"));
						if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 68, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state34 = agent.new listen();
						if (!flowThread.callState(state34, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 68, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 68, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
