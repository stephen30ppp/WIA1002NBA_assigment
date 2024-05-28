package nba;

import java.util.LinkedList;

/**
 *
 * @author fwhed
 */
public class ContractExtension {
    private java.util.LinkedList <contractPlayer> list = new java.util.LinkedList<>();
    
    public void addExtendContract (String player){
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
    
    public LinkedList<contractPlayer> getList() {
        return list;
    }
}
