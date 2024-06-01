import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddRemoveForm extends Application {
    private List<Players> playerList = new ArrayList<>();
    private List<Players> selectedPlayers = new ArrayList<>();
    private ListView<String> playerListView;  // Changed to ListView<String>
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
        primaryStage.setTitle("Add & Remove Player Form");

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

        Label heightLabel = new Label("Height (m):");
        heightLabel.setLayoutX(50);
        heightLabel.setLayoutY(180);
        heightText = new TextField();
        heightText.setLayoutX(150);
        heightText.setLayoutY(180);

        Label weightLabel = new Label("Weight (kg):");
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

        // Create the add player button
        Button addPlayerButton = new Button("Add Player");
        addPlayerButton.setLayoutX(50);
        addPlayerButton.setLayoutY(400);
        addPlayerButton.setOnAction(e -> addPlayer());

        // Create the remove player button for stock players list
        Button removeStockPlayerButton = new Button("Remove Player");
        removeStockPlayerButton.setLayoutX(350);
        removeStockPlayerButton.setLayoutY(400);
        removeStockPlayerButton.setOnAction(e -> removeStockPlayer());
        
        // Create the select player button
        Button selectPlayerButton = new Button("Select Player");
        selectPlayerButton.setLayoutX(250);
        selectPlayerButton.setLayoutY(400);
        selectPlayerButton.setOnAction(e -> addSelectedPlayer());

        // Create the remove player button for current players list
        Button removePlayerButton = new Button("Remove Selected Player");
        removePlayerButton.setLayoutX(305);
        removePlayerButton.setLayoutY(600);
        removePlayerButton.setOnAction(e -> removeSelectedPlayer());

        Label stockPlayersLabel = new Label("Players List:");
        stockPlayersLabel.setLayoutX(50);
        stockPlayersLabel.setLayoutY(430); 

        Label currentPlayersLabel = new Label("Team Players:");
        currentPlayersLabel.setLayoutX(50);
        currentPlayersLabel.setLayoutY(630);

        // Create list view to display players
        playerListView = new ListView<>();
        playerListView.setLayoutX(50);
        playerListView.setLayoutY(450);
        playerListView.setPrefSize(400, 110);

        // Create list view to display selected players
        selectedPlayersListView = new ListView<>();
        selectedPlayersListView.setLayoutX(50);
        selectedPlayersListView.setLayoutY(650);
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
                addPlayerButton, selectPlayerButton, removePlayerButton, removeStockPlayerButton,
                stockPlayersLabel, currentPlayersLabel,
                playerListView, selectedPlayersListView
        );

        Scene scene = new Scene(pane, 500, 800); // Adjust the width and height as needed
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addPlayer() {
        try {
            String name = nameText.getText();
            String position = positionText.getText();
            int salary = Integer.parseInt(salaryText.getText());
            boolean isSuperstar = superstarCheckBox.isSelected();
            int age = Integer.parseInt(ageText.getText());
            double height = Double.parseDouble(heightText.getText());
            int weight = Integer.parseInt(weightText.getText());
            double points = Double.parseDouble(pointsText.getText());
            double rebounds = Double.parseDouble(reboundsText.getText());
            double assists = Double.parseDouble(assistsText.getText());
            double steals = Double.parseDouble(stealsText.getText());
            double blocks = Double.parseDouble(blocksText.getText());

            Players newPlayer = new Players(position, name, salary, isSuperstar, age, height, weight, points, rebounds, assists, steals, blocks);
            playerList.add(newPlayer);
            updatePlayerListView(playerList);
            clearInputFields();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There is an invalid input. Please check each information you input.");
            alert.showAndWait();
        }
    }

    private void addSelectedPlayer() {
        int selectedIndex = playerListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Players selectedPlayer = playerList.get(selectedIndex);
            if (!selectedPlayers.contains(selectedPlayer)) {
                selectedPlayers.add(selectedPlayer);
                updateSelectedPlayersListView();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Player has already been selected.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No player selected.");
            alert.showAndWait();
        }
    }

    private void removeSelectedPlayer() {
        int selectedIndex = selectedPlayersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Players selectedPlayer = selectedPlayers.get(selectedIndex);
            selectedPlayers.remove(selectedPlayer);
            updateSelectedPlayersListView();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No player selected.");
            alert.showAndWait();
        }
    }

    private void removeStockPlayer() {
        int selectedIndex = playerListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Players selectedPlayer = playerList.get(selectedIndex);
            playerList.remove(selectedPlayer);
            updatePlayerListView(playerList);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No player selected.");
            alert.showAndWait();
        }
    }

    private void updatePlayerListView(List<Players> players) {
        List<String> playerNames = players.stream()
                .map(Players::getNameOnly)
                .collect(Collectors.toList());
        playerListView.setItems(FXCollections.observableArrayList(playerNames));
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
