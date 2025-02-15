package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    private Connection conexion;

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
        ResourceBundle resourceBundle = IdiomaController.getResourceBundle();
        desplegablePirata.setPromptText(resourceBundle.getString("DesplegablePirata"));
        BuscarPoster.setText(resourceBundle.getString("Generar"));
        labelPirata.setText(resourceBundle.getString("LabelPirata"));
        desplegablePirata.setTooltip(new Tooltip(resourceBundle.getString("TltDesplegablePirata")));
        BuscarPoster.setTooltip(new Tooltip(resourceBundle.getString("TltbtnBuscarPoster")));
    }

    /**
     * Genera un informe a raíz del parámetros de búsqueda elegido por el usuario dependiendo del nombre del pirata
     * elegido en el como box
     * Si no se selecciona nada y se queda a null o a empty, salta una ventana de error
     */
    public void generarCartelPersonaje() {
        conexion = Conexion.getConexion();
        Map<String, Object> parametros = new LinkedHashMap<>();
        String nombrePirata = desplegablePirata.getValue();

        if(nombrePirata == null || nombrePirata.trim().isEmpty()){
            ResourceBundle resourceBundle= IdiomaController.getResourceBundle();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            String mensajeError= resourceBundle.getString("ErrorPirata");
            alerta.setContentText(mensajeError);
            alerta.showAndWait();
            return;
        }

        //if (!nombrePirata.isEmpty()) {
            parametros.put("Nombre", nombrePirata);

            //Obtener la url de la imagen desde la base de datos
            String imagenPath = obtenerUrlImagenDesdeBD(nombrePirata);
            if (imagenPath == null) {
                System.out.println("No se encuentra url de la imagen de" + nombrePirata);
                return;
            }

            parametros.put("urlImagen", imagenPath);  // Pasamos la URL de la imagen al reporte
            imprimirInforme("/OnePieceWanted-Nombre.jasper", parametros);
        //}
    }

    /**
     * obtenerUrlImagenDesdeBD es un método que hace una consulta a la base de datos y devuelve la url dependiendo del pirata seleccionado
     * @param nombrePirata es el nombre del pirata que se le pasa al método para que obtenga su imagen correspondiente
     * @return retorna null si no encuentra ningún resultado a la consulta
     */
    //Método para obtener el campo url de la base de datos para luego pasarlo al jasper
    private String obtenerUrlImagenDesdeBD(String nombrePirata) {
        String consulta = "SELECT urlImagen FROM piratas WHERE nombre =?";
        try (PreparedStatement pst = conexion.prepareStatement(consulta)) {
            pst.setString(1, nombrePirata);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("urlImagen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Impresión del informe y elección de su ruta de creación
     * @param ficheroInforme String, ruta del jasper en el proyecto Java
     * @param parametros Map<String, Object> parámetros seleccionados para la creación del informe
     */
    private void imprimirInforme (String ficheroInforme, Map<String, Object> parametros){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar el Cartel de los Piratas");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
            fileChooser.setInitialFileName("Cartel_Pirata.pdf");

            //Se muestra el fileChooser
            File rutaInforme = fileChooser.showSaveDialog(new Stage());

            //Se verifica si el usuario ha seleccionado un archivo
            if (rutaInforme != null) {
                String rutaJasper = "src/main/resources" + ficheroInforme;
                System.out.println("Ruta del informe elegido: " + rutaJasper);

                JasperPrint print = JasperFillManager.fillReport(rutaJasper, parametros, conexion);
                JasperExportManager.exportReportToPdfFile(print, rutaInforme.getAbsolutePath());

                System.out.println("Informe generado correctamente en: " + rutaInforme.getAbsolutePath());
            }
        } catch (JRException ex) {
            System.out.println("Error al generar el informe: " + ex.getMessage());
        }
    }
}