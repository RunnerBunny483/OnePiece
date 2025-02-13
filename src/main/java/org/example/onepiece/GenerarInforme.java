package org.example.onepiece;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.awt.*;
import java.io.InputStream;
import java.sql.Connection;
import java.util.*;

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

    private ArrayList<String> listaTripulaciones;

    private Connection conexion;

    /**
     * Método que se ejecuta al inicializar la ventana.
     * Rellena el ComboBox con los nombres de la tripulación y cambia el array según el idioma seleccionado pro el usuario en la ventana principal
     */
    public void initialize() {
        actualizarIdiomaVentana();

        ResourceBundle resourceBundle= IdiomaController.getResourceBundle();
        String tripulacionComboBox = resourceBundle.getString("ComboBoxTripulacion");
//        listaTripulaciones= tripulacionComboBox.split(",");

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

    public void generarInforme(){
        conexion = Conexion.getConexion();

        Map<String, Object> parametros = new LinkedHashMap<>();
        String recompensaMinima = labelRecompensa.getText().trim();
        String tripulacion = Tripulacion.getValue().trim();

        if (!recompensaMinima.isEmpty()) {
            try{
                parametros.put("BerriesMax", Long.parseLong(recompensaMinima));
            } catch (NumberFormatException e){
                throw new NumberFormatException("Recompensa mínima inválida.");
            }
        } else {
            parametros.put("BerriesMax", null);
        }

        if (!tripulacion.isEmpty()) {
            if(IdiomaController.getLocale().getLanguage().equals("en")) {
                listaTripulaciones
            }
            parametros.put("Distrito", distrito);
        } else {
            parametros.put("Distrito", null);
        }

        if (!bando.isEmpty()) {
            parametros.put("Bando", bando);
        } else {
            parametros.put("Bando", null);
        }

        System.out.println("Parámetros seleccionados: " + parametros);

        informeParametros(parametros);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Informe de Jugadores");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
        fileChooser.setInitialFileName("Informe_Jugadores.pdf");

        // Mostrar el cuadro de diálogo para guardar el archivo
        File file = fileChooser.showSaveDialog(new Stage());

        // Verificar si el usuario ha seleccionado un archivo
        if (file != null) {
            String reportPath = "src/main/recursos/LaLiga.jasper";  // Ruta directa
            System.out.println("Ruta del informe: " + reportPath);

            JasperPrint print = JasperFillManager.fillReport(reportPath, parametros, conexion);
            JasperExportManager.exportReportToPdfFile(print, file.getAbsolutePath());

            System.out.println("Informe generado correctamente en: " + file.getAbsolutePath());
        }

    } catch (ClassNotFoundException | JRException e) {
        System.out.println("Error al generar el informe de jugadores: " + e.getMessage());
    }
}

    private void informeParametros(Map<String, Object> parametros) throws JRException {
        InputStream jasperStream = getClass().getResourceAsStream("/DetectiveConan.jasper");
        JasperPrint print = JasperFillManager.fillReport(jasperStream, parametros, conexion);
        JasperExportManager.exportReportToPdfFile(print, rutaDirectorioSeleccionado+"/informeConan.pdf");
    }

    private void informeGroup() throws JRException{
        Map<String, Object> paramVacios = new HashMap<>();

        InputStream jasperStream = getClass().getResourceAsStream("/DetectiveConanGroup.jasper");
        JasperPrint print = JasperFillManager.fillReport(jasperStream, paramVacios, conexion);

        JasperExportManager.exportReportToPdfFile(print, rutaDirectorioSeleccionado+"/informeConanGroup.pdf");
    }
}
