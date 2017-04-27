package iristk.app.questionsAndAnswers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import iristk.cfg.SRGSGrammar;
import iristk.furhat.skill.FlowResource;
import iristk.furhat.skill.Skill;
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

public class QuestionsAndAnswersSkill extends Skill {

	private static final String RECOGNIZER_GRAMMAR = "grammar";
	private static final String RECOGNIZER_OPEN = "open";
	
	private static Logger logger = IrisUtils.getLogger(QuestionsAndAnswersSkill.class); 
	
	private QuestionsAndAnswersFlow flow;
	private File propertiesFile;
	private String name = "QuestionsAndAnswersSkill";
	private Language language = Language.ENGLISH_US;
	private String recognizer = "grammar";
	private List<String> phrases = Arrays.asList("Furhat", "furhat");
	
	public QuestionsAndAnswersSkill() {
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
		addResource(new FlowResource(this, "Flow", getSrcFile("QuestionsAndAnswersFlow.xml")));
		addResource(new XmlResource(this, "Grammar", getPackageFile("QuestionsAndAnswersGrammar.xml")));
		
		//Sets up requirements for this class.
		SkillRequirements requirements = getRequirements();
		requirements.setLanguage(language);
		requirements.setSpeechGrammar(recognizer.equals(RECOGNIZER_GRAMMAR));
		requirements.setOpenVocabulary(recognizer.equals(RECOGNIZER_OPEN));
		
		addEntriesFromFlow(QuestionsAndAnswersFlow.class, () -> flow);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void init() throws Exception {
		SkillHandler handler = getSkillHandler();
		handler.loadContext("default", new OpenVocabularyContext(language, phrases));
		handler.loadContext("default", new SemanticGrammarContext(new SRGSGrammar(getPackageFile("QuestionsAndAnswersGrammar.xml"))));
		handler.setDefaultContext("default");
		
		flow = new QuestionsAndAnswersFlow(handler.getSystemAgentFlow());
	}

	@Override
	public void stop() throws Exception {
	}

}
