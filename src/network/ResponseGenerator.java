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
     public static String onlinePlayers(ArrayList<playerDTO> players){
        JSONObject responseObject = new JSONObject();
        responseObject.put("response", "onlinePlayersList");
        responseObject.put("count",players.size());
        JSONObject data = new JSONObject();
        for(int i =0 ; i<players.size();i++){
            data.put(i, players.get(i).getUserName());
        }
         responseObject.put("data", data);
         return responseObject.toString();
     }
     
}
