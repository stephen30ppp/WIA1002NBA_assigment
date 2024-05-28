/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba;

/**
 *
 * @author fwhed
 */
public class contractPlayer {
    private String player;
    private String Status;
    
    public contractPlayer(){}
    
    public contractPlayer(String player, String Status){
        this.player= player;
        this.Status= Status;
    }
    
    public String getPlayer() {
        return player;
    }
    
    public String getStatus(){
        return Status;
    }
    
    public void setStatus(String Status) {
        this.Status= Status;
    }
    
    @Override
    public String toString(){
        return "Player: "+ getPlayer() +"\nStatus: "+getStatus();
    }
    
}
