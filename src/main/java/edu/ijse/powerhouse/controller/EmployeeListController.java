package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.EmployeeListDto;
import edu.ijse.powerhouse.dto.tm.EmployeeListTM;
import edu.ijse.powerhouse.model.EmployeeListModel;
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

public class EmployeeListController implements Initializable {

    public Label lblEmployeeId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtHireDate;
    public TextField txtPosition;
    public TextField txtSalary;
    public TextField txtEmergencyContact;

    public TableView<EmployeeListTM> tblEmployeeList;
    public TableColumn<EmployeeListTM,String> colId;
    public TableColumn<EmployeeListTM,String> colName;
    public TableColumn<EmployeeListTM,String> colPhone;
    public TableColumn<EmployeeListTM,String> colAddress;
    public TableColumn<EmployeeListTM,Integer> colAge;
    public TableColumn<EmployeeListTM,String> colHireDate;
    public TableColumn<EmployeeListTM,String> colPosition;
    public TableColumn<EmployeeListTM, Double> colSalary;
    public TableColumn<EmployeeListTM,String> colEmergencyContact;

    private final EmployeeListModel employeeListModel = new EmployeeListModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hire_date"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmergencyContact.setCellValueFactory(new PropertyValueFactory<>("emergency_contact"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }

    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblEmployeeList.setItems(FXCollections.observableArrayList(
                employeeListModel.getAllEmployees()
                        .stream()
                        .map(employeeListDto -> new EmployeeListTM(
                                employeeListDto.getEmployee_id(),
                                employeeListDto.getName(),
                                employeeListDto.getContact(),
                                employeeListDto.getAddress(),
                                employeeListDto.getAge(),
                                employeeListDto.getHire_date(),
                                employeeListDto.getPosition(),
                                employeeListDto.getSalary(),
                                employeeListDto.getEmergency_contact()
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
            txtPhone.setText(null);
            txtAddress.setText(null);
            txtAge.setText(null);
            txtHireDate.setText(null);
            txtPosition.setText(null);
            txtSalary.setText(null);
            txtEmergencyContact.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String contact = txtPhone.getText();
        String address = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String hireDate = txtHireDate.getText();
        String position = txtPosition.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String emergencyContact = txtEmergencyContact.getText();


        EmployeeListDto employeeListDto = new EmployeeListDto(
                employeeId,
                name,
                contact,
                address,
                age,
                hireDate,
                position,
                salary,
                emergencyContact
        );
        try {
            boolean isSaved = employeeListModel.saveEmployee(employeeListDto);

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
        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String contact = txtPhone.getText();
        String address = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String hireDate = txtHireDate.getText();
        String position = txtPosition.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String emergencyContact = txtEmergencyContact.getText();


        EmployeeListDto employeeListDto = new EmployeeListDto(
                employeeId,
                name,
                contact,
                address,
                age,
                hireDate,
                position,
                salary,
                emergencyContact
        );
        try {
            boolean isUpdated = employeeListModel.updateEmployee(employeeListDto);
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
            String userId = lblEmployeeId.getText();
            try {
                boolean isDeleted = employeeListModel.deleteEmployee(userId);
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
        String nextId = employeeListModel.getNextEmployeeId();
        lblEmployeeId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        EmployeeListTM selectedItem = tblEmployeeList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblEmployeeId.setText(selectedItem.getEmployee_id());
            txtName.setText(selectedItem.getName());
            txtPhone.setText(selectedItem.getContact());
            txtAddress.setText(selectedItem.getAddress());
            txtAge.setText(String.valueOf(selectedItem.getAge()));
            txtHireDate.setText(selectedItem.getHire_date());
            txtPosition.setText(selectedItem.getPosition());
            txtSalary.setText(String.valueOf(selectedItem.getSalary()));
            txtEmergencyContact.setText(selectedItem.getEmergency_contact());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

}
