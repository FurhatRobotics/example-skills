package iristk.app.presentation;

import java.io.File;

import org.slf4j.Logger;

import iristk.cfg.ParseResult;
import iristk.cfg.Parser;
import iristk.cfg.SRGSGrammar;
import iristk.furhat.QueryResponse;
import iristk.furhat.Queryable;
import iristk.furhat.skill.FlowResource;
import iristk.furhat.skill.FormFileResource;
import iristk.furhat.skill.Skill;
import iristk.furhat.skill.TextFileResource;
import iristk.furhat.util.Localizer;
import iristk.system.IrisUtils;
import iristk.util.Language;
import iristk.util.Record;

public class PresentationSkill extends Skill implements Queryable {

	private PresentationFlow flow;
	private File propertiesFile;
	private String name = "Presentation";
	private Language language = Language.ENGLISH_US;
	private static Logger logger = IrisUtils.getLogger(PresentationSkill.class); 
	private Localizer localizer;
	private SRGSGrammar entryGrammar;
	private Parser entryParser;
	private Record querySemantics; 

	public PresentationSkill() {
		
		this.propertiesFile = getPackageFile("skill.properties");
		
		try {
			Record config = Record.fromProperties(propertiesFile);
			name = config.getString("name", name);
			entryGrammar = new SRGSGrammar(getPackageFile("EntryGrammar.xml"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		addResource(new FlowResource(this, "Flow", getSrcFile("PresentationFlow.xml")));
		addResource(new TextFileResource(this, "Properties", propertiesFile));
		addResource(new FormFileResource(this, "Text - English", getPackageFile("resources/messages_en_US.properties")));
		addResource(new FormFileResource(this, "Text - Dutch", getPackageFile("resources/messages_nl_NL.properties")));
		
		entryParser = new Parser();
        entryParser.loadGrammar("entry", entryGrammar);
        entryParser.activateGrammar("entry");
        
		addEntriesFromFlow(PresentationFlow.class, () -> flow);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void init() throws Exception {
		language = getSkillHandler().getPreferredLanguage();
		localizer = new Localizer(getPackageFile("resources"), "messages", language.toString());
		flow = new PresentationFlow(initialParameters, getSkillHandler().getSystemAgentFlow(), localizer);
	}

	@Override
	public QueryResponse query(String text) {
		QueryResponse response = new QueryResponse();

	    ParseResult result = entryParser.parse(text);
	
	    if (result.getSemCoverage() > 0) {
	        response.confidence = result.getSemCoverage();
	        response.startState = "hotStart";
			response.answer = "my personal presentation";
	        if (result.getSem() != null)
                response.sem = result.getSem();
	    }
	
	    return response;
	}

}
