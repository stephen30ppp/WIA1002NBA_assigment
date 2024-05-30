/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guinba;

/**
 *
 * @author fwhed
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nba.InjuryReserve;
import nba.injuredPlayer;
import java.util.EmptyStackException;

public class InjuryReserveForm extends Application {
    private InjuryReserve injuryReserve = new InjuryReserve();
    private TextArea injuredPlayersTextArea;

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
                injuryReserve.addInjuredPlayer(player, injury);
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

            try {
                injuryReserve.removeInjuredPlayer(playerName, injury);
                updateInjuredPlayersTextArea();
            } catch (EmptyStackException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Player not found in the injury reserve.");
                alert.showAndWait();
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
    }

    private void updateInjuredPlayersTextArea() {
        injuredPlayersTextArea.clear();
        StringBuilder sb = new StringBuilder();
        if (injuryReserve.isStackEmpty()) {
            sb.append("No players are currently on the injury reserve.");
        } else {
            for (injuredPlayer injured : injuryReserve.getList()) {
                sb.append(injured.toString()).append("\n\n");
            }
        }
        injuredPlayersTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
