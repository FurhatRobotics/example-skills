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
	private Record settings;
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

	public Localizer getTranslator() {
		return this.translator;
	}

	public Record getSettings() {
		return this.settings;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("agent")) return this.agent;
		if (name.equals("translator")) return this.translator;
		if (name.equals("settings")) return this.settings;
		return null;
	}


	public PresentationFlow(iristk.situated.SystemAgentFlow agent, Localizer translator, Record settings) {
		this.agent = agent;
		this.translator = translator;
		this.settings = settings;
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
			// Line: 18
			try {
				EXECUTION: {
					int count = getCount(1305427130) + 1;
					incrCount(1305427130);
					// Line: 19
					part1 state0 = new part1();
					flowThread.gotoState(state0, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 19, 34)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 18, 18));
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
			// Line: 24
			try {
				EXECUTION: {
					int count = getCount(2067846577) + 1;
					incrCount(2067846577);
					// Line: 25
					Event sendEvent1 = new Event("action.voice");
					sendEvent1.putIfNotNull("name", settings.get("maleVoice"));
					flowRunner.sendEvent(sendEvent1, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 25, 61)));
					// Line: 26
					Event sendEvent2 = new Event("action.face.texture");
					sendEvent2.putIfNotNull("name", settings.get("defaultTexture"));
					flowRunner.sendEvent(sendEvent2, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 26, 73)));
					// Line: 27
					lookAtRandomPerson state3 = new lookAtRandomPerson();
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 27, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 28
					iristk.flow.DialogFlow.wait waitState4 = new iristk.flow.DialogFlow.wait();
					waitState4.setMsec(500);
					if (!flowThread.callState(waitState4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 28, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 30
					lookAtRandomPerson state5 = new lookAtRandomPerson();
					if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 30, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state6 = agent.new say();
					state6.setText(translator.get("HELLO_THERE"));
					if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 32
					iristk.flow.DialogFlow.wait waitState7 = new iristk.flow.DialogFlow.wait();
					waitState7.setMsec(100);
					if (!flowThread.callState(waitState7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 32, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 34
					Event sendEvent8 = new Event("action.gesture");
					sendEvent8.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 34, 52)));
					iristk.situated.SystemAgentFlow.say state9 = agent.new say();
					state9.setText(translator.get("MY_NAME_IS_FURHAT"));
					if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 36
					Event sendEvent10 = new Event("action.gesture");
					sendEvent10.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 36, 52)));
					// Line: 37
					lookAtRandomPerson state11 = new lookAtRandomPerson();
					if (!flowThread.callState(state11, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 37, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state12 = agent.new say();
					state12.setText(translator.get("I_CAN_SHOW_EMOTIONS"));
					if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 39
					Event sendEvent13 = new Event("action.gesture");
					sendEvent13.putIfNotNull("name", "emotion_anger");
					flowRunner.sendEvent(sendEvent13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 39, 60)));
					// Line: 40
					iristk.flow.DialogFlow.wait waitState14 = new iristk.flow.DialogFlow.wait();
					waitState14.setMsec(1000);
					if (!flowThread.callState(waitState14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 40, 24)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 42
					lookAtRandomPerson state15 = new lookAtRandomPerson();
					if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 42, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 43
					Event sendEvent16 = new Event("action.gesture");
					sendEvent16.putIfNotNull("name", "emotion_neutral");
					flowRunner.sendEvent(sendEvent16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 43, 61)));
					iristk.situated.SystemAgentFlow.say state17 = agent.new say();
					state17.setText(translator.get("I_CAN_HAVE_PERSONALITIES"));
					if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 45
					Event sendEvent18 = new Event("action.face.texture");
					sendEvent18.putIfNotNull("name", settings.get("femaleTexture"));
					flowRunner.sendEvent(sendEvent18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 45, 72)));
					iristk.situated.SystemAgentFlow.say state19 = agent.new say();
					state19.setText(translator.get("I_CAN_LOOK_FEMALE"));
					if (!flowThread.callState(state19, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 47
					lookAtRandomPerson state20 = new lookAtRandomPerson();
					if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 47, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 48
					Event sendEvent21 = new Event("action.voice");
					sendEvent21.putIfNotNull("name", settings.get("femaleVoice"));
					flowRunner.sendEvent(sendEvent21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 48, 63)));
					iristk.situated.SystemAgentFlow.say state22 = agent.new say();
					state22.setText(translator.get("AND_SOUND_FEMALE"));
					if (!flowThread.callState(state22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 50
					lookAtRandomPerson state23 = new lookAtRandomPerson();
					if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 50, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 51
					Event sendEvent24 = new Event("action.voice");
					sendEvent24.putIfNotNull("name", settings.get("maleVoice"));
					flowRunner.sendEvent(sendEvent24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 51, 61)));
					iristk.situated.SystemAgentFlow.say state25 = agent.new say();
					state25.setText(translator.get("OR_LIKE_ANYONE_ELSE"));
					if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 53
					lookAtRandomPerson state26 = new lookAtRandomPerson();
					if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 53, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 54
					Event sendEvent27 = new Event("action.face.texture");
					sendEvent27.putIfNotNull("name", settings.get("avatarTexture"));
					flowRunner.sendEvent(sendEvent27, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 54, 72)));
					iristk.situated.SystemAgentFlow.say state28 = agent.new say();
					state28.setText(translator.get("LIKE_AN_AVATAR"));
					if (!flowThread.callState(state28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 56
					if (eq(settings.get("maleVoice"), "william")) {
						iristk.situated.SystemAgentFlow.say state29 = agent.new say();
						StringCreator string30 = new StringCreator();
						string30.append("GESTURE_GIGGLE_2");
						state29.setText(string30.toString());
						if (!flowThread.callState(state29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 56, 49)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 58
					} else {
						iristk.situated.SystemAgentFlow.say state31 = agent.new say();
						state31.setText(translator.get("GESTURE_BREATH_IN"));
						if (!flowThread.callState(state31, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 56, 49)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 61
					lookAtRandomPerson state32 = new lookAtRandomPerson();
					if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 61, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 62
					Event sendEvent33 = new Event("action.face.texture");
					sendEvent33.putIfNotNull("name", settings.get("defaultTexture"));
					flowRunner.sendEvent(sendEvent33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 62, 73)));
					// Line: 63
					iristk.flow.DialogFlow.wait waitState34 = new iristk.flow.DialogFlow.wait();
					waitState34.setMsec(500);
					if (!flowThread.callState(waitState34, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 63, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 64
					lookAtRandomPerson state35 = new lookAtRandomPerson();
					if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 64, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state36 = agent.new say();
					state36.setText(translator.get("INSIDE_MY_CHEST_FURHATOS"));
					if (!flowThread.callState(state36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 66
					lookAtRandomPerson state37 = new lookAtRandomPerson();
					if (!flowThread.callState(state37, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 66, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state38 = agent.new say();
					state38.setText(translator.get("IT_CONNECTS_ME_TO_SENSORS"));
					if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 68
					iristk.flow.DialogFlow.wait waitState39 = new iristk.flow.DialogFlow.wait();
					waitState39.setMsec(1200);
					if (!flowThread.callState(waitState39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 68, 24)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 70
					lookAtRandomPerson state40 = new lookAtRandomPerson();
					if (!flowThread.callState(state40, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 70, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 71
					if (eq(settings.get("maleVoice"), "william")) {
						iristk.situated.SystemAgentFlow.say state41 = agent.new say();
						StringCreator string42 = new StringCreator();
						string42.append("GESTURE_SIGH_HAPPY");
						state41.setText(string42.toString());
						if (!flowThread.callState(state41, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 71, 49)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 74
					iristk.flow.DialogFlow.wait waitState43 = new iristk.flow.DialogFlow.wait();
					waitState43.setMsec(300);
					if (!flowThread.callState(waitState43, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 74, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state44 = agent.new say();
					state44.setText(translator.get("EXCITING_TIMES"));
					if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 77
					iristk.flow.DialogFlow.wait waitState45 = new iristk.flow.DialogFlow.wait();
					waitState45.setMsec(100);
					if (!flowThread.callState(waitState45, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 77, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state46 = agent.new say();
					state46.setText(translator.get("I_BELIEVE_GREAT_THINGS_ARE_HAPPENING"));
					if (!flowThread.callState(state46, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state47 = agent.new say();
					state47.setText(translator.get("BETWEEN_ROBOTS_AND_HUMANS"));
					if (!flowThread.callState(state47, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 80
					lookAtRandomPerson state48 = new lookAtRandomPerson();
					if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 80, 39)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 81
					iristk.flow.DialogFlow.wait waitState49 = new iristk.flow.DialogFlow.wait();
					waitState49.setMsec(200);
					if (!flowThread.callState(waitState49, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 81, 23)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state50 = agent.new say();
					state50.setText(translator.get("HAPPY_TO_BE_HERE"));
					if (!flowThread.callState(state50, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state51 = agent.new say();
					state51.setText(translator.get("LOOKING_FORWARD_TO_GET_TO_KNOW_YOU"));
					if (!flowThread.callState(state51, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 84
					if (settings.has("personToPassOverTo")) {
						// Line: 85
						iristk.flow.DialogFlow.wait waitState52 = new iristk.flow.DialogFlow.wait();
						waitState52.setMsec(500);
						if (!flowThread.callState(waitState52, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 85, 24)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 86
						Event sendEvent53 = new Event("action.gesture");
						sendEvent53.putIfNotNull("name", "wink");
						flowRunner.sendEvent(sendEvent53, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 86, 51)));
						iristk.situated.SystemAgentFlow.say state54 = agent.new say();
						StringCreator string55 = new StringCreator();
						string55.append("Thanks for me, over to you");
						// Line: 86
						string55.append(settings.get("personToPassOverTo"));
						state54.setText(string55.toString());
						if (!flowThread.callState(state54, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 84, 44)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 89
					Event sendEvent56 = new Event("action.gesture");
					sendEvent56.putIfNotNull("name", "smile");
					flowRunner.sendEvent(sendEvent56, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 89, 52)));
					// Line: 90
					PostPres state57 = new PostPres();
					flowThread.gotoState(state57, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 90, 28)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 24, 12));
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


	private class PostPres extends IdleMoves {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
			flowThread.addEventClock(16000, 32000, "timer_1176383635");
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
			// Line: 95
			count = getCount(1176383635) + 1;
			if (event.triggers("timer_1176383635")) {
				incrCount(1176383635);
				eventResult = EVENT_CONSUMED;
				EXECUTION: {
					// Line: 96
					if (settings.has("shouldCough") && eq(settings.get("maleVoice"), "william")) {
						// Line: 97
						boolean chosen58 = false;
						boolean matching59 = true;
						while (!chosen58 && matching59) {
							int rand60 = random(946927394, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching59 = false;
							if (true) {
								matching59 = true;
								if (rand60 >= 0 && rand60 < 1) {
									chosen58 = true;
									iristk.situated.SystemAgentFlow.say state61 = agent.new say();
									StringCreator string62 = new StringCreator();
									string62.append("GESTURE_SNIFF_1");
									state61.setText(string62.toString());
									if (!flowThread.callState(state61, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 97, 19)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching59 = true;
								if (rand60 >= 1 && rand60 < 2) {
									chosen58 = true;
									iristk.situated.SystemAgentFlow.say state63 = agent.new say();
									StringCreator string64 = new StringCreator();
									string64.append("GESTURE_COUGH_2");
									state63.setText(string64.toString());
									if (!flowThread.callState(state63, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 97, 19)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
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
			// Line: 106
			try {
				EXECUTION: {
					int count = getCount(278016223) + 1;
					incrCount(278016223);
					// Line: 107
					if (system.hasUsers()) {
						iristk.situated.SystemAgentFlow.attendOther state65 = agent.new attendOther();
						if (!flowThread.callState(state65, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 107, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 110
					} else {
						// Line: 112
						boolean chosen66 = false;
						boolean matching67 = true;
						while (!chosen66 && matching67) {
							int rand68 = random(360855489, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching67 = false;
							if (true) {
								matching67 = true;
								if (rand68 >= 0 && rand68 < 1) {
									chosen66 = true;
									iristk.situated.SystemAgentFlow.attend state69 = agent.new attend();
									state69.setX(0.5);
									state69.setY(0);
									state69.setZ(1);
									if (!flowThread.callState(state69, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching67 = true;
								if (rand68 >= 1 && rand68 < 2) {
									chosen66 = true;
									iristk.situated.SystemAgentFlow.attend state70 = agent.new attend();
									state70.setX(0.2);
									state70.setY(0);
									state70.setZ(1);
									if (!flowThread.callState(state70, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching67 = true;
								if (rand68 >= 2 && rand68 < 3) {
									chosen66 = true;
									iristk.situated.SystemAgentFlow.attend state71 = agent.new attend();
									state71.setX(-0.5);
									state71.setY(0.1);
									state71.setZ(1);
									if (!flowThread.callState(state71, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching67 = true;
								if (rand68 >= 3 && rand68 < 4) {
									chosen66 = true;
									iristk.situated.SystemAgentFlow.attend state72 = agent.new attend();
									state72.setX(-0.2);
									state72.setY(-0.1);
									state72.setZ(1);
									if (!flowThread.callState(state72, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching67 = true;
								if (rand68 >= 4 && rand68 < 5) {
									chosen66 = true;
									iristk.situated.SystemAgentFlow.attend state73 = agent.new attend();
									state73.setX(0);
									state73.setY(0);
									state73.setZ(1);
									if (!flowThread.callState(state73, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
					}
					// Line: 120
					flowThread.returnFromCall(this, null, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 120, 14)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 106, 12));
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
			flowThread.addEventClock(4000, 8000, "timer_814642064");
			flowThread.addEventClock(3000, 10000, "timer_1230715505");
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
			// Line: 125
			count = getCount(814642064) + 1;
			if (event.triggers("timer_814642064")) {
				incrCount(814642064);
				eventResult = EVENT_CONSUMED;
				EXECUTION: {
					// Line: 127
					boolean chosen74 = false;
					boolean matching75 = true;
					while (!chosen74 && matching75) {
						int rand76 = random(884369023, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching75 = false;
						if (true) {
							matching75 = true;
							if (rand76 >= 0 && rand76 < 1) {
								chosen74 = true;
								iristk.situated.SystemAgentFlow.gesture state77 = agent.new gesture();
								state77.setAsync(true);
								state77.setName("phone_oh");
								if (!flowThread.callState(state77, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 127, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching75 = true;
							if (rand76 >= 1 && rand76 < 2) {
								chosen74 = true;
								iristk.situated.SystemAgentFlow.gesture state78 = agent.new gesture();
								state78.setAsync(true);
								state78.setName("surprise");
								if (!flowThread.callState(state78, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 127, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching75 = true;
							if (rand76 >= 2 && rand76 < 3) {
								chosen74 = true;
								iristk.situated.SystemAgentFlow.gesture state79 = agent.new gesture();
								state79.setAsync(true);
								state79.setName("shy");
								if (!flowThread.callState(state79, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 127, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching75 = true;
							if (rand76 >= 3 && rand76 < 4) {
								chosen74 = true;
								iristk.situated.SystemAgentFlow.gesture state80 = agent.new gesture();
								state80.setAsync(true);
								state80.setName("smile");
								if (!flowThread.callState(state80, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 127, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching75 = true;
							if (rand76 >= 4 && rand76 < 5) {
								chosen74 = true;
								iristk.situated.SystemAgentFlow.gesture state81 = agent.new gesture();
								state81.setAsync(true);
								state81.setName("thoughtful");
								if (!flowThread.callState(state81, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 127, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
				}
				if (eventResult != EVENT_IGNORED) return eventResult;
			}
			// Line: 135
			count = getCount(1230715505) + 1;
			if (event.triggers("timer_1230715505")) {
				incrCount(1230715505);
				eventResult = EVENT_CONSUMED;
				EXECUTION: {
					// Line: 137
					boolean chosen82 = false;
					boolean matching83 = true;
					while (!chosen82 && matching83) {
						int rand84 = random(358090972, 5, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching83 = false;
						if (true) {
							matching83 = true;
							if (rand84 >= 0 && rand84 < 1) {
								chosen82 = true;
								iristk.situated.SystemAgentFlow.attend state85 = agent.new attend();
								state85.setX(0.2);
								state85.setY(0.1);
								state85.setZ(1);
								if (!flowThread.callState(state85, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 137, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching83 = true;
							if (rand84 >= 1 && rand84 < 2) {
								chosen82 = true;
								iristk.situated.SystemAgentFlow.attend state86 = agent.new attend();
								state86.setX(0.2);
								state86.setY(-0.1);
								state86.setZ(1);
								if (!flowThread.callState(state86, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 137, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching83 = true;
							if (rand84 >= 2 && rand84 < 3) {
								chosen82 = true;
								iristk.situated.SystemAgentFlow.attend state87 = agent.new attend();
								state87.setX(-0.2);
								state87.setY(0.1);
								state87.setZ(1);
								if (!flowThread.callState(state87, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 137, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching83 = true;
							if (rand84 >= 3 && rand84 < 4) {
								chosen82 = true;
								iristk.situated.SystemAgentFlow.attend state88 = agent.new attend();
								state88.setX(-0.2);
								state88.setY(-0.1);
								state88.setZ(1);
								if (!flowThread.callState(state88, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 137, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching83 = true;
							if (rand84 >= 4 && rand84 < 5) {
								chosen82 = true;
								iristk.situated.SystemAgentFlow.attend state89 = agent.new attend();
								state89.setX(0);
								state89.setY(0);
								state89.setZ(1);
								if (!flowThread.callState(state89, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 137, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					iristk.situated.SystemAgentFlow.attend state90 = agent.new attend();
					state90.setMode("eyes");
					state90.setX(0);
					state90.setY(0);
					state90.setZ(1);
					if (!flowThread.callState(state90, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\presentation\\src\\iristk\\app\\presentation\\PresentationFlow.xml"), 135, 33)))) {
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
