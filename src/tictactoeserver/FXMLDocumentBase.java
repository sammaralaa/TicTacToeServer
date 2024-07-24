package tictactoeserver;

import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.apache.derby.jdbc.ClientDriver;

public class FXMLDocumentBase extends AnchorPane {

    protected final Button button;
    protected final Label label;

    public FXMLDocumentBase() {

        button = new Button();
        label = new Label();

        setId("AnchorPane");
        setPrefHeight(200);
        setPrefWidth(320);

        button.setLayoutX(126);
        button.setLayoutY(90);
        // button.setOnAction(this::handleButtonAction);
        button.setText("Click Me!");

        label.setLayoutX(126);
        label.setLayoutY(120);
        label.setMinHeight(16);
        label.setMinWidth(69);

        getChildren().add(button);
        getChildren().add(label);
//        try {
//            DriverManager.registerDriver(new ClientDriver());
//            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/players", "root", "root");
//            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            // String queryString = new String("select * from CONTACTSDATA");
//
//            //        rs = stmt.executeQuery(queryString);
//            PreparedStatement pst = con.prepareStatement("INSERT INTO PLAYERDATA (USERNAME,PASSWORD,ISONLINE,ISAVAILABLE,SCORE) VALUES (?,?,?,?,?)");
//            
//            pst.setString(1, "SAMMAR");
//            pst.setString(2, "sd2344s");
//            pst.setString(3,"true");
//            pst.setString(4, "false");
//            pst.setInt(5, 0);
//            
//            int l = pst.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

    }

    /// protected  void handleButtonAction(javafx.event.ActionEvent actionEvent);
}
