package edu.ijse.powerhouse.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardOAController implements Initializable {

    @FXML
    public Pane sidePane;

    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane ancDashBoardO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addFancyHoverAnimation(btnLogout);
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

    public void addFancyHoverAnimation(Button button) {
        button.setStyle("-fx-background-color: #2f3640; -fx-text-fill: white; -fx-background-radius: 7;");
        DropShadow glow = new DropShadow();
        glow.setColor(Color.LIGHTBLUE);
        glow.setRadius(15);
        glow.setSpread(0.3);

        button.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);

            button.setStyle("-fx-background-color: #2f3640; -fx-text-fill: white; -fx-background-radius: 7;");

            button.setEffect(glow);
            scaleUp.play();
        });

        button.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), button);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);

            button.setStyle("-fx-background-color: #2f3640; -fx-text-fill: white; -fx-background-radius: 7;");
            button.setEffect(null);
            scaleDown.play();
        });
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