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
					int count = getCount(1811075214) + 1;
					incrCount(1811075214);
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
				count = getCount(1940447180) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(1940447180);
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
					int count = getCount(1066376662) + 1;
					incrCount(1066376662);
					// Line: 37
					StringCreator string2 = new StringCreator();
					// Line: 37
					string2.append(initialParameters);
					log(string2.toString());
					// Line: 38
					if (initialParameters != null && initialParameters.has("originSkill")) {
						// Line: 39
						originSkill = initialParameters.getString("originSkill");
					}
					// Line: 43
					startedFromSkill = true;
					// Line: 46
					StartGame state3 = new StartGame();
					flowThread.gotoState(state3, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 46, 29)));
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
			// Line: 51
			try {
				EXECUTION: {
					int count = getCount(358699161) + 1;
					incrCount(358699161);
					iristk.situated.SystemAgentFlow.say state4 = agent.new say();
					StringCreator string5 = new StringCreator();
					string5.append("Let's play, the Fur hat quiz.");
					state4.setText(string5.toString());
					if (!flowThread.callState(state4, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 51, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 53
					system.putUsers("score", 0);
					// Line: 54
					if (system.getNumUsers() > 1) {
						iristk.situated.SystemAgentFlow.say state6 = agent.new say();
						StringCreator string7 = new StringCreator();
						string7.append("The first to reach");
						// Line: 54
						string7.append(winningScore);
						string7.append("points is the winner");
						state6.setText(string7.toString());
						if (!flowThread.callState(state6, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 54, 40)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 56
					} else {
						iristk.situated.SystemAgentFlow.say state8 = agent.new say();
						StringCreator string9 = new StringCreator();
						string9.append("You win if you reach");
						// Line: 56
						string9.append(winningScore);
						string9.append("points!");
						state8.setText(string9.toString());
						if (!flowThread.callState(state8, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 54, 40)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.situated.SystemAgentFlow.say state10 = agent.new say();
					StringCreator string11 = new StringCreator();
					string11.append("Here comes the first question");
					state10.setText(string11.toString());
					if (!flowThread.callState(state10, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 51, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 61
					question = questions.next();
					// Line: 62
					ReadQuestion state12 = new ReadQuestion();
					flowThread.gotoState(state12, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 62, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 51, 12));
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
			// Line: 67
			try {
				EXECUTION: {
					int count = getCount(231685785) + 1;
					incrCount(231685785);
					iristk.situated.SystemAgentFlow.say state13 = agent.new say();
					StringCreator string14 = new StringCreator();
					string14.append("Would you like to play again?");
					state13.setText(string14.toString());
					if (!flowThread.callState(state13, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 67, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 69
					Event raiseEvent15 = new Event("keepListening");
					if (flowThread.raiseEvent(raiseEvent15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 69, 34))) == State.EVENT_ABORTED) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 67, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 72
			try {
				count = getCount(32374789) + 1;
				if (event.triggers("keepListening")) {
					incrCount(32374789);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.listen state16 = agent.new listen();
						if (!flowThread.callState(state16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 72, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 74
						Event raiseEvent17 = new Event("keepListening");
						if (flowThread.raiseEvent(raiseEvent17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 74, 37))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 72, 33));
			}
			// Line: 77
			try {
				count = getCount(1023487453) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(1023487453);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state18 = agent.new say();
							StringCreator string19 = new StringCreator();
							string19.append("Great");
							state18.setText(string19.toString());
							if (!flowThread.callState(state18, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 77, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 79
							StartGame state20 = new StartGame();
							flowThread.gotoState(state20, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 79, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 77, 58));
			}
			// Line: 82
			try {
				count = getCount(515132998) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no") || event.has("sem:goodbye")) {
						incrCount(515132998);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state21 = agent.new say();
							StringCreator string22 = new StringCreator();
							string22.append("Okay, goodbye.");
							state21.setText(string22.toString());
							if (!flowThread.callState(state21, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 82, 85)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 84
							Event sendEvent23 = new Event("action.skill");
							sendEvent23.putIfNotNull("entry", originSkill);
							flowRunner.sendEvent(sendEvent23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 84, 63)));
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 82, 85));
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
			// Line: 89
			try {
				count = getCount(1651191114) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event) || !system.hasUsers()) {
						incrCount(1651191114);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 90
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state24 = agent.new attendRandom();
								if (!flowThread.callState(state24, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 90, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 92
								NextQuestion state25 = new NextQuestion();
								flowThread.gotoState(state25, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 92, 34)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 93
							} else {
								iristk.situated.SystemAgentFlow.say state26 = agent.new say();
								StringCreator string27 = new StringCreator();
								string27.append("Goodbye");
								state26.setText(string27.toString());
								if (!flowThread.callState(state26, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 90, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 95
								Idle state28 = new Idle();
								flowThread.gotoState(state28, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 95, 25)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 89, 92));
			}
			// Line: 99
			try {
				count = getCount(1579572132) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1579572132);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 100
							Event sendEvent29 = new Event("action.gesture");
							sendEvent29.putIfNotNull("name", "smile");
							flowRunner.sendEvent(sendEvent29, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 100, 51)));
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 102));
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
			// Line: 105
			try {
				EXECUTION: {
					int count = getCount(2111991224) + 1;
					incrCount(2111991224);
					// Line: 106
					question = questions.next(); guess = 0;
					// Line: 107
					boolean chosen30 = false;
					boolean matching31 = true;
					while (!chosen30 && matching31) {
						int rand32 = random(917142466, 10, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching31 = false;
						if (true) {
							matching31 = true;
							if (rand32 >= 0 && rand32 < 1) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state33 = agent.new say();
								StringCreator string34 = new StringCreator();
								StringCreator string35 = new StringCreator();
								string35.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_05\">");
								string35.append("ehm");
								string35.append("</spurt>");
								string34.append(string35.toString());
								state33.setText(string34.toString());
								if (!flowThread.callState(state33, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 1 && rand32 < 2) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state36 = agent.new say();
								StringCreator string37 = new StringCreator();
								StringCreator string38 = new StringCreator();
								string38.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_06\">");
								string38.append("ehm");
								string38.append("</spurt>");
								string37.append(string38.toString());
								state36.setText(string37.toString());
								if (!flowThread.callState(state36, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 2 && rand32 < 3) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state39 = agent.new say();
								StringCreator string40 = new StringCreator();
								StringCreator string41 = new StringCreator();
								string41.append("<spurt audio=\"free_dialogue/ehm_exh_hesitation_04\">");
								string41.append("ehm");
								string41.append("</spurt>");
								string40.append(string41.toString());
								state39.setText(string40.toString());
								if (!flowThread.callState(state39, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 3 && rand32 < 4) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state42 = agent.new say();
								StringCreator string43 = new StringCreator();
								StringCreator string44 = new StringCreator();
								string44.append("<spurt audio=\"free_dialogue/ehm_taketurn_01\">");
								string44.append("ehm");
								string44.append("</spurt>");
								string43.append(string44.toString());
								state42.setText(string43.toString());
								if (!flowThread.callState(state42, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 4 && rand32 < 5) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state45 = agent.new say();
								StringCreator string46 = new StringCreator();
								StringCreator string47 = new StringCreator();
								string47.append("<spurt audio=\"free_dialogue/ehm_taketurn_02\">");
								string47.append("ehm");
								string47.append("</spurt>");
								string46.append(string47.toString());
								state45.setText(string46.toString());
								if (!flowThread.callState(state45, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 5 && rand32 < 6) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state48 = agent.new say();
								StringCreator string49 = new StringCreator();
								StringCreator string50 = new StringCreator();
								string50.append("<spurt audio=\"free_dialogue/ehm_hesitation_01\">");
								string50.append("ehm");
								string50.append("</spurt>");
								string49.append(string50.toString());
								state48.setText(string49.toString());
								if (!flowThread.callState(state48, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 6 && rand32 < 7) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state51 = agent.new say();
								StringCreator string52 = new StringCreator();
								StringCreator string53 = new StringCreator();
								string53.append("<spurt audio=\"free_dialogue/ehm_hesitation_02\">");
								string53.append("ehm");
								string53.append("</spurt>");
								string52.append(string53.toString());
								state51.setText(string52.toString());
								if (!flowThread.callState(state51, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 7 && rand32 < 8) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state54 = agent.new say();
								StringCreator string55 = new StringCreator();
								StringCreator string56 = new StringCreator();
								string56.append("<spurt audio=\"free_dialogue/ehm_hesitation_03\">");
								string56.append("ehm");
								string56.append("</spurt>");
								string55.append(string56.toString());
								state54.setText(string55.toString());
								if (!flowThread.callState(state54, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 8 && rand32 < 9) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state57 = agent.new say();
								StringCreator string58 = new StringCreator();
								StringCreator string59 = new StringCreator();
								string59.append("<spurt audio=\"free_dialogue/ehm_hesitation_05\">");
								string59.append("ehm");
								string59.append("</spurt>");
								string58.append(string59.toString());
								state57.setText(string58.toString());
								if (!flowThread.callState(state57, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching31 = true;
							if (rand32 >= 9 && rand32 < 10) {
								chosen30 = true;
								iristk.situated.SystemAgentFlow.say state60 = agent.new say();
								StringCreator string61 = new StringCreator();
								StringCreator string62 = new StringCreator();
								string62.append("<spurt audio=\"free_dialogue/ehm_hesitation_06\">");
								string62.append("ehm");
								string62.append("</spurt>");
								string61.append(string62.toString());
								state60.setText(string61.toString());
								if (!flowThread.callState(state60, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 107, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					// Line: 119
					if (system.hasManyUsers()) {
						// Line: 120
						boolean chosen63 = false;
						boolean matching64 = true;
						while (!chosen63 && matching64) {
							int rand65 = random(1130478920, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching64 = false;
							if (true) {
								matching64 = true;
								if (rand65 >= 0 && rand65 < 1) {
									chosen63 = true;
									iristk.situated.SystemAgentFlow.attendAll state66 = agent.new attendAll();
									if (!flowThread.callState(state66, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 120, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									iristk.situated.SystemAgentFlow.say state67 = agent.new say();
									StringCreator string68 = new StringCreator();
									string68.append("The next category is");
									// Line: 120
									string68.append(question.getCategory());
									state67.setText(string68.toString());
									if (!flowThread.callState(state67, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 120, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									iristk.situated.SystemAgentFlow.say state69 = agent.new say();
									StringCreator string70 = new StringCreator();
									string70.append("Let's see who answers first");
									state69.setText(string70.toString());
									if (!flowThread.callState(state69, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 120, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching64 = true;
								if (rand65 >= 1 && rand65 < 2) {
									chosen63 = true;
									iristk.situated.SystemAgentFlow.attendOther state71 = agent.new attendOther();
									if (!flowThread.callState(state71, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 120, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 128
									if (system.getCurrentUser().has("name")) {
										iristk.situated.SystemAgentFlow.say state72 = agent.new say();
										StringCreator string73 = new StringCreator();
										string73.append("The next one is for you");
										// Line: 128
										string73.append(system.getCurrentUser().get("name"));
										state72.setText(string73.toString());
										if (!flowThread.callState(state72, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 128, 54)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
									iristk.situated.SystemAgentFlow.say state74 = agent.new say();
									StringCreator string75 = new StringCreator();
									string75.append("Let's see what you know about");
									// Line: 128
									string75.append(question.getCategory());
									state74.setText(string75.toString());
									if (!flowThread.callState(state74, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 120, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 134
					} else {
						iristk.situated.SystemAgentFlow.say state76 = agent.new say();
						StringCreator string77 = new StringCreator();
						string77.append("The next category is");
						// Line: 134
						string77.append(question.getCategory());
						state76.setText(string77.toString());
						if (!flowThread.callState(state76, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 119, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 137
					ReadQuestion state78 = new ReadQuestion();
					flowThread.gotoState(state78, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 137, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 105, 12));
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
			// Line: 142
			try {
				EXECUTION: {
					int count = getCount(1101288798) + 1;
					incrCount(1101288798);
					// Line: 143
					if (allowBargein) {
						iristk.situated.SystemAgentFlow.prompt state79 = agent.new prompt();
						state79.setContext("default " + question.getId());
						state79.setText(question.getFullQuestion());
						if (!flowThread.callState(state79, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 143, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 145
					} else {
						iristk.situated.SystemAgentFlow.say state80 = agent.new say();
						state80.setText(question.getFullQuestion());
						if (!flowThread.callState(state80, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 143, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state81 = agent.new listen();
						state81.setContext("quiz " + question.getId());
						if (!flowThread.callState(state81, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 143, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 142, 12));
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
			// Line: 153
			try {
				EXECUTION: {
					int count = getCount(305623748) + 1;
					incrCount(305623748);
					// Line: 154
					if (allowBargein) {
						iristk.situated.SystemAgentFlow.prompt state82 = agent.new prompt();
						state82.setContext("default " + question.getId());
						state82.setText(question.getOptions());
						if (!flowThread.callState(state82, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 154, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 156
					} else {
						iristk.situated.SystemAgentFlow.say state83 = agent.new say();
						state83.setText(question.getOptions());
						if (!flowThread.callState(state83, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 154, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state84 = agent.new listen();
						state84.setContext("quiz " + question.getId());
						if (!flowThread.callState(state84, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 154, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 153, 12));
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
			// Line: 164
			try {
				EXECUTION: {
					int count = getCount(1940030785) + 1;
					incrCount(1940030785);
					iristk.situated.SystemAgentFlow.listen state85 = agent.new listen();
					state85.setContext("quiz " + question.getId());
					if (!flowThread.callState(state85, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 164, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 164, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 169
			try {
				count = getCount(1869997857) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:goodbye")) {
						incrCount(1869997857);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state86 = agent.new say();
							StringCreator string87 = new StringCreator();
							string87.append("Okay, goodbye.");
							state86.setText(string87.toString());
							if (!flowThread.callState(state86, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 169, 68)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 171
							if (startedFromSkill) {
								// Line: 172
								Event sendEvent88 = new Event("action.skill");
								sendEvent88.putIfNotNull("entry", originSkill);
								flowRunner.sendEvent(sendEvent88, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 172, 67)));
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 169, 68));
			}
			// Line: 176
			try {
				count = getCount(125993742) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(125993742);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 177
						if (question.isCorrect(event.get("all:0:sem:answer"))) {
							iristk.situated.SystemAgentFlow.attend state89 = agent.new attend();
							state89.setTarget(event.get("all:0:user"));
							if (!flowThread.callState(state89, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 177, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 179
							CorrectAnswer state90 = new CorrectAnswer();
							flowThread.gotoState(state90, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 179, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 180
						} else if (question.isCorrect(event.get("all:1:sem:answer"))) {
							iristk.situated.SystemAgentFlow.attend state91 = agent.new attend();
							state91.setTarget(event.get("all:1:user"));
							if (!flowThread.callState(state91, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 177, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 182
							CorrectAnswer state92 = new CorrectAnswer();
							flowThread.gotoState(state92, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 182, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 183
						} else {
							iristk.situated.SystemAgentFlow.say state93 = agent.new say();
							StringCreator string94 = new StringCreator();
							string94.append("None of you were correct, let's try another question.");
							state93.setText(string94.toString());
							if (!flowThread.callState(state93, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 177, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 185
							NextQuestion state95 = new NextQuestion();
							flowThread.gotoState(state95, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 185, 33)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 176, 42));
			}
			// Line: 189
			try {
				count = getCount(608188624) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(608188624);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 190
							boolean chosen96 = false;
							boolean matching97 = true;
							while (!chosen96 && matching97) {
								int rand98 = random(1451270520, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching97 = false;
								if (true) {
									matching97 = true;
									if (rand98 >= 0 && rand98 < 1) {
										chosen96 = true;
										iristk.situated.SystemAgentFlow.say state99 = agent.new say();
										StringCreator string100 = new StringCreator();
										string100.append("So, what is your guess?");
										state99.setText(string100.toString());
										if (!flowThread.callState(state99, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 190, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 193
							AwaitAnswer state101 = new AwaitAnswer();
							flowThread.gotoState(state101, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 193, 31)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 189, 58));
			}
			// Line: 196
			try {
				count = getCount(992136656) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:dontknow") || event.has("sem:no")) {
						incrCount(992136656);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 197
							Event raiseEvent102 = new Event("skip");
							if (flowThread.raiseEvent(raiseEvent102, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 197, 25))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 196, 80));
			}
			// Line: 200
			try {
				count = getCount(1297685781) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_repeat")) {
						incrCount(1297685781);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state103 = agent.new say();
							StringCreator string104 = new StringCreator();
							string104.append("Okay");
							state103.setText(string104.toString());
							if (!flowThread.callState(state103, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 200, 65)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 202
							ReadQuestion state105 = new ReadQuestion();
							flowThread.gotoState(state105, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 202, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 200, 65));
			}
			// Line: 205
			try {
				count = getCount(1221555852) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_repeat_options")) {
						incrCount(1221555852);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state106 = agent.new say();
							StringCreator string107 = new StringCreator();
							string107.append("Okay, here are the options");
							state106.setText(string107.toString());
							if (!flowThread.callState(state106, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 205, 73)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 207
							ReadOptions state108 = new ReadOptions();
							flowThread.gotoState(state108, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 207, 31)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 205, 73));
			}
			// Line: 210
			try {
				count = getCount(1556956098) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_quiz_stop")) {
						incrCount(1556956098);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state109 = agent.new say();
							StringCreator string110 = new StringCreator();
							string110.append("Okay, let's stop playing");
							state109.setText(string110.toString());
							if (!flowThread.callState(state109, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 210, 68)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 212
							EndGame state111 = new EndGame();
							flowThread.gotoState(state111, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 212, 27)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 210, 68));
			}
			// Line: 215
			try {
				count = getCount(2036368507) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(2036368507);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 216
						if (system.isAttendingAll()) {
							iristk.situated.SystemAgentFlow.attend state112 = agent.new attend();
							state112.setTarget(event.get("user"));
							if (!flowThread.callState(state112, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 216, 39)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 219
						if (question.isCorrect(event.get("sem:answer"))) {
							// Line: 220
							CorrectAnswer state113 = new CorrectAnswer();
							flowThread.gotoState(state113, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 220, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 221
						} else {
							// Line: 222
							IncorrectAnswer state114 = new IncorrectAnswer();
							flowThread.gotoState(state114, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 222, 36)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 215, 36));
			}
			// Line: 226
			try {
				count = getCount(166239592) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(166239592);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attendOther state115 = agent.new attendOther();
						state115.setMode("eyes");
						if (!flowThread.callState(state115, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 226, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 228
						Event sendEvent116 = new Event("action.gesture");
						sendEvent116.putIfNotNull("name", "express_disgust");
						flowRunner.sendEvent(sendEvent116, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 228, 61)));
						iristk.situated.SystemAgentFlow.say state117 = agent.new say();
						StringCreator string118 = new StringCreator();
						string118.append("You were not supposed to answer that");
						state117.setText(string118.toString());
						if (!flowThread.callState(state117, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 226, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 230
						if (question.isCorrect(event.get("sem:answer"))) {
							iristk.situated.SystemAgentFlow.say state119 = agent.new say();
							StringCreator string120 = new StringCreator();
							string120.append("You also gave away the answer, which is not so smart");
							state119.setText(string120.toString());
							if (!flowThread.callState(state119, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 230, 52)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						iristk.situated.SystemAgentFlow.attendOther state121 = agent.new attendOther();
						state121.setMode("eyes");
						if (!flowThread.callState(state121, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 226, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.say state122 = agent.new say();
						StringCreator string123 = new StringCreator();
						string123.append("So, what is your answer?");
						state122.setText(string123.toString());
						if (!flowThread.callState(state122, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 226, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 235
						AwaitAnswer state124 = new AwaitAnswer();
						flowThread.gotoState(state124, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 235, 31)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 226, 41));
			}
			// Line: 238
			try {
				count = getCount(248609774) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(248609774);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 239
						Event raiseEvent125 = new Event("skip");
						if (flowThread.raiseEvent(raiseEvent125, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 239, 25))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 238, 38));
			}
			// Line: 242
			try {
				count = getCount(1887400018) + 1;
				if (event.triggers("skip")) {
					incrCount(1887400018);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 243
						if (system.hasManyUsers()) {
							iristk.situated.SystemAgentFlow.attendOther state126 = agent.new attendOther();
							if (!flowThread.callState(state126, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 243, 37)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state127 = agent.new say();
							StringCreator string128 = new StringCreator();
							string128.append("Maybe you know the answer?");
							state127.setText(string128.toString());
							if (!flowThread.callState(state127, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 243, 37)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 246
							AwaitAnswer state129 = new AwaitAnswer();
							flowThread.gotoState(state129, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 246, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 247
						} else {
							// Line: 248
							boolean chosen130 = false;
							boolean matching131 = true;
							while (!chosen130 && matching131) {
								int rand132 = random(716083600, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching131 = false;
								if (true) {
									matching131 = true;
									if (rand132 >= 0 && rand132 < 1) {
										chosen130 = true;
										iristk.situated.SystemAgentFlow.say state133 = agent.new say();
										StringCreator string134 = new StringCreator();
										string134.append("Come on, make an educated guess");
										state133.setText(string134.toString());
										if (!flowThread.callState(state133, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 248, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching131 = true;
									if (rand132 >= 1 && rand132 < 2) {
										chosen130 = true;
										iristk.situated.SystemAgentFlow.say state135 = agent.new say();
										StringCreator string136 = new StringCreator();
										string136.append("You could at least try");
										state135.setText(string136.toString());
										if (!flowThread.callState(state135, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 248, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
								if (true) {
									matching131 = true;
									if (rand132 >= 2 && rand132 < 3) {
										chosen130 = true;
										iristk.situated.SystemAgentFlow.say state137 = agent.new say();
										StringCreator string138 = new StringCreator();
										string138.append("Why don't you make a guess");
										state137.setText(string138.toString());
										if (!flowThread.callState(state137, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 248, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 253
							AwaitAnswer state139 = new AwaitAnswer();
							flowThread.gotoState(state139, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 253, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 242, 24));
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
			// Line: 260
			try {
				EXECUTION: {
					int count = getCount(1288141870) + 1;
					incrCount(1288141870);
					// Line: 261
					system.getCurrentUser().putIfNotNull("score", score);
					iristk.situated.SystemAgentFlow.say state140 = agent.new say();
					StringCreator string141 = new StringCreator();
					string141.append("That is correct, you now have a score of");
					// Line: 261
					string141.append(score);
					state140.setText(string141.toString());
					if (!flowThread.callState(state140, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 260, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 263
					if (score >= winningScore) {
						// Line: 264
						Winner state142 = new Winner();
						flowThread.gotoState(state142, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 264, 27)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 265
					} else if (score == winningScore - 1) {
						// Line: 266
						boolean chosen143 = false;
						boolean matching144 = true;
						while (!chosen143 && matching144) {
							int rand145 = random(607635164, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching144 = false;
							if (true) {
								matching144 = true;
								if (rand145 >= 0 && rand145 < 1) {
									chosen143 = true;
									iristk.situated.SystemAgentFlow.say state146 = agent.new say();
									StringCreator string147 = new StringCreator();
									string147.append("You are only one score from winning now");
									state146.setText(string147.toString());
									if (!flowThread.callState(state146, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 266, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching144 = true;
								if (rand145 >= 1 && rand145 < 2) {
									chosen143 = true;
									iristk.situated.SystemAgentFlow.say state148 = agent.new say();
									StringCreator string149 = new StringCreator();
									string149.append("You only need one point more, and you will win");
									state148.setText(string149.toString());
									if (!flowThread.callState(state148, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 266, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 270
						CloseToWinning state150 = new CloseToWinning();
						flowThread.gotoState(state150, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 270, 35)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 271
					} else {
						// Line: 272
						NextQuestion state151 = new NextQuestion();
						flowThread.gotoState(state151, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 272, 33)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 260, 12));
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
			// Line: 278
			try {
				EXECUTION: {
					int count = getCount(697960108) + 1;
					incrCount(697960108);
					iristk.situated.SystemAgentFlow.say state152 = agent.new say();
					StringCreator string153 = new StringCreator();
					string153.append("That was wrong");
					state152.setText(string153.toString());
					if (!flowThread.callState(state152, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 278, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 280
					if (system.hasManyUsers() && guess == 0) {
						// Line: 281
						guess++;
						iristk.situated.SystemAgentFlow.attendOther state154 = agent.new attendOther();
						if (!flowThread.callState(state154, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 280, 52)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 283
						boolean chosen155 = false;
						boolean matching156 = true;
						while (!chosen155 && matching156) {
							int rand157 = random(48612937, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching156 = false;
							if (true) {
								matching156 = true;
								if (rand157 >= 0 && rand157 < 1) {
									chosen155 = true;
									iristk.situated.SystemAgentFlow.say state158 = agent.new say();
									StringCreator string159 = new StringCreator();
									string159.append("Maybe you know the answer?");
									state158.setText(string159.toString());
									if (!flowThread.callState(state158, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 283, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching156 = true;
								if (rand157 >= 1 && rand157 < 2) {
									chosen155 = true;
									iristk.situated.SystemAgentFlow.say state160 = agent.new say();
									StringCreator string161 = new StringCreator();
									string161.append("Do you have a better guess?");
									state160.setText(string161.toString());
									if (!flowThread.callState(state160, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 283, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching156 = true;
								if (rand157 >= 2 && rand157 < 3) {
									chosen155 = true;
									iristk.situated.SystemAgentFlow.say state162 = agent.new say();
									StringCreator string163 = new StringCreator();
									string163.append("What do you think?");
									state162.setText(string163.toString());
									if (!flowThread.callState(state162, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 283, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 288
						AwaitAnswer state164 = new AwaitAnswer();
						flowThread.gotoState(state164, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 288, 32)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state165 = agent.new say();
					StringCreator string166 = new StringCreator();
					string166.append("The correct answer was");
					// Line: 288
					string166.append(question.getCorrectAnswer());
					state165.setText(string166.toString());
					if (!flowThread.callState(state165, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 278, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 291
					NextQuestion state167 = new NextQuestion();
					flowThread.gotoState(state167, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 291, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 278, 12));
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
			// Line: 296
			try {
				EXECUTION: {
					int count = getCount(1018081122) + 1;
					incrCount(1018081122);
					// Line: 297
					boolean chosen168 = false;
					boolean matching169 = true;
					while (!chosen168 && matching169) {
						int rand170 = random(242131142, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching169 = false;
						if (true) {
							matching169 = true;
							if (rand170 >= 0 && rand170 < 1) {
								chosen168 = true;
								iristk.situated.SystemAgentFlow.say state171 = agent.new say();
								StringCreator string172 = new StringCreator();
								string172.append("Do you feel nervous?");
								state171.setText(string172.toString());
								if (!flowThread.callState(state171, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 297, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
						if (true) {
							matching169 = true;
							if (rand170 >= 1 && rand170 < 2) {
								chosen168 = true;
								iristk.situated.SystemAgentFlow.say state173 = agent.new say();
								StringCreator string174 = new StringCreator();
								string174.append("Are you nervous?");
								state173.setText(string174.toString());
								if (!flowThread.callState(state173, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 297, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					iristk.situated.SystemAgentFlow.listen state175 = agent.new listen();
					if (!flowThread.callState(state175, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 296, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 296, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 303
			try {
				count = getCount(1782113663) + 1;
				if (event.triggers("sense.user.speak**")) {
					incrCount(1782113663);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 304
						boolean chosen176 = false;
						boolean matching177 = true;
						while (!chosen176 && matching177) {
							int rand178 = random(1433867275, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching177 = false;
							if (true) {
								matching177 = true;
								if (rand178 >= 0 && rand178 < 1) {
									chosen176 = true;
									iristk.situated.SystemAgentFlow.say state179 = agent.new say();
									StringCreator string180 = new StringCreator();
									string180.append("I think you are doing great");
									state179.setText(string180.toString());
									if (!flowThread.callState(state179, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 304, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
							if (true) {
								matching177 = true;
								if (rand178 >= 1 && rand178 < 2) {
									chosen176 = true;
									iristk.situated.SystemAgentFlow.say state181 = agent.new say();
									StringCreator string182 = new StringCreator();
									string182.append("I think this is really exciting");
									state181.setText(string182.toString());
									if (!flowThread.callState(state181, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 304, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 308
						NextQuestion state183 = new NextQuestion();
						flowThread.gotoState(state183, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 308, 32)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 303, 38));
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
			// Line: 313
			try {
				EXECUTION: {
					int count = getCount(1254526270) + 1;
					incrCount(1254526270);
					// Line: 314
					system.putUsers("score", 0);
					// Line: 315
					system.getCurrentUser().putIfNotNull("score", 0);
					iristk.situated.SystemAgentFlow.say state184 = agent.new say();
					StringCreator string185 = new StringCreator();
					string185.append("Congratulations, you are the winner");
					state184.setText(string185.toString());
					if (!flowThread.callState(state184, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 313, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 317
					if (system.hasManyUsers()) {
						iristk.situated.SystemAgentFlow.attendOther state186 = agent.new attendOther();
						if (!flowThread.callState(state186, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 317, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.say state187 = agent.new say();
						StringCreator string188 = new StringCreator();
						string188.append("I am sorry, but you lost.");
						state187.setText(string188.toString());
						if (!flowThread.callState(state187, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 317, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 321
					EndGame state189 = new EndGame();
					flowThread.gotoState(state189, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 321, 27)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\example-skills\\quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 313, 12));
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
