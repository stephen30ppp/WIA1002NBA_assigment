package views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

public class MainFrame extends Application {
    private TextArea userListTextArea;
    private static Map<String, String> userDatabase;
    private Button spursButton;
    private Button sunsButton;
    private Button lakersButton;

    public MainFrame() {
        // Default constructor required by JavaFX
    }

    public MainFrame(Map<String, String> userDatabase) {
        MainFrame.userDatabase = userDatabase;
    }

    @Override
    public void start(Stage primaryStage) {
        // Set background image using a relative path
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/views/background.png").toExternalForm()),
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
        Button exitButton = createRoundedButton("Exit");

        // Set the onLoginSuccess callback
        loginButton.setOnAction(e -> {
            LoginForm loginForm = new LoginForm(userDatabase);
            loginForm.setOnLoginSuccess(success -> {
                if (success) {
                    System.out.println("Login successful!");
                    MenuFrame menuFrame = new MenuFrame(userDatabase);
                    menuFrame.show(new Stage());
                    primaryStage.close();
                } else {
                    System.out.println("Login failed.");
                }
            });
            loginForm.show(primaryStage);
        });

        signUpButton.setOnAction(e -> {
            SignUpForm signUpForm = new SignUpForm(userDatabase, userListTextArea);
            signUpForm.setOnSignUpSuccess(() -> {
                LoginForm newLoginForm = new LoginForm(userDatabase);
                newLoginForm.show(primaryStage);
            });
            signUpForm.show();
        });

        exitButton.setOnAction(e -> primaryStage.close());

        // Create a VBox for buttons
        VBox buttonBox = new VBox(10, loginButton, signUpButton, exitButton);
        buttonBox.setPadding(new Insets(50));
        buttonBox.setStyle("-fx-background-color: transparent;");
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        // Set buttons to the same size
        for (Button button : new Button[]{loginButton, signUpButton, exitButton}) {
            button.setMaxWidth(Double.MAX_VALUE);
            button.setPrefWidth(200);
        }

        // Create label for the user list text area
        Label userListLabel = new Label("Current Registered Usernames:");
        userListLabel.setStyle("-fx-text-fill: black;" +
                "-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        // Create the user list text area
        userListTextArea = new TextArea();
        userListTextArea.setEditable(false);
        userListTextArea.setPrefSize(300, 200);
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

        VBox userBox = new VBox(5, userListLabel, scrollPane);
        userBox.setAlignment(Pos.TOP_CENTER);
        userBox.setPadding(new Insets(10));
        userBox.setMaxWidth(350);
        userBox.setMaxHeight(250);
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

    public Node getNode() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        spursButton = createTeamButton("Spurs");
        sunsButton = createTeamButton("Suns");
        lakersButton = createTeamButton("Lakers");

        gridPane.add(spursButton, 0, 0);
        gridPane.add(sunsButton, 1, 0);
        gridPane.add(lakersButton, 2, 0);

        return gridPane;
    }

    private Button createTeamButton(String teamName) {
        Button button = new Button(teamName);
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
        Launcher.main(args);
    }
}
