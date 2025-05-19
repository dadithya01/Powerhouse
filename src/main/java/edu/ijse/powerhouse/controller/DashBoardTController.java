package edu.ijse.powerhouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardTController {

    @FXML
    private AnchorPane ancDashBoardT;

    @FXML
    void btnLogoutOnAction() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            ancDashBoardT.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
            ancDashBoardT.getChildren().add(load);
        }
    }
}
