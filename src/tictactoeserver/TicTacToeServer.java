/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static network.playerDAO.*;
import static network.requestHandler.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
/**
 *
 * @author Electronica Care
 */
public class TicTacToeServer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new FXMLDocumentBase();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // startDataBaseConnection();
        //insertPlayer("SAMMAR","sd2344s");
        JSONObject userData = new JSONObject();
        userData.put("username", "SAMMAR");
        userData.put("password", "sd2344s");
        handleLoginRequest(userData);
        launch(args);
    }
    
}
