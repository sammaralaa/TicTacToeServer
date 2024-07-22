/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Electronica Care
 */
public class Server {

    public static ServerSocket serverSocket;
    public static final int SERVER_POORT = 5005;
    public static boolean isClosed = false;
    static Vector<String> clientsVector;
    public static Socket socket;

    public static void startServer() {
        isClosed = false;
        clientsVector = new Vector<String>();
        try {
            serverSocket = new ServerSocket(SERVER_POORT);
            while (true) {
                socket = serverSocket.accept();
                if (isClosed) {
                    socket.close();
                    break;
                }
                //clientsVector.add("aaa");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("problem on openning the server...");
        }

    }

    public static void closeServer() {
         
        //clientsVector.broadCastToAll(string msg);
        for (int i = 0; i < clientsVector.size(); i++) {
            //close streams and socket for every player
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
