package edu.ijse.powerhouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardTController {

    @FXML
    private AnchorPane ancDashBoardT;

    @FXML
    void btnLogoutOnAction() throws IOException {
        ancDashBoardT.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        ancDashBoardT.getChildren().add(load);
    }
}
