package NBA_Team;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final int Max_Players=15;
    private final int Min_Players=10;
    private static final int min_positional_requirement=2;
    private static List<Players>players=new ArrayList<>();
    private static final double Salary_Cap=20000;
    public Team(){
        this.players=new ArrayList<>();
    }
    public boolean addPlayer(Players player){
        if (players.size()>=Max_Players){
            System.out.println("Cannot add player.Salary cap exceeded.");
            return false;
        }
        double totalSalary=players.stream().mapToDouble(Players::getSalary).sum()+player.getSalary();
        //judge the team salary
        if (totalSalary>Salary_Cap){
            System.out.println("Cannot add player.Salary cap exceeded");
            return false;
        }
       requirePosition();
        players.add(player);
        return true;
    }
    private boolean meetsPositionalAfterAdding(Players player){
        players.add(player);
        boolean meetsPositionalAfterAdding=checkPositionalRequirements();
        players.remove(player);
        return meetsPositionalAfterAdding;
    }
    //Determine the team's Position is meet the requirement
    public boolean checkPositionalRequirements(){
        return countPlayersByPosition("Guard")>=min_positional_requirement&&
                countPlayersByPosition("Forward")>=min_positional_requirement&&
                countPlayersByPosition("Center")>=min_positional_requirement;
    }
    // count the every position's number
    private static int countPlayersByPosition(String position){
        int count=0;
        for (Players player : players) {
            if (player.getPosition().equalsIgnoreCase(position)){
                count++;
            }
        }
        return count;
    }
    //hint the every position remain the requirement
    public static void requirePosition(){
        if (countPlayersByPosition("Guard")<=min_positional_requirement){
            System.out.println("you also need"+(min_positional_requirement-countPlayersByPosition("Guard"))+" Guards in your team");
        }
        if (countPlayersByPosition("ForWard")<=min_positional_requirement){
            System.out.println("you also need"+(min_positional_requirement-countPlayersByPosition("Forward"))+" Forward in your team");
        }
        if (countPlayersByPosition("Center")<=min_positional_requirement){
            System.out.println("you also need"+(min_positional_requirement-countPlayersByPosition("Guard"))+" Center in your team");
        }
    }
}
