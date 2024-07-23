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
import java.util.Vector;

/**
 *
 * @author Electronica Care
 */
public class GameHandler {
     private Socket socket;
    private DataInputStream inputStream;
    private PrintStream printStream;
    
    static Vector<GameHandler> clientsVector = new Vector<GameHandler>();
    
    public GameHandler(Socket socket) {
        try {
            this.socket=socket;
            inputStream = new DataInputStream(socket.getInputStream());
            printStream = new PrintStream(socket.getOutputStream());
           // if()
           // ChatHandler.clientsVector.add(this);
           // start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void acceptRequests(){
        new Thread(() -> {
            try {
                String playerRequest;
                    while (!socket.isClosed() &&(playerRequest = inputStream.readLine()) != null) {
                     //   sendResponse(requestHandler.handleRequest(playerRequest, GameHandler.this));
                    }
            } catch (Exception e) {
                System.out.println("exception in accept response");
                //closeEveryThing();
            }
        }).start();
    
    }
}
