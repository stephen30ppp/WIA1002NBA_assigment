package views;

import NBA_Team.Players;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerForm extends Application {
    private List<Players> playerList = new ArrayList<>();
    private List<Players> selectedPlayers = new ArrayList<>();
    private ListView<Players> playerListView;
    private ListView<Players> selectedPlayersListView;
    private TextField nameText;
    private TextField positionText;
    private TextField minHeightText;
    private TextField maxHeightText;
    private TextField minWeightText;
    private TextField maxWeightText;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Player Form");

        // Initialize player list with 5 NBA players
        initializePlayerList();

        // Create labels and text fields for player attributes
        Label nameLabel = new Label("Player Name:");
        nameLabel.setLayoutX(50);
        nameLabel.setLayoutY(30);
        nameText = new TextField();
        nameText.setLayoutX(150);
        nameText.setLayoutY(30);

        Label positionLabel = new Label("Position:");
        positionLabel.setLayoutX(50);
        positionLabel.setLayoutY(60);
        positionText = new TextField();
        positionText.setLayoutX(150);
        positionText.setLayoutY(60);

        Label minHeightLabel = new Label("Min Height (m):");
        minHeightLabel.setLayoutX(50);
        minHeightLabel.setLayoutY(90);
        minHeightText = new TextField();
        minHeightText.setLayoutX(150);
        minHeightText.setLayoutY(90);

        Label maxHeightLabel = new Label("Max Height (m):");
        maxHeightLabel.setLayoutX(50);
        maxHeightLabel.setLayoutY(120);
        maxHeightText = new TextField();
        maxHeightText.setLayoutX(150);
        maxHeightText.setLayoutY(120);

        Label minWeightLabel = new Label("Min Weight (kg):");
        minWeightLabel.setLayoutX(50);
        minWeightLabel.setLayoutY(150);
        minWeightText = new TextField();
        minWeightText.setLayoutX(150);
        minWeightText.setLayoutY(150);

        Label maxWeightLabel = new Label("Max Weight (kg):");
        maxWeightLabel.setLayoutX(50);
        maxWeightLabel.setLayoutY(180);
        maxWeightText = new TextField();
        maxWeightText.setLayoutX(150);
        maxWeightText.setLayoutY(180);


        // Create the search button
        Button searchButton = new Button("Search");
        searchButton.setLayoutX(350);
        searchButton.setLayoutY(180);
        searchButton.setOnAction(e -> searchPlayers());

        // Create the "Players List" label
        Label playerListLabel = new Label("Players List: ");
        playerListLabel.setLayoutX(50);
        playerListLabel.setLayoutY(230);

        // Create list view to display players
        playerListView = new ListView<>();
        playerListView.setLayoutX(50);
        playerListView.setLayoutY(250);
        playerListView.setPrefSize(400, 210);
        // Create a pane and add all elements to it
        Pane pane = new Pane(
                nameLabel, nameText,
                positionLabel, positionText,
                minHeightLabel, minHeightText,
                maxHeightLabel, maxHeightText,
                minWeightLabel, minWeightText,
                maxWeightLabel, maxWeightText,
                searchButton, playerListLabel, playerListView
        );

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        updatePlayerListView(playerList); // Print first all the stock players available
    }

    private void initializePlayerList() {
         String URL1="jdbc:mysql://127.0.0.1:3306/nba_player";
         String USED="root";
         String PASSWORD="Xyg66666";

        String query = "SELECT position, name, salary , age, height, weight FROM team_players";

        try (Connection conn = DriverManager.getConnection(URL1,USED,PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                playerList.add(new Players(
                        rs.getString("position"),
                        rs.getString("name"),
                        rs.getInt("salary"),
                        rs.getInt("age"),
                        rs.getDouble("height"),
                        rs.getInt("weight"),
                        rs.getInt("salary")>=2000?true:false

                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchPlayers() {
        try {
            if (!minHeightText.getText().isEmpty()) {
                double minHeight = Double.parseDouble(minHeightText.getText());
            }
            if (!maxHeightText.getText().isEmpty()) {
                double maxHeight = Double.parseDouble(maxHeightText.getText());
            }
            if (!minWeightText.getText().isEmpty()) {
                double minWeight = Integer.parseInt(minWeightText.getText());
            }
            if (!maxWeightText.getText().isEmpty()) {
                double maxWeight = Integer.parseInt(maxWeightText.getText());
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid number.");
            alert.showAndWait();
            return;
        }

        List<Players> filteredPlayers = playerList.stream().filter(player -> {
            boolean matches = true;

            if (!nameText.getText().isEmpty()) {
                matches &= player.getName().toLowerCase().contains(nameText.getText().toLowerCase());
            }

            if (!positionText.getText().isEmpty()) {
                matches &= player.getPosition().toLowerCase().contains(positionText.getText().toLowerCase());
            }

            if (!minHeightText.getText().isEmpty()) {
                matches &= player.getHeight() >= Double.parseDouble(minHeightText.getText());
            }

            if (!maxHeightText.getText().isEmpty()) {
                matches &= player.getHeight() <= Double.parseDouble(maxHeightText.getText());
            }

            if (!minWeightText.getText().isEmpty()) {
                matches &= player.getWeight() >= Integer.parseInt(minWeightText.getText());
            }

            if (!maxWeightText.getText().isEmpty()) {
                matches &= player.getWeight() <= Integer.parseInt(maxWeightText.getText());
            }

            return matches;
        }).collect(Collectors.toList());

        updatePlayerListView(filteredPlayers);
    }

    private void addSelectedPlayer() {
        Players selectedPlayer = playerListView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            if (selectedPlayers.contains(selectedPlayer)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Player has already been added.");
                alert.showAndWait();
            } else {
                selectedPlayers.add(selectedPlayer);
                updateSelectedPlayersListView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No player selected.");
            alert.showAndWait();
        }
    }

    private void removeSelectedPlayer() {
        Players selectedPlayer = selectedPlayersListView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            selectedPlayers.remove(selectedPlayer);
            updateSelectedPlayersListView();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No player selected.");
            alert.showAndWait();
        }
    }

    private void updatePlayerListView(List<Players> players) {
        playerListView.getItems().setAll(players);
    }

    private void updateSelectedPlayersListView() {
        selectedPlayersListView.getItems().setAll(selectedPlayers);
    }

    private void clearInputFields() {
        nameText.clear();
        positionText.clear();
        minHeightText.clear();
        maxHeightText.clear();
        minWeightText.clear();
        maxWeightText.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}