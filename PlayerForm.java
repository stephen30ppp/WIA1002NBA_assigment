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
    private TextField salaryText;
    private CheckBox superstarCheckBox;
    private TextField ageText;
    private TextField heightText;
    private TextField weightText;
    private TextField pointsText;
    private TextField reboundsText;
    private TextField assistsText;
    private TextField stealsText;
    private TextField blocksText;

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

        Label salaryLabel = new Label("Salary:");
        salaryLabel.setLayoutX(50);
        salaryLabel.setLayoutY(90);
        salaryText = new TextField();
        salaryText.setLayoutX(150);
        salaryText.setLayoutY(90);

        Label superstarLabel = new Label("Superstar:");
        superstarLabel.setLayoutX(50);
        superstarLabel.setLayoutY(120);
        superstarCheckBox = new CheckBox();
        superstarCheckBox.setLayoutX(150);
        superstarCheckBox.setLayoutY(120);

        Label ageLabel = new Label("Age:");
        ageLabel.setLayoutX(50);
        ageLabel.setLayoutY(150);
        ageText = new TextField();
        ageText.setLayoutX(150);
        ageText.setLayoutY(150);

        Label heightLabel = new Label("Height:");
        heightLabel.setLayoutX(50);
        heightLabel.setLayoutY(180);
        heightText = new TextField();
        heightText.setLayoutX(150);
        heightText.setLayoutY(180);

        Label weightLabel = new Label("Weight:");
        weightLabel.setLayoutX(50);
        weightLabel.setLayoutY(210);
        weightText = new TextField();
        weightText.setLayoutX(150);
        weightText.setLayoutY(210);

        Label pointsLabel = new Label("Points:");
        pointsLabel.setLayoutX(50);
        pointsLabel.setLayoutY(240);
        pointsText = new TextField();
        pointsText.setLayoutX(150);
        pointsText.setLayoutY(240);

        Label reboundsLabel = new Label("Rebounds:");
        reboundsLabel.setLayoutX(50);
        reboundsLabel.setLayoutY(270);
        reboundsText = new TextField();
        reboundsText.setLayoutX(150);
        reboundsText.setLayoutY(270);

        Label assistsLabel = new Label("Assists:");
        assistsLabel.setLayoutX(50);
        assistsLabel.setLayoutY(300);
        assistsText = new TextField();
        assistsText.setLayoutX(150);
        assistsText.setLayoutY(300);

        Label stealsLabel = new Label("Steals:");
        stealsLabel.setLayoutX(50);
        stealsLabel.setLayoutY(330);
        stealsText = new TextField();
        stealsText.setLayoutX(150);
        stealsText.setLayoutY(330);

        Label blocksLabel = new Label("Blocks:");
        blocksLabel.setLayoutX(50);
        blocksLabel.setLayoutY(360);
        blocksText = new TextField();
        blocksText.setLayoutX(150);
        blocksText.setLayoutY(360);

        // Create the search button
        Button searchButton = new Button("Search");
        searchButton.setLayoutX(50);
        searchButton.setLayoutY(390);
        searchButton.setOnAction(e -> searchPlayers());

        // Create the add player button
        Button addPlayerButton = new Button("Add Selected Player");
        addPlayerButton.setLayoutX(150);
        addPlayerButton.setLayoutY(390);
        addPlayerButton.setOnAction(e -> addSelectedPlayer());

        // Create the remove player button
        Button removePlayerButton = new Button("Remove Selected Player");
        removePlayerButton.setLayoutX(300);
        removePlayerButton.setLayoutY(390);
        removePlayerButton.setOnAction(e -> removeSelectedPlayer());

        Label stockPlayersLabel = new Label("Stock Players:");
        stockPlayersLabel.setLayoutX(50);
        stockPlayersLabel.setLayoutY(430); // Adjusted position to be above the playerListView

        Label currentPlayersLabel = new Label("Current Players:");
        currentPlayersLabel.setLayoutX(50);
        currentPlayersLabel.setLayoutY(575);
        
        // Create list view to display players
        playerListView = new ListView<>();
        playerListView.setLayoutX(50);
        playerListView.setLayoutY(450);
        playerListView.setPrefSize(400, 110);

        // Create list view to display selected players
        selectedPlayersListView = new ListView<>();
        selectedPlayersListView.setLayoutX(50);
        selectedPlayersListView.setLayoutY(595);
        selectedPlayersListView.setPrefSize(400, 110);

        // Create a pane and add all elements to it
        Pane pane = new Pane(
                nameLabel, nameText,
                positionLabel, positionText,
                salaryLabel, salaryText,
                superstarLabel, superstarCheckBox,
                ageLabel, ageText,
                heightLabel, heightText,
                weightLabel, weightText,
                pointsLabel, pointsText,
                reboundsLabel, reboundsText,
                assistsLabel, assistsText,
                stealsLabel, stealsText,
                blocksLabel, blocksText,
                searchButton, addPlayerButton, removePlayerButton,
                stockPlayersLabel, currentPlayersLabel, 
                playerListView, selectedPlayersListView
        );

        Scene scene = new Scene(pane, 500,750); // Adjust the width and height as needed
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializePlayerList() {
        playerList.add(new Players("Guard", "Stephen Curry", 45780966, true, 35, 6.2, 185, 29.3, 6.3, 5.4, 1.3, 0.4));
        playerList.add(new Players("Forward", "LeBron James", 41180544, true, 38, 6.9, 250, 25.4, 7.9, 7.6, 1.1, 0.6));
        playerList.add(new Players("Forward", "Kevin Durant", 42371840, true, 35, 6.10, 240, 27.1, 7.1, 4.2, 0.9, 1.1));
        playerList.add(new Players("Guard", "James Harden", 44681760, true, 34, 6.5, 220, 24.9, 10.9, 6.2, 1.5, 0.6));
        playerList.add(new Players("Center", "Joel Embiid", 33456789, true, 29, 7.0, 280, 28.7, 3.7, 11.2, 1.0, 1.7));
    }

    private void searchPlayers() {
        List<Players> filteredPlayers = playerList.stream().filter(player -> {
            boolean matches = true;

            if (!nameText.getText().isEmpty()) {
                matches &= player.getName().toLowerCase().contains(nameText.getText().toLowerCase());
            }
            if (!positionText.getText().isEmpty()) {
                matches &= player.getPosition().toLowerCase().contains(positionText.getText().toLowerCase());
            }
            if (!salaryText.getText().isEmpty()) {
                matches &= player.getSalary() == Integer.parseInt(salaryText.getText());
            }
            if (superstarCheckBox.isSelected()) {
                matches &= player.isIssuperstar();
            }
            if (!ageText.getText().isEmpty()) {
                matches &= player.getAge() == Integer.parseInt(ageText.getText());
            }
            if (!heightText.getText().isEmpty()) {
                matches &= player.getHeight() == Double.parseDouble(heightText.getText());
            }
            if (!weightText.getText().isEmpty()) {
                matches &= player.getWeight() == Integer.parseInt(weightText.getText());
            }
            if (!pointsText.getText().isEmpty()) {
                matches &= player.getPoints() == Double.parseDouble(pointsText.getText());
            }
            if (!reboundsText.getText().isEmpty()) {
                matches &= player.getRebounds() == Double.parseDouble(reboundsText.getText());
            }
            if (!assistsText.getText().isEmpty()) {
                matches &= player.getAssists() == Double.parseDouble(assistsText.getText());
            }
            if (!stealsText.getText().isEmpty()) {
                matches &= player.getSteals() == Double.parseDouble(stealsText.getText());
            }
            if (!blocksText.getText().isEmpty()) {
                matches &= player.getBlocks() == Double.parseDouble(blocksText.getText());
            }
            
            return matches;
        }).collect(Collectors.toList());

        updatePlayerListView(filteredPlayers);
    }

    private void addSelectedPlayer() {
        Players selectedPlayer = playerListView.getSelectionModel().getSelectedItem();
        if (selectedPlayer != null) {
            selectedPlayers.add(selectedPlayer);
            updateSelectedPlayersListView();
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
        salaryText.clear();
        superstarCheckBox.setSelected(false);
        ageText.clear();
        heightText.clear();
        weightText.clear();
        pointsText.clear();
        reboundsText.clear();
        assistsText.clear();
        stealsText.clear();
        blocksText.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


