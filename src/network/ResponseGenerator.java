/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author sammar alaa
 */
public class ResponseGenerator {
    public static String playerNotExistResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "playerNotExists");
        return responseObject.toString();
    }
    public static String wrongPasswordResponse() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "wrongPassword");
        return responseObject.toString();
    }
    public static String loginSuccessResponse(String name , String pass){
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "loginSuccess");
        
        JSONObject userData = new JSONObject();
        userData.put("username", name);
        userData.put("password", pass);
        
        responseObject.put("data", userData);
        return responseObject.toString();
    }
    
    public static String successfulReqisration(String name , String pass) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "successfulReqisration");
        
        JSONObject userData = new JSONObject();
        userData.put("username", name);
        userData.put("password", pass);
        
        responseObject.put("data", userData);
        return responseObject.toString();
    }
   public static String playerExist() {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "playerExist");
        return responseObject.toString();
    } 
     public static String onlinePlayers(ArrayList<String> players){
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "onlinePlayersList");
        responseObject.put("count",players.size());
        JSONObject data = new JSONObject();
        for(int i =0 ; i<players.size();i++){
            data.put(i+"", players.get(i));
        }
         responseObject.put("data", data);
         return responseObject.toString();
     }
     public static String invitationReceivedResponse(String sender) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitationReceived");
        responseObject.put("sender", sender);
        return responseObject.toString();
    }
     // to resever
    public static String invitationSentResponse(String receiver,String sender) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitationSent");
        JSONObject data = new JSONObject();
        data.put("receiver", receiver);
        data.put("sender", sender);
        responseObject.put("data",data);
        return responseObject.toString();
    }
    public static String invitationReceived(String sender) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitationReceived");
        responseObject.put("sender", sender);
        return responseObject.toString();
    }

    public static String invitationAccepted(String sender) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitationAccepted");
        responseObject.put("acceptingPlayer", sender);
        return responseObject.toString();
    }
    public static String invitationDeclined(String sender) {
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "invitationDeclined");
        responseObject.put("decliningPlayer", sender);
        return responseObject.toString();
    }
    
    public static String sendMoveToPlayer(JSONObject data){        
        JSONObject response = new JSONObject();
        response.put("response", "sendMove");
        response.put("data",data);
        return response.toString();
    } 
}
