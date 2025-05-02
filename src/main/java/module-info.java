module edu.ijse.powerhouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens edu.ijse.powerhouse.controller to javafx.fxml;
    exports edu.ijse.powerhouse;
}