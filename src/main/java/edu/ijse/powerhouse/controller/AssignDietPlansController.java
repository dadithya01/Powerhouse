package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.AssignDietPlansDto;
import edu.ijse.powerhouse.dto.tm.AssignDietPlansTM;
import edu.ijse.powerhouse.model.AssignDietPlansModel;
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

public class AssignDietPlansController implements Initializable {

    public Label lblDietPlanId;
    public TextField txtMemberId;
    public TextField txtAssignedDate;
    public TextField txtEndDate;
    public TextField txtNotes;
    public TextField txtAssignedBy;

    public TableView<AssignDietPlansTM> tblAssignDiet;
    public TableColumn<AssignDietPlansTM, String> colMemberId;
    public TableColumn<AssignDietPlansTM, String> colDietPlanId;
    public TableColumn<AssignDietPlansTM, String> colAssignedDate;
    public TableColumn<AssignDietPlansTM, String> colEndDate;
    public TableColumn<AssignDietPlansTM, String> colNotes;
    public TableColumn<AssignDietPlansTM, String> colAssignedBy;

    private final AssignDietPlansModel assignDietPlansModel = new AssignDietPlansModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        colDietPlanId.setCellValueFactory(new PropertyValueFactory<>("diet_plan_id"));
        colAssignedDate.setCellValueFactory(new PropertyValueFactory<>("assigned_date"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));
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
        tblAssignDiet.setItems(FXCollections.observableArrayList(
                assignDietPlansModel.getAllAssignDiet()
                        .stream()
                        .map(assignDietPlansDto -> new AssignDietPlansTM(
                                assignDietPlansDto.getMember_id(),
                                assignDietPlansDto.getDiet_plan_id(),
                                assignDietPlansDto.getAssigned_date(),
                                assignDietPlansDto.getEnd_date(),
                                assignDietPlansDto.getNotes(),
                                assignDietPlansDto.getAssigned_by()
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
            txtNotes.setText(null);
            txtAssignedBy.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String dietPlanId = lblDietPlanId.getText();
        String memberId = txtMemberId.getText();
        String assignedDate = txtAssignedDate.getText();
        String endDate = txtEndDate.getText();
        String notes = txtNotes.getText();
        String assignedBy = txtAssignedBy.getText();

        AssignDietPlansDto assignDietPlansDto = new AssignDietPlansDto(
                memberId,
                dietPlanId,
                assignedDate,
                endDate,
                notes,
                assignedBy
        );
        try {
            boolean isSaved = assignDietPlansModel.saveAssignDiet(assignDietPlansDto);

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
        String dietPlanId = lblDietPlanId.getText();
        String memberId = txtMemberId.getText();
        String assignedDate = txtAssignedDate.getText();
        String endDate = txtEndDate.getText();
        String notes = txtNotes.getText();
        String assignedBy = txtAssignedBy.getText();

        AssignDietPlansDto assignDietPlansDto = new AssignDietPlansDto(
                memberId,
                dietPlanId,
                assignedDate,
                endDate,
                notes,
                assignedBy
        );

        try {
            boolean isUpdated = assignDietPlansModel.updateAssignDiet(assignDietPlansDto);
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
            String assignDietPlan = lblDietPlanId.getText();
            try {
                boolean isDeleted = assignDietPlansModel.deleteAssignDiet(assignDietPlan);
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
        String nextId = assignDietPlansModel.getNextDietPlanId();
        lblDietPlanId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        AssignDietPlansTM selectedItem = tblAssignDiet.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblDietPlanId.setText(selectedItem.getDiet_plan_id());
            txtMemberId.setText(selectedItem.getMember_id());
            txtAssignedDate.setText(selectedItem.getAssigned_date());
            txtEndDate.setText(selectedItem.getEnd_date());
            txtNotes.setText(selectedItem.getNotes());
            txtAssignedBy.setText(selectedItem.getAssigned_by());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
