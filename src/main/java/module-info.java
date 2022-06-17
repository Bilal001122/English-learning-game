module com.example.tp_java {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.tp_java to javafx.fxml;
    exports com.example.tp_java;
    exports com.example.tp_java.Cases;
    opens com.example.tp_java.Cases to javafx.fxml;
}