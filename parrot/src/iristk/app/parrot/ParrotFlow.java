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
			// Line: 40
			try {
				count = getCount(183264084) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"),"NO_MATCH")) {
						incrCount(183264084);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state16 = agent.new say();
							StringCreator string17 = new StringCreator();
							// Line: 40
							string17.append(event.get("text"));
							state16.setText(string17.toString());
							if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 40, 70)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 40, 70));
			}
			// Line: 44
			try {
				count = getCount(476402209) + 1;
				if (event.triggers("sense.user.silence")) {
					if (!system.getCurrentUser().isNobody()) {
						incrCount(476402209);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 45
							boolean chosen18 = false;
							boolean matching19 = true;
							while (!chosen18 && matching19) {
								int rand20 = random(1490180672, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching19 = false;
								if (true) {
									matching19 = true;
									if (rand20 >= 0 && rand20 < 1) {
										chosen18 = true;
										iristk.situated.SystemAgentFlow.say state21 = agent.new say();
										StringCreator string22 = new StringCreator();
										string22.append("Speak or I run away");
										state21.setText(string22.toString());
										if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching19 = true;
									if (rand20 >= 1 && rand20 < 2) {
										chosen18 = true;
										iristk.situated.SystemAgentFlow.say state23 = agent.new say();
										StringCreator string24 = new StringCreator();
										string24.append("I have all day to wait!");
										state23.setText(string24.toString());
										if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching19 = true;
									if (rand20 >= 2 && rand20 < 3) {
										chosen18 = true;
										iristk.situated.SystemAgentFlow.say state25 = agent.new say();
										StringCreator string26 = new StringCreator();
										string26.append("Are you going to talk or what?");
										state25.setText(string26.toString());
										if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching19 = true;
									if (rand20 >= 3 && rand20 < 4) {
										chosen18 = true;
										iristk.situated.SystemAgentFlow.say state27 = agent.new say();
										StringCreator string28 = new StringCreator();
										string28.append("Speak or I will forever hold my peace");
										state27.setText(string28.toString());
										if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 45, 12)))) {
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
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 44, 81));
			}
			// Line: 53
			try {
				count = getCount(460332449) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(460332449);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 54
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state29 = agent.new attendRandom();
								if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 54, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 56
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 56, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 57
							} else {
								iristk.situated.SystemAgentFlow.say state30 = agent.new say();
								StringCreator string31 = new StringCreator();
								string31.append("Bye.");
								state30.setText(string31.toString());
								if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 54, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 59
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 59, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 53, 69));
			}
			// Line: 63
			try {
				count = getCount(110718392) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(110718392);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state32 = agent.new attend();
						state32.setTarget(event.get("user"));
						if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 63, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state33 = agent.new listen();
						if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 63, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 63, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
