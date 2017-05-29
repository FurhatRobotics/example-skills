package iristk.app.Houndify;

import java.io.File;

import org.slf4j.Logger;

import com.Hound.HoundJSON.ConversationStateJSON;

import iristk.cfg.SRGSGrammar;
import iristk.furhat.Queryable;
import iristk.furhat.skill.FlowResource;
import iristk.furhat.QueryResponse;
import iristk.furhat.skill.Skill;
import iristk.furhat.skill.SkillHandler;
import iristk.furhat.skill.TextFileResource;
import iristk.furhat.skill.XmlResource;
import iristk.speech.OpenVocabularyContext;
import iristk.speech.SemanticGrammarContext;
import iristk.speech.SpeechGrammarContext;
import iristk.system.IrisUtils;
import iristk.util.Language;
import iristk.util.Record;

public class HoundifySkill extends Skill implements Queryable {
	
	private static final String RECOGNIZER_GRAMMAR = "grammar";
	private static final String RECOGNIZER_OPEN = "open";

	private HoundifyFlow flow;
	private static Logger logger = IrisUtils.getLogger(HoundifySkill.class); 
	
	private File propertiesFile;
	private String name = "HoundifySkill";
	private Language language = Language.ENGLISH_US;
	private String recognizer = "open";
	
	private File houndifyCredentialsFile;
	private HoundifyClient houndifyClient;
	private ConversationStateJSON conversationState;

	private String houndify_client_id;
    private String houndify_client_key;
    private String houndify_user_id;	
    private Double location_lon;
    private Double location_lat;
    private String location_city;
    private String location_state;
    private String location_country;
	
	public HoundifySkill() {
		propertiesFile = getPackageFile("skill.properties");
		houndifyCredentialsFile = getPackageFile("houndify_credentials.properties");
		
		addResource(new TextFileResource(this, "Properties", propertiesFile));
		addResource(new TextFileResource(this, "Credentials", houndifyCredentialsFile));
		addResource(new FlowResource(this, "Flow", getSrcFile("HoundifyFlow.xml")));
		addResource(new XmlResource(this, "Grammar", getPackageFile("HoundifyGrammar.xml")));
		
		try {
			Record config = Record.fromProperties(propertiesFile);
			name = config.getString("name", name);
			language = new Language(config.getString("language", language.getCode()));
			recognizer = config.getString("recognizer", recognizer);
			
			Record credentials = Record.fromProperties(houndifyCredentialsFile);
			houndify_client_id = credentials.getString("houndify_client_id");
			houndify_client_key = credentials.getString("houndify_client_key");
			houndify_user_id = credentials.getString("houndify_user_id");
			location_lon = credentials.getDouble("location_lon");
			location_lat = credentials.getDouble("location_lat");
			location_city = credentials.getString("location_city");
			location_state = credentials.getString("location_state");
			location_country = credentials.getString("location_country");
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		getRequirements().setLanguage(language);
		getRequirements().setSpeechGrammar(recognizer.equals(RECOGNIZER_GRAMMAR));
		getRequirements().setOpenVocabulary(recognizer.equals(RECOGNIZER_OPEN));

		addEntriesFromFlow(HoundifyFlow.class, () -> flow);
		
		if (!houndify_client_id.equals("") && !houndify_client_key.equals("") && !houndify_user_id.equals("")) {
			houndifyClient = new HoundifyClient(
					houndify_client_id, 
					houndify_client_key, 
					houndify_user_id, 
					location_lon, 
					location_lat,
					location_city,
					location_state,
					location_country
					);
		}
    	
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	/*
	 *	Method that is run on skill start. Sets up the recognizer contexts and 
	 *	creates a new dialogue flow and inserts a houndify flow client to it so 
	 *	that we can query houndify from the flow 
	 * 
	 */
	@Override
	public void init() throws Exception {
		SkillHandler handler = getSkillHandler();
		if (recognizer.equals(RECOGNIZER_GRAMMAR))  {
			handler.loadContext("default", new SpeechGrammarContext(new SRGSGrammar(getPackageFile("HoundifyGrammar.xml"))));
			handler.setDefaultContext("default");
		} else if (recognizer.equals(RECOGNIZER_OPEN)) {
			handler.loadContext("default", new OpenVocabularyContext(language));
			handler.loadContext("default", new SemanticGrammarContext(new SRGSGrammar(getPackageFile("HoundifyGrammar.xml"))));
			handler.setDefaultContext("default");
		}
		flow = new HoundifyFlow(handler.getSystemAgentFlow(), new HoundifyFlowClient());
	}

	@Override
	public void stop() throws Exception {
	}
	
	/*
	 * Method used for other skills to be able to query Houndify. 
	 * 
	 */
	public QueryResponse query(String text) {
		
		QueryResponse queryResponse = new QueryResponse();

		// If client is not defined, we do nothing
		if (houndifyClient == null) {
			return queryResponse;
		}

		Record answerRecord = houndifyClient.query(text, conversationState);
		
		if (!answerRecord.empty()) {
			if (answerRecord.has("conversationState")){
				conversationState = (ConversationStateJSON) answerRecord.get("conversationState");				
			}
			queryResponse.answer = answerRecord.getString("answer");
			queryResponse.confidence = 0.3; // dummy value for now, just to indicate that an answer is received
		}
		
		return queryResponse;
	}
	
	/*
	 * Mini class used to query houndify from the flow 
	 * 
	 */
	public class HoundifyFlowClient {
		public String answer(String question) {
			QueryResponse queryResponse = query(question);
			if (queryResponse.answer != null){
				return queryResponse.answer;
			}
			return "";
		}
	}
	
	

}
