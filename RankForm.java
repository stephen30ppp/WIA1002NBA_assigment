package views;

import NBA_Team.InjuryReserve;
import NBA_Team.Players;
import NBA_Team.Team;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static NBA_Team.Team.calculateAndPrintRankings;

public class RankForm extends Application {

    private TextArea rankingTextArea;
    private Team team=new Team();
    private static final String URL1="jdbc:mysql://127.0.0.1:3306/nba_player";
    private static final String USED="root";
    private static final String PASSWORD="Xyg66666";
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL1,USED,PASSWORD);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        ResultSet set=getConnection().createStatement().executeQuery("select  * from team_players ");

        InjuryReserve injuryReserve=new InjuryReserve();
        while (set.next()) {
            Players players1 = new Players(set.getString(5), set.getString(1), set.getInt(6), true, set.getInt(2), set.getDouble(3), set.getInt(4), set.getDouble(7), set.getDouble(9), set.getDouble(8), set.getDouble(10), set.getDouble(11));

           team.addPlayer(players1);

        }
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

//        List<Map.Entry<String, Double>> sortedEntries = PlayerPerformanceRanking.calculateRankings(team);
//        PlayerPerformanceRanking.printRankings(sortedEntries);
//        // Display rankings in the text area
//        StringBuilder sb = new StringBuilder();
//        sb.append(calculateAndPrintRankings());
//        sb.append(" -- Player Performance Ranking -- \n");
//        int rank = 1;
//        for (Map.Entry<String, Double> entry : sortedEntries) {
//            String name = entry.getKey();
//            double score = entry.getValue();
//            sb.append("Player : ").append(name).append("\n");
//            sb.append("Composite Score : ").append(score).append("\n");
//            sb.append("Rank : ").append(rank).append("\n\n");
//            rank++;
//        }
        rankingTextArea.setText(calculateAndPrintRankings());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
