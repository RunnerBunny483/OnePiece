package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.ResourceBundle;

/**
 * Controlador de la vista generar-indorme.fxml para la ventana de generación de informes según los parámetros introducidos por el usuario.
 * Permite al usuario introducir los datos que desee en los campos Tipulación y recompensa.

 * @author Gabriela Jiménez Morcillo
 */
public class GenerarInforme {

    @FXML
    private TextField Recompensa;

    @FXML
    private Button GenerarInforme;

    @FXML
    private ComboBox<String> Tripulacion;
    @FXML
    private Label labelTripulacion, labelRecompensa;

    /**
     * Método que se ejecuta al inicializar la ventana.
     * Rellena el ComboBox con los nombres de la tripulación y cambia el array según el idioma seleccionado pro el usuario en la ventana principal
     */
    public void initialize() {
        actualizarIdiomaVentana();

        ResourceBundle resourceBundle= IdiomaController.getResourceBundle();
        String triputlacionComboBox = resourceBundle.getString("ComboBoxTripulacion");
        String[] listaTripulaciones= triputlacionComboBox.split(",");

        Tripulacion.getItems().clear();
        for (String tripulacion : listaTripulaciones) {
            Tripulacion.getItems().add(tripulacion);
        }
    }
    //cuando se haga una consulta en inglés pillar la posición de la tripula
    //If locale eng -->
    //Gettear posicion en el array de la tripulacion
    //Cambiar locale a esp, gettear listaTripulaciones otra vez
    //Gettear posicion en eng en la lista en esp
    //Guardarlo en variable que se envía al jasper
    //Cambiar locale a eng otra vez

    /**
     * Actualiza los textos de la ventana según el idioma seleccionado.
     */
    //Método que cambia todas las palabras de idioma
    public void actualizarIdiomaVentana() {
        ResourceBundle resourceBundle=IdiomaController.getResourceBundle();
        GenerarInforme.setText(resourceBundle.getString("GenerarInforme"));
        labelTripulacion.setText(resourceBundle.getString("LabelTripulacion"));
        Tripulacion.setPromptText(resourceBundle.getString("PromptTextTripulacion"));
        labelRecompensa.setText(resourceBundle.getString("LabelRecompensa"));

    }
}
