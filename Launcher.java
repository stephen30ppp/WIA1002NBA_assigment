package views;

import javafx.application.Application;
import javafx.stage.Stage;
//import UserRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Launcher extends Application {
    private static UserRepository userRepository;

    @Override
    public void start(Stage primaryStage) {
        userRepository = new UserRepository();
        MainFrame mainFrame = new MainFrame(userRepository);
        mainFrame.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
