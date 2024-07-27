package network;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.ClientDriver;

public class playerDAO {

    private static Connection ServerConnection;

    public static void startDataBaseConnection() {

        try {
            DriverManager.registerDriver(new ClientDriver());
            ServerConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/players", "root", "root");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("can't open the database connection");
        }
    }

    public static void closeDataBaseConnetion() {

        try {
            ServerConnection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertPlayer(String name, String pass) {
        try {
            PreparedStatement pst = ServerConnection.prepareStatement("INSERT INTO PLAYERDATA (USERNAME,PASSWORD,ISONLINE,ISAVAILABLE,SCORE) VALUES (?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, pass);
            pst.setString(3, "true");
            pst.setString(4, "true");
            pst.setInt(5, 0);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static playerDTO searchForPlayer(String name) {
        playerDTO player = null;
        try {
            PreparedStatement pst = ServerConnection.prepareStatement("select * from PLAYERDATA where USERNAME = ?");
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                player = new playerDTO();
                player.setUserName(rs.getString("USERNAME"));
                player.setPassword(rs.getString("PASSWORD"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(playerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return player;
    }

    public static ArrayList<String> getOnlinePlayers() {
        ArrayList<String> onlinePlayers = new ArrayList<>();
        try {
            PreparedStatement pst = ServerConnection.prepareStatement("select USERNAME from PLAYERDATA where ISONLINE = ? and ISAVAILABLE");
            pst.setString(1, "true");
            pst.setString(1, "true");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                onlinePlayers.add(
                        rs.getString("USERNAME")
                );

            }
           

        } catch (SQLException ex) {
            Logger.getLogger(playerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return onlinePlayers;
    }
    // Function to get all players from database
    public static ArrayList<playerDTO> getAllPlayers() {
        
        ArrayList<playerDTO> playersArray = new ArrayList<>();
        try {
            
            PreparedStatement pst = ServerConnection.prepareStatement("SELECT * FROM PLAYERDATA");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                playersArray.add(new playerDTO(
                        resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD"),
                        Boolean.parseBoolean(resultSet.getString("ISONLINE")),
                         Boolean.parseBoolean(resultSet.getString("ISAVAILABLE")),
                        resultSet.getInt("SCORE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(playerDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playersArray;
    }
    
    //Function to set Player Online Status into database
    public static void setPlayerOnlineStatus(String username, boolean isOnline) {
    String query = "UPDATE PLAYERDATA SET ISONLINE = ? WHERE USERNAME = ?";
    try (PreparedStatement pst = ServerConnection.prepareStatement(query)) {
        pst.setString(1, Boolean.toString(isOnline));
        pst.setString(2, username);
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(playerDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    //Function to set Player Online Status into database
    public static void setPlayerAvailableStatus(String username, boolean isAvailable) {
    String query = "UPDATE PLAYERDATA SET ISAVAILABLE = ? WHERE USERNAME = ?";
    try (PreparedStatement pst = ServerConnection.prepareStatement(query)) {
        pst.setString(1, Boolean.toString(isAvailable));
        pst.setString(2, username);
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(playerDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
}
