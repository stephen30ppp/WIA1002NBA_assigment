
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
