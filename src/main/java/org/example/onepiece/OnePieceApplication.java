package org.example.onepiece;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class OnePieceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale locale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("onepiece", locale);

        FXMLLoader fxmlLoader = new FXMLLoader(OnePieceApplication.class.getResource("principal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 415, 325);
        stage.setTitle("One Piece Application");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}