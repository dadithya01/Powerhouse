package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.UserListDto;
import edu.ijse.powerhouse.dto.tm.UserListTM;
import edu.ijse.powerhouse.model.UserListModel;
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


public class UserListController implements Initializable {

    public Label lblUserId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtEmail;
    public TextField txtUserName;
    public TextField txtPassword;
    public TextField txtUserTypeId;
    public TextField txtRegistrationDate;
    public TextField txtStatus;

    public TableView<UserListTM> tblUserList;
    public TableColumn<UserListTM, String> colId;
    public TableColumn<UserListTM, String> colName;
    public TableColumn<UserListTM, String> colPhone;
    public TableColumn<UserListTM, String> colEmail;
    public TableColumn<UserListTM, String> colUserName;
    public TableColumn<UserListTM, String> colPassword;
    public TableColumn<UserListTM, String> colUserTypeId;
    public TableColumn<UserListTM, String> colRegistrationDate;
    public TableColumn<UserListTM, String> colStatus;

    private final UserListModel userListModel = new UserListModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUserTypeId.setCellValueFactory(new PropertyValueFactory<>("userTypeId"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblUserList.setItems(FXCollections.observableArrayList(
                userListModel.getAllUsers()
                        .stream()
                        .map(userListDto -> new UserListTM(
                                userListDto.getUserId(),
                                userListDto.getName(),
                                userListDto.getPhone(),
                                userListDto.getEmail(),
                                userListDto.getUserName(),
                                userListDto.getPassword(),
                                userListDto.getUserTypeId(),
                                userListDto.getRegistrationDate(),
                                userListDto.getStatus()
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
            txtEmail.setText(null);
            txtUserName.setText(null);
            txtPassword.setText(null);
            txtUserTypeId.setText(null);
            txtRegistrationDate.setText(null);
            txtStatus.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String userId = lblUserId.getText();
        String userName = txtName.getText();
        String userContact = txtPhone.getText();
        String userEmail = txtEmail.getText();
        String userUserName = txtUserName.getText();
        String userPassword = txtPassword.getText();
        String userTypeId = txtUserTypeId.getText();
        String userRegistrationDate = txtRegistrationDate.getText();
        String userStatus = txtStatus.getText();

        UserListDto userListDto = new UserListDto(
                userId,
                userName,
                userContact,
                userEmail,
                userUserName,
                userPassword,
                userTypeId,
                userRegistrationDate,
                userStatus
        );
        try {
            boolean isSaved = userListModel.saveUser(userListDto);

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
        String userId = lblUserId.getText();
        String userName = txtName.getText();
        String userContact = txtPhone.getText();
        String userEmail = txtEmail.getText();
        String userUserName = txtUserName.getText();
        String userPassword = txtPassword.getText();
        String userTypeId = txtUserTypeId.getText();
        String userRegistrationDate = txtRegistrationDate.getText();
        String userStatus = txtStatus.getText();


        UserListDto userListDto = new UserListDto(
                userId,
                userName,
                userContact,
                userEmail,
                userUserName,
                userPassword,
                userTypeId,
                userRegistrationDate,
                userStatus
        );
        try {
            boolean isUpdated = userListModel.updateUser(userListDto);
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
            String userId = lblUserId.getText();
            try {
                boolean isDeleted = userListModel.deleteUser(userId);
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
        String nextId = userListModel.getNextCustomerId();
        lblUserId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        UserListTM selectedItem = tblUserList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblUserId.setText(selectedItem.getUserId());
            txtName.setText(selectedItem.getName());
            txtPhone.setText(selectedItem.getPhone());
            txtEmail.setText(selectedItem.getEmail());
            txtUserName.setText(selectedItem.getUserName());
            txtPassword.setText(selectedItem.getPassword());
            txtUserTypeId.setText(selectedItem.getUserTypeId());
            txtRegistrationDate.setText(selectedItem.getRegistrationDate());
            txtStatus.setText(selectedItem.getStatus());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}