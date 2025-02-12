package org.example.onepiece;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class CrearPoster {
    @FXML
    private TextField textFieldNombre, Recompensa, Imagen;

    @FXML
    private Label labelNombre, labelRecompensa, labelImagen;

    @FXML
    private Button CrearPoster;

    @FXML
    private Button ImagenBoton;

    public void initialize() {
        actualizarIdiomaVentana();
    }

    //Método que cambia todas las palabras de idioma
    public void actualizarIdiomaVentana() {
        ResourceBundle resourceBundle=IdiomaController.getResourceBundle();
        labelNombre.setText(resourceBundle.getString("Nombre"));
        labelRecompensa.setText(resourceBundle.getString("Recompensa"));
        labelImagen.setText(resourceBundle.getString("Imagen"));
        CrearPoster.setText(resourceBundle.getString("Crear"));
    }

}
