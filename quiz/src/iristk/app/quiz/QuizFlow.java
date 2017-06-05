package iristk.app.quiz;

import java.util.List;
import java.io.File;
import iristk.xml.XmlMarshaller.XMLLocation;
import iristk.system.Event;
import iristk.flow.*;
import iristk.util.Record;
import static iristk.util.Converters.*;
import static iristk.flow.State.*;

public class QuizFlow extends iristk.flow.Flow {

	private Record initialParameters;
	private QuestionSet questions;
	private iristk.situated.SystemAgentFlow agent;
	private iristk.situated.SystemAgent system;
	private Question question;
	private int guess;
	private int winningScore;
	private boolean startedFromSkill;
	private boolean allowBargein;
	private String originSkill;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
		guess = asInteger(0);
		winningScore = asInteger(3);
		startedFromSkill = (boolean) false;
		allowBargein = (boolean) false;
		originSkill = asString("iristk.furhat.server.IdleSkill");
	}

	public iristk.situated.SystemAgent getSystem() {
		return this.system;
	}

	public void setSystem(iristk.situated.SystemAgent value) {
		this.system = value;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question value) {
		this.question = value;
	}

	public int getGuess() {
		return this.guess;
	}

	public void setGuess(int value) {
		this.guess = value;
	}

	public int getWinningScore() {
		return this.winningScore;
	}

	public void setWinningScore(int value) {
		this.winningScore = value;
	}

	public boolean getStartedFromSkill() {
		return this.startedFromSkill;
	}

	public void setStartedFromSkill(boolean value) {
		this.startedFromSkill = value;
	}

	public boolean getAllowBargein() {
		return this.allowBargein;
	}

	public void setAllowBargein(boolean value) {
		this.allowBargein = value;
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

	public QuestionSet getQuestions() {
		return this.questions;
	}

	public iristk.situated.SystemAgentFlow getAgent() {
		return this.agent;
	}

	@Override
	public Object getVariable(String name) {
		if (name.equals("system")) return this.system;
		if (name.equals("question")) return this.question;
		if (name.equals("guess")) return this.guess;
		if (name.equals("winningScore")) return this.winningScore;
		if (name.equals("startedFromSkill")) return this.startedFromSkill;
		if (name.equals("allowBargein")) return this.allowBargein;
		if (name.equals("originSkill")) return this.originSkill;
		if (name.equals("initialParameters")) return this.initialParameters;
		if (name.equals("questions")) return this.questions;
		if (name.equals("agent")) return this.agent;
		return null;
	}


	public QuizFlow(Record initialParameters, QuestionSet questions, iristk.situated.SystemAgentFlow agent) {
		this.initialParameters = initialParameters;
		this.questions = questions;
		this.agent = agent;
		initVariables();
	}

	@Override
	public State getInitialState() {return new Idle();}


	public class Idle extends State implements Initial {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 23
			try {
				EXECUTION: {
					int count = getCount(1489991035) + 1;
					incrCount(1489991035);
					// Line: 24
					if (system.hasUsers()) {
						// Line: 25
						StartGame state0 = new StartGame();
						flowThread.gotoState(state0, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 25, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 23, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 29
			try {
				count = getCount(1259459810) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1259459810);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 30
						StartGame state1 = new StartGame();
						flowThread.gotoState(state1, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 30, 29)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 29, 36));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	public class play extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 36
			try {
				EXECUTION: {
					int count = getCount(1174363353) + 1;
					incrCount(1174363353);
					// Line: 37
					if (initialParameters != null && initialParameters.has("originSkill")) {
						// Line: 38
						originSkill = asString(initialParameters.get("originSkill"));
					}
					// Line: 42
					startedFromSkill = true;
					// Line: 45
					StartGame state2 = new StartGame();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 45, 29)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 36, 12));
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


	private class StartGame extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 50
			try {
				EXECUTION: {
					int count = getCount(37311834) + 1;
					incrCount(37311834);
					iristk.situated.SystemAgentFlow.say state3 = agent.new say();
					StringCreator string4 = new StringCreator();
					string4.append("Let's play, the Fur hat quiz.");
					state3.setText(string4.toString());
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 50, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 52
					system.putUsers("score", 0);
					// Line: 53
					if (system.getNumUsers() > 1) {
						iristk.situated.SystemAgentFlow.say state5 = agent.new say();
						StringCreator string6 = new StringCreator();
						string6.append("The first to reach");
						// Line: 53
						string6.append(winningScore);
						string6.append("points is the winner");
						state5.setText(string6.toString());
						if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 53, 40)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 55
					} else {
						iristk.situated.SystemAgentFlow.say state7 = agent.new say();
						StringCreator string8 = new StringCreator();
						string8.append("You win if you reach");
						// Line: 55
						string8.append(winningScore);
						string8.append("points!");
						state7.setText(string8.toString());
						if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 53, 40)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.situated.SystemAgentFlow.say state9 = agent.new say();
					StringCreator string10 = new StringCreator();
					string10.append("Here comes the first question");
					state9.setText(string10.toString());
					if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 50, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 60
					question = questions.next();
					// Line: 61
					ReadQuestion state11 = new ReadQuestion();
					flowThread.gotoState(state11, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 61, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 50, 12));
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


	private class EndGame extends State {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 66
			try {
				EXECUTION: {
					int count = getCount(2139816491) + 1;
					incrCount(2139816491);
					iristk.situated.SystemAgentFlow.say state12 = agent.new say();
					StringCreator string13 = new StringCreator();
					string13.append("Would you like to play again?");
					state12.setText(string13.toString());
					if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 66, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 68
					Event raiseEvent14 = new Event("keepListening");
					if (flowThread.raiseEvent(raiseEvent14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 68, 34))) == State.EVENT_ABORTED) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 66, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 71
			try {
				count = getCount(47349011) + 1;
				if (event.triggers("keepListening")) {
					incrCount(47349011);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.listen state15 = agent.new listen();
						if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 71, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 73
						Event raiseEvent16 = new Event("keepListening");
						if (flowThread.raiseEvent(raiseEvent16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 73, 37))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 71, 33));
			}
			// Line: 76
			try {
				count = getCount(526789118) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(526789118);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state17 = agent.new say();
							StringCreator string18 = new StringCreator();
							string18.append("Great");
							state17.setText(string18.toString());
							if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 76, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 78
							StartGame state19 = new StartGame();
							flowThread.gotoState(state19, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 78, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 76, 58));
			}
			// Line: 81
			try {
				count = getCount(1429766963) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no") || event.has("sem:goodbye")) {
						incrCount(1429766963);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state20 = agent.new say();
							StringCreator string21 = new StringCreator();
							string21.append("Okay, goodbye.");
							state20.setText(string21.toString());
							if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 81, 85)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 83
							Event sendEvent22 = new Event("action.skill");
							sendEvent22.putIfNotNull("entry", originSkill);
							flowRunner.sendEvent(sendEvent22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 83, 63)));
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 81, 85));
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
			// Line: 88
			try {
				count = getCount(1108889602) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event) || !system.hasUsers()) {
						incrCount(1108889602);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 89
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state23 = agent.new attendRandom();
								if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 89, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 91
								NextQuestion state24 = new NextQuestion();
								flowThread.gotoState(state24, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 91, 34)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 92
							} else {
								iristk.situated.SystemAgentFlow.say state25 = agent.new say();
								StringCreator string26 = new StringCreator();
								string26.append("Goodbye");
								state25.setText(string26.toString());
								if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 89, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 94
								Idle state27 = new Idle();
								flowThread.gotoState(state27, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 94, 25)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 88, 92));
			}
			// Line: 98
			try {
				count = getCount(1251672723) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1251672723);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 99
							Event sendEvent28 = new Event("action.gesture");
							sendEvent28.putIfNotNull("name", "smile");
							flowRunner.sendEvent(sendEvent28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 51)));
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 98, 102));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class NextQuestion extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 104
			try {
				EXECUTION: {
					int count = getCount(803248033) + 1;
					incrCount(803248033);
					// Line: 105
					question = questions.next(); guess = 0;
					// Line: 106
					boolean chosen29 = false;
					boolean matching30 = true;
					while (!chosen29 && matching30) {
						int rand31 = random(21820739, 10, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching30 = false;
						if (true) {
							matching30 = true;
							if (rand31 >= 0 && rand31 < 1) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state32 = agent.new say();
								StringCreator string33 = new StringCreator();
								StringCreator string34 = new StringCreator();
								string34.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_05\">");
								string34.append("ehm");
								string34.append("</spurt>");
								string33.append(string34.toString());
								state32.setText(string33.toString());
								if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 1 && rand31 < 2) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state35 = agent.new say();
								StringCreator string36 = new StringCreator();
								StringCreator string37 = new StringCreator();
								string37.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_06\">");
								string37.append("ehm");
								string37.append("</spurt>");
								string36.append(string37.toString());
								state35.setText(string36.toString());
								if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 2 && rand31 < 3) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state38 = agent.new say();
								StringCreator string39 = new StringCreator();
								StringCreator string40 = new StringCreator();
								string40.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_04\">");
								string40.append("ehm");
								string40.append("</spurt>");
								string39.append(string40.toString());
								state38.setText(string39.toString());
								if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 3 && rand31 < 4) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state41 = agent.new say();
								StringCreator string42 = new StringCreator();
								StringCreator string43 = new StringCreator();
								string43.append("<spurt audio=\"free_dialogue/ehm_taketurn_01\">");
								string43.append("ehm");
								string43.append("</spurt>");
								string42.append(string43.toString());
								state41.setText(string42.toString());
								if (!flowThread.callState(state41, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 4 && rand31 < 5) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state44 = agent.new say();
								StringCreator string45 = new StringCreator();
								StringCreator string46 = new StringCreator();
								string46.append("<spurt audio=\"free_dialogue/ehm_taketurn_02\">");
								string46.append("ehm");
								string46.append("</spurt>");
								string45.append(string46.toString());
								state44.setText(string45.toString());
								if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 5 && rand31 < 6) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state47 = agent.new say();
								StringCreator string48 = new StringCreator();
								StringCreator string49 = new StringCreator();
								string49.append("<spurt audio=\"free_dialogue/ehm_hesitation_01\">");
								string49.append("ehm");
								string49.append("</spurt>");
								string48.append(string49.toString());
								state47.setText(string48.toString());
								if (!flowThread.callState(state47, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 6 && rand31 < 7) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state50 = agent.new say();
								StringCreator string51 = new StringCreator();
								StringCreator string52 = new StringCreator();
								string52.append("<spurt audio=\"free_dialogue/ehm_hesitation_02\">");
								string52.append("ehm");
								string52.append("</spurt>");
								string51.append(string52.toString());
								state50.setText(string51.toString());
								if (!flowThread.callState(state50, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 7 && rand31 < 8) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state53 = agent.new say();
								StringCreator string54 = new StringCreator();
								StringCreator string55 = new StringCreator();
								string55.append("<spurt audio=\"free_dialogue/ehm_hesitation_03\">");
								string55.append("ehm");
								string55.append("</spurt>");
								string54.append(string55.toString());
								state53.setText(string54.toString());
								if (!flowThread.callState(state53, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 8 && rand31 < 9) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state56 = agent.new say();
								StringCreator string57 = new StringCreator();
								StringCreator string58 = new StringCreator();
								string58.append("<spurt audio=\"free_dialogue/ehm_hesitation_05\">");
								string58.append("ehm");
								string58.append("</spurt>");
								string57.append(string58.toString());
								state56.setText(string57.toString());
								if (!flowThread.callState(state56, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching30 = true;
							if (rand31 >= 9 && rand31 < 10) {
								chosen29 = true;
								iristk.situated.SystemAgentFlow.say state59 = agent.new say();
								StringCreator string60 = new StringCreator();
								StringCreator string61 = new StringCreator();
								string61.append("<spurt audio=\"free_dialogue/ehm_hesitation_06\">");
								string61.append("ehm");
								string61.append("</spurt>");
								string60.append(string61.toString());
								state59.setText(string60.toString());
								if (!flowThread.callState(state59, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 106, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					// Line: 118
					if (system.hasManyUsers()) {
						// Line: 119
						boolean chosen62 = false;
						boolean matching63 = true;
						while (!chosen62 && matching63) {
							int rand64 = random(208051859, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching63 = false;
							if (true) {
								matching63 = true;
								if (rand64 >= 0 && rand64 < 1) {
									chosen62 = true;
									iristk.situated.SystemAgentFlow.attendAll state65 = agent.new attendAll();
									if (!flowThread.callState(state65, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 119, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									iristk.situated.SystemAgentFlow.say state66 = agent.new say();
									StringCreator string67 = new StringCreator();
									string67.append("The next category is");
									// Line: 119
									string67.append(question.getCategory());
									state66.setText(string67.toString());
									if (!flowThread.callState(state66, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 119, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									iristk.situated.SystemAgentFlow.say state68 = agent.new say();
									StringCreator string69 = new StringCreator();
									string69.append("Let's see who answers first");
									state68.setText(string69.toString());
									if (!flowThread.callState(state68, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 119, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching63 = true;
								if (rand64 >= 1 && rand64 < 2) {
									chosen62 = true;
									iristk.situated.SystemAgentFlow.attendOther state70 = agent.new attendOther();
									if (!flowThread.callState(state70, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 119, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 127
									if (system.getCurrentUser().has("name")) {
										iristk.situated.SystemAgentFlow.say state71 = agent.new say();
										StringCreator string72 = new StringCreator();
										string72.append("The next one is for you");
										// Line: 127
										string72.append(system.getCurrentUser().get("name"));
										state71.setText(string72.toString());
										if (!flowThread.callState(state71, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 127, 54)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
									iristk.situated.SystemAgentFlow.say state73 = agent.new say();
									StringCreator string74 = new StringCreator();
									string74.append("Let's see what you know about");
									// Line: 127
									string74.append(question.getCategory());
									state73.setText(string74.toString());
									if (!flowThread.callState(state73, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 119, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 133
					} else {
						iristk.situated.SystemAgentFlow.say state75 = agent.new say();
						StringCreator string76 = new StringCreator();
						string76.append("The next category is");
						// Line: 133
						string76.append(question.getCategory());
						state75.setText(string76.toString());
						if (!flowThread.callState(state75, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 118, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 136
					ReadQuestion state77 = new ReadQuestion();
					flowThread.gotoState(state77, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 136, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 104, 12));
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


	private class ReadQuestion extends AwaitAnswer {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 141
			try {
				EXECUTION: {
					int count = getCount(750239174) + 1;
					incrCount(750239174);
					// Line: 142
					if (allowBargein) {
						iristk.situated.SystemAgentFlow.prompt state78 = agent.new prompt();
						state78.setContext("default " + question.getId());
						state78.setText(question.getFullQuestion());
						if (!flowThread.callState(state78, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 142, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 144
					} else {
						iristk.situated.SystemAgentFlow.say state79 = agent.new say();
						state79.setText(question.getFullQuestion());
						if (!flowThread.callState(state79, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 142, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state80 = agent.new listen();
						state80.setContext("quiz " + question.getId());
						if (!flowThread.callState(state80, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 142, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 141, 12));
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


	private class ReadOptions extends AwaitAnswer {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 152
			try {
				EXECUTION: {
					int count = getCount(918731417) + 1;
					incrCount(918731417);
					// Line: 153
					if (allowBargein) {
						iristk.situated.SystemAgentFlow.prompt state81 = agent.new prompt();
						state81.setContext("default " + question.getId());
						state81.setText(question.getOptions());
						if (!flowThread.callState(state81, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 153, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 155
					} else {
						iristk.situated.SystemAgentFlow.say state82 = agent.new say();
						state82.setText(question.getOptions());
						if (!flowThread.callState(state82, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 153, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state83 = agent.new listen();
						state83.setContext("quiz " + question.getId());
						if (!flowThread.callState(state83, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 153, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 152, 12));
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


	private class AwaitAnswer extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 163
			try {
				EXECUTION: {
					int count = getCount(2120525806) + 1;
					incrCount(2120525806);
					iristk.situated.SystemAgentFlow.listen state84 = agent.new listen();
					state84.setContext("quiz " + question.getId());
					if (!flowThread.callState(state84, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 163, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 163, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 168
			try {
				count = getCount(1349092374) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:goodbye")) {
						incrCount(1349092374);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state85 = agent.new say();
							StringCreator string86 = new StringCreator();
							string86.append("Okay, goodbye.");
							state85.setText(string86.toString());
							if (!flowThread.callState(state85, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 168, 68)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 170
							if (startedFromSkill) {
								// Line: 171
								Event sendEvent87 = new Event("action.skill");
								sendEvent87.putIfNotNull("entry", originSkill);
								flowRunner.sendEvent(sendEvent87, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 171, 67)));
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 168, 68));
			}
			// Line: 175
			try {
				count = getCount(111708280) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(111708280);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 176
						if (question.isCorrect(event.get("all:0:sem:answer"))) {
							iristk.situated.SystemAgentFlow.attend state88 = agent.new attend();
							state88.setTarget(event.get("all:0:user"));
							if (!flowThread.callState(state88, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 176, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 178
							CorrectAnswer state89 = new CorrectAnswer();
							flowThread.gotoState(state89, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 178, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 179
						} else if (question.isCorrect(event.get("all:1:sem:answer"))) {
							iristk.situated.SystemAgentFlow.attend state90 = agent.new attend();
							state90.setTarget(event.get("all:1:user"));
							if (!flowThread.callState(state90, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 176, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 181
							CorrectAnswer state91 = new CorrectAnswer();
							flowThread.gotoState(state91, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 181, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 182
						} else {
							iristk.situated.SystemAgentFlow.say state92 = agent.new say();
							StringCreator string93 = new StringCreator();
							string93.append("None of you were correct, let's try another question.");
							state92.setText(string93.toString());
							if (!flowThread.callState(state92, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 176, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 184
							NextQuestion state94 = new NextQuestion();
							flowThread.gotoState(state94, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 184, 33)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 175, 42));
			}
			// Line: 188
			try {
				count = getCount(66353284) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(66353284);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 189
							boolean chosen95 = false;
							boolean matching96 = true;
							while (!chosen95 && matching96) {
								int rand97 = random(1526586616, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching96 = false;
								if (true) {
									matching96 = true;
									if (rand97 >= 0 && rand97 < 1) {
										chosen95 = true;
										iristk.situated.SystemAgentFlow.say state98 = agent.new say();
										StringCreator string99 = new StringCreator();
										string99.append("So, what is your guess?");
										state98.setText(string99.toString());
										if (!flowThread.callState(state98, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 189, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 192
							AwaitAnswer state100 = new AwaitAnswer();
							flowThread.gotoState(state100, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 192, 31)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 188, 58));
			}
			// Line: 195
			try {
				count = getCount(1164238865) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:dontknow") || event.has("sem:no")) {
						incrCount(1164238865);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 196
							Event raiseEvent101 = new Event("skip");
							if (flowThread.raiseEvent(raiseEvent101, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 196, 25))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 195, 80));
			}
			// Line: 199
			try {
				count = getCount(1373276715) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_repeat")) {
						incrCount(1373276715);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state102 = agent.new say();
							StringCreator string103 = new StringCreator();
							string103.append("Okay");
							state102.setText(string103.toString());
							if (!flowThread.callState(state102, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 199, 65)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 201
							ReadQuestion state104 = new ReadQuestion();
							flowThread.gotoState(state104, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 201, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 199, 65));
			}
			// Line: 204
			try {
				count = getCount(1152168079) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_repeat_options")) {
						incrCount(1152168079);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state105 = agent.new say();
							StringCreator string106 = new StringCreator();
							string106.append("Okay, here are the options");
							state105.setText(string106.toString());
							if (!flowThread.callState(state105, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 204, 73)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 206
							ReadOptions state107 = new ReadOptions();
							flowThread.gotoState(state107, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 206, 31)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 204, 73));
			}
			// Line: 209
			try {
				count = getCount(9703081) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_quiz_stop")) {
						incrCount(9703081);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state108 = agent.new say();
							StringCreator string109 = new StringCreator();
							string109.append("Okay, let's stop playing");
							state108.setText(string109.toString());
							if (!flowThread.callState(state108, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 209, 68)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 211
							EndGame state110 = new EndGame();
							flowThread.gotoState(state110, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 211, 27)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 209, 68));
			}
			// Line: 214
			try {
				count = getCount(1384459022) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1384459022);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 215
						if (system.isAttendingAll()) {
							iristk.situated.SystemAgentFlow.attend state111 = agent.new attend();
							state111.setTarget(event.get("user"));
							if (!flowThread.callState(state111, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 215, 39)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 218
						if (question.isCorrect(event.get("sem:answer"))) {
							// Line: 219
							CorrectAnswer state112 = new CorrectAnswer();
							flowThread.gotoState(state112, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 219, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 220
						} else {
							// Line: 221
							IncorrectAnswer state113 = new IncorrectAnswer();
							flowThread.gotoState(state113, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 221, 36)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 214, 36));
			}
			// Line: 225
			try {
				count = getCount(1727437809) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(1727437809);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attendOther state114 = agent.new attendOther();
						state114.setMode("eyes");
						if (!flowThread.callState(state114, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 225, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 227
						Event sendEvent115 = new Event("action.gesture");
						sendEvent115.putIfNotNull("name", "express_disgust");
						flowRunner.sendEvent(sendEvent115, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 227, 61)));
						iristk.situated.SystemAgentFlow.say state116 = agent.new say();
						StringCreator string117 = new StringCreator();
						string117.append("You were not supposed to answer that");
						state116.setText(string117.toString());
						if (!flowThread.callState(state116, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 225, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 229
						if (question.isCorrect(event.get("sem:answer"))) {
							iristk.situated.SystemAgentFlow.say state118 = agent.new say();
							StringCreator string119 = new StringCreator();
							string119.append("You also gave away the answer, which is not so smart");
							state118.setText(string119.toString());
							if (!flowThread.callState(state118, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 229, 52)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						iristk.situated.SystemAgentFlow.attendOther state120 = agent.new attendOther();
						state120.setMode("eyes");
						if (!flowThread.callState(state120, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 225, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.say state121 = agent.new say();
						StringCreator string122 = new StringCreator();
						string122.append("So, what is your answer?");
						state121.setText(string122.toString());
						if (!flowThread.callState(state121, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 225, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 234
						AwaitAnswer state123 = new AwaitAnswer();
						flowThread.gotoState(state123, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 234, 31)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 225, 41));
			}
			// Line: 237
			try {
				count = getCount(850202761) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(850202761);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 238
						Event raiseEvent124 = new Event("skip");
						if (flowThread.raiseEvent(raiseEvent124, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 238, 25))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 237, 38));
			}
			// Line: 241
			try {
				count = getCount(700611728) + 1;
				if (event.triggers("skip")) {
					incrCount(700611728);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 242
						if (system.hasManyUsers()) {
							iristk.situated.SystemAgentFlow.attendOther state125 = agent.new attendOther();
							if (!flowThread.callState(state125, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 242, 37)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state126 = agent.new say();
							StringCreator string127 = new StringCreator();
							string127.append("Maybe you know the answer?");
							state126.setText(string127.toString());
							if (!flowThread.callState(state126, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 242, 37)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 245
							AwaitAnswer state128 = new AwaitAnswer();
							flowThread.gotoState(state128, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 245, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 246
						} else {
							// Line: 247
							boolean chosen129 = false;
							boolean matching130 = true;
							while (!chosen129 && matching130) {
								int rand131 = random(1409120662, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching130 = false;
								if (true) {
									matching130 = true;
									if (rand131 >= 0 && rand131 < 1) {
										chosen129 = true;
										iristk.situated.SystemAgentFlow.say state132 = agent.new say();
										StringCreator string133 = new StringCreator();
										string133.append("Come on, make an educated guess");
										state132.setText(string133.toString());
										if (!flowThread.callState(state132, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 247, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching130 = true;
									if (rand131 >= 1 && rand131 < 2) {
										chosen129 = true;
										iristk.situated.SystemAgentFlow.say state134 = agent.new say();
										StringCreator string135 = new StringCreator();
										string135.append("You could at least try");
										state134.setText(string135.toString());
										if (!flowThread.callState(state134, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 247, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching130 = true;
									if (rand131 >= 2 && rand131 < 3) {
										chosen129 = true;
										iristk.situated.SystemAgentFlow.say state136 = agent.new say();
										StringCreator string137 = new StringCreator();
										string137.append("Why don't you make a guess");
										state136.setText(string137.toString());
										if (!flowThread.callState(state136, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 247, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 252
							AwaitAnswer state138 = new AwaitAnswer();
							flowThread.gotoState(state138, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 252, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 241, 24));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class CorrectAnswer extends Dialog {

		final State currentState = this;

		public int score = asInteger(system.getCurrentUser().incrInteger("score"));

		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 259
			try {
				EXECUTION: {
					int count = getCount(1321410147) + 1;
					incrCount(1321410147);
					// Line: 260
					system.getCurrentUser().putIfNotNull("score", score);
					iristk.situated.SystemAgentFlow.say state139 = agent.new say();
					StringCreator string140 = new StringCreator();
					string140.append("That is correct, you now have a score of");
					// Line: 260
					string140.append(score);
					state139.setText(string140.toString());
					if (!flowThread.callState(state139, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 259, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 262
					if (score >= winningScore) {
						// Line: 263
						Winner state141 = new Winner();
						flowThread.gotoState(state141, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 263, 27)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 264
					} else if (score == winningScore - 1) {
						// Line: 265
						boolean chosen142 = false;
						boolean matching143 = true;
						while (!chosen142 && matching143) {
							int rand144 = random(717051752, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching143 = false;
							if (true) {
								matching143 = true;
								if (rand144 >= 0 && rand144 < 1) {
									chosen142 = true;
									iristk.situated.SystemAgentFlow.say state145 = agent.new say();
									StringCreator string146 = new StringCreator();
									string146.append("You are only one score from winning now");
									state145.setText(string146.toString());
									if (!flowThread.callState(state145, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 265, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching143 = true;
								if (rand144 >= 1 && rand144 < 2) {
									chosen142 = true;
									iristk.situated.SystemAgentFlow.say state147 = agent.new say();
									StringCreator string148 = new StringCreator();
									string148.append("You only need one point more, and you will win");
									state147.setText(string148.toString());
									if (!flowThread.callState(state147, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 265, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 269
						CloseToWinning state149 = new CloseToWinning();
						flowThread.gotoState(state149, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 269, 35)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 270
					} else {
						// Line: 271
						NextQuestion state150 = new NextQuestion();
						flowThread.gotoState(state150, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 271, 33)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 259, 12));
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


	private class IncorrectAnswer extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 277
			try {
				EXECUTION: {
					int count = getCount(1284946629) + 1;
					incrCount(1284946629);
					iristk.situated.SystemAgentFlow.say state151 = agent.new say();
					StringCreator string152 = new StringCreator();
					string152.append("That was wrong");
					state151.setText(string152.toString());
					if (!flowThread.callState(state151, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 277, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 279
					if (system.hasManyUsers() && guess == 0) {
						// Line: 280
						guess++;
						iristk.situated.SystemAgentFlow.attendOther state153 = agent.new attendOther();
						if (!flowThread.callState(state153, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 279, 52)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 282
						boolean chosen154 = false;
						boolean matching155 = true;
						while (!chosen154 && matching155) {
							int rand156 = random(43142663, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching155 = false;
							if (true) {
								matching155 = true;
								if (rand156 >= 0 && rand156 < 1) {
									chosen154 = true;
									iristk.situated.SystemAgentFlow.say state157 = agent.new say();
									StringCreator string158 = new StringCreator();
									string158.append("Maybe you know the answer?");
									state157.setText(string158.toString());
									if (!flowThread.callState(state157, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 282, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching155 = true;
								if (rand156 >= 1 && rand156 < 2) {
									chosen154 = true;
									iristk.situated.SystemAgentFlow.say state159 = agent.new say();
									StringCreator string160 = new StringCreator();
									string160.append("Do you have a better guess?");
									state159.setText(string160.toString());
									if (!flowThread.callState(state159, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 282, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching155 = true;
								if (rand156 >= 2 && rand156 < 3) {
									chosen154 = true;
									iristk.situated.SystemAgentFlow.say state161 = agent.new say();
									StringCreator string162 = new StringCreator();
									string162.append("What do you think?");
									state161.setText(string162.toString());
									if (!flowThread.callState(state161, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 282, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 287
						AwaitAnswer state163 = new AwaitAnswer();
						flowThread.gotoState(state163, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 287, 32)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state164 = agent.new say();
					StringCreator string165 = new StringCreator();
					string165.append("The correct answer was");
					// Line: 287
					string165.append(question.getCorrectAnswer());
					state164.setText(string165.toString());
					if (!flowThread.callState(state164, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 277, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 290
					NextQuestion state166 = new NextQuestion();
					flowThread.gotoState(state166, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 290, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 277, 12));
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


	private class CloseToWinning extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 295
			try {
				EXECUTION: {
					int count = getCount(1672156535) + 1;
					incrCount(1672156535);
					// Line: 296
					boolean chosen167 = false;
					boolean matching168 = true;
					while (!chosen167 && matching168) {
						int rand169 = random(1153912476, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching168 = false;
						if (true) {
							matching168 = true;
							if (rand169 >= 0 && rand169 < 1) {
								chosen167 = true;
								iristk.situated.SystemAgentFlow.say state170 = agent.new say();
								StringCreator string171 = new StringCreator();
								string171.append("Do you feel nervous?");
								state170.setText(string171.toString());
								if (!flowThread.callState(state170, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 296, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching168 = true;
							if (rand169 >= 1 && rand169 < 2) {
								chosen167 = true;
								iristk.situated.SystemAgentFlow.say state172 = agent.new say();
								StringCreator string173 = new StringCreator();
								string173.append("Are you nervous?");
								state172.setText(string173.toString());
								if (!flowThread.callState(state172, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 296, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					iristk.situated.SystemAgentFlow.listen state174 = agent.new listen();
					if (!flowThread.callState(state174, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 295, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 295, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 302
			try {
				count = getCount(1046011369) + 1;
				if (event.triggers("sense.user.speak**")) {
					incrCount(1046011369);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 303
						boolean chosen175 = false;
						boolean matching176 = true;
						while (!chosen175 && matching176) {
							int rand177 = random(29691816, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching176 = false;
							if (true) {
								matching176 = true;
								if (rand177 >= 0 && rand177 < 1) {
									chosen175 = true;
									iristk.situated.SystemAgentFlow.say state178 = agent.new say();
									StringCreator string179 = new StringCreator();
									string179.append("I think you are doing great");
									state178.setText(string179.toString());
									if (!flowThread.callState(state178, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 303, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching176 = true;
								if (rand177 >= 1 && rand177 < 2) {
									chosen175 = true;
									iristk.situated.SystemAgentFlow.say state180 = agent.new say();
									StringCreator string181 = new StringCreator();
									string181.append("I think this is really exciting");
									state180.setText(string181.toString());
									if (!flowThread.callState(state180, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 303, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 307
						NextQuestion state182 = new NextQuestion();
						flowThread.gotoState(state182, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 307, 32)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 302, 38));
			}
			eventResult = super.onFlowEvent(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			eventResult = callerHandlers(event);
			if (eventResult != EVENT_IGNORED) return eventResult;
			return EVENT_IGNORED;
		}

	}


	private class Winner extends Dialog {

		final State currentState = this;


		@Override
		public void setFlowThread(FlowRunner.FlowThread flowThread) {
			super.setFlowThread(flowThread);
		}

		@Override
		public void onentry() throws Exception {
			int eventResult;
			Event event = new Event("state.enter");
			// Line: 312
			try {
				EXECUTION: {
					int count = getCount(1014792909) + 1;
					incrCount(1014792909);
					// Line: 313
					system.putUsers("score", 0);
					// Line: 314
					system.getCurrentUser().putIfNotNull("score", 0);
					iristk.situated.SystemAgentFlow.say state183 = agent.new say();
					StringCreator string184 = new StringCreator();
					string184.append("Congratulations, you are the winner");
					state183.setText(string184.toString());
					if (!flowThread.callState(state183, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 312, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 316
					if (system.hasManyUsers()) {
						iristk.situated.SystemAgentFlow.attendOther state185 = agent.new attendOther();
						if (!flowThread.callState(state185, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 316, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.say state186 = agent.new say();
						StringCreator string187 = new StringCreator();
						string187.append("I am sorry, but you lost.");
						state186.setText(string187.toString());
						if (!flowThread.callState(state186, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 316, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 320
					EndGame state188 = new EndGame();
					flowThread.gotoState(state188, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 320, 27)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 312, 12));
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
