package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.List;

public class GraphGUI {
    private TextArea pathsTextArea;
    private TextArea shortestPathTextArea;
    private Stage stage;

    public void show() {
        stage = new Stage();
        stage.setTitle("City Graph");
        stage.setResizable(false);

        // Create a GridPane for layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Create TextAreas for displaying paths and shortest path
        pathsTextArea = new TextArea();
        pathsTextArea.setEditable(false);
        pathsTextArea.setWrapText(true);
        pathsTextArea.setPrefRowCount(10);
        pathsTextArea.setPrefColumnCount(30);
        pathsTextArea.setPromptText("Possible Paths will be displayed here");

        shortestPathTextArea = new TextArea();
        shortestPathTextArea.setEditable(false);
        shortestPathTextArea.setWrapText(true);
        shortestPathTextArea.setPrefRowCount(3);
        shortestPathTextArea.setPrefColumnCount(30);
        shortestPathTextArea.setPromptText("Shortest Path will be displayed here");

        gridPane.add(pathsTextArea, 0, 0);
        gridPane.add(shortestPathTextArea, 0, 1);

        // Create buttons for possible paths and shortest path
        Button possiblePathsButton = createPathButton("Possible Paths");
        Button shortestPathButton = createPathButton("Shortest Path");

        // Add buttons to the grid
        gridPane.add(possiblePathsButton, 0, 2);
        gridPane.add(shortestPathButton, 1, 2); // Place the shortest path button in the next column

        // Create the scene
        Scene scene = new Scene(gridPane, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    private Button createPathButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 30; -fx-border-radius: 30; " +
                "-fx-background-color: #fee12b; -fx-text-fill: white; " +
                "-fx-font-family: 'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold; " +
                "-fx-padding: 10px 20px;");
        button.setOnAction(e -> {
            if (text.equals("Possible Paths")) {
                List<String> possiblePaths = Graph.getInstance().findPaths();
                updatePathsTextArea(possiblePaths);
            } else if (text.equals("Shortest Path")) {
                String shortestPath = Graph.getInstance().findShortestPath();
                updateShortestPathTextArea(shortestPath);
            }
        });
        return button;
    }

    private void updatePathsTextArea(List<String> possiblePaths) {
        if (possiblePaths.isEmpty()) {
            pathsTextArea.setText("No possible paths found from Spurs.");
        } else {
            StringBuilder pathsText = new StringBuilder();
            for (String path : possiblePaths) {
                pathsText.append(path).append("\n");
            }
            pathsTextArea.setText(pathsText.toString());
        }
    }

    private void updateShortestPathTextArea(String shortestPath) {
        if (shortestPath.isEmpty()) {
            shortestPathTextArea.setText("No shortest path found from Spurs.");
        } else {
            shortestPathTextArea.setText(shortestPath);
        }
    }
}
