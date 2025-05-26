package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.WorkoutPlanListDto;
import edu.ijse.powerhouse.dto.tm.WorkoutPlanListTM;
import edu.ijse.powerhouse.model.WorkoutPlanListModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class WorkoutPlanListController implements Initializable {

    public Label lblWorkoutPlanId;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtDifficultyLevel;
    public TextField txtCreatedBy;
    public TextField txtCreatedDate;
    public TextField txtDurationWeeks;

    public TableView<WorkoutPlanListTM> tblWorkoutPlanList;
    public TableColumn<WorkoutPlanListTM, String> colId;
    public TableColumn<WorkoutPlanListTM, String> colName;
    public TableColumn<WorkoutPlanListTM, String> colDescription;
    public TableColumn<WorkoutPlanListTM, String> colDifficultyLevel;
    public TableColumn<WorkoutPlanListTM, String> colCreatedBy;
    public TableColumn<WorkoutPlanListTM, String> colCreatedDate;
    public TableColumn<WorkoutPlanListTM, String> colDurationWeeks;

    private final WorkoutPlanListModel workoutPlanListModel = new WorkoutPlanListModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("workout_plan_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDifficultyLevel.setCellValueFactory(new PropertyValueFactory<>("difficulty_level"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("created_by"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("created_date"));
        colDurationWeeks.setCellValueFactory(new PropertyValueFactory<>("duration_weeks"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblWorkoutPlanList.setItems(FXCollections.observableArrayList(
                workoutPlanListModel.getAllWorkoutPlans()
                        .stream()
                        .map(workoutPlanListDto -> new WorkoutPlanListTM(
                                workoutPlanListDto.getWorkout_plan_id(),
                                workoutPlanListDto.getName(),
                                workoutPlanListDto.getDescription(),
                                workoutPlanListDto.getDifficulty_level(),
                                workoutPlanListDto.getCreated_by(),
                                workoutPlanListDto.getCreated_date(),
                                workoutPlanListDto.getDuration_weeks()
                        )).toList()
        ));
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtName.setText(null);
            txtDescription.setText(null);
            txtDifficultyLevel.setText(null);
            txtCreatedBy.setText(null);
            txtCreatedDate.setText(null);
            txtDurationWeeks.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String workoutPlanId = lblWorkoutPlanId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String difficultyLevel = txtDifficultyLevel.getText();
        String createdBy = txtCreatedBy.getText();
        String createdDate = txtCreatedDate.getText();
        String durationWeeks = txtDurationWeeks.getText();

        WorkoutPlanListDto workoutPlanListDto = new WorkoutPlanListDto(
                workoutPlanId,
                name,
                description,
                difficultyLevel,
                createdBy,
                createdDate,
                durationWeeks
        );
        try {
            boolean isSaved = workoutPlanListModel.saveWorkoutPlan(workoutPlanListDto);

            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String workoutPlanId = lblWorkoutPlanId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String difficultyLevel = txtDifficultyLevel.getText();
        String createdBy = txtCreatedBy.getText();
        String createdDate = txtCreatedDate.getText();
        String durationWeeks = txtDurationWeeks.getText();

        WorkoutPlanListDto workoutPlanListDto = new WorkoutPlanListDto(
                workoutPlanId,
                name,
                description,
                difficultyLevel,
                createdBy,
                createdDate,
                durationWeeks
        );
        try {
            boolean isUpdated = workoutPlanListModel.updateWorkoutPlan(workoutPlanListDto);
            if(isUpdated){
                resetPage();
                new Alert(Alert.AlertType.INFORMATION,"Updated").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail").show();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are You Sure ? ",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> response = alert.showAndWait();

        if(response.isPresent() && response.get() == ButtonType.YES){
            String workoutPlanId = lblWorkoutPlanId.getText();
            try {
                boolean isDeleted = workoutPlanListModel.deleteWorkoutPlan(workoutPlanId);
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Fail").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = workoutPlanListModel.getNextWorkoutPlanId();
        lblWorkoutPlanId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        WorkoutPlanListTM selectedItem = tblWorkoutPlanList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblWorkoutPlanId.setText(selectedItem.getWorkout_plan_id());
            txtName.setText(selectedItem.getName());
            txtDescription.setText(selectedItem.getDescription());
            txtDifficultyLevel.setText(selectedItem.getDifficulty_level());
            txtCreatedBy.setText(selectedItem.getCreated_by());
            txtCreatedDate.setText(selectedItem.getCreated_date());
            txtDurationWeeks.setText(selectedItem.getDuration_weeks());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
