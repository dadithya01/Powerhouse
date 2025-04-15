package edu.ijse.powerhouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardOController {

    @FXML
    private AnchorPane ancDashBoardO;

    @FXML
    void btnLogoutOnAction() throws IOException {
        ancDashBoardO.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
        ancDashBoardO.getChildren().add(load);
    }
}
