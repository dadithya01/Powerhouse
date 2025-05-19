package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.TrainerListDto;
import edu.ijse.powerhouse.dto.tm.TrainerListTM;
import edu.ijse.powerhouse.model.TrainerListModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class TrainerListController implements Initializable {

    public Label lblTrainerId;
    public TextField txtUserId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtSpecialization;
    public TextField txtCertification;
    public TextField txtHireDate;
    public TextField txtBio;
    public TextField txtRating;

    public TableView<TrainerListTM> tblTrainerList;
    public TableColumn<TrainerListTM, String> colTId;
    public TableColumn<TrainerListTM, String> colUId;
    public TableColumn<TrainerListTM, String> colName;
    public TableColumn<TrainerListTM, String> colPhone;
    public TableColumn<TrainerListTM, String> colAddress;
    public TableColumn<TrainerListTM, Integer> colAge;
    public TableColumn<TrainerListTM, String> colSpecialization;
    public TableColumn<TrainerListTM, String> colCertification;
    public TableColumn<TrainerListTM, String> colHireDate;
    public TableColumn<TrainerListTM, String> colBio;
    public TableColumn<TrainerListTM, Double> colRating;

    private final TrainerListModel trainerListModel = new TrainerListModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTId.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
        colUId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        colCertification.setCellValueFactory(new PropertyValueFactory<>("certification"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        colBio.setCellValueFactory(new PropertyValueFactory<>("bio"));
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblTrainerList.setItems(FXCollections.observableArrayList(
                trainerListModel.getAllTrainers()
                        .stream()
                        .map(trainerListDto -> new TrainerListTM(
                                trainerListDto.getTrainer_id(),
                                trainerListDto.getUser_id(),
                                trainerListDto.getName(),
                                trainerListDto.getContact(),
                                trainerListDto.getAddress(),
                                trainerListDto.getAge(),
                                trainerListDto.getSpecialization(),
                                trainerListDto.getCertification(),
                                trainerListDto.getHire_date(),
                                trainerListDto.getBio(),
                                trainerListDto.getRating()
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

            txtUserId.setText(null);
            txtName.setText(null);
            txtPhone.setText(null);
            txtAddress.setText(null);
            txtAge.setText(null);
            txtSpecialization.setText(null);
            txtCertification.setText(null);
            txtHireDate.setText(null);
            txtBio.setText(null);
            txtRating.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String trainerId = lblTrainerId.getText();
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String contact = txtPhone.getText();
        String address = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String specialization = txtSpecialization.getText();
        String certification = txtCertification.getText();
        String hireDate = txtHireDate.getText();
        String bio = txtBio.getText();
        Double rating = Double.valueOf(txtRating.getText());


        TrainerListDto trainerListDto = new TrainerListDto(
                trainerId,
                userId,
                name,
                contact,
                address,
                age,
                specialization,
                certification,
                hireDate,
                bio,
                rating
        );
        try {
            boolean isSaved = trainerListModel.saveTrainer(trainerListDto);

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
        String trainerId = lblTrainerId.getText();
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String contact = txtPhone.getText();
        String address = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String specialization = txtSpecialization.getText();
        String certification = txtCertification.getText();
        String hireDate = txtHireDate.getText();
        String bio = txtBio.getText();
        Double rating = Double.valueOf(txtRating.getText());

        TrainerListDto trainerListDto = new TrainerListDto(
                trainerId,
                userId,
                name,
                contact,
                address,
                age,
                specialization,
                certification,
                hireDate,
                bio,
                rating
        );
        try {
            boolean isUpdated = trainerListModel.updateTrainer(trainerListDto);
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
            String trainerId = lblTrainerId.getText();
            try {
                boolean isDeleted = trainerListModel.deleteTrainer(trainerId);
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
        String nextId = trainerListModel.getNextTrainerId();
        lblTrainerId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        TrainerListTM selectedItem = tblTrainerList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblTrainerId.setText(selectedItem.getTrainer_id());
            txtUserId.setText(selectedItem.getUser_id());
            txtName.setText(selectedItem.getName());
            txtPhone.setText(selectedItem.getContact());
            txtAddress.setText(selectedItem.getAddress());
            txtAge.setText(String.valueOf(selectedItem.getAge()));
            txtSpecialization.setText(selectedItem.getSpecialization());
            txtCertification.setText(selectedItem.getCertification());
            txtHireDate.setText(selectedItem.getHire_date());
            txtBio.setText(selectedItem.getBio());
            txtRating.setText(String.valueOf(selectedItem.getRating()));

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
