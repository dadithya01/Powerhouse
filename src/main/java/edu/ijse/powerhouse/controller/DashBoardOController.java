package edu.ijse.powerhouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashBoardOController {

    public Pane sidePane;
    @FXML
    private AnchorPane ancDashBoardO;

    @FXML
    void btnLogoutOnAction() throws IOException {
        ancDashBoardO.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));
        ancDashBoardO.getChildren().add(load);
    }
    @FXML
    void btnUserListOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/UserList.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnUserTypesOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/UserTypes.fxml"));
        sidePane.getChildren().add(load);
    }
}