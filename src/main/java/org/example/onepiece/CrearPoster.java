package org.example.onepiece;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.ResourceBundle;

/**
 * Controlador de la vista crear-poster.fxml para la ventana de creacion de posters personalizados por el usuario.
 * Permite al usuario introducir los datos que desee en los campos nombre, recompensa e imagen que aparecerá en el cartel.

 * @author Gabriela Jiménez Morcillo
 */
public class CrearPoster {
    @FXML
    private TextField textFieldNombre, Recompensa, Imagen;

    @FXML
    private Label labelNombre, labelRecompensa, labelImagen;

    @FXML
    private Button CrearPoster;

    @FXML
    private Button ImagenBoton;

    /**
     * Método que se ejecuta al inicializar la ventana.
     * Cambia las palabras al idioma seleccionado por el usuario en la ventana principal
     */
    public void initialize() {
        actualizarIdiomaVentana();
    }

    /**
     * Actualiza los textos de la ventana según el idioma seleccionado.
     */
    //Método que cambia todas las palabras de idioma
    public void actualizarIdiomaVentana() {
        ResourceBundle resourceBundle=IdiomaController.getResourceBundle();
        labelNombre.setText(resourceBundle.getString("Nombre"));
        labelRecompensa.setText(resourceBundle.getString("Recompensa"));
        labelImagen.setText(resourceBundle.getString("Imagen"));
        CrearPoster.setText(resourceBundle.getString("Crear"));
        textFieldNombre.setTooltip(new Tooltip(resourceBundle.getString("TltNombre")));
        Recompensa.setTooltip(new Tooltip(resourceBundle.getString("TltRecompensa")));
        ImagenBoton.setTooltip(new Tooltip(resourceBundle.getString("TltImagen")));
        CrearPoster.setTooltip(new Tooltip(resourceBundle.getString("TltbtnCrearPoster")));
    }

}
