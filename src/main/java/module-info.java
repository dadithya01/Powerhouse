module edu.ijse.powerhouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens edu.ijse.powerhouse.controller to javafx.fxml;
    opens edu.ijse.powerhouse.dto to javafx.base;
    exports edu.ijse.powerhouse;
}