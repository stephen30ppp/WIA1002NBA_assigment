/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba;

/**
 *
 * @author fwhed
 */
public class injuredPlayer<E,I,S> {
    
    private String player;
    private String Injury;
    private String Status;
    
    public injuredPlayer(){}
    
    public injuredPlayer(String player, String Injury, String Status){
        this.player= player;
        this.Injury= Injury;
        this.Status= Status;
    }
    
    public String getPlayer() {
        return player;
    }
    
    public String getInjury(){
        return Injury;
    }
    
    public String getStatus(){
        return Status;
    }
    
    public void setStatus(String Status) {
        this.Status= Status;
    }
    
    public void setInjury(String Injury) {
        this.Injury= Injury;
    }
    
    @Override
    public String toString(){
        return "Player: "+ getPlayer() +"\nInjury: "+ getInjury()+ "\nStatus: "+getStatus();
    }
    
}
