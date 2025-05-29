package edu.ijse.powerhouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashBoardTController {

    @FXML
    private AnchorPane ancDashBoardT;

    @FXML
    private Pane sidePane;

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

    @FXML
    void btnMemberListOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MemberList.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnAttendanceOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Attendance.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnAssignedworkoutOnAction() throws IOException {
//        sidePane.getChildren().clear();
//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WorkoutPlanList.fxml"));
//        sidePane.getChildren().add(load);
    }

    @FXML
    void btnAssignedDietPlansOnAction() throws IOException {
//        sidePane.getChildren().clear();
//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WorkoutPlan.fxml"));
//        sidePane.getChildren().add(load);
    }

    @FXML
    void btnWorkoutPlanListOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WorkoutPlanList.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnExercisesLibraryOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ExercisesLibrary.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnDietPlansOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DietPlan.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnMealListOnAction() throws IOException {
//        sidePane.getChildren().clear();
//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DietPlanList.fxml"));
//        sidePane.getChildren().add(load);
    }
}
