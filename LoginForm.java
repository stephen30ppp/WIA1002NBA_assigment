package views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Map;
import java.util.function.Consumer;

public class LoginForm {
    private Stage stage;
    private Map<String, String> userDatabase;
    private Consumer<Boolean> onLoginSuccess;

    public LoginForm(Map<String, String> userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void setOnLoginSuccess(Consumer<Boolean> onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    public void show(Stage owner) {
        if (stage == null) {
            stage = new Stage();
            stage.setTitle("Login Form");

            // Create the user label
            Label userLabel = new Label("Username:");
            userLabel.setLayoutX(50);
            userLabel.setLayoutY(30);

            // Create the user text field
            TextField userText = new TextField();
            userText.setLayoutX(150);
            userText.setLayoutY(30);

            // Create the password label
            Label passwordLabel = new Label("Password:");
            passwordLabel.setLayoutX(50);
            passwordLabel.setLayoutY(70);

            // Create the password field
            PasswordField passwordText = new PasswordField();
            passwordText.setLayoutX(150);
            passwordText.setLayoutY(70);

            // Create the login button
            Button loginButton = new Button("Login");
            loginButton.setLayoutX(150);
            loginButton.setLayoutY(110);

            // Set action for login button
            loginButton.setOnAction(e -> {
                String username = userText.getText();
                String password = passwordText.getText();

                if (userDatabase.containsKey(username)) {
                    if (userDatabase.get(username).equals(password)) {
                        // Login successful
                        stage.close();
                        if (onLoginSuccess != null) {
                            onLoginSuccess.accept(true);
                        }
                    } else {
                        // Invalid password
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid password.");
                        alert.showAndWait();
                    }
                } else {
                    // Invalid username
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username.");
                    alert.showAndWait();
                }
            });

            // Create a pane and add all elements to it
            Pane pane = new Pane(userLabel, userText, passwordLabel, passwordText, loginButton);
            Scene scene = new Scene(pane, 350, 200);
            stage.setScene(scene);
            stage.initOwner(owner); // Set the owner of the stage
        }
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }
}
