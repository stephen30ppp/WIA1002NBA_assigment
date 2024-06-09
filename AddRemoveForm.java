package views;

import NBA_Team.Players;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import static NBA_Team.Team.players;

//import static NBA_Team.Team.countPlayersByPosition;

public class AddRemoveForm extends Application {
    private static final String URL1="jdbc:mysql://127.0.0.1:3306/nba_player";
    private static final String USED="root";
    private static final String PASSWORD="Xyg66666";
    private List<String> playerNames = new ArrayList<>();
    private static List<Players> selectedPlayers = new ArrayList<>();
    private ListView<String> playerListView;
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
        private static final int MAX_PLAYERS = 15;
    private static final int MIN_PLAYERS = 10;
    private static final int MIN_POS_REQUIREMENT = 2;
    private static final double SALARY_CAP = 20000;

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
        addPlayerButton.setLayoutX(370);
        addPlayerButton.setLayoutY(360);
        addPlayerButton.setOnAction(e -> addPlayer());

        // Create the remove player button for current players list
        Button removePlayerButton = new Button("Remove Selected Player");
        removePlayerButton.setLayoutX(305);
        removePlayerButton.setLayoutY(590);
        removePlayerButton.setOnAction(e -> removeSelectedPlayer());

        Label stockPlayersLabel = new Label("Players List:");
        stockPlayersLabel.setLayoutX(50);
        stockPlayersLabel.setLayoutY(410);

        Label currentPlayersLabel = new Label("Team Players:");
        currentPlayersLabel.setLayoutX(50);
        currentPlayersLabel.setLayoutY(600);

        // Create list view to display players
        playerListView = new ListView<>();
        playerListView.setLayoutX(50);
        playerListView.setLayoutY(435);
        playerListView.setPrefSize(400, 130);

        // Create list view to display selected players
        selectedPlayersListView = new ListView<>();
        selectedPlayersListView.setLayoutX(50);
        selectedPlayersListView.setLayoutY(625);
        selectedPlayersListView.setPrefSize(400, 130);

        updatePlayerListViewFromDatabase();

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
                addPlayerButton, removePlayerButton,
                stockPlayersLabel, currentPlayersLabel,
                playerListView, selectedPlayersListView
        );

        Scene scene = new Scene(pane, 500, 800);
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
            selectedPlayers.add(newPlayer);
            addPlayerToDatabase(newPlayer);
            updateSelectedPlayersListView();
            clearInputFields();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There is an invalid input. Please check each information you input.");
            alert.showAndWait();
        }
    }

        private boolean removeSelectedPlayer() {
        if (selectedPlayers.size() == MIN_PLAYERS) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot remove player. A team should consist of at least " + MIN_PLAYERS + " players");
            alert.showAndWait();
            requirePosition();
            return false;
        }
        if (selectedPlayers.size() == MIN_PLAYERS && checkPositionalRequirements() == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot remove player. A team should consist of at least 2 Guards, Forwards and Centers");
            alert.showAndWait();
            return false;
        }
        int selectedIndex = selectedPlayersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Players selectedPlayer = selectedPlayers.get(selectedIndex);
            selectedPlayers.remove(selectedPlayer);
            removePlayerFromDatabase(selectedPlayer);
            updateSelectedPlayersListView();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No player selected.");
            alert.showAndWait();
        }
        return true;
    }

    public static boolean checkPositionalRequirements(){
        return countPlayersByPosition("Guard") >= MIN_POS_REQUIREMENT&&
               countPlayersByPosition("Forward") >= MIN_POS_REQUIREMENT&&
               countPlayersByPosition("Center") >= MIN_POS_REQUIREMENT;
    }
    public static int countPlayersByPosition(String position){
        int count=0;
        for (Players player : selectedPlayers) {
            if (player.getPosition().equalsIgnoreCase(position)){
                count++;
            }
        }
        return count;
    }

    private void updatePlayerListViewFromDatabase() {
        playerNames.clear();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM nba_allplayer");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                playerNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        playerListView.setItems(FXCollections.observableArrayList(playerNames));
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM team_players");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Players player = new Players(
                        rs.getString("position"),
                        rs.getString("name"),
                        rs.getInt("salary"),
                        rs.getInt("salary")>=2000?true:false,
                        rs.getInt("age"),
                        rs.getDouble("height"),
                        rs.getInt("weight"),
                        rs.getDouble("points"),
                        rs.getDouble("rebounds"),
                        rs.getDouble("assists"),
                        rs.getDouble("steals"),
                        rs.getDouble("blocks")
                );
                selectedPlayers.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        selectedPlayersListView.setItems(FXCollections.observableArrayList(selectedPlayers));
    }

        private void updateSelectedPlayersListView() {
        selectedPlayersListView.getItems().setAll(selectedPlayers);
        checkRoster();
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
        public static void checkRoster() {
        if (selectedPlayers.size() < MIN_PLAYERS) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There should be a minimum of " + MIN_PLAYERS + " players in the team. Please add " + (MIN_PLAYERS - selectedPlayers.size()) + " more players.");
            alert.showAndWait();
        }
        requirePosition();
    }

    public static void requirePosition() {
        if (selectedPlayers.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "There should be a minimum of 2 Guards, Forwards and Centers in the team.");
            alert.showAndWait();
        } else {
            if (countPlayersByPosition("Guard") < MIN_POS_REQUIREMENT) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There should be a minimum of 2 Guards in the team. Please add " + (MIN_POS_REQUIREMENT - countPlayersByPosition("Guard")) + " more Guards in your team");
                alert.showAndWait();
            }
            if (countPlayersByPosition("Forward") < MIN_POS_REQUIREMENT) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There should be a minimum of 2 Forwards in the team. Please add " + (MIN_POS_REQUIREMENT - countPlayersByPosition("Forward")) + " more Forwards in your team");
                alert.showAndWait();
            }
            if (countPlayersByPosition("Center") < MIN_POS_REQUIREMENT) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "There should be a minimum of 2 Centers in the team. Please add " + (MIN_POS_REQUIREMENT - countPlayersByPosition("Center")) + " more Centers in your team");
                alert.showAndWait();
            }
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL1,USED,PASSWORD);
    }

    private void addPlayerToDatabase(Players player) {
        String insertSQL = "INSERT INTO team_players (name, position, salary, age, height, weight, points, rebounds, assists, steals, blocks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getPosition());
            pstmt.setInt(3, player.getSalary());
            pstmt.setInt(4, player.getAge());
            pstmt.setDouble(5, player.getHeight());
            pstmt.setInt(6, player.getWeight());
            pstmt.setDouble(7, player.getPoints());
            pstmt.setDouble(8, player.getRebounds());
            pstmt.setDouble(9, player.getAssists());
            pstmt.setDouble(10, player.getSteals());
            pstmt.setDouble(11, player.getBlocks());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removePlayerFromDatabase(Players player) {
        String deleteSQL = "DELETE FROM team_players WHERE name = ? AND position = ? AND salary = ?  AND age = ? AND height = ? AND weight = ? AND points = ? AND rebounds = ? AND assists = ? AND steals = ? AND blocks = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getPosition());
            pstmt.setInt(3, player.getSalary());
            pstmt.setInt(4, player.getAge());
            pstmt.setDouble(5, player.getHeight());
            pstmt.setInt(6, player.getWeight());
            pstmt.setDouble(7, player.getPoints());
            pstmt.setDouble(8, player.getRebounds());
            pstmt.setDouble(9, player.getAssists());
            pstmt.setDouble(10, player.getSteals());
            pstmt.setDouble(11, player.getBlocks());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
