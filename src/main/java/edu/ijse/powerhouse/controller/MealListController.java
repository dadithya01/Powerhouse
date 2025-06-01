package edu.ijse.powerhouse.controller;

import edu.ijse.powerhouse.dto.MealListDto;
import edu.ijse.powerhouse.dto.tm.MealListTM;
import edu.ijse.powerhouse.model.MealListModel;
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

public class MealListController implements Initializable {

    public Label lblMealId;
    public TextField txtDietPlanId;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtMealTime;
    public TextField txtCalories;
    public TextField txtProtein;
    public TextField txtCarbs;
    public TextField txtFat;
    public TextField txtRecipe;

    public TableView<MealListTM> tblMealList;
    public TableColumn<MealListTM, String> colMId;
    public TableColumn<MealListTM, String> colDPId;
    public TableColumn<MealListTM, String> colName;
    public TableColumn<MealListTM, String> colDescription;
    public TableColumn<MealListTM, String> colMealTime;
    public TableColumn<MealListTM, Integer> colCalories;
    public TableColumn<MealListTM, Integer> colProtein;
    public TableColumn<MealListTM, Integer> colCarbs;
    public TableColumn<MealListTM, Integer> colFat;
    public TableColumn<MealListTM, String> colRecipe;

    private final MealListModel mealListModel = new MealListModel();
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colMId.setCellValueFactory(new PropertyValueFactory<>("meal_id"));
        colDPId.setCellValueFactory(new PropertyValueFactory<>("diet_plan_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colMealTime.setCellValueFactory(new PropertyValueFactory<>("meal_time"));
        colCalories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        colProtein.setCellValueFactory(new PropertyValueFactory<>("protein"));
        colCarbs.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        colFat.setCellValueFactory(new PropertyValueFactory<>("fat"));
        colRecipe.setCellValueFactory(new PropertyValueFactory<>("recipe"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblMealList.setItems(FXCollections.observableArrayList(
                mealListModel.getAllMeal()
                        .stream()
                        .map(mealListDto -> new MealListTM(
                                mealListDto.getMeal_id(),
                                mealListDto.getDiet_plan_id(),
                                mealListDto.getName(),
                                mealListDto.getDescription(),
                                mealListDto.getMeal_time(),
                                mealListDto.getCalories(),
                                mealListDto.getProtein(),
                                mealListDto.getCarbs(),
                                mealListDto.getFat(),
                                mealListDto.getRecipe()
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

            txtDietPlanId.setText(null);
            txtName.setText(null);
            txtDescription.setText(null);
            txtMealTime.setText(null);
            txtCalories.setText(null);
            txtProtein.setText(null);
            txtCarbs.setText(null);
            txtFat.setText(null);
            txtRecipe.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String mealId = lblMealId.getText();
        String dietPlanId = txtDietPlanId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String mealTime = txtMealTime.getText();
        int calories = Integer.parseInt(txtCalories.getText());
        int protein = Integer.parseInt(txtProtein.getText());
        int carbs = Integer.parseInt(txtCarbs.getText());
        int fat = Integer.parseInt(txtFat.getText());
        String recipe = txtRecipe.getText();


        MealListDto mealListDto = new MealListDto(
                mealId,
                dietPlanId,
                name,
                description,
                mealTime,
                calories,
                protein,
                carbs,
                fat,
                recipe
        );
        try {
            boolean isSaved = mealListModel.saveMeal(mealListDto);

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
        String mealId = lblMealId.getText();
        String dietPlanId = txtDietPlanId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        String mealTime = txtMealTime.getText();
        int calories = Integer.parseInt(txtCalories.getText());
        int protein = Integer.parseInt(txtProtein.getText());
        int carbs = Integer.parseInt(txtCarbs.getText());
        int fat = Integer.parseInt(txtFat.getText());
        String recipe = txtRecipe.getText();


        MealListDto mealListDto = new MealListDto(
                mealId,
                dietPlanId,
                name,
                description,
                mealTime,
                calories,
                protein,
                carbs,
                fat,
                recipe
        );

        try {
            boolean isUpdated = mealListModel.updateMeal(mealListDto);
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
            String mealId = lblMealId.getText();
            try {
                boolean isDeleted = mealListModel.deleteMeal(mealId);
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
        String nextId = mealListModel.getNextMealId();
        lblMealId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        MealListTM selectedItem = tblMealList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblMealId.setText(selectedItem.getMeal_id());
            txtDietPlanId.setText(selectedItem.getDiet_plan_id());
            txtName.setText(selectedItem.getName());
            txtDescription.setText(selectedItem.getDescription());
            txtMealTime.setText(selectedItem.getMeal_time());
            txtCalories.setText(String.valueOf(selectedItem.getCalories()));
            txtProtein.setText(String.valueOf(selectedItem.getProtein()));
            txtCarbs.setText(String.valueOf(selectedItem.getCarbs()));
            txtFat.setText(String.valueOf(selectedItem.getFat()));
            txtRecipe.setText(selectedItem.getRecipe());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }
}
