package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.ExercisesLibraryDto;
import edu.ijse.powerhouse.dto.tm.ExercisesLibraryTM;
import edu.ijse.powerhouse.model.ExercisesLibraryModel;
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

public class ExercisesLibraryController implements Initializable {

    public Label lblExerciseId;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtMuscleGroup;
    public TextField txtEquipmentNeeded;
    public TextField txtDifficultyLevel;
    public TextField txtVideoUrl;
    public TextField txtInstructions;
    public TextField txtAddedBy;

    public TableView<ExercisesLibraryTM> tblExerciseLibrary;
    public TableColumn<ExercisesLibraryTM, String> colId;
    public TableColumn<ExercisesLibraryTM, String> colName;
    public TableColumn<ExercisesLibraryTM, String> colDescription;
    public TableColumn<ExercisesLibraryTM, String> colMuscleGroup;
    public TableColumn<ExercisesLibraryTM, String> colEquipmentNeeded;
    public TableColumn<ExercisesLibraryTM, String> colDifficultyLevel;
    public TableColumn<ExercisesLibraryTM, String> colVideoUrl;
    public TableColumn<ExercisesLibraryTM, String> colInstructions;
    public TableColumn<ExercisesLibraryTM, String> colAddedBy;

    private final ExercisesLibraryModel employeeListModel = new ExercisesLibraryModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("exercise_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMuscleGroup.setCellValueFactory(new PropertyValueFactory<>("muscle_group"));
        colEquipmentNeeded.setCellValueFactory(new PropertyValueFactory<>("equipment_needed"));
        colDifficultyLevel.setCellValueFactory(new PropertyValueFactory<>("difficulty_level"));
        colVideoUrl.setCellValueFactory(new PropertyValueFactory<>("video_url"));
        colInstructions.setCellValueFactory(new PropertyValueFactory<>("instructions"));
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
        tblExerciseLibrary.setItems(FXCollections.observableArrayList(
                employeeListModel.getAllExercises()
                        .stream()
                        .map(exercisesLibraryDto -> new ExercisesLibraryTM(
                                exercisesLibraryDto.getExercise_id(),
                                exercisesLibraryDto.getName(),
                                exercisesLibraryDto.getDescription(),
                                exercisesLibraryDto.getMuscle_group(),
                                exercisesLibraryDto.getEquipment_needed(),
                                exercisesLibraryDto.getDifficulty_level(),
                                exercisesLibraryDto.getVideo_url(),
                                exercisesLibraryDto.getInstructions(),
                                exercisesLibraryDto.getAdded_by()
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
            txtMuscleGroup.setText(null);
            txtEquipmentNeeded.setText(null);
            txtDifficultyLevel.setText(null);
            txtVideoUrl.setText(null);
            txtInstructions.setText(null);
            txtAddedBy.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String exerciseId = lblExerciseId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String muscleGroup = txtMuscleGroup.getText();
        String equipmentNeeded = txtEquipmentNeeded.getText();
        String difficultyLevel = txtDifficultyLevel.getText();
        String videoUrl = txtVideoUrl.getText();
        String instructions = txtInstructions.getText();
        String addedBy = txtAddedBy.getText();

        ExercisesLibraryDto exercisesLibraryDto = new ExercisesLibraryDto(
                exerciseId,
                name,
                description,
                muscleGroup,
                equipmentNeeded,
                difficultyLevel,
                videoUrl,
                instructions,
                addedBy
        );
        try {
            boolean isSaved = employeeListModel.saveExercise(exercisesLibraryDto);

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
        String exerciseId = lblExerciseId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String muscleGroup = txtMuscleGroup.getText();
        String equipmentNeeded = txtEquipmentNeeded.getText();
        String difficultyLevel = txtDifficultyLevel.getText();
        String videoUrl = txtVideoUrl.getText();
        String instructions = txtInstructions.getText();
        String addedBy = txtAddedBy.getText();

        ExercisesLibraryDto exercisesLibraryDto = new ExercisesLibraryDto(
                exerciseId,
                name,
                description,
                muscleGroup,
                equipmentNeeded,
                difficultyLevel,
                videoUrl,
                instructions,
                addedBy
        );

        try {
            boolean isUpdated = employeeListModel.updateExercise(exercisesLibraryDto);
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
            String exerciseId = lblExerciseId.getText();
            try {
                boolean isDeleted = employeeListModel.deleteExercise(exerciseId);
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
        String nextId = employeeListModel.getNextUserId();
        lblExerciseId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        ExercisesLibraryTM selectedItem = tblExerciseLibrary.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblExerciseId.setText(selectedItem.getExercise_id());
            txtName.setText(selectedItem.getName());
            txtDescription.setText(selectedItem.getDescription());
            txtMuscleGroup.setText(selectedItem.getMuscle_group());
            txtEquipmentNeeded.setText(selectedItem.getEquipment_needed());
            txtDifficultyLevel.setText(selectedItem.getDifficulty_level());
            txtVideoUrl.setText(selectedItem.getVideo_url());
            txtInstructions.setText(selectedItem.getInstructions());
            txtAddedBy.setText(selectedItem.getAdded_by());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
