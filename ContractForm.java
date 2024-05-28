/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guinba;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nba.ContractExtension;
import nba.contractPlayer;

import java.util.LinkedList;

public class ContractForm extends Application {
    private ContractExtension contractExtension = new ContractExtension();
    private TextArea contractPlayersTextArea;

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
                contractExtension.addExtendContract(playerName); // Pass player name to ContractExtension
                updateContractPlayersTextArea();
                nameText.clear();
            }
        });

        // Create the remove player button
        Button removePlayerButton = new Button("Remove Player");
        removePlayerButton.setLayoutX(225);
        removePlayerButton.setLayoutY(320);
        removePlayerButton.setOnAction(e -> {
            if (contractExtension.isQueueEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No players in the contract extension queue.");
                alert.showAndWait();
            } else {
                contractExtension.removeExtendContract();
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
    }

    private void updateContractPlayersTextArea() {
        contractPlayersTextArea.clear();
        StringBuilder sb = new StringBuilder();
        if (contractExtension.isQueueEmpty()) {
            sb.append("No players are currently on the contract extension.");
        } else {
            LinkedList<contractPlayer> list = contractExtension.getList();
            for (contractPlayer contract : list) {
                sb.append(contract.toString()).append("\n\n");
            }
        }
        contractPlayersTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}


