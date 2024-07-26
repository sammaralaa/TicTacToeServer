/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

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
}
