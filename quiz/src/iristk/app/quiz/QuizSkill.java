package iristk.app.quiz;

import java.io.File;

import org.slf4j.Logger;

import iristk.cfg.SRGSGrammar;
import iristk.flow.FlowReturner;
import iristk.furhat.skill.FlowResource;
import iristk.furhat.skill.Skill;
import iristk.furhat.skill.SkillEntry;
import iristk.furhat.skill.SkillHandler;
import iristk.furhat.skill.TextFileResource;
import iristk.furhat.skill.XmlResource;
import iristk.furhat.skill.SkillRequirements;
import iristk.speech.OpenVocabularyContext;
import iristk.speech.SemanticGrammarContext;
import iristk.speech.SpeechGrammarContext;
import iristk.system.IrisUtils;
import iristk.util.Language;
import iristk.util.Record;
import iristk.flow.State;

public class QuizSkill extends Skill {

	private static final String RECOGNIZER_GRAMMAR  = "grammar";
	private static final String RECOGNIZER_OPEN 	= "open";

	private static Logger logger = IrisUtils.getLogger(QuizSkill.class); 

	private String name = "QuizSkill";
	private String recognizer = "grammar";
	private Language language = Language.ENGLISH_US;
	private QuizFlow flow;
	private File propertiesFile;

	public QuizSkill() {
		this.propertiesFile = getPackageFile("skill.properties");

		try {
			Record config = Record.fromProperties(propertiesFile);
			name = config.getString("name", name);
			language = new Language(config.getString("language", language.getCode()));
			recognizer = config.getString("recognizer", recognizer);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		//Adds these resources as links in FurhatOS webserver.
		addResource(new TextFileResource(this, "Properties", propertiesFile));
		addResource(new FlowResource(this, "Flow", getSrcFile("QuizFlow.xml")));
		addResource(new XmlResource(this, "Grammar", getPackageFile("QuizGrammar.xml")));
		addResource(new QuestionResource(this, "Questions", getPackageFile("resources/questions.txt")));
		//Sets up requirements for this class.
		SkillRequirements requirements = getRequirements();
		requirements.setLanguage(language);
		requirements.setSpeechGrammar(recognizer.equals(RECOGNIZER_GRAMMAR));
		requirements.setOpenVocabulary(recognizer.equals(RECOGNIZER_OPEN));
		//addEntriesFromFlow(QuizFlow.class, () -> flow);

		FlowReturner returner = ()->flow;
		addEntry(new SkillEntry() {
			@Override
			public State getInitialState() {
				return  returner.getFlow().getState((QuizFlow.Idle.class).getSimpleName());
			}
		}, getId(), getName());

		addEntry(new SkillEntry() {
			@Override
			public State getInitialState() {
				return returner.getFlow().getState((QuizFlow.play.class).getSimpleName());
			}
		}, getId() + "." + (QuizFlow.play.class).getSimpleName(), (QuizFlow.play.class).getSimpleName(), false);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void init() throws Exception {
		QuestionSet questions = new QuestionSet(getPackageFile("resources/questions.txt"));

		SkillHandler handler = getSkillHandler();
		Long startTime = System.currentTimeMillis();
		if (recognizer.equals(RECOGNIZER_GRAMMAR))  {
			handler.loadContext("quiz", new SpeechGrammarContext(new SRGSGrammar(getPackageFile("QuizGrammar.xml"))));
			for (Question q : questions) {
				getSkillHandler().loadContext(q.getId(), new SpeechGrammarContext(q.getGrammar()));
			}
		} else if (recognizer.equals(RECOGNIZER_OPEN)) {
			handler.loadContext("quiz", new OpenVocabularyContext(language));
			handler.loadContext("quiz", new SemanticGrammarContext(new SRGSGrammar(getPackageFile("QuizGrammar.xml"))));
			for (Question q : questions) {
				getSkillHandler().loadContext(q.getId(), new SemanticGrammarContext(q.getGrammar()));
				getSkillHandler().loadContext(q.getId(), new OpenVocabularyContext(getSkillHandler().getPreferredLanguage(), q.getOptionList()));
			}

			handler.setDefaultContext("quiz");
		}

		Long totalTime = System.currentTimeMillis()-startTime;
		logger.warn("------------Took "+totalTime+ "milliseconds---------------" );
		flow = new QuizFlow(questions, getSkillHandler().getSystemAgentFlow());
	}

}
