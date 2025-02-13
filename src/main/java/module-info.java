module org.example.onepiece {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires net.sf.jasperreports.core;


    opens org.example.onepiece to javafx.fxml;
    exports org.example.onepiece;
}