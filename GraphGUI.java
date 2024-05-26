package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class GraphGUI {
    private ComboBox<String> fromCityComboBox;
    private ComboBox<String> toCityComboBox;
    private TextArea pathsTextArea;
    private TextField shortestPathTextField;

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("City Graph");
        stage.setResizable(false);

        // Create a GridPane for layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Create ComboBoxes for city selection
        fromCityComboBox = new ComboBox<>();
        fromCityComboBox.setPromptText("Select starting city");

        toCityComboBox = new ComboBox<>();
        toCityComboBox.setPromptText("Select destination city");

        gridPane.add(fromCityComboBox, 0, 0);
        gridPane.add(toCityComboBox, 1, 0);

        // Create buttons
        Button findPathsButton = new Button("Find Paths");
        Button findShortestPathButton = new Button("Find Shortest Path");

        // Create TextAreas and TextField
        pathsTextArea = new TextArea();
        pathsTextArea.setEditable(false);
        pathsTextArea.setWrapText(true);
        pathsTextArea.setPrefRowCount(10);
        pathsTextArea.setPrefColumnCount(30);
        pathsTextArea.setPromptText("Paths will be displayed here");

        shortestPathTextField = new TextField();
        shortestPathTextField.setEditable(false);
        shortestPathTextField.setPromptText("Shortest Path will be displayed here");

        gridPane.add(findPathsButton, 0, 1);
        gridPane.add(findShortestPathButton, 1, 1);
        gridPane.add(pathsTextArea, 0, 2, 2, 1);
        gridPane.add(shortestPathTextField, 0, 3, 2, 1);

        // Set action for findPathsButton
        findPathsButton.setOnAction(e -> {
            String fromCity = fromCityComboBox.getValue();
            String toCity = toCityComboBox.getValue();
            if (fromCity != null && toCity != null) {
                List<String> paths = Graph.getInstance().findPaths(fromCity, toCity);
                updatePathsTextArea(paths);
            } else {
                pathsTextArea.setText("Please select both starting and destination cities.");
            }
        });

        // Set action for findShortestPathButton
        findShortestPathButton.setOnAction(e -> {
            String fromCity = fromCityComboBox.getValue();
            String toCity = toCityComboBox.getValue();
            if (fromCity != null && toCity != null) {
                String shortestPath = Graph.getInstance().findShortestPath(fromCity, toCity);
                updateShortestPathTextField(shortestPath);
            } else {
                shortestPathTextField.setText("Please select both starting and destination cities.");
            }
        });

        // Populate ComboBoxes with city names
        ObservableList<String> cityNames = FXCollections.observableArrayList(Graph.getInstance().getCityNames());
        fromCityComboBox.setItems(cityNames);
        toCityComboBox.setItems(cityNames);

        // Create the scene
        Scene scene = new Scene(gridPane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void updatePathsTextArea(List<String> paths) {
        if (paths.isEmpty()) {
            pathsTextArea.setText("No paths found between the selected cities.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String path : paths) {
                sb.append(path).append("\n");
            }
            pathsTextArea.setText(sb.toString());
        }
    }

    private void updateShortestPathTextField(String shortestPath) {
        if (shortestPath.isEmpty()) {
            shortestPathTextField.setText("No shortest path found between the selected cities.");
        } else {
            shortestPathTextField.setText(shortestPath);
        }
    }
}
