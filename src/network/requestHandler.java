/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author Electronica Care
 */
import static network.playerDAO.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
public class requestHandler {
    
    public static String handleRequest(String reqeust, GameHandler gameHandler){
        String serverResponse = null;
        JSONObject requestObject = (JSONObject) JSONValue.parse(reqeust);
        String request = (String) requestObject.get("request");
        JSONObject data = (JSONObject) requestObject.get("data");
        
        switch(request){
            case "login":
                //serverResponse = handleLoginRequest(data,gameHandler);
                break;
            case "register":
                
        }
        return serverResponse;
    }
    
    public static String handleLoginRequest(JSONObject userData/*,GameHandler gamehandeler*/){
        String userName = (String) userData.get("username");
        String password = (String) userData.get("password");
        String response;
        startDataBaseConnection();
        playerDTO p = searchForPlayer(userName);
        if(p!=null){
            if(p.getPassword().equals(password)){
                 response = "validLogin";
            }
            else{
                System.out.println(p.getPassword());
                 response = "wrongPassword";
            }
        }
        else{
             response = "invalidUserName";
        }
        return response;
    }
}
