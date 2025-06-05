package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.DietPlanDto;
import edu.ijse.powerhouse.dto.tm.DietPlanTM;
import edu.ijse.powerhouse.model.DietPlanModel;
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

public class DietPlanController implements Initializable {

    public Label lblDietPlanId;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtCreatedBy;
    public TextField txtCreatedDate;
    public TextField txtCalorieTarget;
    public TextField txtProteinTarget;
    public TextField txtCarbsTarget;
    public TextField txtFatTarget;
    public TextField txtNote;

    public TableView<DietPlanTM> tblDietPlan;
    public TableColumn<DietPlanTM, String> colDietPlanId;
    public TableColumn<DietPlanTM, String> colName;
    public TableColumn<DietPlanTM, String> colDescription;
    public TableColumn<DietPlanTM, String> colCreatedBy;
    public TableColumn<DietPlanTM, String> colCreatedDate;
    public TableColumn<DietPlanTM, String> colCalorieTarget;
    public TableColumn<DietPlanTM, String> colProteinTarget;
    public TableColumn<DietPlanTM, String> colCarbsTarget;
    public TableColumn<DietPlanTM, String> colFatTarget;
    public TableColumn<DietPlanTM, String> colNote;

    private final DietPlanModel dietPlanModel = new DietPlanModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDietPlanId.setCellValueFactory(new PropertyValueFactory<>("diet_plan_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("created_by"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("created_date"));
        colCalorieTarget.setCellValueFactory(new PropertyValueFactory<>("calorie_target"));
        colProteinTarget.setCellValueFactory(new PropertyValueFactory<>("protein_target"));
        colCarbsTarget.setCellValueFactory(new PropertyValueFactory<>("carbs_target"));
        colFatTarget.setCellValueFactory(new PropertyValueFactory<>("fat_target"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("notes"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblDietPlan.setItems(FXCollections.observableArrayList(
                dietPlanModel.getAllDietPlans()
                        .stream()
                        .map(dietPlanDto -> new DietPlanTM(
                                dietPlanDto.getDiet_plan_id(),
                                dietPlanDto.getName(),
                                dietPlanDto.getDescription(),
                                dietPlanDto.getCreated_by(),
                                dietPlanDto.getCreated_date(),
                                dietPlanDto.getCalorie_target(),
                                dietPlanDto.getProtein_target(),
                                dietPlanDto.getCarbs_target(),
                                dietPlanDto.getFat_target(),
                                dietPlanDto.getNotes()
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
            txtCreatedBy.setText(null);
            txtCreatedDate.setText(null);
            txtCalorieTarget.setText(null);
            txtProteinTarget.setText(null);
            txtCarbsTarget.setText(null);
            txtFatTarget.setText(null);
            txtNote.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private boolean isValidInput() {
        StringBuilder errors = new StringBuilder();

        if (txtName.getText().isEmpty()) errors.append("Name is required.\n");
        if (txtDescription.getText().isEmpty()) errors.append("Description is required.\n");
        if (txtCreatedBy.getText().isEmpty()) errors.append("Created By is required.\n");
        if (txtCreatedDate.getText().isEmpty()) {
            errors.append("Created Date is required.\n");
        } else if (!txtCreatedDate.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
            errors.append("Created Date must be in YYYY-MM-DD format.\n");
        }

        if (!isValidNumber(txtCalorieTarget.getText())) errors.append("Calorie Target must be a valid number.\n");
        if (!isValidNumber(txtProteinTarget.getText())) errors.append("Protein Target must be a valid number.\n");
        if (!isValidNumber(txtCarbsTarget.getText())) errors.append("Carbs Target must be a valid number.\n");
        if (!isValidNumber(txtFatTarget.getText())) errors.append("Fat Target must be a valid number.\n");

        if (errors.length() > 0) {
            new Alert(Alert.AlertType.WARNING, errors.toString()).show();
            return false;
        }

        return true;
    }

    private boolean isValidNumber(String text) {
        if (text == null || text.isEmpty()) return false;
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (!isValidInput()) return;
        String dietPlanId = lblDietPlanId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String createdBy = txtCreatedBy.getText();
        String createdDate = txtCreatedDate.getText();
        String calorieTarget = txtCalorieTarget.getText();
        String proteinTarget = txtProteinTarget.getText();
        String carbsTarget = txtCarbsTarget.getText();
        String fatTarget = txtFatTarget.getText();
        String notes = txtNote.getText();


        DietPlanDto dietPlanDto = new DietPlanDto(
                dietPlanId,
                name,
                description,
                createdBy,
                createdDate,
                calorieTarget,
                proteinTarget,
                carbsTarget,
                fatTarget,
                notes
        );
        try {
            boolean isSaved = dietPlanModel.saveDietPlan(dietPlanDto);

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

        if (!isValidInput()) return;
        String dietPlanId = lblDietPlanId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String createdBy = txtCreatedBy.getText();
        String createdDate = txtCreatedDate.getText();
        String calorieTarget = txtCalorieTarget.getText();
        String proteinTarget = txtProteinTarget.getText();
        String carbsTarget = txtCarbsTarget.getText();
        String fatTarget = txtFatTarget.getText();
        String notes = txtNote.getText();


        DietPlanDto dietPlanDto = new DietPlanDto(
                dietPlanId,
                name,
                description,
                createdBy,
                createdDate,
                calorieTarget,
                proteinTarget,
                carbsTarget,
                fatTarget,
                notes
        );
        try {
            boolean isUpdated = dietPlanModel.updateDietPlan(dietPlanDto);
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
            String dietPanId = lblDietPlanId.getText();
            try {
                boolean isDeleted = dietPlanModel.deleteDietPlan(dietPanId);
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
        String nextId = dietPlanModel.getNextDietPlanId();
        lblDietPlanId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        DietPlanTM selectedItem = tblDietPlan.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblDietPlanId.setText(selectedItem.getDiet_plan_id());
            txtName.setText(selectedItem.getName());
            txtDescription.setText(selectedItem.getDescription());
            txtCreatedBy.setText(selectedItem.getCreated_by());
            txtCreatedDate.setText(selectedItem.getCreated_date());
            txtCalorieTarget.setText(selectedItem.getCalorie_target());
            txtProteinTarget.setText(selectedItem.getProtein_target());
            txtCarbsTarget.setText(selectedItem.getCarbs_target());
            txtFatTarget.setText(selectedItem.getFat_target());
            txtNote.setText(selectedItem.getNotes());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
