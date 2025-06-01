package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.MealListDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MealListModel {

    public boolean saveMeal(MealListDto mealListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Meal (meal_id, diet_plan_id, name, description, meal_time, calories, protein, carbs, fat, recipe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                mealListDto.getMeal_id(),
                mealListDto.getDiet_plan_id(),
                mealListDto.getName(),
                mealListDto.getDescription(),
                mealListDto.getMeal_time(),
                mealListDto.getCalories(),
                mealListDto.getProtein(),
                mealListDto.getCarbs(),
                mealListDto.getFat(),
                mealListDto.getRecipe());
    }

    public boolean updateMeal(MealListDto mealListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Meal SET diet_plan_id = ?, name = ?, description = ?, meal_time = ?, calories = ?, protein = ?, carbs = ?, fat = ?, recipe = ? WHERE meal_id = ?",
                mealListDto.getDiet_plan_id(),
                mealListDto.getName(),
                mealListDto.getDescription(),
                mealListDto.getMeal_time(),
                mealListDto.getCalories(),
                mealListDto.getProtein(),
                mealListDto.getCarbs(),
                mealListDto.getFat(),
                mealListDto.getRecipe(),
                mealListDto.getMeal_id());
    }

    public boolean deleteMeal(String userId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Meal WHERE meal_id = ?", userId);
    }

    public ArrayList<MealListDto> getAllMeal() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Meal");
        ArrayList<MealListDto> mealList = new ArrayList<>();

        while (resultSet.next()){
            MealListDto mealListDto = new MealListDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8),
                    resultSet.getInt(9),
                    resultSet.getString(10)
            );
            mealList.add(mealListDto);
        }
        return mealList;
    }

    public String getNextMealId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT meal_id FROM Meal ORDER BY meal_id DESC LIMIT 1");
        String tableCharacter = "ME";

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(2);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableCharacter+ "1";
    }
}
