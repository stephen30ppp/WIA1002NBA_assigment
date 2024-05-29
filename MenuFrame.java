package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuFrame {
    private UserRepository userRepository;

    public MenuFrame(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void show(Stage menuStage) {
        menuStage.setTitle("Menu");

        // Create buttons
        Button button1 = createRoundedButton("Button 1");
        Button button2 = createRoundedButton("Button 2");
        Button button3 = createRoundedButton("Button 3");
        Button button4 = createRoundedButton("Button 4");
        Button cityGraphButton = createRoundedButton("City Graph");
        Button button6 = createRoundedButton("Button 6");
        Button mainFrameButton = createRoundedButton("Main Frame");

        cityGraphButton.setOnAction(e -> new GraphGUI().show());
        mainFrameButton.setOnAction(e -> {
            // Close the current stage
            menuStage.close();
            // Open the MainFrame
            MainFrame mainFrame = new MainFrame(userRepository);
            Stage mainStage = new Stage();
            try {
                mainFrame.start(mainStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create a VBox for buttons
        VBox buttonBox = new VBox(10, button1, button2, button3, button4, cityGraphButton, button6, mainFrameButton);
        buttonBox.setPadding(new Insets(50));
        buttonBox.setStyle("-fx-background-color: transparent;");
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        // Set buttons to the same size
        for (Button button : new Button[]{button1, button2, button3, button4, cityGraphButton, button6, mainFrameButton}) {
            button.setPrefHeight(30);
            button.setPrefWidth(195);
        }

        // Set background image using a relative path
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/views/background.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        BorderPane root = new BorderPane();
        root.setBackground(new Background(backgroundImage));
        root.setPadding(new Insets(10));

        // Add buttonBox to the center of the BorderPane
        root.setCenter(buttonBox);

        // Create the scene with the same size as MainFrame
        Scene menuScene = new Scene(root, 800, 600);

        menuStage.setScene(menuScene);
        menuStage.show();
    }

    private Button createRoundedButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 30; -fx-border-radius: 30; " +
                "-fx-background-color: #fee12b; -fx-text-fill: white; " +
                "-fx-font-family: 'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold; " +
                "-fx-padding: 10px 20px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #fffdd0; " +
                "-fx-background-radius: 30; -fx-border-radius: 30; " +
                "-fx-text-fill: black; " +
                "-fx-font-family: 'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold; " +
                "-fx-padding: 10px 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #fee12b; " +
                "-fx-background-radius: 30; -fx-border-radius: 30; " +
                "-fx-text-fill: white; " +
                "-fx-font-family: 'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold; " +
                "-fx-padding: 10px 20px;"));

        return button;
    }
}
