package iristk.app.Houndify;

import iristk.system.IrisUtils;
import iristk.util.Record;
import java.util.UUID;

import org.slf4j.Logger;

import com.Hound.HoundJSON.CommandResultJSON;
import com.Hound.HoundJSON.ConversationStateJSON;
import com.Hound.HoundJSON.HoundServerJSON;
import com.Hound.HoundJSON.RequestInfoJSON;
import com.Hound.HoundRequester.*;

public class HoundifyClient {

	private String client_id;
	private String client_key;
	private String user_id;
	private double location_lon;
	private double location_lat;
	private String location_city;
	private String location_state;
	private String location_country;
	private static Logger logger = IrisUtils.getLogger(HoundifySkill.class); 

	
	public HoundifyClient(String clientId, String clientKey, String userId, double locationLon, double locationLat, String locationCity, String locationState, String locationCountry) {
		client_id = clientId;
		client_key = clientKey;
		user_id = userId;
		location_lon = locationLon;
		location_lat = locationLat;
		location_city = locationCity;
		location_state = locationState;
		location_country = locationCountry;
	}

	public Record query(String query) {
		return query(query, null);
	}
	
    public Record query(String query, ConversationStateJSON conversationState) {
        HoundCloudRequester requester = new HoundCloudRequester(client_id, client_key, user_id);
        RequestInfoJSON requestInfo = new RequestInfoJSON();
        HoundServerJSON houndResult = new HoundServerJSON();
        Record response = new Record();
        try {
            String session_id = UUID.randomUUID().toString();
            requestInfo.setUnitPreference(RequestInfoJSON.TypeUnitPreference.UnitPreference_METRIC);
            requestInfo.setRequestID(UUID.randomUUID().toString());
            requestInfo.setSessionID(session_id);
            requestInfo.setLatitude(location_lat);
            requestInfo.setLongitude(location_lon);
            requestInfo.setCountry(location_country);
            requestInfo.setCity(location_city);
            requestInfo.setState(location_state);
            RequestInfoJSON.TypeClientVersion clientVersion = new RequestInfoJSON.TypeClientVersion();
            clientVersion.key = 0;
            clientVersion.choice0 = "1.0";
            requestInfo.setClientVersion(clientVersion);
            /* Perform text request to Houndify Web Service */
            houndResult = requester.do_text_request(query, conversationState, requestInfo);
            
            if (houndResult == null)
            	return response;
            
            if (houndResult.hasAllResults()) {
                if (houndResult.countOfAllResults() == 0) {
                    conversationState = null;
                }
                else {  
                    CommandResultJSON command = houndResult.elementOfAllResults(0);
                    if (houndResult.hasLocalOrRemote()) {
                        System.out.format(" [%s]",
                                ((houndResult.getLocalOrRemote() == 
                                HoundServerJSON.TypeLocalOrRemote.LocalOrRemote_Local) ? "Local" : "Remote"));
                    }
                    response.put("answer", command.getWrittenResponseLong());
                    
                    if (command.hasConversationState())
                        response.put("conversationState", command.getConversationState());
                }
            }
            else if (houndResult.hasErrorMessage()) {
                logger.warn("Houndify error: " + houndResult.getErrorMessage() + "/n");
                conversationState = null;
            }
            else {
                logger.info("Houndify: No result or error from server.\n");
                conversationState = null;
            }           
        }
        catch (Exception e1) {
            logger.error("Houndify exception: " + e1.getMessage());
            e1.printStackTrace();
        }
        return response;
    }
}