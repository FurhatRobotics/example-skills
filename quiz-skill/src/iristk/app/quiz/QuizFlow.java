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

	private QuestionSet questions;
	private iristk.situated.SystemAgentFlow agent;
	private iristk.situated.SystemAgent system;
	private Question question;
	private int guess;
	private int winningScore;
	private boolean startedFromSkill;
	private boolean allowBargein;
	private String targetSkill;

	private void initVariables() {
		system = (iristk.situated.SystemAgent) agent.getSystemAgent();
		guess = asInteger(0);
		winningScore = asInteger(3);
		startedFromSkill = (boolean) false;
		allowBargein = (boolean) false;
		targetSkill = asString("iristk.furhat.server.IdleSkill");
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

	public String getTargetSkill() {
		return this.targetSkill;
	}

	public void setTargetSkill(String value) {
		this.targetSkill = value;
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
		if (name.equals("targetSkill")) return this.targetSkill;
		if (name.equals("questions")) return this.questions;
		if (name.equals("agent")) return this.agent;
		return null;
	}


	public QuizFlow(QuestionSet questions, iristk.situated.SystemAgentFlow agent) {
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
			// Line: 22
			try {
				EXECUTION: {
					int count = getCount(938609549) + 1;
					incrCount(938609549);
					// Line: 23
					if (system.hasUsers()) {
						// Line: 24
						StartGame state0 = new StartGame();
						flowThread.gotoState(state0, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 24, 30)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 22, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 28
			try {
				count = getCount(2092334204) + 1;
				if (event.triggers("sense.user.enter")) {
					incrCount(2092334204);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 29
						StartGame state1 = new StartGame();
						flowThread.gotoState(state1, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 29, 29)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 28, 36));
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
			// Line: 35
			try {
				EXECUTION: {
					int count = getCount(800120845) + 1;
					incrCount(800120845);
					// Line: 36
					startedFromSkill = true;
					// Line: 37
					targetSkill = "iristk.app.social.SocialSkill.QuizReturn";
					// Line: 38
					StartGame state2 = new StartGame();
					flowThread.gotoState(state2, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 38, 29)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 35, 12));
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
			// Line: 43
			try {
				EXECUTION: {
					int count = getCount(608154678) + 1;
					incrCount(608154678);
					iristk.situated.SystemAgentFlow.say state3 = agent.new say();
					StringCreator string4 = new StringCreator();
					string4.append("Let's play, the Fur hat quiz.");
					state3.setText(string4.toString());
					if (!flowThread.callState(state3, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 43, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 45
					system.putUsers("score", 0);
					// Line: 46
					if (system.getNumUsers() > 1) {
						iristk.situated.SystemAgentFlow.say state5 = agent.new say();
						StringCreator string6 = new StringCreator();
						string6.append("The first to reach");
						// Line: 46
						string6.append(winningScore);
						string6.append("points is the winner");
						state5.setText(string6.toString());
						if (!flowThread.callState(state5, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 46, 40)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 48
					} else {
						iristk.situated.SystemAgentFlow.say state7 = agent.new say();
						StringCreator string8 = new StringCreator();
						string8.append("You win if you reach");
						// Line: 48
						string8.append(winningScore);
						string8.append("points!");
						state7.setText(string8.toString());
						if (!flowThread.callState(state7, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 46, 40)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					iristk.situated.SystemAgentFlow.say state9 = agent.new say();
					StringCreator string10 = new StringCreator();
					string10.append("Here comes the first question");
					state9.setText(string10.toString());
					if (!flowThread.callState(state9, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 43, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 53
					question = questions.next();
					// Line: 54
					ReadQuestion state11 = new ReadQuestion();
					flowThread.gotoState(state11, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 54, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 43, 12));
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
			// Line: 59
			try {
				EXECUTION: {
					int count = getCount(1552247319) + 1;
					incrCount(1552247319);
					iristk.situated.SystemAgentFlow.say state12 = agent.new say();
					StringCreator string13 = new StringCreator();
					string13.append("Would you like to play again?");
					state12.setText(string13.toString());
					if (!flowThread.callState(state12, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 59, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 61
					Event raiseEvent14 = new Event("keepListening");
					if (flowThread.raiseEvent(raiseEvent14, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 61, 34))) == State.EVENT_ABORTED) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 59, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 64
			try {
				count = getCount(518236184) + 1;
				if (event.triggers("keepListening")) {
					incrCount(518236184);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.listen state15 = agent.new listen();
						if (!flowThread.callState(state15, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 64, 33)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 66
						Event raiseEvent16 = new Event("keepListening");
						if (flowThread.raiseEvent(raiseEvent16, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 66, 37))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 64, 33));
			}
			// Line: 69
			try {
				count = getCount(163956687) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(163956687);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state17 = agent.new say();
							StringCreator string18 = new StringCreator();
							string18.append("Great");
							state17.setText(string18.toString());
							if (!flowThread.callState(state17, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 69, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 71
							StartGame state19 = new StartGame();
							flowThread.gotoState(state19, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 71, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 69, 58));
			}
			// Line: 74
			try {
				count = getCount(907213064) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:no") || event.has("sem:goodbye")) {
						incrCount(907213064);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state20 = agent.new say();
							StringCreator string21 = new StringCreator();
							string21.append("Okay, goodbye.");
							state20.setText(string21.toString());
							if (!flowThread.callState(state20, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 74, 85)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 76
							Event sendEvent22 = new Event("action.skill");
							sendEvent22.putIfNotNull("entry", targetSkill);
							flowRunner.sendEvent(sendEvent22, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 76, 63)));
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 74, 85));
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
			// Line: 81
			try {
				count = getCount(1973914098) + 1;
				if (event.triggers("sense.user.leave")) {
					if (system.isAttending(event) || !system.hasUsers()) {
						incrCount(1973914098);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 82
							if (system.hasUsers()) {
								iristk.situated.SystemAgentFlow.attendRandom state23 = agent.new attendRandom();
								if (!flowThread.callState(state23, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 82, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 84
								NextQuestion state24 = new NextQuestion();
								flowThread.gotoState(state24, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 84, 34)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
								// Line: 85
							} else {
								iristk.situated.SystemAgentFlow.say state25 = agent.new say();
								StringCreator string26 = new StringCreator();
								string26.append("Goodbye");
								state25.setText(string26.toString());
								if (!flowThread.callState(state25, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 82, 33)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
								// Line: 87
								Idle state27 = new Idle();
								flowThread.gotoState(state27, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 87, 25)));
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 81, 92));
			}
			// Line: 91
			try {
				count = getCount(1811490301) + 1;
				if (event.triggers("sense.user.speech.start")) {
					if (system.isAttending(event) && eq(event.get("speakers"), 1)) {
						incrCount(1811490301);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 92
							Event sendEvent28 = new Event("action.gesture");
							sendEvent28.putIfNotNull("name", "smile");
							flowRunner.sendEvent(sendEvent28, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 92, 51)));
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 91, 102));
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
			// Line: 97
			try {
				EXECUTION: {
					int count = getCount(1086820287) + 1;
					incrCount(1086820287);
					// Line: 98
					question = questions.next(); guess = 0;
					// Line: 99
					boolean chosen29 = false;
					boolean matching30 = true;
					while (!chosen29 && matching30) {
						int rand31 = random(272062311, 10, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
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
								if (!flowThread.callState(state32, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state35, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state38, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state41, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state44, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state47, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state50, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state53, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state56, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
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
								if (!flowThread.callState(state59, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 99, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					// Line: 111
					if (system.hasManyUsers()) {
						// Line: 112
						boolean chosen62 = false;
						boolean matching63 = true;
						while (!chosen62 && matching63) {
							int rand64 = random(454402079, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching63 = false;
							if (true) {
								matching63 = true;
								if (rand64 >= 0 && rand64 < 1) {
									chosen62 = true;
									iristk.situated.SystemAgentFlow.attendAll state65 = agent.new attendAll();
									if (!flowThread.callState(state65, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									iristk.situated.SystemAgentFlow.say state66 = agent.new say();
									StringCreator string67 = new StringCreator();
									string67.append("The next category is");
									// Line: 112
									string67.append(question.getCategory());
									state66.setText(string67.toString());
									if (!flowThread.callState(state66, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									iristk.situated.SystemAgentFlow.say state68 = agent.new say();
									StringCreator string69 = new StringCreator();
									string69.append("Let's see who answers first");
									state68.setText(string69.toString());
									if (!flowThread.callState(state68, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 112, 13)))) {
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
									if (!flowThread.callState(state70, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
									// Line: 120
									if (system.getCurrentUser().has("name")) {
										iristk.situated.SystemAgentFlow.say state71 = agent.new say();
										StringCreator string72 = new StringCreator();
										string72.append("The next one is for you");
										// Line: 120
										string72.append(system.getCurrentUser().get("name"));
										state71.setText(string72.toString());
										if (!flowThread.callState(state71, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 120, 54)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
									iristk.situated.SystemAgentFlow.say state73 = agent.new say();
									StringCreator string74 = new StringCreator();
									string74.append("Let's see what you know about");
									// Line: 120
									string74.append(question.getCategory());
									state73.setText(string74.toString());
									if (!flowThread.callState(state73, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 112, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 126
					} else {
						iristk.situated.SystemAgentFlow.say state75 = agent.new say();
						StringCreator string76 = new StringCreator();
						string76.append("The next category is");
						// Line: 126
						string76.append(question.getCategory());
						state75.setText(string76.toString());
						if (!flowThread.callState(state75, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 111, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 129
					ReadQuestion state77 = new ReadQuestion();
					flowThread.gotoState(state77, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 129, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 97, 12));
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
			// Line: 134
			try {
				EXECUTION: {
					int count = getCount(1218228529) + 1;
					incrCount(1218228529);
					// Line: 135
					if (allowBargein) {
						iristk.situated.SystemAgentFlow.prompt state78 = agent.new prompt();
						state78.setContext("default " + question.getId());
						state78.setText(question.getFullQuestion());
						if (!flowThread.callState(state78, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 135, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 137
					} else {
						iristk.situated.SystemAgentFlow.say state79 = agent.new say();
						state79.setText(question.getFullQuestion());
						if (!flowThread.callState(state79, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 135, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state80 = agent.new listen();
						state80.setContext("quiz " + question.getId());
						if (!flowThread.callState(state80, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 135, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 134, 12));
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
			// Line: 145
			try {
				EXECUTION: {
					int count = getCount(1497166852) + 1;
					incrCount(1497166852);
					// Line: 146
					if (allowBargein) {
						iristk.situated.SystemAgentFlow.prompt state81 = agent.new prompt();
						state81.setContext("default " + question.getId());
						state81.setText(question.getOptions());
						if (!flowThread.callState(state81, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 146, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 148
					} else {
						iristk.situated.SystemAgentFlow.say state82 = agent.new say();
						state82.setText(question.getOptions());
						if (!flowThread.callState(state82, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 146, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.listen state83 = agent.new listen();
						state83.setContext("quiz " + question.getId());
						if (!flowThread.callState(state83, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 146, 28)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 145, 12));
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
			// Line: 156
			try {
				EXECUTION: {
					int count = getCount(431391003) + 1;
					incrCount(431391003);
					iristk.situated.SystemAgentFlow.listen state84 = agent.new listen();
					state84.setContext("quiz " + question.getId());
					if (!flowThread.callState(state84, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 156, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 156, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 161
			try {
				count = getCount(789591577) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:goodbye")) {
						incrCount(789591577);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state85 = agent.new say();
							StringCreator string86 = new StringCreator();
							string86.append("Okay, goodbye.");
							state85.setText(string86.toString());
							if (!flowThread.callState(state85, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 161, 68)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 163
							if (startedFromSkill) {
								// Line: 164
								Event sendEvent87 = new Event("action.skill");
								sendEvent87.putIfNotNull("entry", targetSkill);
								flowRunner.sendEvent(sendEvent87, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 164, 67)));
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 161, 68));
			}
			// Line: 168
			try {
				count = getCount(1147390506) + 1;
				if (event.triggers("sense.user.speak.multi")) {
					incrCount(1147390506);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 169
						if (question.isCorrect(event.get("all:0:sem:answer"))) {
							iristk.situated.SystemAgentFlow.attend state88 = agent.new attend();
							state88.setTarget(event.get("all:0:user"));
							if (!flowThread.callState(state88, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 169, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 171
							CorrectAnswer state89 = new CorrectAnswer();
							flowThread.gotoState(state89, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 171, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 172
						} else if (question.isCorrect(event.get("all:1:sem:answer"))) {
							iristk.situated.SystemAgentFlow.attend state90 = agent.new attend();
							state90.setTarget(event.get("all:1:user"));
							if (!flowThread.callState(state90, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 169, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 174
							CorrectAnswer state91 = new CorrectAnswer();
							flowThread.gotoState(state91, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 174, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 175
						} else {
							iristk.situated.SystemAgentFlow.say state92 = agent.new say();
							StringCreator string93 = new StringCreator();
							string93.append("None of you were correct, let's try another question.");
							state92.setText(string93.toString());
							if (!flowThread.callState(state92, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 169, 58)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 177
							NextQuestion state94 = new NextQuestion();
							flowThread.gotoState(state94, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 177, 33)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 168, 42));
			}
			// Line: 181
			try {
				count = getCount(1810662387) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:yes")) {
						incrCount(1810662387);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 182
							boolean chosen95 = false;
							boolean matching96 = true;
							while (!chosen95 && matching96) {
								int rand97 = random(243006465, 1, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching96 = false;
								if (true) {
									matching96 = true;
									if (rand97 >= 0 && rand97 < 1) {
										chosen95 = true;
										iristk.situated.SystemAgentFlow.say state98 = agent.new say();
										StringCreator string99 = new StringCreator();
										string99.append("So, what is your guess?");
										state98.setText(string99.toString());
										if (!flowThread.callState(state98, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 182, 12)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 185
							AwaitAnswer state100 = new AwaitAnswer();
							flowThread.gotoState(state100, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 185, 31)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 181, 58));
			}
			// Line: 188
			try {
				count = getCount(960637222) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:dontknow") || event.has("sem:no")) {
						incrCount(960637222);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							// Line: 189
							Event raiseEvent101 = new Event("skip");
							if (flowThread.raiseEvent(raiseEvent101, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 189, 25))) == State.EVENT_ABORTED) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 188, 80));
			}
			// Line: 192
			try {
				count = getCount(1735704532) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_repeat")) {
						incrCount(1735704532);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state102 = agent.new say();
							StringCreator string103 = new StringCreator();
							string103.append("Okay");
							state102.setText(string103.toString());
							if (!flowThread.callState(state102, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 192, 65)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 194
							ReadQuestion state104 = new ReadQuestion();
							flowThread.gotoState(state104, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 194, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 192, 65));
			}
			// Line: 197
			try {
				count = getCount(1985468491) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_repeat_options")) {
						incrCount(1985468491);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state105 = agent.new say();
							StringCreator string106 = new StringCreator();
							string106.append("Okay, here are the options");
							state105.setText(string106.toString());
							if (!flowThread.callState(state105, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 197, 73)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 199
							ReadOptions state107 = new ReadOptions();
							flowThread.gotoState(state107, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 199, 31)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 197, 73));
			}
			// Line: 202
			try {
				count = getCount(1951840921) + 1;
				if (event.triggers("sense.user.speak")) {
					if (event.has("sem:req_quiz_stop")) {
						incrCount(1951840921);
						eventResult = EVENT_CONSUMED;
						EXECUTION: {
							iristk.situated.SystemAgentFlow.say state108 = agent.new say();
							StringCreator string109 = new StringCreator();
							string109.append("Okay, let's stop playing");
							state108.setText(string109.toString());
							if (!flowThread.callState(state108, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 202, 68)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 204
							EndGame state110 = new EndGame();
							flowThread.gotoState(state110, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 204, 27)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						if (eventResult != EVENT_IGNORED) return eventResult;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 202, 68));
			}
			// Line: 207
			try {
				count = getCount(1141528825) + 1;
				if (event.triggers("sense.user.speak")) {
					incrCount(1141528825);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 208
						if (system.isAttendingAll()) {
							iristk.situated.SystemAgentFlow.attend state111 = agent.new attend();
							state111.setTarget(event.get("user"));
							if (!flowThread.callState(state111, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 208, 39)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						// Line: 211
						if (question.isCorrect(event.get("sem:answer"))) {
							// Line: 212
							CorrectAnswer state112 = new CorrectAnswer();
							flowThread.gotoState(state112, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 212, 34)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 213
						} else {
							// Line: 214
							IncorrectAnswer state113 = new IncorrectAnswer();
							flowThread.gotoState(state113, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 214, 36)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 207, 36));
			}
			// Line: 218
			try {
				count = getCount(1008393786) + 1;
				if (event.triggers("sense.user.speak.side")) {
					incrCount(1008393786);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						iristk.situated.SystemAgentFlow.attendOther state114 = agent.new attendOther();
						state114.setMode("eyes");
						if (!flowThread.callState(state114, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 218, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 220
						Event sendEvent115 = new Event("action.gesture");
						sendEvent115.putIfNotNull("name", "express_disgust");
						flowRunner.sendEvent(sendEvent115, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 220, 61)));
						iristk.situated.SystemAgentFlow.say state116 = agent.new say();
						StringCreator string117 = new StringCreator();
						string117.append("You were not supposed to answer that");
						state116.setText(string117.toString());
						if (!flowThread.callState(state116, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 218, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 222
						if (question.isCorrect(event.get("sem:answer"))) {
							iristk.situated.SystemAgentFlow.say state118 = agent.new say();
							StringCreator string119 = new StringCreator();
							string119.append("You also gave away the answer, which is not so smart");
							state118.setText(string119.toString());
							if (!flowThread.callState(state118, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 222, 52)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
						}
						iristk.situated.SystemAgentFlow.attendOther state120 = agent.new attendOther();
						state120.setMode("eyes");
						if (!flowThread.callState(state120, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 218, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.say state121 = agent.new say();
						StringCreator string122 = new StringCreator();
						string122.append("So, what is your answer?");
						state121.setText(string122.toString());
						if (!flowThread.callState(state121, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 218, 41)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 227
						AwaitAnswer state123 = new AwaitAnswer();
						flowThread.gotoState(state123, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 227, 31)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 218, 41));
			}
			// Line: 230
			try {
				count = getCount(1693500625) + 1;
				if (event.triggers("sense.user.silence")) {
					incrCount(1693500625);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 231
						Event raiseEvent124 = new Event("skip");
						if (flowThread.raiseEvent(raiseEvent124, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 231, 25))) == State.EVENT_ABORTED) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 230, 38));
			}
			// Line: 234
			try {
				count = getCount(1751476944) + 1;
				if (event.triggers("skip")) {
					incrCount(1751476944);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 235
						if (system.hasManyUsers()) {
							iristk.situated.SystemAgentFlow.attendOther state125 = agent.new attendOther();
							if (!flowThread.callState(state125, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 235, 37)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							iristk.situated.SystemAgentFlow.say state126 = agent.new say();
							StringCreator string127 = new StringCreator();
							string127.append("Maybe you know the answer?");
							state126.setText(string127.toString());
							if (!flowThread.callState(state126, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 235, 37)))) {
								eventResult = EVENT_ABORTED;
								break EXECUTION;
							}
							// Line: 238
							AwaitAnswer state128 = new AwaitAnswer();
							flowThread.gotoState(state128, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 238, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
							// Line: 239
						} else {
							// Line: 240
							boolean chosen129 = false;
							boolean matching130 = true;
							while (!chosen129 && matching130) {
								int rand131 = random(308920557, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
								matching130 = false;
								if (true) {
									matching130 = true;
									if (rand131 >= 0 && rand131 < 1) {
										chosen129 = true;
										iristk.situated.SystemAgentFlow.say state132 = agent.new say();
										StringCreator string133 = new StringCreator();
										string133.append("Come on, make an educated guess");
										state132.setText(string133.toString());
										if (!flowThread.callState(state132, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 240, 13)))) {
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
										if (!flowThread.callState(state134, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 240, 13)))) {
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
										if (!flowThread.callState(state136, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 240, 13)))) {
											eventResult = EVENT_ABORTED;
											break EXECUTION;
										}
									}
								}
							}
							// Line: 245
							AwaitAnswer state138 = new AwaitAnswer();
							flowThread.gotoState(state138, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 245, 32)));
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 234, 24));
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
			// Line: 252
			try {
				EXECUTION: {
					int count = getCount(294477790) + 1;
					incrCount(294477790);
					// Line: 253
					system.getCurrentUser().putIfNotNull("score", score);
					iristk.situated.SystemAgentFlow.say state139 = agent.new say();
					StringCreator string140 = new StringCreator();
					string140.append("That is correct, you now have a score of");
					// Line: 253
					string140.append(score);
					state139.setText(string140.toString());
					if (!flowThread.callState(state139, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 252, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 255
					if (score >= winningScore) {
						// Line: 256
						Winner state141 = new Winner();
						flowThread.gotoState(state141, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 256, 27)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 257
					} else if (score == winningScore - 1) {
						// Line: 258
						boolean chosen142 = false;
						boolean matching143 = true;
						while (!chosen142 && matching143) {
							int rand144 = random(1598707377, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching143 = false;
							if (true) {
								matching143 = true;
								if (rand144 >= 0 && rand144 < 1) {
									chosen142 = true;
									iristk.situated.SystemAgentFlow.say state145 = agent.new say();
									StringCreator string146 = new StringCreator();
									string146.append("You are only one score from winning now");
									state145.setText(string146.toString());
									if (!flowThread.callState(state145, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 258, 13)))) {
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
									if (!flowThread.callState(state147, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 258, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 262
						CloseToWinning state149 = new CloseToWinning();
						flowThread.gotoState(state149, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 262, 35)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
						// Line: 263
					} else {
						// Line: 264
						NextQuestion state150 = new NextQuestion();
						flowThread.gotoState(state150, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 264, 33)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 252, 12));
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
			// Line: 270
			try {
				EXECUTION: {
					int count = getCount(1507734780) + 1;
					incrCount(1507734780);
					iristk.situated.SystemAgentFlow.say state151 = agent.new say();
					StringCreator string152 = new StringCreator();
					string152.append("That was wrong");
					state151.setText(string152.toString());
					if (!flowThread.callState(state151, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 270, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 272
					if (system.hasManyUsers() && guess == 0) {
						// Line: 273
						guess++;
						iristk.situated.SystemAgentFlow.attendOther state153 = agent.new attendOther();
						if (!flowThread.callState(state153, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 272, 52)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						// Line: 275
						boolean chosen154 = false;
						boolean matching155 = true;
						while (!chosen154 && matching155) {
							int rand156 = random(788327968, 3, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching155 = false;
							if (true) {
								matching155 = true;
								if (rand156 >= 0 && rand156 < 1) {
									chosen154 = true;
									iristk.situated.SystemAgentFlow.say state157 = agent.new say();
									StringCreator string158 = new StringCreator();
									string158.append("Maybe you know the answer?");
									state157.setText(string158.toString());
									if (!flowThread.callState(state157, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 275, 13)))) {
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
									if (!flowThread.callState(state159, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 275, 13)))) {
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
									if (!flowThread.callState(state161, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 275, 13)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 280
						AwaitAnswer state163 = new AwaitAnswer();
						flowThread.gotoState(state163, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 280, 32)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					iristk.situated.SystemAgentFlow.say state164 = agent.new say();
					StringCreator string165 = new StringCreator();
					string165.append("The correct answer was");
					// Line: 280
					string165.append(question.getCorrectAnswer());
					state164.setText(string165.toString());
					if (!flowThread.callState(state164, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 270, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 283
					NextQuestion state166 = new NextQuestion();
					flowThread.gotoState(state166, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 283, 32)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 270, 12));
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
			// Line: 288
			try {
				EXECUTION: {
					int count = getCount(1166549486) + 1;
					incrCount(1166549486);
					// Line: 289
					boolean chosen167 = false;
					boolean matching168 = true;
					while (!chosen167 && matching168) {
						int rand169 = random(696212598, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
						matching168 = false;
						if (true) {
							matching168 = true;
							if (rand169 >= 0 && rand169 < 1) {
								chosen167 = true;
								iristk.situated.SystemAgentFlow.say state170 = agent.new say();
								StringCreator string171 = new StringCreator();
								string171.append("Do you feel nervous?");
								state170.setText(string171.toString());
								if (!flowThread.callState(state170, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 289, 12)))) {
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
								if (!flowThread.callState(state172, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 289, 12)))) {
									eventResult = EVENT_ABORTED;
									break EXECUTION;
								}
							}
						}
					}
					iristk.situated.SystemAgentFlow.listen state174 = agent.new listen();
					if (!flowThread.callState(state174, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 288, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 288, 12));
			}
		}

		@Override
		public int onFlowEvent(Event event) throws Exception {
			int eventResult;
			int count;
			// Line: 295
			try {
				count = getCount(1695324082) + 1;
				if (event.triggers("sense.user.speak**")) {
					incrCount(1695324082);
					eventResult = EVENT_CONSUMED;
					EXECUTION: {
						// Line: 296
						boolean chosen175 = false;
						boolean matching176 = true;
						while (!chosen175 && matching176) {
							int rand177 = random(196329761, 2, iristk.util.RandomList.RandomModel.DECK_RESHUFFLE_NOREPEAT);
							matching176 = false;
							if (true) {
								matching176 = true;
								if (rand177 >= 0 && rand177 < 1) {
									chosen175 = true;
									iristk.situated.SystemAgentFlow.say state178 = agent.new say();
									StringCreator string179 = new StringCreator();
									string179.append("I think you are doing great");
									state178.setText(string179.toString());
									if (!flowThread.callState(state178, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 296, 12)))) {
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
									if (!flowThread.callState(state180, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 296, 12)))) {
										eventResult = EVENT_ABORTED;
										break EXECUTION;
									}
								}
							}
						}
						// Line: 300
						NextQuestion state182 = new NextQuestion();
						flowThread.gotoState(state182, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 300, 32)));
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					if (eventResult != EVENT_IGNORED) return eventResult;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 295, 38));
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
			// Line: 305
			try {
				EXECUTION: {
					int count = getCount(1573903038) + 1;
					incrCount(1573903038);
					// Line: 306
					system.putUsers("score", 0);
					// Line: 307
					system.getCurrentUser().putIfNotNull("score", 0);
					iristk.situated.SystemAgentFlow.say state183 = agent.new say();
					StringCreator string184 = new StringCreator();
					string184.append("Congratulations, you are the winner");
					state183.setText(string184.toString());
					if (!flowThread.callState(state183, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 305, 12)))) {
						eventResult = EVENT_ABORTED;
						break EXECUTION;
					}
					// Line: 309
					if (system.hasManyUsers()) {
						iristk.situated.SystemAgentFlow.attendOther state185 = agent.new attendOther();
						if (!flowThread.callState(state185, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 309, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
						iristk.situated.SystemAgentFlow.say state186 = agent.new say();
						StringCreator string187 = new StringCreator();
						string187.append("I am sorry, but you lost.");
						state186.setText(string187.toString());
						if (!flowThread.callState(state186, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 309, 37)))) {
							eventResult = EVENT_ABORTED;
							break EXECUTION;
						}
					}
					// Line: 313
					EndGame state188 = new EndGame();
					flowThread.gotoState(state188, currentState, new FlowEventInfo(currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 313, 27)));
					eventResult = EVENT_ABORTED;
					break EXECUTION;
				}
			} catch (Exception e) {
				throw new FlowException(e, currentState, event, new XMLLocation(new File("C:\\Users\\ludvig\\dev\\furhat\\skills\\Quiz\\src\\iristk\\app\\quiz\\QuizFlow.xml"), 305, 12));
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
