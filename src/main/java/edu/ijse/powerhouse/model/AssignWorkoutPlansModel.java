package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.AssignWorkoutPlansDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssignWorkoutPlansModel {

    public boolean saveAssignWorkout(AssignWorkoutPlansDto assignWorkoutPlansDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Member_Workout_Plan (member_id, workout_plan_id, assigned_date, end_date, progress, notes, assigned_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                assignWorkoutPlansDto.getMember_id(),
                assignWorkoutPlansDto.getWorkout_plan_id(),
                assignWorkoutPlansDto.getAssigned_date(),
                assignWorkoutPlansDto.getEnd_date(),
                assignWorkoutPlansDto.getProgress(),
                assignWorkoutPlansDto.getNotes(),
                assignWorkoutPlansDto.getAssigned_by());
    }

    public boolean updateAssignWorkout(AssignWorkoutPlansDto assignWorkoutPlansDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Member_Workout_Plan SET workout_plan_id = ?, assigned_date = ?, end_date = ?, progress = ?, notes = ?, assigned_by = ? WHERE member_id = ?",
                assignWorkoutPlansDto.getWorkout_plan_id(),
                assignWorkoutPlansDto.getAssigned_date(),
                assignWorkoutPlansDto.getEnd_date(),
                assignWorkoutPlansDto.getProgress(),
                assignWorkoutPlansDto.getNotes(),
                assignWorkoutPlansDto.getAssigned_by(),
                assignWorkoutPlansDto.getMember_id());
    }

    public boolean deleteAssignWorkout(String member_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Member_Workout_Plan WHERE member_id = ?", member_id);
    }

    public ArrayList<AssignWorkoutPlansDto> getAllAssignWorkouts() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Member_Workout_Plan");
        ArrayList<AssignWorkoutPlansDto> assignWorkout = new ArrayList<>();

        while (resultSet.next()){
            AssignWorkoutPlansDto assignWorkoutPlansDto = new AssignWorkoutPlansDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            assignWorkout.add(assignWorkoutPlansDto);
        }
        return assignWorkout;
    }

    public String getNextWorkoutPlanId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT workout_plan_id FROM Member_Workout_Plan ORDER BY workout_plan_id DESC LIMIT 1");
        String tableCharacter = "WP";

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
