package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.util.Animations;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardOAController implements Initializable {

    @FXML
    private Pane sidePane;

    @FXML
    private Label lblIcon;

    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane ancDashBoardO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animations.AddFancyHoverAnimation(btnLogout, "#2f3640", "#353b48");
        Animations.AnimateLabelSlideIn(lblIcon);
    }

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
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Attendance.fxml"));
        sidePane.getChildren().add(load);
    }
    @FXML
    void btnDietPlansOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DietPlan.fxml"));
        sidePane.getChildren().add(load);
    }
    @FXML
    void btnMealListOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MealList.fxml"));
        sidePane.getChildren().add(load);
    }
    @FXML
    void btnPaymentRecordManageOnAction(ActionEvent event) throws IOException {
//        sidePane.getChildren().clear();
//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Settings.fxml"));
//        sidePane.getChildren().add(load);
    }

    @FXML
    void btnPaymentPlan(ActionEvent event) throws IOException {
//        sidePane.getChildren().clear();
//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Settings.fxml"));
//        sidePane.getChildren().add(load);
    }

    @FXML
    void btnEquipmentManagementOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/EquipmentManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnAssignedDietPlansOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/AssignDietPlans.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnAssignedworkoutOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/AssignWorkoutPlans.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnWorkoutPlanListOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/WorkoutPlanList.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnExercisesLibraryOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ExercisesLibrary.fxml"));
        sidePane.getChildren().add(load);
    }
}