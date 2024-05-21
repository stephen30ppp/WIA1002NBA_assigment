

import java.util.EmptyStackException;

/**
 *
 * @author fwhed
 */
public class InjuryReserve {
    private java.util.ArrayList<injuredPlayer> list= new java.util.ArrayList<>();

    public void addInjuredPlayer (Players player,String injury){
        injuredPlayer injured = new injuredPlayer(player, injury, "Added to Injury Reserve");
        list.add(injured);
        System.out.println("-- Adding Player to Injury Reserve --");
        System.out.println(newInjuredPlayer().toString());
        System.out.println();
    }
    public injuredPlayer removeInjuredPlayer(String player){
        if (!isStackEmpty()) {
            int indexPlayer= getIndexPlayer(player);
            if (indexPlayer != -1) {
                list.get(indexPlayer).setStatus("Cleared to Play");
                System.out.println("-- Removing Player from Injury Reserve --");
                System.out.println(list.get(indexPlayer).toString());
                return list.remove(indexPlayer);
            } else {
                System.out.println("Player not found in the injury reserve.");
                throw new EmptyStackException();
            }
        } else {
            System.out.println("No players in the injury reserve.");
            throw new EmptyStackException();
        }
    }

    public injuredPlayer newInjuredPlayer(){
        return list.get(list.size()-1);
    }
    public int getIndexPlayer (String player){
        int i=0;
        for (injuredPlayer injured : list) {
            if (player.equalsIgnoreCase(injured.getPlayerName())){
                list.get(i);
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean isStackEmpty(){
        return list.isEmpty();
    }

    public void displayInjuredPlayers() {
        if (isStackEmpty()) {
            System.out.println("\nNo players are currently on the injury reserve.");
        } else {
            System.out.println("\nInjured Players:");
            for (injuredPlayer injured : list) {
                System.out.println(injured);
                System.out.println();
            }
        }
    }

    public String toString(){
        return "Injury Reserve Stack: "+ list.toString();
    }
}

class injuredPlayer<E,I,S> {
    private Players player;
    private String Injury;
    private String Status;

    public injuredPlayer(){}

    public injuredPlayer(Players player, String Injury, String Status){
        this.player= player;
        this.Injury= Injury;
        this.Status= Status;
    }

    public Players getPlayer() {
        return player;
    }

    public String getPlayerName(){
        return player.getFirstName() + player.getLastName();
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
        return "Player: "+ getPlayerName() +"\nInjury: "+ getInjury()+ "\nStatus: "+getStatus();
    }

}
