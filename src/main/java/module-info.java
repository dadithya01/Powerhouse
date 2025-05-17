module edu.ijse.powerhouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens edu.ijse.powerhouse.controller to javafx.fxml;
    opens edu.ijse.powerhouse.dto.tm to javafx.base;
    opens edu.ijse.powerhouse.dto to javafx.base;

    exports edu.ijse.powerhouse;
    exports edu.ijse.powerhouse.controller;
    exports edu.ijse.powerhouse.dto;
    exports edu.ijse.powerhouse.dto.tm;
}