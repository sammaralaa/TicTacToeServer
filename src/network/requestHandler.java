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
import java.util.ArrayList;
import static network.ResponseGenerator.*;
import static network.playerDAO.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class requestHandler {

    public static String handleRequest(String reqeust, ClientHandler clientHandler) {
        String serverResponse = null;
        JSONObject requestObject = (JSONObject) JSONValue.parse(reqeust);
        String request = (String) requestObject.get("request");
        JSONObject data = (JSONObject) requestObject.get("data");

        switch (request) {
            case "login":
                serverResponse = handleLoginRequest(data, clientHandler);
                break;
            case "register":
                serverResponse = handleRegisterRequest(data, clientHandler);
                break;
            case "getOnlinePlayersList":
                serverResponse = handleGetOnlinePlayersRequest(data, clientHandler);
                break;
            case "sendInvitation":
                sendInvitationRequest(data, clientHandler);
        }
        return serverResponse;
    }

    public static String handleLoginRequest(JSONObject userData, ClientHandler clientHandler) {
        String userName = (String) userData.get("username");
        String password = (String) userData.get("password");
        String response;
        playerDAO.startDataBaseConnection();
        clientHandler.player = searchForPlayer(userName);
        if (clientHandler.player != null) {
            if (clientHandler.player.getPassword().equals(password)) {
                response = ResponseGenerator.loginSuccessResponse(userName, password);
            } else {
                // System.out.println(clientHandler.player.getPassword());
                response = wrongPasswordResponse();
            }
        } else {
            response = playerNotExistResponse();
        }
        playerDAO.closeDataBaseConnetion();
        return response;
    }

    public static String handleRegisterRequest(JSONObject userData, ClientHandler clienthandeler) {
        String userName = (String) userData.get("username");
        String password = (String) userData.get("password");
        String response;
        startDataBaseConnection();
        playerDTO p = playerDAO.searchForPlayer(userName);
        if (p == null) {
            playerDAO.insertPlayer(userName, password);
            response = successfulReqisration(userName, password);
        } else {
            response = playerExist();
        }
        playerDAO.closeDataBaseConnetion();
        return response;
    }

    public static String handleGetOnlinePlayersRequest(JSONObject userData, ClientHandler clienthandeler) {
        startDataBaseConnection();
        ArrayList<String> onlinePlayersLIst = new ArrayList<>();
        onlinePlayersLIst = playerDAO.getOnlinePlayers();
        String response;
        response = ResponseGenerator.onlinePlayers(onlinePlayersLIst);
        return response;
    }
    public static int sendInvitationRequest(JSONObject userData, ClientHandler clienthandeler){
        int size = ClientHandler.clientsHandler.size();
        //ClientHandler Reciever = new ClientHandler();
        String userName = (String) userData.get("username");
        int userID=-1;
        for(int i=0;i<size;i++){
            if(ClientHandler.clientsHandler.get(i).player.getUserName().equals(userName)){
                userID=i;
                //ClientHandler.clientsHandler.get(i).sendResponse(userName);
                break;
            }
        }
        return userID;
    }
}
