/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author Electronica Care
 */
public class playerDTO {
    private String userName;
    private String password;
    private boolean isOnline;
    private boolean isAvailable;
    private int score;
    
    playerDTO(String name,String pass,boolean online,boolean available,int score){
        
        userName=name;
        password=pass;
        isOnline =online;
        isAvailable = available;
        this.score=score;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    
    public void setIsOnline(boolean online){
        isOnline = online;
    }
    public boolean getIsOnline(){
        return isOnline;
    }
    
    public void setIsAvailable(boolean available){
        isOnline = available;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }
    
    public void setScore(int s){
        score = s;
    }
    public int getScore(){
        return score;
    }
}
