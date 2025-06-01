package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.AssignWorkoutPlansDto;
import edu.ijse.powerhouse.dto.tm.AssignWorkoutPlansTM;
import edu.ijse.powerhouse.model.AssignWorkoutPlansModel;
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

public class AssignWorkoutPlansController implements Initializable {

    public Label lblWorkoutPlanId;
    public TextField txtMemberId;
    public TextField txtAssignedDate;
    public TextField txtEndDate;
    public TextField txtProgress;
    public TextField txtNotes;
    public TextField txtAssignedBy;

    public TableView<AssignWorkoutPlansTM> tblAssignWorkout;
    public TableColumn<AssignWorkoutPlansTM, String> colMemberId;
    public TableColumn<AssignWorkoutPlansTM, String> colWorkoutPlanId;
    public TableColumn<AssignWorkoutPlansTM, String> colAssignedDate;
    public TableColumn<AssignWorkoutPlansTM, String> colEndDate;
    public TableColumn<AssignWorkoutPlansTM, String> colProgress;
    public TableColumn<AssignWorkoutPlansTM, String> colNotes;
    public TableColumn<AssignWorkoutPlansTM, String> colAssignedBy;

    private final AssignWorkoutPlansModel assignWorkoutPlansModel = new AssignWorkoutPlansModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        colWorkoutPlanId.setCellValueFactory(new PropertyValueFactory<>("workout_plan_id"));
        colAssignedDate.setCellValueFactory(new PropertyValueFactory<>("assigned_date"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        colProgress.setCellValueFactory(new PropertyValueFactory<>("progress"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        colAssignedBy.setCellValueFactory(new PropertyValueFactory<>("assigned_by"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblAssignWorkout.setItems(FXCollections.observableArrayList(
                assignWorkoutPlansModel.getAllAssignWorkouts()
                        .stream()
                        .map(assignWorkoutPlansDto -> new AssignWorkoutPlansTM(
                                assignWorkoutPlansDto.getMember_id(),
                                assignWorkoutPlansDto.getWorkout_plan_id(),
                                assignWorkoutPlansDto.getAssigned_date(),
                                assignWorkoutPlansDto.getEnd_date(),
                                assignWorkoutPlansDto.getProgress(),
                                assignWorkoutPlansDto.getNotes(),
                                assignWorkoutPlansDto.getAssigned_by()
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

            txtMemberId.setText(null);
            txtAssignedDate.setText(null);
            txtEndDate.setText(null);
            txtProgress.setText(null);
            txtNotes.setText(null);
            txtAssignedBy.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String workoutPlanId = lblWorkoutPlanId.getText();
        String memberId = txtMemberId.getText();
        String assignedDate = txtAssignedDate.getText();
        String endDate = txtEndDate.getText();
        String progress = txtProgress.getText();
        String notes = txtNotes.getText();
        String assignedBy = txtAssignedBy.getText();

        AssignWorkoutPlansDto assignWorkoutPlansDto = new AssignWorkoutPlansDto(
                memberId,
                workoutPlanId,
                assignedDate,
                endDate,
                progress,
                notes,
                assignedBy
        );
        try {
            boolean isSaved = assignWorkoutPlansModel.saveAssignWorkout(assignWorkoutPlansDto);

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
        String memberId = txtMemberId.getText();
        String assignedDate = txtAssignedDate.getText();
        String endDate = txtEndDate.getText();
        String progress = txtProgress.getText();
        String notes = txtNotes.getText();
        String assignedBy = txtAssignedBy.getText();

        AssignWorkoutPlansDto assignWorkoutPlansDto = new AssignWorkoutPlansDto(
                memberId,
                workoutPlanId,
                assignedDate,
                endDate,
                progress,
                notes,
                assignedBy
        );

        try {
            boolean isUpdated = assignWorkoutPlansModel.updateAssignWorkout(assignWorkoutPlansDto);
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
            String assignWorkoutPlan = lblWorkoutPlanId.getText();
            try {
                boolean isDeleted = assignWorkoutPlansModel.deleteAssignWorkout(assignWorkoutPlan);
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
        String nextId = assignWorkoutPlansModel.getNextWorkoutPlanId();
        lblWorkoutPlanId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        AssignWorkoutPlansTM selectedItem = tblAssignWorkout.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblWorkoutPlanId.setText(selectedItem.getWorkout_plan_id());
            txtMemberId.setText(selectedItem.getMember_id());
            txtAssignedDate.setText(selectedItem.getAssigned_date());
            txtEndDate.setText(selectedItem.getEnd_date());
            txtProgress.setText(selectedItem.getProgress());
            txtNotes.setText(selectedItem.getNotes());
            txtAssignedBy.setText(selectedItem.getAssigned_by());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
