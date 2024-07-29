/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import static network.ClientHandler.*;

/**
 *
 * @author sammar alaa
 */
public class Server {

    public static ServerSocket serverSocket;
    public static final int SERVER_POORT = 5005;
    public static boolean isClosed = true;
    //static Vector<String> clientsVector;
    public static Socket socket;

    public static void startServer() {
        isClosed = false;
        clientsHandler = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(SERVER_POORT);
            while (serverSocket != null && !serverSocket.isClosed()) {
                socket = serverSocket.accept();
                
                if (isClosed) {
                    socket.close();
                    break;
                }
                //System.out.println(socket.isClosed());
                newClientHandler(socket);
                

            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("problem on openning the server...");
        }

    }

    public static void closeServer() {
         
        //clientsVector.broadCastToAll(string msg);
        for (int i = 0; i < ClientHandler.clientsHandler.size(); i++) {
            clientsHandler.get(i).closeConnection();
           
        }
        if (!isClosed) {

            try {
                serverSocket.close();
                socket.close();
                isClosed=true;
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("problem on closeing the server...");
            }
        }
    }
}
