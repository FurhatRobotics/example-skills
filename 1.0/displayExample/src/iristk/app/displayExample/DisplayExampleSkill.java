package iristk.app.displayExample;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;

import iristk.cfg.SRGSGrammar;
import iristk.furhat.server.Display;
import iristk.furhat.server.FreemarkerHandler;
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

public class DisplayExampleSkill extends Skill {

	private static final String RECOGNIZER_GRAMMAR = "grammar";
	private static final String RECOGNIZER_OPEN = "open";
	
	private static Logger logger = IrisUtils.getLogger(DisplayExampleSkill.class); 
	
	private DisplayExampleFlow flow;
	private File propertiesFile;
	private String name = "DisplayExampleSkill";
	private Language language = Language.ENGLISH_US;
	private String recognizer = "grammar";
	private Display display;
	
	public DisplayExampleSkill() {
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
		addResource(new FlowResource(this, "Flow", getSrcFile("DisplayExampleFlow.xml")));
		addResource(new XmlResource(this, "Grammar", getPackageFile("DisplayExampleGrammar.xml")));
		addResource(new XmlResource(this, "Entrygrammar", getPackageFile("EntryGrammar.xml")));
		
		//Sets up requirements for this class.
		SkillRequirements requirements = getRequirements();
		requirements.setLanguage(language);
		requirements.setSpeechGrammar(recognizer.equals(RECOGNIZER_GRAMMAR));
		requirements.setOpenVocabulary(recognizer.equals(RECOGNIZER_OPEN));
		requirements.setDisplay(Display.TOUCH_TABLE);
		
		addEntriesFromFlow(DisplayExampleFlow.class, () -> flow);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void init() throws Exception {
		SkillHandler handler = getSkillHandler();
		if (recognizer.equals(RECOGNIZER_GRAMMAR))  {
			handler.loadContext("default", new SpeechGrammarContext(new SRGSGrammar(getPackageFile("DisplayExampleGrammar.xml"))));
			handler.setDefaultContext("default");
		} else if (recognizer.equals(RECOGNIZER_OPEN)) {
			handler.loadContext("default", new OpenVocabularyContext(language));
			handler.loadContext("default", new SemanticGrammarContext(new SRGSGrammar(getPackageFile("DisplayExampleGrammar.xml"))));
			handler.setDefaultContext("default");
		}
				
		display = handler.getDisplay(Display.TOUCH_TABLE);
		
		display.subscribe("action.game**");

		handler.registerWebHandler(new DisplayExampleHandler(), "/cats");
		display.load("/cats/cats.html");

		flow = new DisplayExampleFlow(handler.getSystemAgentFlow());
	}

	@Override
	public void stop() throws Exception {
	}

	
	private class DisplayExampleHandler extends FreemarkerHandler {

		public DisplayExampleHandler() throws IOException {
			super(new Record(), getPackageFile("web"));
		}
	}
}
