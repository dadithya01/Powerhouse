package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.DietPlanDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DietPlanModel {

    public boolean saveDietPlan(DietPlanDto dietPlanDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Diet_Plan (diet_plan_id, name, description, created_by, created_date, calorie_target, protein_target, carbs_target, fat_target, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                dietPlanDto.getDiet_plan_id(),
                dietPlanDto.getName(),
                dietPlanDto.getDescription(),
                dietPlanDto.getCreated_by(),
                dietPlanDto.getCreated_date(),
                dietPlanDto.getCalorie_target(),
                dietPlanDto.getProtein_target(),
                dietPlanDto.getCarbs_target(),
                dietPlanDto.getFat_target(),
                dietPlanDto.getNotes());
    }

    public boolean updateDietPlan(DietPlanDto dietPlanDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE diet_plan_id SET name = ?, description = ?, created_by = ?, created_date = ?, calorie_target = ?, protein_target = ?, carbs_target = ?, fat_target = ?, notes = ? WHERE diet_plan_id = ?",
                dietPlanDto.getName(),
                dietPlanDto.getDescription(),
                dietPlanDto.getCreated_by(),
                dietPlanDto.getCreated_date(),
                dietPlanDto.getCalorie_target(),
                dietPlanDto.getProtein_target(),
                dietPlanDto.getCarbs_target(),
                dietPlanDto.getFat_target(),
                dietPlanDto.getNotes(),
                dietPlanDto.getDiet_plan_id());
    }

    public boolean deleteDietPlan(String userId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Diet_Plan WHERE diet_plan_id = ?", userId);
    }

    public ArrayList<DietPlanDto> getAllDietPlans() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Diet_Plan");
        ArrayList<DietPlanDto> dietPlan = new ArrayList<>();

        while (resultSet.next()){
            DietPlanDto dietPlanDto = new DietPlanDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            );
            dietPlan.add(dietPlanDto);
        }
        return dietPlan;
    }

    public String getNextDietPlanId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT diet_plan_id FROM Diet_Plan ORDER BY diet_plan_id DESC LIMIT 1");
        String tableCharacter = "DP";

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
