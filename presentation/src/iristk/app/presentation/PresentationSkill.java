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

public class PresentationSkill extends Skill {

	private PresentationFlow flow;
	private File propertiesFile;
	private String name = "Presentation";
	private Language language = Language.ENGLISH_US;
	private static Logger logger = IrisUtils.getLogger(PresentationSkill.class); 
	private Localizer localizer;
	private Record settings;
	
	public PresentationSkill() {
		
		this.propertiesFile = getPackageFile("skill.properties");
		
		try {
			Record config = Record.fromProperties(propertiesFile);
			settings = new Record();
			for (String field : config.getFields()) {
				if (!field.equals("")) {
					settings.put(field, config.get(field));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		addResource(new FlowResource(this, "Flow", getSrcFile("PresentationFlow.xml")));
		addResource(new TextFileResource(this, "Properties", propertiesFile));
		addResource(new FormFileResource(this, "Text - English", getPackageFile("resources/messages_en_US.properties")));
        
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
		flow = new PresentationFlow(getSkillHandler().getSystemAgentFlow(), localizer, settings);
	}

}
