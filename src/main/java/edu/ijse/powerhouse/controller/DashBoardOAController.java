package edu.ijse.powerhouse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashBoardOAController {

    @FXML
    public Pane sidePane;
    @FXML
    private AnchorPane ancDashBoardO;

    @FXML
    void btnLogoutOnAction() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
        ancDashBoardO.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        ancDashBoardO.getChildren().add(load);
        }
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

    @FXML
    void btnEmployeeListOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/EmployeeList.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnTrainerListOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/TrainerList.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnMemberListOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MemberList.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnMembershipTypeOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MembershipType.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnAttendanceOnAction() throws IOException {
//        sidePane.getChildren().clear();
//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/PaymentList.fxml"));
//        sidePane.getChildren().add(load);
    }
}