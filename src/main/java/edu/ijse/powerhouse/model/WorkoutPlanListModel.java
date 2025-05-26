package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.WorkoutPlanListDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkoutPlanListModel {

    public boolean saveWorkoutPlan(WorkoutPlanListDto workoutPlanListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Workout_Plan (workout_plan_id, name, description, difficulty_level, created_by, created_date, duration_weeks) VALUES (?, ?, ?, ?, ?, ?, ?)",
                workoutPlanListDto.getWorkout_plan_id(),
                workoutPlanListDto.getName(),
                workoutPlanListDto.getDescription(),
                workoutPlanListDto.getDifficulty_level(),
                workoutPlanListDto.getCreated_by(),
                workoutPlanListDto.getCreated_date(),
                workoutPlanListDto.getDuration_weeks());
    }

    public boolean updateWorkoutPlan(WorkoutPlanListDto workoutPlanListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Workout_Plan SET name = ?, description = ?, difficulty_level = ?, created_by = ?, created_date = ?, duration_weeks = ? WHERE workout_plan_id = ?",
                workoutPlanListDto.getName(),
                workoutPlanListDto.getDescription(),
                workoutPlanListDto.getDifficulty_level(),
                workoutPlanListDto.getCreated_by(),
                workoutPlanListDto.getCreated_date(),
                workoutPlanListDto.getDuration_weeks(),
                workoutPlanListDto.getWorkout_plan_id());
    }

    public boolean deleteWorkoutPlan(String workoutPlanId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Workout_Plan WHERE workout_plan_id = ?", workoutPlanId);
    }

    public ArrayList<WorkoutPlanListDto> getAllWorkoutPlans() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Workout_Plan");
        ArrayList<WorkoutPlanListDto> workoutPlan = new ArrayList<>();

        while (resultSet.next()){
            WorkoutPlanListDto workoutPlanListDto = new WorkoutPlanListDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            workoutPlan.add(workoutPlanListDto);
        }
        return workoutPlan;
    }

    public String getNextWorkoutPlanId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT workout_plan_id FROM Workout_Plan ORDER BY workout_plan_id DESC LIMIT 1");
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
