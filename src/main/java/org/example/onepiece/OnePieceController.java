package org.example.onepiece;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class OnePieceController {
    private ResourceBundle resourceBundle;
    @FXML
    private Label ayuda;
    @FXML
    private ComboBox idioma;
    @FXML
    private Button crearPoster;
    @FXML
    private Button buscarPoster;

    public void initialize() {
        //Añadir los idiomas al combo box
        idioma.getItems().addAll("Español", "English");

        //Buscar el idioma por defecto
        Locale locale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle("onepiece", locale);

        //Llamada al método que actualiza el idioma
        actualizarIdiomaVentana();

        //Listener al cambiar la opcion del combobox de idioma
        idioma.setOnAction(actionEvent -> seleccionIdioma());

        //Llamada al método que abre el manual de usuario al hacer click sobre el label ayuda
        ayuda.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                abrirManualUsuario();
            }
        });

    }

    //Método para ver qué opción del combobox de idiomas está elegida
    public void seleccionIdioma() {
        String seleccion = (String) idioma.getValue();
        Locale locale;
        if("English".equals(seleccion)) {
            locale = new Locale("en");
        } else {
            locale = new Locale("es");
        }

        resourceBundle = ResourceBundle.getBundle("onepiece", locale);
        actualizarIdiomaVentana();    }

    //Método que cambia todas las palabras de idioma
    public void actualizarIdiomaVentana() {
        ayuda.setText(resourceBundle.getString("Ayuda"));
        crearPoster.setText(resourceBundle.getString("CrearPoster"));
        buscarPoster.setText(resourceBundle.getString("BuscarPoster"));

    }

    //Método para abrir el manual de usuario
    public void abrirManualUsuario(){
        try{
            File manualEspañol = new File("src/main/resources/ManualUsuario/ManualUsuarioSpanish.html");
            File manualEnglish = new File("src/main/resources/ManualUsuario/ManualUsuarioEnglish.html");

            File manual=null;

            if("English".equals(idioma.getValue())){
                manual=manualEnglish;
            } else{
                manual=manualEspañol;
            }

            //if(manual!=null){
                String sistemaOperativo = System.getProperty("os.name").toLowerCase();

                if (sistemaOperativo.contains("win")) {
                    Desktop.getDesktop().browse(manual.toURI());
                } else if (sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux")) {
                    //Para Linux
                    Runtime.getRuntime().exec(new String[]{"xdg-open", manual.getAbsolutePath()});
                }
            //}

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Método para abrir "Buscar Poster"
    public void abrirBuscarPoster() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/onepiece/buscar-poster.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Buscar Poster");
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Método para abrir "Crear Poster"
    public void abrirCrearPoster() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/onepiece/crear-poster.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Crear Poster");
        stage.setScene(new Scene(root));
        stage.show();
    }


}