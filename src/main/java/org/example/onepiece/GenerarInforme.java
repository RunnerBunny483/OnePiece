package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.ResourceBundle;

public class GenerarInforme {

    @FXML
    private TextField Recompensa;

    @FXML
    private Button GenerarInforme;

    @FXML
    private ComboBox<String> Tripulacion;
    @FXML
    private Label labelTripulacion, labelRecompensa;

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

    //Método que cambia todas las palabras de idioma
    public void actualizarIdiomaVentana() {
        ResourceBundle resourceBundle=IdiomaController.getResourceBundle();
        GenerarInforme.setText(resourceBundle.getString("GenerarInforme"));
        labelTripulacion.setText(resourceBundle.getString("LabelTripulacion"));
        Tripulacion.setPromptText(resourceBundle.getString("PromptTextTripulacion"));
        labelRecompensa.setText(resourceBundle.getString("LabelRecompensa"));

    }
}
