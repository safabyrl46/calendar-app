module com.example.calendar3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.calendar3 to javafx.fxml;
    exports com.example.calendar3;
}