package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.MembershipTypeDto;
import edu.ijse.powerhouse.dto.tm.MembershipTypeTM;
import edu.ijse.powerhouse.model.MembershipTypeModel;
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

public class MembershipTypeController implements Initializable {

    public Label lblMembershipTypeId;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtDuration;
    public TextField txtPrice;
    public TextField txtFeatures;
    public TextField txtStatus;

    public TableView<MembershipTypeTM> tblMembershipType;
    public TableColumn<MembershipTypeTM, String> colId;
    public TableColumn<MembershipTypeTM, String> colName;
    public TableColumn<MembershipTypeTM, String> colDescription;
    public TableColumn<MembershipTypeTM, String> colDuration;
    public TableColumn<MembershipTypeTM, Double> colPrice;
    public TableColumn<MembershipTypeTM, String> colFeatures;
    public TableColumn<MembershipTypeTM, String> colStatus;

    private final MembershipTypeModel membershipTypeModel = new MembershipTypeModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("membership_type_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colFeatures.setCellValueFactory(new PropertyValueFactory<>("features"));
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
        tblMembershipType.setItems(FXCollections.observableArrayList(
                membershipTypeModel.getAllMembershipTypes()
                        .stream()
                        .map(membershipTypeDto -> new MembershipTypeTM(
                                membershipTypeDto.getMembership_type_id(),
                                membershipTypeDto.getName(),
                                membershipTypeDto.getDescription(),
                                membershipTypeDto.getDuration(),
                                membershipTypeDto.getPrice(),
                                membershipTypeDto.getFeatures(),                                                                                                                                                                                                            
                                membershipTypeDto.getStatus()
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
            txtDuration.setText(null);
            txtPrice.setText(null);
            txtFeatures.setText(null);
            txtStatus.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private boolean validateMembershipForm() {
        String name = txtName.getText().trim();
        String description = txtDescription.getText().trim();
        String durationText = txtDuration.getText().trim();
        String priceText = txtPrice.getText().trim();
        String features = txtFeatures.getText().trim();
        String status = txtStatus.getText().trim();

        if (name.isEmpty() || description.isEmpty() || durationText.isEmpty() ||
                priceText.isEmpty() || features.isEmpty() || status.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields.").show();
            return false;
        }

        try {
            int duration = Integer.parseInt(durationText);
            if (duration <= 0) {
                new Alert(Alert.AlertType.WARNING, "Duration must be a positive number.").show();
                return false;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Invalid duration. Please enter a valid number.").show();
            return false;
        }

        try {
            double price = Double.parseDouble(priceText);
            if (price <= 0) {
                new Alert(Alert.AlertType.WARNING, "Price must be a positive number.").show();
                return false;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Invalid price. Please enter a valid number.").show();
            return false;
        }

        if (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Inactive")) {
            new Alert(Alert.AlertType.WARNING, "Status must be 'Active' or 'Inactive'.").show();
            return false;
        }

        return true;
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (!validateMembershipForm()) {
            return; // Exit early if validation fails
        }

        String membershipTypeId = lblMembershipTypeId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText();
        Double price = Double.valueOf(txtPrice.getText());
        String features = txtFeatures.getText();
        String status = txtStatus.getText();


        MembershipTypeDto membershipTypeDto = new MembershipTypeDto(
                membershipTypeId,
                name,
                description,
                duration,
                price,
                features,
                status
        );
        try {
            boolean isSaved = membershipTypeModel.saveMembershipType(membershipTypeDto);

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

        if (!validateMembershipForm()) {
            return; // Exit early if validation fails
        }

        String membershipTypeId = lblMembershipTypeId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText();
        Double price = Double.valueOf(txtPrice.getText());
        String features = txtFeatures.getText();
        String status = txtStatus.getText();

        MembershipTypeDto membershipTypeDto = new MembershipTypeDto(
                membershipTypeId,
                name,
                description,
                duration,
                price,
                features,
                status
        );
        try {
            boolean isUpdated = membershipTypeModel.updateMembershipType(membershipTypeDto);
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
            String membershipTypeId = lblMembershipTypeId.getText();
            try {
                boolean isDeleted = membershipTypeModel.deleteMembershipType(membershipTypeId);
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
        String nextId = membershipTypeModel.getNextMembershipTypeId();
        lblMembershipTypeId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        MembershipTypeTM selectedItem = tblMembershipType.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblMembershipTypeId.setText(selectedItem.getMembership_type_id());
            txtName.setText(selectedItem.getName());
            txtDescription.setText(selectedItem.getDescription());
            txtDuration.setText(selectedItem.getDuration());
            txtPrice.setText(String.valueOf(selectedItem.getPrice()));
            txtFeatures.setText(selectedItem.getFeatures());
            txtStatus.setText(selectedItem.getStatus());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
