
public class ContractExtension {
    private java.util.LinkedList <contractPlayer> list = new java.util.LinkedList<>();

    public void addExtendContract (Players player){
        contractPlayer contract = new contractPlayer(player,"Added to Contract Extension Queue");
        list.addLast(contract);
        System.out.println("-- Adding Player to Contract Extension Queue --");
        System.out.println(contract.toString());
        System.out.println();
    }
    public contractPlayer removeExtendContract(){
        getContractPlayer().setStatus("Contract Renewed");
        System.out.println("-- Removing Player from Contract Extension Queue --");
        System.out.println(getContractPlayer().toString());
        System.out.println();
        return list.removeFirst();
    }
    public contractPlayer getContractPlayer(){
        return list.getFirst();
    }

    public boolean isQueueEmpty(){
        return list.isEmpty();
    }

    public String toString(){
        return "Contract Extension Queue: "+ list.toString();
    }

    public void displayContractPlayers() {
        if (isQueueEmpty()) {
            System.out.println("\nNo players are currently on the contract extension.");
        } else {
            System.out.println("\nContract Extension Players:");
            for (contractPlayer contract : list) {
                System.out.println(contract);
                System.out.println();
            }
        }
    }
}

class contractPlayer <E,S> {
    private Players player;
    private String Status;

    public contractPlayer(){}

    public contractPlayer(Players player, String Status){
        this.player= player;
        this.Status= Status;
    }

    public Players getPlayer() {
        return player;
    }

    public String getPlayerName(){
        return player.getFirstName() + player.getLastName();
    }

    public String getStatus(){
        return Status;
    }

    public void setStatus(String Status) {
        this.Status= Status;
    }

    @Override
    public String toString(){
        return "Player: "+ getPlayerName() +"\nStatus: "+getStatus();
    }

}