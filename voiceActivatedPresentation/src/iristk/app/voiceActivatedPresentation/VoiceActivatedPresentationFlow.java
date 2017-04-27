package iristk.app.voiceActivatedPresentation;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;

public class VoiceActivatedPresentationFlow extends iristk.flow.Flow {

	private iristk.situated.SystemAgentFlow agent;
	private iristk.situated.SystemAgent system;
	private String nextState;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public String getNextState() {
		return this.nextState;
	}

	public void setNextState(String value) {
		this.nextState = value;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("nextState")) return this.nextState;
		if (name.equals("agent")) return this.agent;
		return null;
	}


	public VoiceActivatedPresentationFlow(iristk.situated.SystemAgentFlow agent) {
		this.agent = agent;
		initVariables();
	}

	@Override
	public State getInitialState() {return new state0();}


	public class state0 extends Triggers implements Initial {

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
					int count = getCount(1289696681) + 1;
					incrCount(1289696681);
					iristk.situated.SystemAgentFlow.say state0 = agent.new say();
					StringCreator string1 = new StringCreator();
					string1.append("Welcome to this example voice activated linear presentation. This is state 1, to go to the next step you have to say a fruit or go or next");
					state0.setText(string1.toString());
					if (!flowThread.callState(state0, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 13, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.listen state2 = agent.new listen();
					if (!flowThread.callState(state2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 13, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 13, 12));
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


	private class state1 extends Triggers {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 20
			try {
				EXECUTION: {
					int count = getCount(1811075214) + 1;
					incrCount(1811075214);
					iristk.situated.SystemAgentFlow.say state3 = agent.new say();
					StringCreator string4 = new StringCreator();
					string4.append("State 1. Same thing again, to visualize how you can have the same utterance triggering in several states. Say a fruit or go or next.");
					state3.setText(string4.toString());
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 20, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.listen state5 = agent.new listen();
					if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 20, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 20, 12));
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


	private class state2 extends Triggers {

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
					int count = getCount(1407343478) + 1;
					incrCount(1407343478);
					iristk.situated.SystemAgentFlow.say state6 = agent.new say();
					StringCreator string7 = new StringCreator();
					string7.append("State 2. Say a color to go to the final step. Note in the grammar how this single-state trigger phase can be simplified compared to the multi-state fruits.");
					state6.setText(string7.toString());
					if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 27, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.listen state8 = agent.new listen();
					if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 27, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 27, 12));
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


	private class state3 extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 35
			try {
				EXECUTION: {
					int count = getCount(245565335) + 1;
					incrCount(245565335);
					iristk.situated.SystemAgentFlow.say state9 = agent.new say();
					StringCreator string10 = new StringCreator();
					string10.append("Final state. No trigger exists in this state since the state does not extend the Triggers state. Check out the code to learn about how this state works.");
					state9.setText(string10.toString());
					if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 35, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 35, 12));
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


	private class Triggers extends State {

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
			// Line: 42
			try {
				count = getCount(1066376662) + 1;
				if (event.triggers("sense.skill.next")) {
					incrCount(1066376662);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 44
						String prefix = super.getClass().getSimpleName().replaceAll("[0-9]", "");
						int nextStateNumber = (Integer.parseInt(super.getClass().getSimpleName().replaceAll("[^0-9]", "")) + 1);
						nextState = prefix + nextStateNumber;
						// Line: 49
						flowThread.gotoState(getState(nextState), currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 49, 38)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 42, 36));
			}
			// Line: 53
			try {
				count = getCount(1490180672) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:trigger")) {
						incrCount(1490180672);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 54
							if (eq(event.get("sem:trigger"), "any") || eq(event.get("sem:trigger"), super.getClass().getSimpleName()) || asRecord(event.get("sem:trigger")).has(super.getClass().getSimpleName())) {
								// Line: 55
								Event raiseEvent11 = new Event("sense.skill.next");
								if (flowThread.raiseEvent(raiseEvent11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 55, 41))) == State.EVENT_ABORTED) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 56
							} else {
								iristk.situated.SystemAgentFlow.listen state12 = agent.new listen();
								if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 54, 172)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 53, 62));
			}
			// Line: 62
			try {
				count = getCount(250075633) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(250075633);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.listen state13 = agent.new listen();
						if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 62, 36)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 62, 36));
			}
			// Line: 67
			try {
				count = getCount(517938326) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(517938326);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.listen state14 = agent.new listen();
						if (!flowThread.callState(state14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 67, 38)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\voiceActivatedPresentation\\src\\iristk\\app\\voiceActivatedPresentation\\VoiceActivatedPresentationFlow.xml"), 67, 38));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
