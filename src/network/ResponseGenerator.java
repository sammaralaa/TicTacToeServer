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
        responseObject.put("response", "player not exists");
        return responseObject.toString();
    }
}
