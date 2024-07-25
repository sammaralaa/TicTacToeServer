/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author sammar alaa
 */
import static network.ResponseGenerator.playerNotExistResponse;
import static network.playerDAO.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
public class requestHandler {
    
    public static String handleRequest(String reqeust, ClientHandler clientHandler){
        String serverResponse = null;
        JSONObject requestObject = (JSONObject) JSONValue.parse(reqeust);
        String request = (String) requestObject.get("request");
        JSONObject data = (JSONObject) requestObject.get("data");
        
        switch(request){
            case "login":
                //serverResponse = handleLoginRequest(data,clientHandler);
                break;
            case "register":
                
        }
        return serverResponse;
    }
    
    public static String handleLoginRequest(JSONObject userData/*,ClientHandler clienthandeler*/){
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
             response = playerNotExistResponse();
        }
        return response;
    }
}
