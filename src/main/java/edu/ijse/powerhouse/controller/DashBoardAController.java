package edu.ijse.powerhouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardAController {

    @FXML
    private AnchorPane ancDashBoardA;

    @FXML
    void btnLogoutOnAction() throws IOException {
        ancDashBoardA.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
        ancDashBoardA.getChildren().add(load);
    }

}
