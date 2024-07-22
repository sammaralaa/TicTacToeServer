package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

public class playerDAO {

    private static Connection ServerConnection;

    public void startDataBaseConnection() {

        try {
            DriverManager.registerDriver(new ClientDriver());
            ServerConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/players", "root", "root");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("can't open the database connection");
        }
    }

    public void closeDataBaseConnetion() {

        try {
            ServerConnection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertPlayer(String name, String pass) {
        try {
            PreparedStatement pst = ServerConnection.prepareStatement("INSERT INTO PLAYERSDATA (USERNAME,PASSWORD,ISONLINE,ISAVAILABLE) VALUES (?,?,?,?)");
            pst.setString(2, name);
            pst.setString(3, pass);
            pst.setString(4, "true");
            pst.setString(5, "true");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
