package views;

import NBA_Team.ContractExtension;
import NBA_Team.contractPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.LinkedList;

public class ContractForm extends Application {
    private TextArea contractPlayersTextArea;
    private static final String URL1="jdbc:mysql://127.0.0.1:3306/nba_player";
    private static final String USED="root";
    private static final String PASSWORD="Xyg66666";
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Contract Extension Form");

        // Create labels and text fields for player name
        Label nameLabel = new Label("Player Name:");
        nameLabel.setLayoutX(50);
        nameLabel.setLayoutY(30);

        TextField nameText = new TextField();
        nameText.setLayoutX(175);
        nameText.setLayoutY(30);

        // Create the add player button
        Button addPlayerButton = new Button("Add Player");
        addPlayerButton.setLayoutX(250);
        addPlayerButton.setLayoutY(70);
        addPlayerButton.setOnAction(e -> {
            String playerName = nameText.getText();

            if (playerName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Player name must not be empty.");
                alert.showAndWait();
            } else {
                addPlayerToDatabase(playerName);
                updateContractPlayersTextArea();
                nameText.clear();
            }
        });

        // Create the remove player button
        Button removePlayerButton = new Button("Remove Player");
        removePlayerButton.setLayoutX(225);
        removePlayerButton.setLayoutY(320);
        removePlayerButton.setOnAction(e -> {
            if (isQueueEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No players in the contract extension queue.");
                alert.showAndWait();
            } else {
                removePlayerFromDatabase();
                updateContractPlayersTextArea();
            }
        });

        // Create text area to display contract players
        contractPlayersTextArea = new TextArea();
        contractPlayersTextArea.setLayoutX(50);
        contractPlayersTextArea.setLayoutY(110);
        contractPlayersTextArea.setPrefSize(270, 200);
        contractPlayersTextArea.setEditable(false);

        // Create a pane and add all elements to it
        Pane pane = new Pane(nameLabel, nameText, addPlayerButton, removePlayerButton, contractPlayersTextArea);
        Scene scene = new Scene(pane, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();

        updateContractPlayersTextArea();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL1,USED,PASSWORD);
    }
    private void addPlayerToDatabase(String playerName) {
        String insertSQL = "INSERT INTO contract_player (name) VALUES (?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removePlayerFromDatabase() {
        String deleteSQL = "DELETE FROM contract_player WHERE rowid = (SELECT MIN(rowid) FROM contract_player)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No players found to remove.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateContractPlayersTextArea() {
        contractPlayersTextArea.clear();
        StringBuilder sb = new StringBuilder();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM contract_player");
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                sb.append("No players are currently on the contract extension.");
            } else {
                while (rs.next()) {
                    sb.append("Player Name: ").append(rs.getString("name")).append("\n\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        contractPlayersTextArea.setText(sb.toString());
    }

    private boolean isQueueEmpty() {
        boolean isEmpty = true;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM contract_player");
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                isEmpty = rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isEmpty;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

