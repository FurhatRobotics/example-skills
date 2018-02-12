package iristk.app.social;

import iristk.cfg.ABNFGrammar;
import iristk.cfg.SRGSGrammar;
import iristk.furhat.skill.FlowResource;
import iristk.furhat.skill.Skill;
import iristk.furhat.skill.SkillEntry;
import iristk.furhat.skill.XmlResource;
import iristk.speech.OpenVocabularyContext;
import iristk.speech.RecognizerFactory;
import iristk.speech.SemanticGrammarContext;
import iristk.speech.SpeechGrammarContext;
import iristk.util.Language;
import iristk.flow.FlowReturner;
import iristk.flow.State;

public class SocialSkill extends Skill {

	private SocialFlow socialFlow;

	public SocialSkill() {
		addResource(new FlowResource(this, "Flow", getSrcFile("SocialFlow.xml")));
		addResource(new XmlResource(this, "Grammar", getPackageFile("semantics.xml")));
		getRequirements().setLanguage(Language.ENGLISH_US);

		// Special handler to be able to "hot-start" the social flow from the Quiz state without showing a start-button on the skills list 
		FlowReturner returner = () -> socialFlow;
		addEntry(new SkillEntry() {
			@Override
			public State getInitialState() {
				return  returner.getFlow().getState((SocialFlow.Start.class).getSimpleName());
			}
		}, getId(), getName());
		addEntry(new SkillEntry() {
			@Override
			public State getInitialState() {
				return returner.getFlow().getState((SocialFlow.QuizReturn.class).getSimpleName());
			}
		}, getId() + "." + (SocialFlow.QuizReturn.class).getSimpleName(), (SocialFlow.QuizReturn.class).getSimpleName(), false);
	}

	@Override
	public void init() throws Exception {
		socialFlow = new SocialFlow(getSkillHandler().getSystemAgentFlow());
		RecognizerFactory recFactory = getSkillHandler().getRecognizerFactory();
		if (recFactory.supportsSpeechGrammar()) {
			getSkillHandler().loadContext("name", new SpeechGrammarContext(new ABNFGrammar(getPackageFile("resources/name_grammar.abnf").toURI())));
			getSkillHandler().loadContext("default", new SpeechGrammarContext(new SRGSGrammar(getPackageFile("speechgrammar.xml").toURI())));
			getSkillHandler().loadContext("default", new SemanticGrammarContext(new SRGSGrammar(getPackageFile("semantics.xml").toURI())));
		} else if (recFactory.supportsOpenVocabulary()) {
			getSkillHandler().loadContext("name", new OpenVocabularyContext(getSkillHandler().getPreferredLanguage()));
			getSkillHandler().loadContext("name", new SemanticGrammarContext(new ABNFGrammar(getPackageFile("resources/name_grammar.abnf").toURI())));
			getSkillHandler().loadContext("default", new OpenVocabularyContext(getSkillHandler().getPreferredLanguage()));
			getSkillHandler().loadContext("default", new SemanticGrammarContext(new SRGSGrammar(getPackageFile("semantics.xml").toURI())));
		}
		getSkillHandler().setDefaultContext("default");
	}

	@Override
	public String getName() {
		return "Social";
	}

}
