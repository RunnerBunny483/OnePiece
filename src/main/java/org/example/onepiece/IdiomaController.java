package org.example.onepiece;

import java.util.Locale;
import java.util.ResourceBundle;

public class IdiomaController {
    private static Locale locale= Locale.getDefault();
    private static ResourceBundle resourceBundle= ResourceBundle.getBundle("onepiece", locale);

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    //Método para ver qué opción del combobox de idiomas está elegida
    public static void seleccionIdioma(String idioma) {
        if(idioma.equals("English")) {
            locale = new Locale("en");
        } else {
            locale = new Locale("es");
        }

        resourceBundle = ResourceBundle.getBundle("onepiece", locale);
    }
}
