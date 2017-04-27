package iristk.app.presentation;

import java.io.File;

import org.slf4j.Logger;

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

	public PresentationSkill() {
		
		this.propertiesFile = getPackageFile("skill.properties");
		
		try {
			Record config = Record.fromProperties(propertiesFile);
			name = config.getString("name", name);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		addResource(new FlowResource(this, "Flow", getSrcFile("PresentationFlow.xml")));
		addResource(new TextFileResource(this, "Properties", propertiesFile));
		addResource(new FormFileResource(this, "Text - English", getPackageFile("resources/messages_en_US.properties")));
		addResource(new FormFileResource(this, "Text - Dutch", getPackageFile("resources/messages_nl_NL.properties")));
		
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
		flow = new PresentationFlow(getSkillHandler().getSystemAgentFlow(), localizer);
	}

}
