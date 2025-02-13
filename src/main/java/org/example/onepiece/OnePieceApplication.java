package org.example.onepiece;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase principal de la aplicación One Piece, que extiende de {@link javafx.application.Application}.
 * Esta clase lanza la ventana principal de la aplicación y carga principal-view.fxml.
 * El idioma de la aplicación se configura de acuerdo con la configuración por defecto deñ sistema.
 * la aplicación, utilizando el {@link ResourceBundle} "onepiece" que tiene una version en español y otra en ingles.
 */

public class OnePieceApplication extends Application {

    /**
     * Método principal. Lanza la ventana principal usando el archivo FXML.
     *
     * @param stage El {@link Stage} principal de la aplicación.
     * @throws IOException Excepcion que se lanza si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Locale locale = Locale.getDefault();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("onepiece", locale);

        FXMLLoader fxmlLoader = new FXMLLoader(OnePieceApplication.class.getResource("principal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 325);
        stage.setTitle("One Piece Wanted");
        stage.getIcons().add(new Image(OnePieceApplication.class.getResourceAsStream("images/JollyRogerMugiwara.png")));
        stage.setScene(scene);
        stage.show();


    }

    /**
     * Método principal para lanzar la aplicación.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}