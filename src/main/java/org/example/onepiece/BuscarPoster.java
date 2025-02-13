package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

import java.util.ResourceBundle;

/**
 * Controlador de la vista buscar-poster.fxml para la ventana de búsqueda de posters de los personajes de One Piece.
 * Permite al usuario seleccionar un pirata de una lista desplegable y generar un poster.
 *
 * @author Gabriela Jiménez Morcillo
 */

public class BuscarPoster {
    @FXML
    private ComboBox<String> desplegablePirata;

    @FXML
    private Button BuscarPoster;
    @FXML
    private Label labelPirata;

    /**
     * Método que se ejecuta al inicializar la ventana.
     * Rellena el ComboBox con los nombres de los piratas
     */
    public void initialize() {
        actualizarIdiomaVentana();
        //Añadir los personajes al combo box
        desplegablePirata.getItems().addAll("Gol D. Roger", "Edward Newgate", "Kaido", "Charlotte Linlin", "Shanks",
                "Marshall D. Teach", "Monkey D. Luffy", "Roronoa Zoro", "Sanji", "Nico Robin",
                "Jinbe", "Brook", "Tony Tony Chopper", "Trafalgar D. Water Law", "Eustass Kid",
                "Crocodile", "Boa Hancock", "Buggy", "Dracule Mihawk", "Donquixote Doflamingo",
                "X Drake", "Charlotte Katakuri", "Jack", "Queen", "King", "Basil Hawkins",
                "Scratchmen Apoo", "Shiryu", "Jesus Burgess", "Van Augur", "Avalo Pizarro",
                "Laffitte", "Killer", "Capone Bege", "Urouge", "Jewelry Bonney", "Bartolomeo",
                "Cavendish", "Orlumbus", "Don Chinjao", "Sai", "Marco", "Sabo", "Portgas D. Ace",
                "Charlotte Cracker", "Charlotte Smoothie", "Charlotte Katakuri", "Charlotte Daifuku",
                "Charlotte Oven", "Charlotte Perospero", "Charlotte Mont-d Or", "Charlotte Amande",
                "Charlotte Valin", "Charlotte Lemo", "Charlotte Conne", "Bentham (Bon Clay)",
                "Galdino (Mr. 3)", "Sogeking", "Usopp");
    }

    /**
     * Actualiza los textos de la ventana según el idioma seleccionado.
     */
    //Método que cambia todas las palabras de idioma
    public void actualizarIdiomaVentana() {
        ResourceBundle resourceBundle=IdiomaController.getResourceBundle();
        desplegablePirata.setPromptText(resourceBundle.getString("DesplegablePirata"));
        BuscarPoster.setText(resourceBundle.getString("Generar"));
        labelPirata.setText(resourceBundle.getString("LabelPirata"));
        desplegablePirata.setTooltip(new Tooltip(resourceBundle.getString("TltDesplegablePirata")));
        BuscarPoster.setTooltip(new Tooltip(resourceBundle.getString("TltbtnBuscarPoster")));
    }
}
