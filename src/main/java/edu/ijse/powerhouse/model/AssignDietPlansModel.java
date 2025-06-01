package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.AssignDietPlansDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssignDietPlansModel {

    public boolean saveAssignDiet(AssignDietPlansDto assignDietPlansDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Member_Diet_Plan (member_id, diet_plan_id, assigned_date, end_date, notes, assigned_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                assignDietPlansDto.getMember_id(),
                assignDietPlansDto.getDiet_plan_id(),
                assignDietPlansDto.getAssigned_date(),
                assignDietPlansDto.getEnd_date(),
                assignDietPlansDto.getNotes(),
                assignDietPlansDto.getAssigned_by());
    }

    public boolean updateAssignDiet(AssignDietPlansDto assignDietPlansDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Member_Diet_Plan SET diet_plan_id = ?, assigned_date = ?, end_date = ?, notes = ?, assigned_by = ? WHERE member_id = ?",
                assignDietPlansDto.getDiet_plan_id(),
                assignDietPlansDto.getAssigned_date(),
                assignDietPlansDto.getEnd_date(),
                assignDietPlansDto.getNotes(),
                assignDietPlansDto.getAssigned_by(),
                assignDietPlansDto.getMember_id());
    }

    public boolean deleteAssignDiet(String member_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Member_Diet_Plan WHERE member_id = ?", member_id);
    }

    public ArrayList<AssignDietPlansDto> getAllAssignDiet() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Member_Diet_Plan");
        ArrayList<AssignDietPlansDto> assignDiet = new ArrayList<>();

        while (resultSet.next()){
            AssignDietPlansDto assignDietPlansDto = new AssignDietPlansDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            assignDiet.add(assignDietPlansDto);
        }
        return assignDiet;
    }

    public String getNextDietPlanId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT diet_plan_id FROM Member_Diet_Plan ORDER BY diet_plan_id DESC LIMIT 1");
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
