package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.*;
import java.util.List;

/**
 * Controlador de la vista generar-indorme.fxml para la ventana de generación de informes según los parámetros introducidos por el usuario.
 * Permite al usuario introducir los datos que desee en los campos Tipulación y recompensa.

 * @author Gabriela Jiménez Morcillo, Marcos Pérez Aceituno
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

    private List<String> listaTripulaciones;

    private Connection conexion;

    /**
     * Método que se ejecuta al inicializar la ventana.
     * Rellena el ComboBox con los nombres de la tripulación y cambia el array según el idioma seleccionado pro el usuario en la ventana principal
     */
    public void initialize() {
        actualizarIdiomaVentana();

        ResourceBundle resourceBundle= IdiomaController.getResourceBundle();
        String tripulacionComboBox = resourceBundle.getString("ComboBoxTripulacion");
        //Array de Strings a List<String>
        listaTripulaciones= Arrays.asList(tripulacionComboBox.split(","));

        Tripulacion.getItems().clear();
        for (String tripulacion : listaTripulaciones) {
            Tripulacion.getItems().add(tripulacion);
        }
    }

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

    /**
     * Genera un informe a raíz de los parámetros de búsqueda elegidos por el usuario,
     * en la ruta elegida por el mismo
     */
    public void generarInforme(){
        conexion = Conexion.getConexion();

        Map<String, Object> parametros = new LinkedHashMap<>();
        String recompensaMinima = labelRecompensa.getText().trim();
        String tripulacion = Tripulacion.getValue().trim();

        if (!recompensaMinima.isEmpty()) {
            try {
                parametros.put("BerriesMax", Long.parseLong(recompensaMinima));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Recompensa mínima inválida.");
            }
        } else {
            parametros.put("BerriesMax", null);
        }

        if (!tripulacion.isEmpty()) {
            //Si el idioma está en inglés:
            if (IdiomaController.getLocale().getLanguage().equals("en")) {
                //Guardamos la posicion en la lista de la tripulación en inglés
                int posicion = listaTripulaciones.indexOf(tripulacion);
                //Convertimos el idioma a español para enviar el dato en el idioma original
                IdiomaController.seleccionIdioma("Español");
                String tripulacionComboBox = IdiomaController.getResourceBundle().getString("ComboBoxTripulacion");
                List<String> listaTripulacionesEsp = Arrays.asList(tripulacionComboBox.split(","));
                parametros.put("Tripulacion", listaTripulacionesEsp.get(posicion));

                //Reseteamos el idioma a español
                IdiomaController.seleccionIdioma("English");
            } else {
                parametros.put("Tripulacion", tripulacion);
            }
        } else {
            parametros.put("Tripulacion", null);
        }
        System.out.println("Parámetros seleccionados: " + parametros);

        //Llamada al método de imprimir informe
        imprimirInforme("/OnePieceInforme-2Params.jasper", parametros);
}

    /**
     * Impresión del informe y elección de su ruta de creación
     * @param ficheroInforme ruta del jasper en el proyecto Java
     * @param parametros parámetros seleccionados para la creación del informe
     */
    private void imprimirInforme(String ficheroInforme, Map<String, Object> parametros){
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar el Informe de los Piratas");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
            fileChooser.setInitialFileName("Informe_Piratas.pdf");

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
