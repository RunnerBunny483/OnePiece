package org.example.onepiece;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controlador encargado de gestionar el idioma de la aplicación One Piece.
 * Permite cambiar el idioma de la interfaz dependiendo de la opción seleccionada en el combo box de la ventana principal.
 * Utiliza un resource bundle llamado "onepiece" que tiene una version en español y otra en inglés
 * *
 * @autor Gabriela Jiménez Morcillo
 */

public class IdiomaController {
    private static Locale locale= Locale.getDefault();
    private static ResourceBundle resourceBundle= ResourceBundle.getBundle("onepiece", locale);

    /**
     * Obtiene el {@link ResourceBundle} correspondiente al idioma que elige el usuario.
     *
     * @return El {@link ResourceBundle} con las traducciones para el idioma elegido.
     */
    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    /**
     * Obtiene el {@link Locale} correspondiente al idioma que elige el usuario.
     *
     * @return El {@link Locale} con el idioma elegido.
     */
    public static Locale getLocale() {
        return locale;
    }

    /**
     * Método que cambia de forma dinámica el icioma según la opción elegida por el usuario
     *
     * @param idioma es un String que coincide o no con el valor elegido por el usuario en el combo box
     */

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
