package NBA_Team;

import java.util.*;
import java.util.stream.Collectors;

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
//       requirePosition();
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
    public static int countPlayersByPosition(String position){
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
            System.out.println("you also need"+(min_positional_requirement-countPlayersByPosition("Center"))+" Center in your team");
        }
    }
    public static void findPlayers(double minHeight, int minWeight, String position) {
        List<Players> filteredPlayers = players.stream()
                .filter(p -> p.getHeight() >= minHeight )
                .filter(p -> p.getWeight() >= minWeight)
                .filter(p -> p.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());

        if (filteredPlayers.isEmpty()) {
//            return "No players found.";
            System.out.println("No players found.");
        }

//        StringBuilder result = new StringBuilder();
//        for (Players p : filteredPlayers) {
//            result.append(p.getName()).append(" | ").append(p.getAge()).append(" | ").append(p.getHeight())
//                    .append(" | ").append(p.getWeight()).append(" | ").append(p.getPosition()).append(" | ")
//                    .append(p.getSalary()).append(" | ").append(p.getPoints()).append(" | ")
//                    .append(p.getRebounds()).append(" | ").append(p.getAssists()).append(" | ")
//                    .append(p.getSteals()).append(" | ").append(p.getBlocks()).append("\n");
//        }
        System.out.println("Player List :");
        System.out.printf("| %-15s | %3s | %6s | %6s | %-8s | %6s | %6s | %8s | %7s | %6s | %6s |%n",
                "Name", "Age", "Height", "Weight", "Position", "Salary", "Points", "Rebounds", "Assists", "Steals", "Blocks");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for (Players player : filteredPlayers) {
            System.out.printf("| %-15s | %3d | %6.2f | %6d | %-8s | %6d | %6.1f | %8.1f | %7.1f | %6.1f | %6.1f |%n",
                    player.getName(), player.getAge(), player.getHeight(),player.getWeight() ,player.getPosition() ,player.getSalary(),player.getPoints() ,player.getRebounds() ,player.getAssists() ,player.getSteals() ,player.getBlocks());
//        return result.toString();
    }}
    public static void printteam(){
        System.out.println("Player List :");
        System.out.printf("| %-15s | %3s | %6s | %6s | %-8s | %6s | %6s | %8s | %7s | %6s | %6s |%n",
                "Name", "Age", "Height", "Weight", "Position", "Salary", "Points", "Rebounds", "Assists", "Steals", "Blocks");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        for (Players player : players) {
            System.out.printf("| %-15s | %3d | %6.2f | %6d | %-8s | %6d | %6.1f | %8.1f | %7.1f | %6.1f | %6.1f |%n",
                    player.getName(), player.getAge(), player.getHeight(),player.getWeight() ,player.getPosition() ,player.getSalary(),player.getPoints() ,player.getRebounds() ,player.getAssists() ,player.getSteals() ,player.getBlocks());
        }
        requirePosition();
        }
        public static void searchplayer(){
            Scanner sc=new Scanner(System.in);
            System.out.println("please input the information about the player ");
            System.out.println("The minHeight:");
            double minHeight=sc.nextDouble();
            System.out.println("The minWeight:");
            int minWeight=sc.nextInt();
            System.out.println("Position:");
            String position=sc.next();
            findPlayers(minHeight,minWeight,position);
        }
        public List<Players> getPlayers(){
        return players;
        }
        public List<Players> printsearchplayer(double minWeight,double minHeight,String position){
               return players.stream()
                       .filter(p ->p.getWeight()>=minWeight)
                       .filter(p->p.getHeight()>=minHeight)
                       .filter(p->p.getPosition().equalsIgnoreCase(position))
                       .collect(Collectors.toList());
        }
    public static String calculateAndPrintRankings() {

        HashMap<String, Double> playerScores = new HashMap<>();

        // Calculate composite score for each player
        for (Players player : players) {
            String name = player.getName();
            double compositionScore = compositeScore(player.getPosition(), player);
            playerScores.put(name, compositionScore);
        }

        // Sort players based on composite score
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(playerScores.entrySet());
//        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        insertionSort(sortedEntries);
        // Print rankings
        StringBuilder sb = new StringBuilder();
        sb.append(" -- Player Performance Ranking -- \n");
        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedEntries) {
            String name = entry.getKey();
            double score = entry.getValue();
            String formattedScore = String.format("%.2f", score);
            sb.append("Player : ").append(name).append("\n");
            sb.append("Composite Score : ").append(formattedScore).append("\n");
            sb.append("Rank : ").append(rank).append("\n\n");
            rank++;
        }
        return sb.toString();
    }

    private static double compositeScore(String position, Players player) {
        double compositionScore = 0;
        double points = player.getPoints();
        double assists = player.getAssists();
        double rebounds = player.getRebounds();
        double steals = player.getSteals();
        double blocks = player.getBlocks();

        if (position.equalsIgnoreCase("Forward"))
            compositionScore = ((points / 23 + steals / 1.5 + rebounds / 7 + blocks / 0.3 + assists / 8) * 100) / 5;
        else if (position.equalsIgnoreCase("Center"))
            compositionScore = ((points / 10 + blocks / 0.5 + rebounds / 12 + assists / 8 + steals / 1.3) * 100) / 5;
        else if (position.equalsIgnoreCase("Guard"))
            compositionScore = (((points / 18) + (assists / 9) + (steals / 1.3) + (rebounds / 5) + (blocks / 0.3)) * 100) / 5;

        return compositionScore;
    }
    public static void insertionSort(List<Map.Entry<String, Double>> list) {
        // To iterate through all the elements
        for (int i = 1; i < list.size(); i++) {
            Map.Entry<String, Double> currentEntry = list.get(i);
            Double currentScore = currentEntry.getValue();
            int k;

            // Inner loop to arrange the elements before i in ascending order
            for (k = i-1 ; k >= 0 && list.get(k).getValue() < currentScore ; k--) {
                list.set(k + 1, list.get(k));
            }
            list.set(k + 1, currentEntry);
        }
    }
//    public static void insertionSort(List<Map.Entry<String, Double>> list) {
//
//        // to iterate through all the elements
//        for (int i = 0; i<list.size(); i++) {
//            int current = list[i];
//            int k;
//
//            // inner loop to arrange the elements before i in ascending order
//            for (k=i-1 ; k>=0 && list[k]>current ; k--) {
//                list[k+1] = list[k];  // pushing to the right hand side
//            }
//            list[k+1] = current;
//        }
//    }
    }

