package iristk.app.parrot;

import java.io.File;

import org.slf4j.Logger;

import iristk.cfg.ParseResult;
import iristk.cfg.Parser;
import iristk.cfg.SRGSGrammar;
import iristk.furhat.QueryResponse;
import iristk.furhat.Queryable;
import iristk.furhat.server.EntryGramResponder;
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

public class ParrotSkill extends Skill implements Queryable {

	private static final String RECOGNIZER_GRAMMAR = "grammar";
	private static final String RECOGNIZER_OPEN = "open";
	
	private static Logger logger = IrisUtils.getLogger(ParrotSkill.class); 
	
	private ParrotFlow flow;
	private File propertiesFile;
	private String name = "ParrotSkill";
	private Language language = Language.ENGLISH_US;
	private String recognizer = "grammar";
	private EntryGramResponder responder;
	private SRGSGrammar entryGrammar;
	private Parser entryParser;
	private Record querySemantics; 
	
	public ParrotSkill() {
		this.propertiesFile = getPackageFile("skill.properties");
		
		responder= new EntryGramResponder(getPackageFile("EntryGrammar.xml"));
		
		addResource(new TextFileResource(this, "Properties", propertiesFile));
		try {
			Record config = Record.fromProperties(propertiesFile);
			name = config.getString("name", name);
			language = new Language(config.getString("language", language.getCode()));
			recognizer = config.getString("recognizer", recognizer);
			entryGrammar = new SRGSGrammar(getPackageFile("EntryGrammar.xml"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		//Adds these resources as links in FurhatOS webserver.
		addResource(new FlowResource(this, "Flow", getSrcFile("ParrotFlow.xml")));
		addResource(new XmlResource(this, "Grammar", getPackageFile("ParrotGrammar.xml")));
		addResource(new XmlResource(this, "Entrygrammar", getPackageFile("EntryGrammar.xml")));
		
		//Sets up requirements for this class.
		SkillRequirements requirements = getRequirements();
		requirements.setLanguage(language);
		requirements.setSpeechGrammar(recognizer.equals(RECOGNIZER_GRAMMAR));
		requirements.setOpenVocabulary(recognizer.equals(RECOGNIZER_OPEN));
		
		entryParser = new Parser();
        entryParser.loadGrammar("entry", entryGrammar);
        entryParser.activateGrammar("entry");
		
		addEntriesFromFlow(ParrotFlow.class, () -> flow);
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void init() throws Exception {
		SkillHandler handler = getSkillHandler();
		
		handler.loadContext("default", new OpenVocabularyContext(language));
		handler.loadContext("default", new SemanticGrammarContext(new SRGSGrammar(getPackageFile("ParrotGrammar.xml"))));
		handler.setDefaultContext("default");
		
		flow = new ParrotFlow(initialParameters, handler.getSystemAgentFlow());
	}

	@Override
	public void stop() throws Exception {
	}

	@Override
	public QueryResponse query(String text) {
		
		QueryResponse response = new QueryResponse();
	    ParseResult result = entryParser.parse(text);
	
	    if (result.getSemCoverage() > 0) {
	        response.confidence = result.getSemCoverage();
	        response.startState = "HotStart";
			response.answer = "a mimicing parrot";
			System.out.println(result.getSem());

			if (result.getSem() != null) {
	        	response.sem = result.getSem();
	        	querySemantics = new Record();
	        	querySemantics.put("sem", response.sem);
	        }
	    }
	
	    return response;
	    
	}
}
