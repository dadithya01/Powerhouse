package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.EmployeeListDto;
import edu.ijse.powerhouse.dto.EquipmentDto;
import edu.ijse.powerhouse.dto.tm.EmployeeListTM;
import edu.ijse.powerhouse.dto.tm.EquipmentTM;
import edu.ijse.powerhouse.model.EquipmentModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EquipmentManagementController implements Initializable {

    @FXML
    private TextField MaintenanceSchedule;

    @FXML
    private AnchorPane ancUserList;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<EquipmentTM, String> colAddedBy;

    @FXML
    private TableColumn<EquipmentTM, String> colCost;

    @FXML
    private TableColumn<EquipmentTM, String> colDescription;

    @FXML
    private TableColumn<EquipmentTM, String> colEquipmentId;

    @FXML
    private TableColumn<EquipmentTM, String> colLastMaintenanceDate;

    @FXML
    private TableColumn<EquipmentTM, String> colMaintenanceSchedule;

    @FXML
    private TableColumn<EquipmentTM, String> colName;

    @FXML
    private TableColumn<EquipmentTM, String> colPurchaseDate;

    @FXML
    private TableColumn<EquipmentTM, String> colStatus;

    @FXML
    private Label lblEquipmentId;

    @FXML
    private TableView<EquipmentTM> tblEquipmentList;

    @FXML
    private TextField txtAddedBy;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtLastMaintenanceDate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPurchaseDate;

    @FXML
    private TextField txtStatus;

    private final EquipmentModel equipmentModel = new EquipmentModel();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEquipmentId.setCellValueFactory(new PropertyValueFactory<>("equipment_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchase_date"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colMaintenanceSchedule.setCellValueFactory(new PropertyValueFactory<>("maintenance_schedule"));
        colLastMaintenanceDate.setCellValueFactory(new PropertyValueFactory<>("last_maintenance_date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAddedBy.setCellValueFactory(new PropertyValueFactory<>("added_by"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblEquipmentList.setItems(FXCollections.observableArrayList(
                equipmentModel.getAllEquipments()
                        .stream()
                        .map(equipmentDto -> new EquipmentTM(
                                equipmentDto.getEquipment_id(),
                                equipmentDto.getName(),
                                equipmentDto.getDescription(),
                                equipmentDto.getPurchase_date(),
                                equipmentDto.getCost(),
                                equipmentDto.getMaintenance_schedule(),
                                equipmentDto.getLast_maintenance_date(),
                                equipmentDto.getStatus(),
                                equipmentDto.getAdded_by()
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
            txtPurchaseDate.setText(null);
            txtCost.setText(null);
            MaintenanceSchedule.setText(null);
            txtLastMaintenanceDate.setText(null);
            txtStatus.setText(null);
            txtAddedBy.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private boolean validateEquipmentForm() {
        String name = txtName.getText().trim();
        String description = txtDescription.getText().trim();
        String purchaseDate = txtPurchaseDate.getText().trim();
        String costText = txtCost.getText().trim();
        String maintenanceSchedule = MaintenanceSchedule.getText().trim();
        String lastMaintenanceDate = txtLastMaintenanceDate.getText().trim();
        String status = txtStatus.getText().trim();
        String addedBy = txtAddedBy.getText().trim();

        if (name.isEmpty() || description.isEmpty() || purchaseDate.isEmpty() ||
                costText.isEmpty() || maintenanceSchedule.isEmpty() || lastMaintenanceDate.isEmpty() ||
                status.isEmpty() || addedBy.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields.").show();
            return false;
        }

        try {
            double cost = Double.parseDouble(costText);
            if (cost <= 0) {
                new Alert(Alert.AlertType.WARNING, "Cost must be a positive number.").show();
                return false;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Invalid cost. Please enter a valid number.").show();
            return false;
        }

        // Optional: validate date formats (assumes yyyy-MM-dd or similar)
        if (!isValidDate(purchaseDate) || !isValidDate(lastMaintenanceDate)) {
            new Alert(Alert.AlertType.WARNING, "Invalid date format. Use yyyy-MM-dd.").show();
            return false;
        }

        // Optional: validate status
        if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Unavailable") &&
                !status.equalsIgnoreCase("In use")) {
            new Alert(Alert.AlertType.WARNING, "Status must be Available or Unavailable").show();
            return false;
        }

        return true;
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date); // Assumes format yyyy-MM-dd
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (!validateEquipmentForm()) {
            return; // Stop if validation fails
        }

        String equipmentId = lblEquipmentId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String purchaseDate = txtPurchaseDate.getText();
        Double cost = Double.valueOf(txtCost.getText());
        String maintenanceSchedule = MaintenanceSchedule.getText();
        String lastMaintenanceDate = txtLastMaintenanceDate.getText();
        String status = txtStatus.getText();
        String addedBy = txtAddedBy.getText();


        EquipmentDto equipmentDto = new EquipmentDto(
                equipmentId,
                name,
                description,
                purchaseDate,
                cost,
                maintenanceSchedule,
                lastMaintenanceDate,
                status,
                addedBy
        );
        try {
            boolean isSaved = equipmentModel.saveEquipment(equipmentDto);

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

        if (!validateEquipmentForm()) {
            return; // Stop if validation fails
        }

        String equipmentId = lblEquipmentId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String purchaseDate = txtPurchaseDate.getText();
        Double cost = Double.valueOf(txtCost.getText());
        String maintenanceSchedule = MaintenanceSchedule.getText();
        String lastMaintenanceDate = txtLastMaintenanceDate.getText();
        String status = txtStatus.getText();
        String addedBy = txtAddedBy.getText();


        EquipmentDto equipmentDto = new EquipmentDto(
                equipmentId,
                name,
                description,
                purchaseDate,
                cost,
                maintenanceSchedule,
                lastMaintenanceDate,
                status,
                addedBy
        );
        try {
            boolean isUpdated = equipmentModel.updateEquipment(equipmentDto);
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
            String equipmentId = lblEquipmentId.getText();
            try {
                boolean isDeleted = equipmentModel.deleteEquipment(equipmentId);
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
        String nextId = equipmentModel.getNextEquipmentId();
        lblEquipmentId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        EquipmentTM selectedItem = tblEquipmentList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblEquipmentId.setText(selectedItem.getEquipment_id());
            txtName.setText(selectedItem.getName());
            txtDescription.setText(selectedItem.getDescription());
            txtPurchaseDate.setText(selectedItem.getPurchase_date());
            txtCost.setText(String.valueOf(selectedItem.getCost()));
            MaintenanceSchedule.setText(selectedItem.getMaintenance_schedule());
            txtLastMaintenanceDate.setText(selectedItem.getLast_maintenance_date());
            txtStatus.setText(selectedItem.getStatus());
            txtAddedBy.setText(selectedItem.getAdded_by());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
