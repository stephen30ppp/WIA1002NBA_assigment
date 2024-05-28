package guinba;

import java.util.ArrayList;
import java.util.Comparator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.List;
import java.util.Map;
import nba.PlayerPerformanceRanking;
import nba.Players;
import nba.Team;

public class RankForm extends Application {

    private TextArea rankingTextArea;
    private List<Players> players;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Player Performance Ranking");

        // Create text area to display rankings
        rankingTextArea = new TextArea();
        rankingTextArea.setLayoutX(20);
        rankingTextArea.setLayoutY(20);
        rankingTextArea.setPrefSize(360, 400);
        rankingTextArea.setEditable(false);

        // Create the show rankings button
        Button showRankingsButton = new Button("Show Rankings");
        showRankingsButton.setLayoutX(150);
        showRankingsButton.setLayoutY(450);
        showRankingsButton.setOnAction(e -> showRankings());


        // Create a pane and add all elements to it
        Pane pane = new Pane(rankingTextArea, showRankingsButton);
        Scene scene = new Scene(pane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showRankings() {
        rankingTextArea.clear();
        List<Map.Entry<String, Double>> sortedEntries = PlayerPerformanceRanking.calculateRankings(players);
        PlayerPerformanceRanking.printRankings(sortedEntries);
        // Display rankings in the text area
        StringBuilder sb = new StringBuilder();
        sb.append(" -- Player Performance Ranking -- \n");
        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedEntries) {
            String name = entry.getKey();
            double score = entry.getValue();
            sb.append("Player : ").append(name).append("\n");
            sb.append("Composite Score : ").append(score).append("\n");
            sb.append("Rank : ").append(rank).append("\n\n");
            rank++;
        }
        rankingTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
