package views;

import NBA_Team.Players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerPerformanceRanking    {
    /*
        public static void main(String[] args) throws SQLException {
            PlayerRepository playerRepository = new PlayerRepository();
            playerRepository.savePlayer(new Players("Lebron","James","Forward",40,200,90.5,25.5,8.5,7.0,1.0,0.9,20000,true));
        }
    */
    public static List<Map.Entry<String, Double>> calculateRankings(List<Players> players) {

        HashMap<String, Double> playerScores = new HashMap<>();

        // Calculate composite score for each player
        for (Players player : players) {
            String name = player.getName();
            double compositionScore = compositeScore(player.getPosition(), player);
            playerScores.put(name, compositionScore);
        }

        // Sort players based on composite score
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(playerScores.entrySet());
        //sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        insertionSort(sortedEntries);

        return sortedEntries;
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
/*
    public static void insertionSort(List<Map.Entry<String, Double>> list) {

        // to iterate through all the elements
        for (int i = 0; i<list.size(); i++) {
            int current = list[i];
            int k;

            // inner loop to arrange the elements before i in ascending order
            for (k=i-1 ; k>=0 && list[k]>current ; k--) {
                list[k+1] = list[k];  // pushing to the right hand side
            }
            list[k+1] = current;
        }
    }
 */

    public static void printRankings(List<Map.Entry<String, Double>> sortedPlayers) {
        System.out.println(" -- Player Performance Ranking -- ");
        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedPlayers) {
            System.out.println("Player : " + entry.getKey());
            System.out.printf("Composite Score : %.2f\n" ,entry.getValue());
            System.out.println("Rank : " + rank);
            System.out.println();
            rank++;
        }
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

}