package org.example.onepiece;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controlador principal de aplicación
 * Gestiona el funcionamiento de los botones y el combo box de idiomas.
 * Abre las distintas ventanas al puslar su botón correspondiente.
 * Permite al usuario seleccionar el idioma de la aplicación y acceder al manual de usuario
 * El manual de usuario tambien tiene una versión en inglés y otra en español
 *
 * @author Gabriela Jiménez Morcillo
 */

public class OnePieceController {
    private ResourceBundle resourceBundle;
    @FXML
    private Label ayuda;
    @FXML
    private ComboBox idioma;
    @FXML
    private Button crearPoster, buscarPoster, botonGenerar;

    /**
     * Inicializa los componentes y configura el idioma.
     * Abre el manual de usuario al hacer click en el label
     */
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

        //Llamada al método que abre el manual de usuario al hacer click sobre el label ayuda
        ayuda.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                abrirManualUsuario();
            }
        });

    }

    /**
     * Cambia el idioma de la aplicación según la opción seleccionada por el usuario en el combo box idioma
     */
    //Método para cambiar idioma según la opción del combo box
    public void seleccionIdioma() {
        String seleccion = (String) idioma.getValue();

        IdiomaController.seleccionIdioma(seleccion);
        resourceBundle= IdiomaController.getResourceBundle();
        actualizarIdiomaVentana();
    }

    /**
     * Actualiza los textos de todos los elementos dependiendo del idioma seleccionado.
     */
    //Método que cambia todas las palabras de idioma
    public void actualizarIdiomaVentana() {
        ayuda.setText(resourceBundle.getString("Ayuda"));
        crearPoster.setText(resourceBundle.getString("CrearPoster"));
        buscarPoster.setText(resourceBundle.getString("BuscarPoster"));
        botonGenerar.setText(resourceBundle.getString("BotonGenerar"));

    }

    /**
     * Abre el manual de usuario en el idioma dependiendo del idoma seleccionado
     * El manual se abre en el navegador dependiendo del sistema operativo del usuario.
     */
    //Método para abrir el manual de usuario
    public void abrirManualUsuario(){
        try{
            File manualEspañol = new File("src/main/resources/ManualUsuario/ManualUsuarioSpanish.html");
            File manualEnglish = new File("src/main/resources/ManualUsuario/ManualUsuarioEnglish.html");

            File manual=null;

            if("English".equals(idioma.getValue())){
                manual=manualEnglish;
            } else{
                manual=manualEspañol;
            }

            //if(manual!=null){
                String sistemaOperativo = System.getProperty("os.name").toLowerCase();

                if (sistemaOperativo.contains("win")) {
                    Desktop.getDesktop().browse(manual.toURI());
                } else if (sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux")) {
                    //Para Linux
                    Runtime.getRuntime().exec(new String[]{"xdg-open", manual.getAbsolutePath()});
                }
            //}

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Abre la ventana Buscar Póster
     *
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    //Método para abrir "Buscar Póster"
    public void abrirBuscarPoster() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/onepiece/buscar-poster.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Buscar Póster");
        Image icon = new Image(getClass().getResourceAsStream("images/JollyRogerMugiwara.png"));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Abre la ventana Crear Póster
     *
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    //Método para abrir "Crear Póster"
    public void abrirCrearPoster() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/onepiece/crear-poster.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Crear Póster");
        Image icon = new Image(getClass().getResourceAsStream("images/JollyRogerMugiwara.png"));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Abre la ventana Generar Informe
     *
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    //Método para abrir "Generar Informe"
    public void abrirBotonGenerar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/onepiece/generar-informe.fxml"));

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Generar Informe");
        Image icon = new Image(getClass().getResourceAsStream("images/JollyRogerMugiwara.png"));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.show();
    }


}