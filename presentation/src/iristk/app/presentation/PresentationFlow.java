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

	private Record initialParameters;
	private iristk.situated.SystemAgentFlow agent;
	private Localizer translator;
	private iristk.situated.SystemAgent system;
	private String defaultVoice;
	private String femaleVoice;
	private boolean startedFromSkill;
	private String originSkill;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
		startedFromSkill = (boolean) false;
		originSkill = asString("iristk.furhat.server.IdleSkill");
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

	public boolean getStartedFromSkill() {
		return this.startedFromSkill;
	}

	public void setStartedFromSkill(boolean value) {
		this.startedFromSkill = value;
	}

	public String getOriginSkill() {
		return this.originSkill;
	}

	public void setOriginSkill(String value) {
		this.originSkill = value;
	}

	public Record getInitialParameters() {
		return this.initialParameters;
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
		if (name.equals("startedFromSkill")) return this.startedFromSkill;
		if (name.equals("originSkill")) return this.originSkill;
		if (name.equals("initialParameters")) return this.initialParameters;
		if (name.equals("agent")) return this.agent;
		if (name.equals("translator")) return this.translator;
		return null;
	}


	public PresentationFlow(Record initialParameters, iristk.situated.SystemAgentFlow agent, Localizer translator) {
		this.initialParameters = initialParameters;
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
			// Line: 22
			try {
				EXECUTION: {
					int count = getCount(2121744517) + 1;
					incrCount(2121744517);
					// Line: 23
					defaultVoice = translator.get("MALE_VOICE");
					femaleVoice = translator.get("FEMALE_VOICE");
					// Line: 27
					part1 state0 = new part1();
					flowThread.gotoState(state0, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 27, 34)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 22, 18));
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


	public class hotStart extends State {

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
					int count = getCount(1490180672) + 1;
					incrCount(1490180672);
					// Line: 33
					if (initialParameters != null && initialParameters.has("originSkill")) {
						// Line: 34
						originSkill = asString(initialParameters.get("originSkill"));
					}
					// Line: 38
					startedFromSkill = true;
					// Line: 39
					init state1 = new init();
					flowThread.gotoState(state1, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 39, 27)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 32, 15));
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
			// Line: 44
			try {
				EXECUTION: {
					int count = getCount(517938326) + 1;
					incrCount(517938326);
					// Line: 45
					Event sendEvent2 = new Event("action.voice");
					sendEvent2.putIfNotNull("name", defaultVoice);
					flowRunner.sendEvent(sendEvent2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 45, 55)));
					// Line: 46
					Event sendEvent3 = new Event("action.face.texture");
					sendEvent3.putIfNotNull("name", "default");
					flowRunner.sendEvent(sendEvent3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 46, 59)));
					// Line: 47
					lookAtRandomPerson state4 = new lookAtRandomPerson();
					if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 47, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 48
					iristk.flow.DialogFlow.wait waitState5 = new iristk.flow.DialogFlow.wait();
					waitState5.setMsec(500);
					if (!flowThread.callState(waitState5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 48, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 50
					lookAtRandomPerson state6 = new lookAtRandomPerson();
					if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 50, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state7 = agent.new say();
					state7.setText(translator.get("HELLO_THERE"));
					if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 52
					iristk.flow.DialogFlow.wait waitState8 = new iristk.flow.DialogFlow.wait();
					waitState8.setMsec(100);
					if (!flowThread.callState(waitState8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 52, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 54
					Event sendEvent9 = new Event("action.gesture");
					sendEvent9.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 54, 52)));
					iristk.situated.SystemAgentFlow.say state10 = agent.new say();
					state10.setText(translator.get("MY_NAME_IS_FURHAT"));
					if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 56
					Event sendEvent11 = new Event("action.gesture");
					sendEvent11.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 56, 52)));
					// Line: 57
					lookAtRandomPerson state12 = new lookAtRandomPerson();
					if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 57, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state13 = agent.new say();
					state13.setText(translator.get("I_CAN_SHOW_EMOTIONS"));
					if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 59
					Event sendEvent14 = new Event("action.gesture");
					sendEvent14.putIfNotNull("name", "emotion_anger");
					flowRunner.sendEvent(sendEvent14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 59, 60)));
					// Line: 60
					iristk.flow.DialogFlow.wait waitState15 = new iristk.flow.DialogFlow.wait();
					waitState15.setMsec(1000);
					if (!flowThread.callState(waitState15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 60, 24)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 62
					lookAtRandomPerson state16 = new lookAtRandomPerson();
					if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 62, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 63
					Event sendEvent17 = new Event("action.gesture");
					sendEvent17.putIfNotNull("name", "emotion_neutral");
					flowRunner.sendEvent(sendEvent17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 63, 61)));
					iristk.situated.SystemAgentFlow.say state18 = agent.new say();
					state18.setText(translator.get("I_CAN_HAVE_PERSONALITIES"));
					if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 65
					Event sendEvent19 = new Event("action.face.texture");
					sendEvent19.putIfNotNull("name", "female");
					flowRunner.sendEvent(sendEvent19, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 65, 58)));
					iristk.situated.SystemAgentFlow.say state20 = agent.new say();
					state20.setText(translator.get("I_CAN_LOOK_FEMALE"));
					if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 67
					lookAtRandomPerson state21 = new lookAtRandomPerson();
					if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 67, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 68
					Event sendEvent22 = new Event("action.voice");
					sendEvent22.putIfNotNull("name", femaleVoice);
					flowRunner.sendEvent(sendEvent22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 68, 54)));
					iristk.situated.SystemAgentFlow.say state23 = agent.new say();
					state23.setText(translator.get("AND_SOUND_FEMALE"));
					if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 70
					lookAtRandomPerson state24 = new lookAtRandomPerson();
					if (!flowThread.callState(state24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 70, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 71
					Event sendEvent25 = new Event("action.voice");
					sendEvent25.putIfNotNull("name", defaultVoice);
					flowRunner.sendEvent(sendEvent25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 71, 55)));
					iristk.situated.SystemAgentFlow.say state26 = agent.new say();
					state26.setText(translator.get("OR_LIKE_ANYONE_ELSE"));
					if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 73
					lookAtRandomPerson state27 = new lookAtRandomPerson();
					if (!flowThread.callState(state27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 73, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 74
					Event sendEvent28 = new Event("action.face.texture");
					sendEvent28.putIfNotNull("name", "avatar");
					flowRunner.sendEvent(sendEvent28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 74, 58)));
					iristk.situated.SystemAgentFlow.say state29 = agent.new say();
					state29.setText(translator.get("LIKE_AN_AVATAR"));
					if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 76
					Event sendEvent30 = new Event("action.voice");
					sendEvent30.putIfNotNull("name", defaultVoice);
					flowRunner.sendEvent(sendEvent30, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 76, 55)));
					// Line: 77
					if (eq(defaultVoice, "william")) {
						iristk.situated.SystemAgentFlow.say state31 = agent.new say();
						StringCreator string32 = new StringCreator();
						string32.append("GESTURE_GIGGLE_2");
						state31.setText(string32.toString());
						if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 77, 43)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 79
					} else {
						iristk.situated.SystemAgentFlow.say state33 = agent.new say();
						state33.setText(translator.get("GESTURE_GIGGLE_FALLBACK"));
						if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 77, 43)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 82
					lookAtRandomPerson state34 = new lookAtRandomPerson();
					if (!flowThread.callState(state34, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 82, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 83
					Event sendEvent35 = new Event("action.face.texture");
					sendEvent35.putIfNotNull("name", "default");
					flowRunner.sendEvent(sendEvent35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 83, 59)));
					// Line: 84
					iristk.flow.DialogFlow.wait waitState36 = new iristk.flow.DialogFlow.wait();
					waitState36.setMsec(500);
					if (!flowThread.callState(waitState36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 84, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 86
					lookAtRandomPerson state37 = new lookAtRandomPerson();
					if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 86, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state38 = agent.new say();
					state38.setText(translator.get("INSIDE_MY_CHEST_FURHATOS"));
					if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 88
					lookAtRandomPerson state39 = new lookAtRandomPerson();
					if (!flowThread.callState(state39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 88, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state40 = agent.new say();
					state40.setText(translator.get("IT_CONNECTS_ME_TO_SENSORS"));
					if (!flowThread.callState(state40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 90
					iristk.flow.DialogFlow.wait waitState41 = new iristk.flow.DialogFlow.wait();
					waitState41.setMsec(1000);
					if (!flowThread.callState(waitState41, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 90, 24)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 92
					lookAtRandomPerson state42 = new lookAtRandomPerson();
					if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 92, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state43 = agent.new say();
					state43.setText(translator.get("EXCITING_TIMES"));
					if (!flowThread.callState(state43, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state44 = agent.new say();
					state44.setText(translator.get("HAPPY_TO_BE_HERE"));
					if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state45 = agent.new say();
					state45.setText(translator.get("LOOKING_FORWARD_TO_GET_TO_KNOW_YOU"));
					if (!flowThread.callState(state45, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 96
					lookAtRandomPerson state46 = new lookAtRandomPerson();
					if (!flowThread.callState(state46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 96, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 97
					iristk.flow.DialogFlow.wait waitState47 = new iristk.flow.DialogFlow.wait();
					waitState47.setMsec(100);
					if (!flowThread.callState(waitState47, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 97, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state48 = agent.new say();
					state48.setText(translator.get("I_BELIEVE_GREAT_THINGS_ARE_HAPPENING"));
					if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state49 = agent.new say();
					state49.setText(translator.get("BETWEEN_ROBOTS_AND_HUMANS"));
					if (!flowThread.callState(state49, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 101
					lookAtRandomPerson state50 = new lookAtRandomPerson();
					if (!flowThread.callState(state50, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 101, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 102
					iristk.flow.DialogFlow.wait waitState51 = new iristk.flow.DialogFlow.wait();
					waitState51.setMsec(200);
					if (!flowThread.callState(waitState51, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 102, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 104
					Event sendEvent52 = new Event("action.gesture");
					sendEvent52.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent52, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 104, 52)));
					iristk.situated.SystemAgentFlow.say state53 = agent.new say();
					state53.setText(translator.get("HAPPY_TO_BE_HERE"));
					if (!flowThread.callState(state53, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 106
					if (startedFromSkill) {
						// Line: 107
						Event sendEvent54 = new Event("action.skill");
						sendEvent54.putIfNotNull("entry", originSkill);
						flowRunner.sendEvent(sendEvent54, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 107, 55)));
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 44, 12));
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
			// Line: 113
			try {
				EXECUTION: {
					int count = getCount(305623748) + 1;
					incrCount(305623748);
					// Line: 114
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendOther state55 = agent.new attendOther();
						if (!flowThread.callState(state55, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 114, 32)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 117
					} else {
						// Line: 119
						boolean chosen56 = false;
						boolean matching57 = true;
						while (!chosen56 && matching57) {
							int rand58 = random(1521118594, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching57 = false;
							if (true) {
								matching57 = true;
								if (rand58 >= 0 && rand58 < 1) {
									chosen56 = true;
									iristk.situated.SystemAgentFlow.attend state59 = agent.new attend();
									state59.setX(0.5);
									state59.setY(0);
									state59.setZ(1);
									if (!flowThread.callState(state59, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 119, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching57 = true;
								if (rand58 >= 1 && rand58 < 2) {
									chosen56 = true;
									iristk.situated.SystemAgentFlow.attend state60 = agent.new attend();
									state60.setX(0.2);
									state60.setY(0);
									state60.setZ(1);
									if (!flowThread.callState(state60, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 119, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching57 = true;
								if (rand58 >= 2 && rand58 < 3) {
									chosen56 = true;
									iristk.situated.SystemAgentFlow.attend state61 = agent.new attend();
									state61.setX(-0.5);
									state61.setY(0.1);
									state61.setZ(1);
									if (!flowThread.callState(state61, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 119, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching57 = true;
								if (rand58 >= 3 && rand58 < 4) {
									chosen56 = true;
									iristk.situated.SystemAgentFlow.attend state62 = agent.new attend();
									state62.setX(-0.2);
									state62.setY(-0.1);
									state62.setZ(1);
									if (!flowThread.callState(state62, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 119, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching57 = true;
								if (rand58 >= 4 && rand58 < 5) {
									chosen56 = true;
									iristk.situated.SystemAgentFlow.attend state63 = agent.new attend();
									state63.setX(0);
									state63.setY(0);
									state63.setZ(1);
									if (!flowThread.callState(state63, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 119, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
					}
					// Line: 127
					flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 127, 13)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 113, 11));
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
			flowThread.addEventClock(4000, 8000, "timer_1617791695");
			flowThread.addEventClock(3000, 10000, "timer_1192108080");
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
			// Line: 132
			count = getCount(1617791695) + 1;
			if (event.triggers("timer_1617791695")) {
				incrCount(1617791695);
				eventResult = EVENT_CONSUMED;
				EXECUTION: {
					// Line: 134
					boolean chosen64 = false;
					boolean matching65 = true;
					while (!chosen64 && matching65) {
						int rand66 = random(125993742, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching65 = false;
						if (true) {
							matching65 = true;
							if (rand66 >= 0 && rand66 < 1) {
								chosen64 = true;
								iristk.situated.SystemAgentFlow.gesture state67 = agent.new gesture();
								state67.setAsync(true);
								state67.setName("phone_oh");
								if (!flowThread.callState(state67, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 134, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching65 = true;
							if (rand66 >= 1 && rand66 < 2) {
								chosen64 = true;
								iristk.situated.SystemAgentFlow.gesture state68 = agent.new gesture();
								state68.setAsync(true);
								state68.setName("surprise");
								if (!flowThread.callState(state68, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 134, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching65 = true;
							if (rand66 >= 2 && rand66 < 3) {
								chosen64 = true;
								iristk.situated.SystemAgentFlow.gesture state69 = agent.new gesture();
								state69.setAsync(true);
								state69.setName("shy");
								if (!flowThread.callState(state69, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 134, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching65 = true;
							if (rand66 >= 3 && rand66 < 4) {
								chosen64 = true;
								iristk.situated.SystemAgentFlow.gesture state70 = agent.new gesture();
								state70.setAsync(true);
								state70.setName("smile");
								if (!flowThread.callState(state70, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 134, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching65 = true;
							if (rand66 >= 4 && rand66 < 5) {
								chosen64 = true;
								iristk.situated.SystemAgentFlow.gesture state71 = agent.new gesture();
								state71.setAsync(true);
								state71.setName("thoughtful");
								if (!flowThread.callState(state71, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 134, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
				}
				if (eventResult != EVENT_IGNORED) return eventResult;
			}
			// Line: 142
			count = getCount(1192108080) + 1;
			if (event.triggers("timer_1192108080")) {
				incrCount(1192108080);
				eventResult = EVENT_CONSUMED;
				EXECUTION: {
					// Line: 144
					boolean chosen72 = false;
					boolean matching73 = true;
					while (!chosen72 && matching73) {
						int rand74 = random(1068824137, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching73 = false;
						if (true) {
							matching73 = true;
							if (rand74 >= 0 && rand74 < 1) {
								chosen72 = true;
								iristk.situated.SystemAgentFlow.attend state75 = agent.new attend();
								state75.setX(0.2);
								state75.setY(0.1);
								state75.setZ(1);
								if (!flowThread.callState(state75, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 144, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching73 = true;
							if (rand74 >= 1 && rand74 < 2) {
								chosen72 = true;
								iristk.situated.SystemAgentFlow.attend state76 = agent.new attend();
								state76.setX(0.2);
								state76.setY(-0.1);
								state76.setZ(1);
								if (!flowThread.callState(state76, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 144, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching73 = true;
							if (rand74 >= 2 && rand74 < 3) {
								chosen72 = true;
								iristk.situated.SystemAgentFlow.attend state77 = agent.new attend();
								state77.setX(-0.2);
								state77.setY(0.1);
								state77.setZ(1);
								if (!flowThread.callState(state77, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 144, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching73 = true;
							if (rand74 >= 3 && rand74 < 4) {
								chosen72 = true;
								iristk.situated.SystemAgentFlow.attend state78 = agent.new attend();
								state78.setX(-0.2);
								state78.setY(-0.1);
								state78.setZ(1);
								if (!flowThread.callState(state78, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 144, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching73 = true;
							if (rand74 >= 4 && rand74 < 5) {
								chosen72 = true;
								iristk.situated.SystemAgentFlow.attend state79 = agent.new attend();
								state79.setX(0);
								state79.setY(0);
								state79.setZ(1);
								if (!flowThread.callState(state79, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 144, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					iristk.situated.SystemAgentFlow.attend state80 = agent.new attend();
					state80.setMode("eyes");
					state80.setX(0);
					state80.setY(0);
					state80.setZ(1);
					if (!flowThread.callState(state80, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 142, 33)))) {
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
