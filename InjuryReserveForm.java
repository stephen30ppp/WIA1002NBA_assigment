package views;

import NBA_Team.InjuryReserve;
import NBA_Team.injuredPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.EmptyStackException;

public class InjuryReserveForm extends Application {
    private TextArea injuredPlayersTextArea;
    private static final String URL1="jdbc:mysql://127.0.0.1:3306/nba_player";
    private static final String USED="root";
    private static final String PASSWORD="Xyg66666";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Injury Reserve Form");

        // Create labels and text fields for player name and injury
        Label nameLabel = new Label("Player Name:");
        nameLabel.setLayoutX(50);
        nameLabel.setLayoutY(30);

        TextField nameText = new TextField();
        nameText.setLayoutX(150);
        nameText.setLayoutY(30);

        Label injuryLabel = new Label("Injury:");
        injuryLabel.setLayoutX(50);
        injuryLabel.setLayoutY(70);

        TextField injuryText = new TextField();
        injuryText.setLayoutX(150);
        injuryText.setLayoutY(70);

        // Create the add player button
        Button addPlayerButton = new Button("Add Player");
        addPlayerButton.setLayoutX(150);
        addPlayerButton.setLayoutY(110);
        addPlayerButton.setOnAction(e -> {
            String player = nameText.getText();
            String injury = injuryText.getText();

            if (player.isEmpty() || injury.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Player name and injury must not be empty.");
                alert.showAndWait();
            } else {
                addInjuredPlayerToDatabase(player, injury);
                updateInjuredPlayersTextArea();
                nameText.clear();
                injuryText.clear();
            }
        });

        // Create the remove player button
        Button removePlayerButton = new Button("Remove Player");
        removePlayerButton.setLayoutX(250);
        removePlayerButton.setLayoutY(110);
        removePlayerButton.setOnAction(e -> {
            String playerName = nameText.getText();
            String injury = injuryText.getText();

            if (playerName.isEmpty() || injury.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Player name and injury must not be empty.");
                alert.showAndWait();
            } else {
                removeInjuredPlayerFromDatabase(playerName, injury);
                updateInjuredPlayersTextArea();
            }
        });

        // Create text area to display injured players
        injuredPlayersTextArea = new TextArea();
        injuredPlayersTextArea.setLayoutX(50);
        injuredPlayersTextArea.setLayoutY(150);
        injuredPlayersTextArea.setPrefSize(300, 200);
        injuredPlayersTextArea.setEditable(false);

        // Create a pane and add all elements to it
        Pane pane = new Pane(nameLabel, nameText, injuryLabel, injuryText, addPlayerButton, removePlayerButton, injuredPlayersTextArea);
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        updateInjuredPlayersTextArea();
    }

    private Connection getConnection() throws SQLException {
      return DriverManager.getConnection(URL1,USED,PASSWORD);
    }

    private void addInjuredPlayerToDatabase(String name, String injury) {
        String insertSQL = "INSERT INTO injury_player (player, injuryText) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, injury);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeInjuredPlayerFromDatabase(String name, String injury) {
        String deleteSQL = "DELETE FROM injury_player WHERE player = ? AND injuryText = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, injury);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Player not found in the injury reserve.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateInjuredPlayersTextArea() {
        injuredPlayersTextArea.clear();
        StringBuilder sb = new StringBuilder();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT player, injuryText FROM injury_player");
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                sb.append("No players are currently on the injury reserve.");
            } else {
                while (rs.next()) {
                    sb.append("Player Name: ").append(rs.getString("player")).append("\n");
                    sb.append("Injury: ").append(rs.getString("injuryText")).append("\n\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        injuredPlayersTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}