package views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Map;

@FunctionalInterface
interface OnSignUpSuccessCallback {
    void onSignUpSuccess();
}

public class SignUpForm {
    private Stage stage;
    private Map<String, String> userDatabase;
    private TextArea userListTextArea;
    private OnSignUpSuccessCallback onSignUpSuccessCallback;

    public SignUpForm(Map<String, String> userDatabase, TextArea userListTextArea) {
        this.userDatabase = userDatabase;
        this.userListTextArea = userListTextArea;

        // Create the stage
        stage = new Stage();
        stage.setTitle("Sign Up Form");

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

        // Create the password strength label
        Label passwordStrengthLabel = new Label("Password Strength: ");
        passwordStrengthLabel.setLayoutX(50);
        passwordStrengthLabel.setLayoutY(110);

        // Add document listener to password field to check strength
        passwordText.textProperty().addListener((observable, oldValue, newValue) -> {
            String strength = getPasswordStrength(newValue);
            passwordStrengthLabel.setText("Password Strength: " + strength);
        });

        // Create the sign-up button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setLayoutX(150);
        signUpButton.setLayoutY(150);
        signUpButton.setOnAction(e -> {
            String username = userText.getText();
            String password = passwordText.getText();

            if (userDatabase.containsKey(username)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Username already exists.");
                alert.showAndWait();
            } else {
                userDatabase.put(username, password);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sign up successful!");
                alert.showAndWait();
                userListTextArea.appendText(username + "\n");
                stage.close();
                if (onSignUpSuccessCallback != null) {
                    onSignUpSuccessCallback.onSignUpSuccess();
                }
            }
        });

        // Create a pane and add all elements to it
        Pane pane = new Pane(userLabel, userText, passwordLabel, passwordText, passwordStrengthLabel, signUpButton);
        Scene scene = new Scene(pane, 350, 250);
        stage.setScene(scene);
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

    public void setOnSignUpSuccess(OnSignUpSuccessCallback onSignUpSuccessCallback) {
        this.onSignUpSuccessCallback = onSignUpSuccessCallback;
    }

    public void show() {
        stage.show();
    }
}