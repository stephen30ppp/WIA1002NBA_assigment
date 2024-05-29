import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

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

        Button btn = new Button("Sign Up");
        grid.add(btn, 1, 4);

        final Label signUpStatus = new Label();
        grid.add(signUpStatus, 1, 6);

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            User user = new User(username,password);
            if (!userRepository.validateUser(username,password)) {
                try {
                    userRepository.saveUser(user);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                signUpStatus.setText("Sign up successful");
                updateUserListTextArea();
                if (onSignUpSuccess != null) {
                    onSignUpSuccess.run();
                }
                primaryStage.close();
            } else {
                signUpStatus.setText("Sign up failed: username already exists");
            }
        });

        Scene scene = new Scene(grid, 300, 275);
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
}
