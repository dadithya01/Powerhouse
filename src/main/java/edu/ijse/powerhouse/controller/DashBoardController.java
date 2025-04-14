package edu.ijse.powerhouse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private AnchorPane ancDashBoard;

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        ancDashBoard.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
        ancDashBoard.getChildren().add(load);
    }
}
