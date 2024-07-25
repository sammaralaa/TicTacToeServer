/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Electronica Care
 */
public class ClientHandler {
     private Socket socket;
    private DataInputStream inputStream;
    private PrintStream printStream;
    
    static ArrayList<ClientHandler> clientsHandler;
    
    public ClientHandler(Socket socket) {
        try {
            this.socket=socket;
            inputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());
           // if()
           // ChatHandler.clientsVector.add(this);
           // start();
           acceptRequests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void newClientHandler(Socket s){
        clientsHandler.add(new ClientHandler(s));
    }
    
    public  void acceptRequests(){
        new Thread(() -> {
            try {
                String playerRequest;
                    while (!socket.isClosed() &&(playerRequest = inputStream.readLine()) != null) {
                      sendResponse(requestHandler.handleRequest(playerRequest, ClientHandler.this));
                     // System.out.println(playerRequest);
                    }
            } catch (Exception e) {
                System.out.println("exception in accept response");
                //closeEveryThing();
            }
        }).start();
    
    }
    
    public void sendResponse(String response){
        if(response == null)
            return;
        try{
        printStream.println(response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        
    }
}
