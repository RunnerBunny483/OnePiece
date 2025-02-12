package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class BuscarPoster {
    @FXML
    private ComboBox<String> desplegablePirata;

    public void initialize() {
        //Añadir los idiomas al combo box
        desplegablePirata.getItems().addAll("Español", "English");

}
