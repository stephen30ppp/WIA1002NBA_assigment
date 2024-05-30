package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.function.Consumer;

public class LoginForm {
    private UserRepository userRepository;
    private Consumer<Boolean> onLoginSuccess;

    public LoginForm(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void show() {
        Stage primaryStage = new Stage();           // changed it so it still shows mainFrame when login form popup
        primaryStage.setTitle("Login Form");

        Label userName = new Label("Username:");
        userName.setLayoutX(50);
        userName.setLayoutY(30);

        TextField userTextField = new TextField();
        userTextField.setLayoutX(150);
        userTextField.setLayoutY(30);

        Label pw = new Label("Password:");
        pw.setLayoutX(50);
        pw.setLayoutY(70);

        PasswordField pwBox = new PasswordField();
        pwBox.setLayoutX(150);
        pwBox.setLayoutY(70);

        Button btn = new Button("Login");
        btn.setLayoutX(150);
        btn.setLayoutY(150);

        final Label loginStatus = new Label();
        loginStatus.setLayoutX(150);
        loginStatus.setLayoutY(190);

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            System.out.println(username);
            System.out.println(password);
            if(Objects.equals(username, "") || Objects.equals(password, "")){         // added this because   before this can login with empty username and password
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username.");
                alert.showAndWait();
            }
            else {
                if (userRepository.validateUser(username, password)) {
                    loginStatus.setText("Login successful");
                    primaryStage.close();
                    if (onLoginSuccess != null) {
                        onLoginSuccess.accept(true);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username.");
                    alert.showAndWait();
                /*
                loginStatus.setText("Login failed");
                if (onLoginSuccess != null) {
                    onLoginSuccess.accept(false);
                }
                */
                }
            }
        });

        // Create a pane and add all elements to it
        Pane pane = new Pane(userName, userTextField, pw, pwBox, btn, loginStatus);
        Scene scene = new Scene(pane, 400, 275); // Increased width to 400 to match SignUpForm
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setOnLoginSuccess(Consumer<Boolean> onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }
}
