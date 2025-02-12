package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class BuscarPoster {
    @FXML
    private ComboBox<String> desplegablePirata;

    public void initialize() {
        //Añadir los idiomas al combo box
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
}
