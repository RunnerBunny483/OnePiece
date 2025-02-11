package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
}