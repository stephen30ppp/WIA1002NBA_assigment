import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class SignUpForm {
    private UserRepository userRepository;
    private TextArea userListTextArea;
    private Runnable onSignUpSuccess;

    public SignUpForm(UserRepository userRepository, TextArea userListTextArea) {
        this.userRepository = userRepository;
        this.userListTextArea = userListTextArea;
    }

    public void show() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sign Up Form");

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

        // Create the password strength label
        Label passwordStrength = new Label("Password Strength: ");
        passwordStrength.setLayoutX(50);
        passwordStrength.setLayoutY(110);

        // Add document listener to password field to check strength
        pwBox.textProperty().addListener((observable, oldValue, newValue) -> {
            String strength = getPasswordStrength(newValue);
            passwordStrength.setText("Password Strength: " + strength);
        });

        Button btn = new Button("Sign Up");
        btn.setLayoutX(150);
        btn.setLayoutY(150);

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            String strength = getPasswordStrength(password);
            User user = new User(username, password);

            if (Objects.equals(username, "") || Objects.equals(password, "")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
                alert.showAndWait();
            } else if ("Weak".equals(strength)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The password is weak. Login failed.");
                alert.showAndWait();
            } else {
                if (!userRepository.validateUser(username, password)) {
                    try {
                        userRepository.saveUser(user);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sign up successful!");
                    alert.showAndWait();
                    updateUserListTextArea();
                    if (onSignUpSuccess != null) {
                        onSignUpSuccess.run();
                    }
                    primaryStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Username already exists.");
                    alert.showAndWait();
                }
            }
        });

        // Create a pane and add all elements to it
        Pane pane = new Pane(userName, userTextField, pw, pwBox, passwordStrength, btn);
        Scene scene = new Scene(pane, 400, 275); // Increased width from 300 to 400
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setOnSignUpSuccess(Runnable onSignUpSuccess) {
        this.onSignUpSuccess = onSignUpSuccess;
    }

    private void updateUserListTextArea() {
        userListTextArea.clear();
        for (String username : userRepository.getAllUsernames()) {
            userListTextArea.appendText(username + "\n");
        }
    }

    private String getPasswordStrength(String password) {
        int length = password.length();
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        if (length >= 12 && hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Strong";
        } else if (length >= 8 && ((hasUpper && hasLower) || (hasLower && hasDigit) || (hasUpper && hasDigit))) {
            return "Moderate";
        } else {
            return "Weak";
        }
    }
}
