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
                System.out.println("from handle login request from requestHandler");
                break;
            case "register":
                serverResponse = handleRegisterRequest(data, clientHandler);
                break;
            case "getOnlinePlayersList":
                serverResponse = handleGetOnlinePlayersRequest(data, clientHandler);
                break;
            case "sendInvitation":
                serverResponse = handleSendInvitationRequest(data, clientHandler);
                break;
            case "acceptInvitation":
                serverResponse = handleAcceptInvitationRequest(data, clientHandler);
                break;
            case "declineInvitation":
                serverResponse = handleDeclineInvitationRequest(data, clientHandler);
                break;
                
            case "logout":
                handleLogoutRequest(data,clientHandler);
                System.out.println("from handle logout request request handler");
                break;
                
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
                playerDAO.setPlayerOnlineStatus(userName, "T");
                playerDAO.setPlayerAvailableStatus(userName, "T");
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
            clienthandeler.player.setUserName(userName);
        } else {
            response = playerExist();
        }
        playerDAO.closeDataBaseConnetion();
        return response;
    }
    
    public static String handleLogoutRequest(JSONObject userData, ClientHandler clienthandler){
        
        String name = (String) userData.get("username");
        playerDAO.startDataBaseConnection();
        System.out.print(name + "is loged out");
        setPlayerOnlineStatus(name,"F");
        setPlayerAvailableStatus(name,"F");
        playerDAO.closeDataBaseConnetion();
        
        return "true";
    }

    public static String handleGetOnlinePlayersRequest(JSONObject userData, ClientHandler clienthandler) {
        playerDAO.startDataBaseConnection();
        ArrayList<String> onlinePlayersLIst = new ArrayList<>();
        onlinePlayersLIst = playerDAO.getOnlinePlayers();
        String response;
        response = ResponseGenerator.onlinePlayers(onlinePlayersLIst);
        return response;
    }

//    public static int sendInvitationRequest(JSONObject userData, ClientHandler clienthandeler) {
//        int size = ClientHandler.clientsHandler.size();
//        //ClientHandler Reciever = new ClientHandler();
//        String userName = (String) userData.get("username");
//        int userID = -1;
//        for (int i = 0; i < size; i++) {
//            if (ClientHandler.clientsHandler.get(i).player.getUserName().equals(userName)) {
//                userID = i;
//                //ClientHandler.clientsHandler.get(i).sendResponse(userName);
//                break;
//            }
//        }
//        return userID;
//    }
    public static String handleSendInvitationRequest(JSONObject invitationData, ClientHandler clientHandler) {
        String sender = (String) invitationData.get("invitationSender");
        String receiver = (String) invitationData.get("invitationReceiver");
        ClientHandler receiverHandler = findClientHandlerByName(receiver);
        
        if (receiverHandler != null) {
            receiverHandler.sendResponse(ResponseGenerator.invitationReceivedResponse(sender));
            System.out.println(ResponseGenerator.invitationReceivedResponse(sender));
            return ResponseGenerator.invitationSentResponse(receiver);
        } else {
            return ResponseGenerator.playerNotExistResponse();
        }
    }

    private static ClientHandler findClientHandlerByName(String playerName) {
        for (ClientHandler handler : ClientHandler.clientsHandler) {
            if (handler.player.getUserName().equals(playerName)) {
                return handler;
            }
        }
        return null;
    }
     private static String handleAcceptInvitationRequest(JSONObject data, ClientHandler clientHandler) {
        String senderPlayerName = (String) data.get("sender");
        ClientHandler senderPlayerHandler = findClientHandlerByName(senderPlayerName);

        if (senderPlayerHandler != null) {
            // Notify the sender that the invitation was accepted
            senderPlayerHandler.sendResponse(ResponseGenerator.invitationAccepted(clientHandler.player.getUserName()));
            return ResponseGenerator.invitationAccepted(clientHandler.player.getUserName());
        } else {
            return ResponseGenerator.playerNotExistResponse();
        }
    }

    private static String handleDeclineInvitationRequest(JSONObject data, ClientHandler clientHandler) {
        String senderPlayerName = (String) data.get("sender");
        ClientHandler senderPlayerHandler = findClientHandlerByName(senderPlayerName);

        if (senderPlayerHandler != null) {
            // Notify the sender that the invitation was declined
            senderPlayerHandler.sendResponse(ResponseGenerator.invitationDeclined(clientHandler.player.getUserName()));
            return ResponseGenerator.invitationDeclined(clientHandler.player.getUserName());
        } else {
            return ResponseGenerator.playerNotExistResponse();
        }
    }
    
}
