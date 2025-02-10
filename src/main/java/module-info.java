module org.example.onepiece {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.onepiece to javafx.fxml;
    exports org.example.onepiece;
}