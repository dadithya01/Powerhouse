package edu.ijse.powerhouse.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardTController implements Initializable {

    @FXML
    private AnchorPane ancDashBoardT;

    @FXML
    private Label lblIcon;

    @FXML
    private Pane sidePane;

    @FXML
    private Button btnLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addFancyHoverAnimation(btnLogout);
        animateLabelSlideIn();
    }

    private void animateLabelSlideIn() {
        String loginText = lblIcon.getText();
        lblIcon.setText(loginText);
        lblIcon.setOpacity(0);
        lblIcon.setTranslateX(-50);

        TranslateTransition slide = new TranslateTransition(Duration.millis(2000), lblIcon);
        slide.setFromX(-100);
        slide.setToX(0);

        FadeTransition fade = new FadeTransition(Duration.millis(2000), lblIcon);
        fade.setFromValue(0);
        fade.setToValue(1);

        ParallelTransition parallel = new ParallelTransition(slide, fade);
        parallel.play();
    }

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

    public void addFancyHoverAnimation(Button button) {
        button.setStyle("-fx-background-color: #2f3640; -fx-text-fill: white; -fx-background-radius: 50;");
        DropShadow glow = new DropShadow();
        glow.setColor(Color.LIGHTBLUE);
        glow.setRadius(15);
        glow.setSpread(0.3);

        button.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);

            button.setStyle("-fx-background-color: #2f3640; -fx-text-fill: white; -fx-background-radius: 50;");

            button.setEffect(glow);
            scaleUp.play();
        });

        button.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), button);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);

            button.setStyle("-fx-background-color: #2f3640; -fx-text-fill: white; -fx-background-radius: 50;");
            button.setEffect(null);
            scaleDown.play();
        });
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
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/AssignWorkoutPlans.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnAssignedDietPlansOnAction() throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/AssignDietPlans.fxml"));
        sidePane.getChildren().add(load);
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
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MealList.fxml"));
        sidePane.getChildren().add(load);
    }
}
