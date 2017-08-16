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

	private Record initialParameters;
	private iristk.situated.SystemAgentFlow agent;
	private String originSkill;
	private iristk.situated.SystemAgent system;

	private void initVariables() {
		originSkill = asString("iristk.furhat.server.IdleSkill");
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
	}

	public String getOriginSkill() {
		return this.originSkill;
	}

	public void setOriginSkill(String value) {
		this.originSkill = value;
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public Record getInitialParameters() {
		return this.initialParameters;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("originSkill")) return this.originSkill;
		if (name.equals("system")) return this.system;
		if (name.equals("initialParameters")) return this.initialParameters;
		if (name.equals("agent")) return this.agent;
		return null;
	}


	public ParrotFlow(Record initialParameters, iristk.situated.SystemAgentFlow agent) {
		this.initialParameters = initialParameters;
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
			// Line: 14
			try {
				EXECUTION: {
					int count = getCount(1973336893) + 1;
					incrCount(1973336893);
					// Line: 15
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendRandom state0 = agent.new attendRandom();
						if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 15, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 17
					} else {
						iristk.situated.SystemAgentFlow.attendNobody state1 = agent.new attendNobody();
						if (!flowThread.callState(state1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 15, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 20
					Dialog state2 = new Dialog();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 20, 29)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 14, 12));
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


	public class HotStart extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 25
			try {
				EXECUTION: {
					int count = getCount(1811075214) + 1;
					incrCount(1811075214);
					// Line: 26
					if (initialParameters != null && initialParameters.has("originSkill")) {
						// Line: 27
						originSkill = initialParameters.getString("originSkill");
					}
					iristk.situated.SystemAgentFlow.say state3 = agent.new say();
					StringCreator string4 = new StringCreator();
					string4.append("say something then");
					state3.setText(string4.toString());
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 25, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 32
					Dialog state5 = new Dialog();
					flowThread.gotoState(state5, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 32, 26)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 25, 12));
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
			// Line: 37
			try {
				EXECUTION: {
					int count = getCount(2121744517) + 1;
					incrCount(2121744517);
					iristk.situated.SystemAgentFlow.listen state6 = agent.new listen();
					if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 37, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 39
					flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 39, 14)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 37, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 42
			try {
				count = getCount(183264084) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(183264084);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.gesture state7 = agent.new gesture();
							state7.setName("smile");
							if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 42, 102)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 42, 102));
			}
			// Line: 46
			try {
				count = getCount(476402209) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(476402209);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 47
						boolean chosen8 = false;
						boolean matching9 = true;
						while (!chosen8 && matching9) {
							int rand10 = random(1490180672, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching9 = false;
							if (true) {
								matching9 = true;
								if (rand10 >= 0 && rand10 < 1) {
									chosen8 = true;
									iristk.situated.SystemAgentFlow.say state11 = agent.new say();
									StringCreator string12 = new StringCreator();
									string12.append("Hey humans! one at a time");
									state11.setText(string12.toString());
									if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 47, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching9 = true;
								if (rand10 >= 1 && rand10 < 2) {
									chosen8 = true;
									iristk.situated.SystemAgentFlow.say state13 = agent.new say();
									StringCreator string14 = new StringCreator();
									string14.append("Ah! Shut up you guys!");
									state13.setText(string14.toString());
									if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 47, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching9 = true;
								if (rand10 >= 2 && rand10 < 3) {
									chosen8 = true;
									iristk.situated.SystemAgentFlow.say state15 = agent.new say();
									StringCreator string16 = new StringCreator();
									string16.append("Too Loud! You are making it too loud!");
									state15.setText(string16.toString());
									if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 47, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching9 = true;
								if (rand10 >= 3 && rand10 < 4) {
									chosen8 = true;
									iristk.situated.SystemAgentFlow.say state17 = agent.new say();
									StringCreator string18 = new StringCreator();
									string18.append("Don't speak at the same time");
									state17.setText(string18.toString());
									if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 47, 12)))) {
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
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 46, 42));
			}
			// Line: 56
			try {
				count = getCount(460332449) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(460332449);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 57
						Event sendEvent19 = new Event("sense.user.speak");
						sendEvent19.copyParams(event);
						flowRunner.sendEvent(sendEvent19, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 57, 49)));
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 56, 41));
			}
			// Line: 60
			try {
				count = getCount(1143839598) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:goodbye")) {
						incrCount(1143839598);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 61
							Exit state20 = new Exit();
							flowThread.gotoState(state20, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 61, 33)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 60, 68));
			}
			// Line: 64
			try {
				count = getCount(517938326) + 1;
				if (event.triggers("sense.user.speak")) {
					if (!eq(event.get("text"),"NO_MATCH")) {
						incrCount(517938326);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state21 = agent.new say();
							StringCreator string22 = new StringCreator();
							// Line: 64
							string22.append(event.get("text"));
							state21.setText(string22.toString());
							if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 64, 70)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
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
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 64, 70));
			}
			// Line: 69
			try {
				count = getCount(110718392) + 1;
				if (event.triggers("sense.user.silence")) {
					if (!system.getCurrentUser().isNobody()) {
						incrCount(110718392);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 70
							boolean chosen23 = false;
							boolean matching24 = true;
							while (!chosen23 && matching24) {
								int rand25 = random(425918570, 4, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching24 = false;
								if (true) {
									matching24 = true;
									if (rand25 >= 0 && rand25 < 1) {
										chosen23 = true;
										iristk.situated.SystemAgentFlow.say state26 = agent.new say();
										StringCreator string27 = new StringCreator();
										string27.append("Speak or I run away");
										state26.setText(string27.toString());
										if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 70, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching24 = true;
									if (rand25 >= 1 && rand25 < 2) {
										chosen23 = true;
										iristk.situated.SystemAgentFlow.say state28 = agent.new say();
										StringCreator string29 = new StringCreator();
										string29.append("I have all day to wait!");
										state28.setText(string29.toString());
										if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 70, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching24 = true;
									if (rand25 >= 2 && rand25 < 3) {
										chosen23 = true;
										iristk.situated.SystemAgentFlow.say state30 = agent.new say();
										StringCreator string31 = new StringCreator();
										string31.append("Are you going to talk or what?");
										state30.setText(string31.toString());
										if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 70, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching24 = true;
									if (rand25 >= 3 && rand25 < 4) {
										chosen23 = true;
										iristk.situated.SystemAgentFlow.say state32 = agent.new say();
										StringCreator string33 = new StringCreator();
										string33.append("Speak or I will forever hold my peace");
										state32.setText(string33.toString());
										if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 70, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 76
							flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 76, 17)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 69, 81));
			}
			// Line: 79
			try {
				count = getCount(1100439041) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event)) {
						incrCount(1100439041);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 80
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state34 = agent.new attendRandom();
								if (!flowThread.callState(state34, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 80, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 82
								flowThread.reentryState(this, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 82, 15)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 83
							} else {
								// Line: 84
								Exit state35 = new Exit();
								flowThread.gotoState(state35, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 84, 25)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 79, 69));
			}
			// Line: 88
			try {
				count = getCount(1973538135) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1973538135);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attend state36 = agent.new attend();
						state36.setTarget(event.get("user"));
						if (!flowThread.callState(state36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 88, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state37 = agent.new listen();
						if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 88, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 88, 36));
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
			// Line: 95
			try {
				EXECUTION: {
					int count = getCount(515132998) + 1;
					incrCount(515132998);
					iristk.situated.SystemAgentFlow.say state38 = agent.new say();
					StringCreator string39 = new StringCreator();
					string39.append("Okay, goodbye.");
					state38.setText(string39.toString());
					if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 95, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 97
					if (initialParameters != null && !initialParameters.empty() ) {
						// Line: 98
						Event sendEvent40 = new Event("action.skill");
						sendEvent40.putIfNotNull("entry", originSkill);
						flowRunner.sendEvent(sendEvent40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 98, 64)));
						// Line: 99
					} else {
						// Line: 100
						Init state41 = new Init();
						flowThread.gotoState(state41, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 100, 31)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\parrot\\src\\iristk\\app\\parrot\\ParrotFlow.xml"), 95, 12));
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
