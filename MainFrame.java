package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends Application {
    public static Graph graph;
    private TextArea userListTextArea;
    private Map<String, String> userDatabase;

    @Override
    public void start(Stage primaryStage) {
        userDatabase = new HashMap<>();

        // Set background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:C:\\Users\\Thrisha\\IdeaProjects\\untitled\\src\\views\\background.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        // Create a BorderPane for layout
        BorderPane root = new BorderPane();
        root.setBackground(new Background(backgroundImage));
        root.setPadding(new Insets(10));

        // Create buttons
        Button loginButton = createRoundedButton("Login");
        Button signUpButton = createRoundedButton("Sign Up");
        Button directoryButton1 = createRoundedButton("Button 1");
        Button extraButton = createRoundedButton("City Graph");

        // Set button actions
        loginButton.setOnAction(e -> new LoginForm(userDatabase, userListTextArea).show());
        signUpButton.setOnAction(e -> {
            SignUpForm signUpForm = new SignUpForm(userDatabase, userListTextArea);
            signUpForm.show();
            signUpForm.setOnSignUpSuccess(() -> {
                // On successful sign up, open the login form
                new LoginForm(userDatabase, userListTextArea).show();
            });
        });

        extraButton.setOnAction(e -> new GraphGUI().show());

        // Create a VBox for buttons
        VBox buttonBox = new VBox(10, loginButton, signUpButton, directoryButton1, extraButton);
        buttonBox.setPadding(new Insets(50));
        buttonBox.setStyle("-fx-background-color: transparent;");
        buttonBox.setAlignment(Pos.CENTER);  // Center the buttons within the VBox
        buttonBox.setSpacing(10);  // Add spacing between buttons

        // Set buttons to the same size
        for (Button button : new Button[]{loginButton, signUpButton, directoryButton1, extraButton}) {
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefWidth(200); // Set a preferred width for all buttons
        }

        // Create label for the user list text area
        Label userListLabel = new Label("Current Registered Usernames:");
        userListLabel.setStyle("-fx-text-fill: black;" +
                "-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        // Create the user list text area
        userListTextArea = new TextArea();
        userListTextArea.setEditable(false);
        userListTextArea.setPrefSize(300, 200);  // Adjust size
        userListTextArea.setStyle("-fx-background-color: white; -fx-border-color: transparent;");

        VBox userBox = getBox(userListLabel);

        // Add components to the layout
        root.setLeft(buttonBox);
        root.setCenter(userBox);

        // Create the scene with the new size
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Main Frame");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox getBox(Label userListLabel) {
        ScrollPane scrollPane = new ScrollPane(userListTextArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: white; -fx-border-color: transparent;");

        // Create a VBox for the label and the text area with a border and semi-transparent background
        VBox userBox = new VBox(5, userListLabel, scrollPane);
        userBox.setAlignment(Pos.TOP_CENTER);
        userBox.setPadding(new Insets(10));
        userBox.setMaxWidth(350);  // Set max width to align with the text area
        userBox.setMaxHeight(250); // Set a max height
        userBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5); -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5;");
        return userBox;
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

    public static void main(String[] args) {
        launch(args);
    }
}
