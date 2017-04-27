package iristk.app.presentation;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;
import iristk.furhat.util.Localizer;

public class PresentationFlow extends iristk.flow.Flow {

	private iristk.situated.SystemAgentFlow agent;
	private Localizer translator;
	private iristk.situated.SystemAgent system;
	private String defaultVoice;
	private String femaleVoice;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public String getDefaultVoice() {
		return this.defaultVoice;
	}

	public void setDefaultVoice(String value) {
		this.defaultVoice = value;
	}

	public String getFemaleVoice() {
		return this.femaleVoice;
	}

	public void setFemaleVoice(String value) {
		this.femaleVoice = value;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	public Localizer getTranslator() {
		return this.translator;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("defaultVoice")) return this.defaultVoice;
		if (name.equals("femaleVoice")) return this.femaleVoice;
		if (name.equals("agent")) return this.agent;
		if (name.equals("translator")) return this.translator;
		return null;
	}


	public PresentationFlow(iristk.situated.SystemAgentFlow agent, Localizer translator) {
		this.agent = agent;
		this.translator = translator;
		initVariables();
	}

	@Override
	public State getInitialState() {return new init();}


	public class init extends State implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 19
			try {
				EXECUTION: {
					int count = getCount(1174290147) + 1;
					incrCount(1174290147);
					// Line: 20
					defaultVoice = translator.get("VAR_DEFAULT_VOICE");
					femaleVoice = translator.get("FEMALE_VOICE");
					// Line: 24
					part1 state0 = new part1();
					flowThread.gotoState(state0, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 34)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 19, 18));
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


