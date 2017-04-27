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
			// Line: 11
			try {
				EXECUTION: {
					int count = getCount(1347137144) + 1;
					incrCount(1347137144);
					iristk.situated.SystemAgentFlow.listen state0 = agent.new listen();
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 11, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 13
					flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 13, 14)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 11, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 15
			try {
				count = getCount(1212899836) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1212899836);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state1 = agent.new gesture();
							state1.setName("smile");
							if (!flowThread.callState(state1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 15, 102)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 15, 102));
			}
			// Line: 18
			try {
				count = getCount(1174290147) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(1174290147);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 19
						boolean chosen2 = false;
						boolean matching3 = true;
						while (!chosen2 && matching3) {
							int rand4 = random(1289696681, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching3 = false;
							if (true) {
								matching3 = true;
								if (rand4 >= 0 && rand4 < 1) {
									chosen2 = true;
									iristk.situated.SystemAgentFlow.say state5 = agent.new say();
									StringCreator string6 = new StringCreator();
									string6.append("Hey humans! one at a time");
									state5.setText(string6.toString());
									if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 19, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching3 = true;
								if (rand4 >= 1 && rand4 < 2) {
									chosen2 = true;
									iristk.situated.SystemAgentFlow.say state7 = agent.new say();
									StringCreator string8 = new StringCreator();
									string8.append("Ah! Shut up you guys!");
									state7.setText(string8.toString());
									if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 19, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching3 = true;
								if (rand4 >= 2 && rand4 < 3) {
									chosen2 = true;
									iristk.situated.SystemAgentFlow.say state9 = agent.new say();
									StringCreator string10 = new StringCreator();
									string10.append("Too Loud! You are making it too loud!");
									state9.setText(string10.toString());
									if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 19, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching3 = true;
								if (rand4 >= 3 && rand4 < 4) {
									chosen2 = true;
									iristk.situated.SystemAgentFlow.say state11 = agent.new say();
									StringCreator string12 = new StringCreator();
									string12.append("Don't speak at the same time.");
									state11.setText(string12.toString());
									if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 19, 12)))) {
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
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 18, 42));
			}
			// Line: 26
			try {
				count = getCount(1285044316) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"),"NO_MATCH")) {
						incrCount(1285044316);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state13 = agent.new say();
							StringCreator string14 = new StringCreator();
							// Line: 26
							string14.append(event.get("text"));
							state13.setText(string14.toString());
							if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 26, 70)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 26, 70));
			}
			// Line: 29
			try {
				count = getCount(1607460018) + 1;
				if (event.triggers("sense.user.silence")) {
					if (!system.getCurrentUser().isNobody()) {
						incrCount(1607460018);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 30
							boolean chosen15 = false;
							boolean matching16 = true;
							while (!chosen15 && matching16) {
								int rand17 = random(1811075214, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching16 = false;
								if (true) {
									matching16 = true;
									if (rand17 >= 0 && rand17 < 1) {
										chosen15 = true;
										iristk.situated.SystemAgentFlow.say state18 = agent.new say();
										StringCreator string19 = new StringCreator();
										string19.append("Talk or I'll kill you");
										state18.setText(string19.toString());
										if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 30, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching16 = true;
									if (rand17 >= 1 && rand17 < 2) {
										chosen15 = true;
										iristk.situated.SystemAgentFlow.say state20 = agent.new say();
										StringCreator string21 = new StringCreator();
										string21.append("I have all day to wait!");
										state20.setText(string21.toString());
										if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 30, 12)))) {
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
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 29, 81));
			}
			// Line: 35
			try {
				count = getCount(1588970020) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(1588970020);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 36
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state22 = agent.new attendRandom();
								if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 36, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 38
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 38, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 39
							} else {
								iristk.situated.SystemAgentFlow.say state23 = agent.new say();
								StringCreator string24 = new StringCreator();
								string24.append("Bye.");
								state23.setText(string24.toString());
								if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 36, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 41
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 41, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 35, 69));
			}
			// Line: 45
			try {
				count = getCount(1066376662) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1066376662);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state25 = agent.new attend();
						state25.setTarget(event.get("user"));
						if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state26 = agent.new listen();
						if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


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
			// Line: 53
			try {
				EXECUTION: {
					int count = getCount(476402209) + 1;
					incrCount(476402209);
					// Line: 54
					system.setMaxUsers(2);
					system.setInteractionDistance(2);
					// Line: 58
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state27 = agent.new attendRandom();
						if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 58, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 60
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state28 = agent.new attendNobody();
						if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 58, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 63
					Dialog state29 = new Dialog();
					flowThread.gotoState(state29, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 63, 29)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ledan\\Documents\\GitHub\\skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 53, 12));
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
