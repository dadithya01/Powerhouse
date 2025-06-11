package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.UserTypeDto;
import edu.ijse.powerhouse.dto.tm.UserListTM;
import edu.ijse.powerhouse.dto.tm.UserTypeTM;
import edu.ijse.powerhouse.model.UserListModel;
import edu.ijse.powerhouse.model.UserTypeModel;
import edu.ijse.powerhouse.util.Animations;
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

public class UserTypesController implements Initializable {

    public Label lblUserTypeId;
    public TextField txtType;
    public Label lblMain;

    public TableView<UserTypeTM> tblUserTypes;
    public TableColumn<UserTypeTM, String> colUserTypeId;
    public TableColumn<UserTypeTM, String> colType;

    private final UserTypeModel userTypeModel = new UserTypeModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colUserTypeId.setCellValueFactory(new PropertyValueFactory<>("user_Type_Id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        Animations.AnimateLabelSlideIn(lblMain);
        Animations.AddFancyHoverAnimation(btnSave, "#27ae60", "#353b48");
        Animations.AddFancyHoverAnimation(btnUpdate, "#2980b9", "#353b48");
        Animations.AddFancyHoverAnimation(btnDelete, "#e74c3c", "#353b48");
        Animations.AddFancyHoverAnimation(btnClear, "#130f40", "#353b48");

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblUserTypes.setItems(FXCollections.observableArrayList(
                userTypeModel.getAllUserTypes()
                        .stream()
                        .map(userTypeDto -> new UserTypeTM(
                                userTypeDto.getUser_Type_Id(),
                                userTypeDto.getType()
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

            txtType.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private boolean isValidInput() {
        String type = txtType.getText();

        if (type == null || type.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "User type cannot be empty!").show();
            return false;
        }

        if (!type.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.WARNING, "User type must contain only letters and spaces!").show();
            return false;
        }

        if (type.length() > 30) {
            new Alert(Alert.AlertType.WARNING, "User type is too long!").show();
            return false;
        }

        return true;
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (!isValidInput()) return;

        String userTypeId = lblUserTypeId.getText();
        String type = txtType.getText();


        UserTypeDto userTypeDto = new UserTypeDto(
                userTypeId,
                type
        );
        try {
            boolean isSaved = userTypeModel.saveUserType(userTypeDto);

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

        String userTypeId = lblUserTypeId.getText();
        String type = txtType.getText();

        UserTypeDto userTypeDto = new UserTypeDto(
                userTypeId,
                type
        );
        try {
            boolean isUpdated = userTypeModel.updateUserType(userTypeDto);
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
            String userTypeId = lblUserTypeId.getText();
            try {
                boolean isDeleted = userTypeModel.deleteUserType(userTypeId);
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
        String nextId = userTypeModel.getNextUserTypeId();
        lblUserTypeId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        UserTypeTM selectedItem = tblUserTypes.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblUserTypeId.setText(selectedItem.getUser_Type_Id());
            txtType.setText(selectedItem.getType());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