	private class part1 extends IdleMoves {

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
					int count = getCount(1811075214) + 1;
					incrCount(1811075214);
					// Line: 30
					Event sendEvent1 = new Event("action.voice");
					sendEvent1.putIfNotNull("name", defaultVoice);
					flowRunner.sendEvent(sendEvent1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 30, 55)));
					// Line: 31
					Event sendEvent2 = new Event("action.face.texture");
					sendEvent2.putIfNotNull("name", "default");
					flowRunner.sendEvent(sendEvent2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 31, 59)));
					// Line: 32
					lookAtRandomPerson state3 = new lookAtRandomPerson();
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 32, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 33
					iristk.flow.DialogFlow.wait waitState4 = new iristk.flow.DialogFlow.wait();
					waitState4.setMsec(500);
					if (!flowThread.callState(waitState4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 33, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 35
					lookAtRandomPerson state5 = new lookAtRandomPerson();
					if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 35, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state6 = agent.new say();
					state6.setText(translator.get("HELLO_THERE"));
					if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 37
					iristk.flow.DialogFlow.wait waitState7 = new iristk.flow.DialogFlow.wait();
					waitState7.setMsec(100);
					if (!flowThread.callState(waitState7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 37, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 39
					Event sendEvent8 = new Event("action.gesture");
					sendEvent8.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 39, 52)));
					iristk.situated.SystemAgentFlow.say state9 = agent.new say();
					state9.setText(translator.get("MY_NAME_IS_FURHAT"));
					if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 41
					Event sendEvent10 = new Event("action.gesture");
					sendEvent10.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 41, 52)));
					// Line: 42
					lookAtRandomPerson state11 = new lookAtRandomPerson();
					if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 42, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state12 = agent.new say();
					state12.setText(translator.get("I_CAN_SHOW_EMOTIONS"));
					if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 44
					Event sendEvent13 = new Event("action.gesture");
					sendEvent13.putIfNotNull("name", "emotion_anger");
					flowRunner.sendEvent(sendEvent13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 60)));
					// Line: 45
					iristk.flow.DialogFlow.wait waitState14 = new iristk.flow.DialogFlow.wait();
					waitState14.setMsec(1000);
					if (!flowThread.callState(waitState14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 45, 24)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 47
					lookAtRandomPerson state15 = new lookAtRandomPerson();
					if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 47, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 48
					Event sendEvent16 = new Event("action.gesture");
					sendEvent16.putIfNotNull("name", "emotion_neutral");
					flowRunner.sendEvent(sendEvent16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 48, 61)));
					iristk.situated.SystemAgentFlow.say state17 = agent.new say();
					state17.setText(translator.get("I_CAN_HAVE_PERSONALITIES"));
					if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 50
					Event sendEvent18 = new Event("action.face.texture");
					sendEvent18.putIfNotNull("name", "female");
					flowRunner.sendEvent(sendEvent18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 50, 58)));
					iristk.situated.SystemAgentFlow.say state19 = agent.new say();
					state19.setText(translator.get("I_CAN_LOOK_FEMALE"));
					if (!flowThread.callState(state19, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 52
					lookAtRandomPerson state20 = new lookAtRandomPerson();
					if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 52, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 53
					Event sendEvent21 = new Event("action.voice");
					sendEvent21.putIfNotNull("name", femaleVoice);
					flowRunner.sendEvent(sendEvent21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 53, 54)));
					iristk.situated.SystemAgentFlow.say state22 = agent.new say();
					state22.setText(translator.get("AND_SOUND_FEMALE"));
					if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 55
					lookAtRandomPerson state23 = new lookAtRandomPerson();
					if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 55, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 56
					Event sendEvent24 = new Event("action.voice");
					sendEvent24.putIfNotNull("name", defaultVoice);
					flowRunner.sendEvent(sendEvent24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 56, 55)));
					iristk.situated.SystemAgentFlow.say state25 = agent.new say();
					state25.setText(translator.get("OR_LIKE_ANYONE_ELSE"));
					if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 58
					lookAtRandomPerson state26 = new lookAtRandomPerson();
					if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 58, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 59
					Event sendEvent27 = new Event("action.face.texture");
					sendEvent27.putIfNotNull("name", "avatar");
					flowRunner.sendEvent(sendEvent27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 59, 58)));
					iristk.situated.SystemAgentFlow.say state28 = agent.new say();
					state28.setText(translator.get("LIKE_AN_AVATAR"));
					if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 61
					Event sendEvent29 = new Event("action.voice");
					sendEvent29.putIfNotNull("name", defaultVoice);
					flowRunner.sendEvent(sendEvent29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 61, 55)));
					// Line: 62
					if (eq(defaultVoice, "william")) {
						iristk.situated.SystemAgentFlow.say state30 = agent.new say();
						StringCreator string31 = new StringCreator();
						string31.append("GESTURE_GIGGLE_2");
						state30.setText(string31.toString());
						if (!flowThread.callState(state30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 62, 43)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 64
					} else {
						iristk.situated.SystemAgentFlow.say state32 = agent.new say();
						state32.setText(translator.get("GESTURE_GIGGLE_FALLBACK"));
						if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 62, 43)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 67
					lookAtRandomPerson state33 = new lookAtRandomPerson();
					if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 67, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 68
					Event sendEvent34 = new Event("action.face.texture");
					sendEvent34.putIfNotNull("name", "default");
					flowRunner.sendEvent(sendEvent34, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 68, 59)));
					// Line: 69
					iristk.flow.DialogFlow.wait waitState35 = new iristk.flow.DialogFlow.wait();
					waitState35.setMsec(500);
					if (!flowThread.callState(waitState35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 69, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 71
					lookAtRandomPerson state36 = new lookAtRandomPerson();
					if (!flowThread.callState(state36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 71, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state37 = agent.new say();
					state37.setText(translator.get("INSIDE_MY_CHEST_FURHATOS"));
					if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 73
					lookAtRandomPerson state38 = new lookAtRandomPerson();
					if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 73, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state39 = agent.new say();
					state39.setText(translator.get("IT_CONNECTS_ME_TO_SENSORS"));
					if (!flowThread.callState(state39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 75
					iristk.flow.DialogFlow.wait waitState40 = new iristk.flow.DialogFlow.wait();
					waitState40.setMsec(1000);
					if (!flowThread.callState(waitState40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 75, 24)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 77
					lookAtRandomPerson state41 = new lookAtRandomPerson();
					if (!flowThread.callState(state41, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 77, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state42 = agent.new say();
					state42.setText(translator.get("EXCITING_TIMES"));
					if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state43 = agent.new say();
					state43.setText(translator.get("HAPPY_TO_BE_HERE"));
					if (!flowThread.callState(state43, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state44 = agent.new say();
					state44.setText(translator.get("LOOKING_FORWARD_TO_GET_TO_KNOW_YOU"));
					if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 81
					lookAtRandomPerson state45 = new lookAtRandomPerson();
					if (!flowThread.callState(state45, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 81, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 82
					iristk.flow.DialogFlow.wait waitState46 = new iristk.flow.DialogFlow.wait();
					waitState46.setMsec(100);
					if (!flowThread.callState(waitState46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 82, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state47 = agent.new say();
					state47.setText(translator.get("I_BELIEVE_GREAT_THINGS_ARE_HAPPENING"));
					if (!flowThread.callState(state47, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state48 = agent.new say();
					state48.setText(translator.get("BETWEEN_ROBOTS_AND_HUMANS"));
					if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 86
					lookAtRandomPerson state49 = new lookAtRandomPerson();
					if (!flowThread.callState(state49, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 86, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 87
					iristk.flow.DialogFlow.wait waitState50 = new iristk.flow.DialogFlow.wait();
					waitState50.setMsec(200);
					if (!flowThread.callState(waitState50, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 87, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 89
					Event sendEvent51 = new Event("action.gesture");
					sendEvent51.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent51, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 89, 52)));
					iristk.situated.SystemAgentFlow.say state52 = agent.new say();
					state52.setText(translator.get("HAPPY_TO_BE_HERE"));
					if (!flowThread.callState(state52, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 29, 12));
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


	private class lookAtRandomPerson extends State {

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
					int count = getCount(2111991224) + 1;
					incrCount(2111991224);
					// Line: 96
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendOther state53 = agent.new attendOther();
						if (!flowThread.callState(state53, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 96, 32)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 99
					} else {
						// Line: 101
						boolean chosen54 = false;
						boolean matching55 = true;
						while (!chosen54 && matching55) {
							int rand56 = random(1993134103, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching55 = false;
							if (true) {
								matching55 = true;
								if (rand56 >= 0 && rand56 < 1) {
									chosen54 = true;
									iristk.situated.SystemAgentFlow.attend state57 = agent.new attend();
									state57.setX(0.5);
									state57.setY(0);
									state57.setZ(1);
									if (!flowThread.callState(state57, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 101, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching55 = true;
								if (rand56 >= 1 && rand56 < 2) {
									chosen54 = true;
									iristk.situated.SystemAgentFlow.attend state58 = agent.new attend();
									state58.setX(0.2);
									state58.setY(0);
									state58.setZ(1);
									if (!flowThread.callState(state58, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 101, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching55 = true;
								if (rand56 >= 2 && rand56 < 3) {
									chosen54 = true;
									iristk.situated.SystemAgentFlow.attend state59 = agent.new attend();
									state59.setX(-0.5);
									state59.setY(0.1);
									state59.setZ(1);
									if (!flowThread.callState(state59, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 101, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching55 = true;
								if (rand56 >= 3 && rand56 < 4) {
									chosen54 = true;
									iristk.situated.SystemAgentFlow.attend state60 = agent.new attend();
									state60.setX(-0.2);
									state60.setY(-0.1);
									state60.setZ(1);
									if (!flowThread.callState(state60, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 101, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching55 = true;
								if (rand56 >= 4 && rand56 < 5) {
									chosen54 = true;
									iristk.situated.SystemAgentFlow.attend state61 = agent.new attend();
									state61.setX(0);
									state61.setY(0);
									state61.setZ(1);
									if (!flowThread.callState(state61, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 101, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
					}
					// Line: 109
					flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 109, 13)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 95, 11));
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


	private class IdleMoves extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
			flowThread.addEventClock(4000, 8000, "timer_1130478920");
			flowThread.addEventClock(3000, 10000, "timer_123961122");
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
			count = getCount(1130478920) + 1;
			if (event.triggers("timer_1130478920")) {
				incrCount(1130478920);
				eventResult = EVENT_CONSUMED;
				EXECUTION: {
					// Line: 116
					boolean chosen62 = false;
					boolean matching63 = true;
					while (!chosen62 && matching63) {
						int rand64 = random(1404928347, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching63 = false;
						if (true) {
							matching63 = true;
							if (rand64 >= 0 && rand64 < 1) {
								chosen62 = true;
								iristk.situated.SystemAgentFlow.gesture state65 = agent.new gesture();
								state65.setAsync(true);
								state65.setName("phone_oh");
								if (!flowThread.callState(state65, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 116, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching63 = true;
							if (rand64 >= 1 && rand64 < 2) {
								chosen62 = true;
								iristk.situated.SystemAgentFlow.gesture state66 = agent.new gesture();
								state66.setAsync(true);
								state66.setName("surprise");
								if (!flowThread.callState(state66, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 116, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching63 = true;
							if (rand64 >= 2 && rand64 < 3) {
								chosen62 = true;
								iristk.situated.SystemAgentFlow.gesture state67 = agent.new gesture();
								state67.setAsync(true);
								state67.setName("shy");
								if (!flowThread.callState(state67, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 116, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching63 = true;
							if (rand64 >= 3 && rand64 < 4) {
								chosen62 = true;
								iristk.situated.SystemAgentFlow.gesture state68 = agent.new gesture();
								state68.setAsync(true);
								state68.setName("smile");
								if (!flowThread.callState(state68, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 116, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching63 = true;
							if (rand64 >= 4 && rand64 < 5) {
								chosen62 = true;
								iristk.situated.SystemAgentFlow.gesture state69 = agent.new gesture();
								state69.setAsync(true);
								state69.setName("thoughtful");
								if (!flowThread.callState(state69, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 116, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
				}
				if (eventResult != EVENT_IGNORED) return eventResult;
			}
			// Line: 124
			count = getCount(123961122) + 1;
			if (event.triggers("timer_123961122")) {
				incrCount(123961122);
				eventResult = EVENT_CONSUMED;
				EXECUTION: {
					// Line: 126
					boolean chosen70 = false;
					boolean matching71 = true;
					while (!chosen70 && matching71) {
						int rand72 = random(1227229563, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching71 = false;
						if (true) {
							matching71 = true;
							if (rand72 >= 0 && rand72 < 1) {
								chosen70 = true;
								iristk.situated.SystemAgentFlow.attend state73 = agent.new attend();
								state73.setX(0.2);
								state73.setY(0.1);
								state73.setZ(1);
								if (!flowThread.callState(state73, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 126, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching71 = true;
							if (rand72 >= 1 && rand72 < 2) {
								chosen70 = true;
								iristk.situated.SystemAgentFlow.attend state74 = agent.new attend();
								state74.setX(0.2);
								state74.setY(-0.1);
								state74.setZ(1);
								if (!flowThread.callState(state74, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 126, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching71 = true;
							if (rand72 >= 2 && rand72 < 3) {
								chosen70 = true;
								iristk.situated.SystemAgentFlow.attend state75 = agent.new attend();
								state75.setX(-0.2);
								state75.setY(0.1);
								state75.setZ(1);
								if (!flowThread.callState(state75, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 126, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching71 = true;
							if (rand72 >= 3 && rand72 < 4) {
								chosen70 = true;
								iristk.situated.SystemAgentFlow.attend state76 = agent.new attend();
								state76.setX(-0.2);
								state76.setY(-0.1);
								state76.setZ(1);
								if (!flowThread.callState(state76, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 126, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching71 = true;
							if (rand72 >= 4 && rand72 < 5) {
								chosen70 = true;
								iristk.situated.SystemAgentFlow.attend state77 = agent.new attend();
								state77.setX(0);
								state77.setY(0);
								state77.setZ(1);
								if (!flowThread.callState(state77, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 126, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					iristk.situated.SystemAgentFlow.attend state78 = agent.new attend();
					state78.setMode("eyes");
					state78.setX(0);
					state78.setY(0);
					state78.setZ(1);
					if (!flowThread.callState(state78, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 124, 33)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
				if (eventResult != EVENT_IGNORED) return eventResult;
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


}
