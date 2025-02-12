package org.example.onepiece;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

    //Método para ver que opción del combobox de idiomas está elegida
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
                    //Para LInux
                    Runtime.getRuntime().exec(new String[]{"xdg-open", manual.getAbsolutePath()});
                }
            //}

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}