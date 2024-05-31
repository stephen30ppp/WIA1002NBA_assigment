package nbareal2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        primaryStage.setTitle("Player Form");

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
        searchButton.setLayoutX(50);
        searchButton.setLayoutY(230);
        searchButton.setOnAction(e -> searchPlayers());

        // Create the add player button
        Button addPlayerButton = new Button("Add Selected Player");
        addPlayerButton.setLayoutX(150);
        addPlayerButton.setLayoutY(230);
        addPlayerButton.setOnAction(e -> addSelectedPlayer());

        // Create the remove player button
        Button removePlayerButton = new Button("Remove Selected Player");
        removePlayerButton.setLayoutX(300);
        removePlayerButton.setLayoutY(230);
        removePlayerButton.setOnAction(e -> removeSelectedPlayer());
        

        Label stockPlayersLabel = new Label("Stock Players:");
        stockPlayersLabel.setLayoutX(50);
        stockPlayersLabel.setLayoutY(270);
        Label currentPlayersLabel = new Label("Current Players:");
        currentPlayersLabel.setLayoutX(50);
        currentPlayersLabel.setLayoutY(510);
        
        // Create list view to display players
        playerListView = new ListView<>();
        playerListView.setLayoutX(50);
        playerListView.setLayoutY(295);
        playerListView.setPrefSize(400, 200);

        // Create list view to display selected players
        selectedPlayersListView = new ListView<>();
        selectedPlayersListView.setLayoutX(50);
        selectedPlayersListView.setLayoutY(535);
        selectedPlayersListView.setPrefSize(400, 180);

        // Create a pane and add all elements to it
        Pane pane = new Pane(
                nameLabel, nameText,
                positionLabel, positionText,
                minHeightLabel, minHeightText,
                maxHeightLabel, maxHeightText,
                minWeightLabel, minWeightText,
                maxWeightLabel, maxWeightText,
                searchButton, addPlayerButton, removePlayerButton,
                stockPlayersLabel, currentPlayersLabel, 
                playerListView, selectedPlayersListView
        );

        Scene scene = new Scene(pane, 500,750); 
        primaryStage.setScene(scene);
        primaryStage.show();
        
        updatePlayerListView(playerList); // Print first all the stock players available 
    }

    
    // TO TRY AND SEE THE OUTPUT PURPOSE, CAN DELETE AFTER CONNECTED WITH DATABASE
    private void initializePlayerList() {
        playerList.add(new Players("Guard", "Stephen Curry", 45780966, true, 35, 6.2, 185, 29.3, 6.3, 5.4, 1.3, 0.4));
        playerList.add(new Players("Forward", "LeBron James", 41180544, true, 38, 6.9, 250, 25.4, 7.9, 7.6, 1.1, 0.6));
        playerList.add(new Players("Forward", "Kevin Durant", 42371840, true, 35, 6.10, 240, 27.1, 7.1, 4.2, 0.9, 1.1));
        playerList.add(new Players("Guard", "James Harden", 44681760, true, 34, 6.5, 220, 24.9, 10.9, 6.2, 1.5, 0.6));
        playerList.add(new Players("Center", "Joel Embiid", 33456789, true, 29, 7.0, 280, 28.7, 3.7, 11.2, 1.0, 1.7));
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


