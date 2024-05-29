package views;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Launcher extends Application {
    private static Map<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainFrame mainFrame = new MainFrame(userDatabase);
        try {
            mainFrame.start(primaryStage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
