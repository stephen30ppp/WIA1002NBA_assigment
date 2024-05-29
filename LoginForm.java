/*
package views;//package views;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Map;

public class LoginForm {
    private Stage stage;
    private Map<String, String> userDatabase;

    public LoginForm(Map<String, String> userDatabase, TextArea userListTextArea) {
        this.userDatabase = userDatabase;

        // Create the stage
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
        loginButton.setOnAction(e -> {
            String username = userText.getText();
            String password = passwordText.getText();

            if (userDatabase.containsKey(username)) {
                if (userDatabase.get(username).equals(password)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login successful!");
                    alert.showAndWait();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid password.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username.");
                alert.showAndWait();
            }
        });

        // Create a pane and add all elements to it
        Pane pane = new Pane(userLabel, userText, passwordLabel, passwordText, loginButton);
        Scene scene = new Scene(pane, 350, 200);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}


package views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginForm {
    private Stage stage;
    private UserRepository userRepository;

    public LoginForm(UserRepository userRepository) {
        this.userRepository = userRepository;

        // Create the stage
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
        loginButton.setOnAction(e -> {
            String username = userText.getText();
            String password = passwordText.getText();

            try {
                User user = userRepository.getUserByUsername(username);
                if (user != null && user.getPassword().equals(password)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login successful!");
                    alert.showAndWait();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
                    alert.showAndWait();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Database error: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        // Create a pane and add all elements to it
        Pane pane = new Pane(userLabel, userText, passwordLabel, passwordText, loginButton);
        Scene scene = new Scene(pane, 350, 200);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }
}


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

import java.sql.SQLException;
import java.util.function.Consumer;

public class LoginForm {
    private Stage stage;
    private UserRepository userRepository;
    private Consumer<Boolean> onLoginSuccess;

    public LoginForm(UserRepository userRepository) {
        this.userRepository = userRepository;
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

                try {
                    User user = userRepository.getUserByUsername(username);
                    if (user != null) {
                        if (user.getPassword().equals(password)) {
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
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error connecting to the database.");
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


 */


package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class LoginForm {
    private UserRepository userRepository;
    private Consumer<Boolean> onLoginSuccess;

    public LoginForm(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void show(Stage primaryStage) {
        primaryStage.setTitle("Login Form");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Login");
        grid.add(btn, 1, 4);

        final Label loginStatus = new Label();
        grid.add(loginStatus, 1, 6);

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            if (userRepository.validateUser(username, password)) {
                loginStatus.setText("Login successful");
                if (onLoginSuccess != null) {
                    onLoginSuccess.accept(true);
                }
                primaryStage.close();
            } else {
                loginStatus.setText("Login failed");
                if (onLoginSuccess != null) {
                    onLoginSuccess.accept(false);
                }
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setOnLoginSuccess(Consumer<Boolean> onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }
}
