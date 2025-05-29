package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.ExercisesLibraryDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExercisesLibraryModel {

    public boolean saveExercise(ExercisesLibraryDto exercisesLibraryDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Exercise (exercise_id, name, description, muscle_group, equipment_needed, difficulty_level, video_url, instructions, added_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                exercisesLibraryDto.getExercise_id(),
                exercisesLibraryDto.getName(),
                exercisesLibraryDto.getDescription(),
                exercisesLibraryDto.getMuscle_group(),
                exercisesLibraryDto.getEquipment_needed(),
                exercisesLibraryDto.getDifficulty_level(),
                exercisesLibraryDto.getVideo_url(),
                exercisesLibraryDto.getInstructions(),
                exercisesLibraryDto.getAdded_by());
    }

    public boolean updateExercise(ExercisesLibraryDto exercisesLibraryDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Exercise SET name = ?, description = ?, muscle_group = ?, equipment_needed = ?, difficulty_level = ?, video_url = ?, instructions = ?, added_by = ? WHERE exercise_id = ?",
                exercisesLibraryDto.getName(),
                exercisesLibraryDto.getDescription(),
                exercisesLibraryDto.getMuscle_group(),
                exercisesLibraryDto.getEquipment_needed(),
                exercisesLibraryDto.getDifficulty_level(),
                exercisesLibraryDto.getVideo_url(),
                exercisesLibraryDto.getInstructions(),
                exercisesLibraryDto.getAdded_by(),
                exercisesLibraryDto.getExercise_id());
    }

    public boolean deleteExercise(String exercise_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Exercise WHERE exercise_id = ?", exercise_id);
    }

    public ArrayList<ExercisesLibraryDto> getAllExercises() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Exercise");
        ArrayList<ExercisesLibraryDto> exercisesLibrary = new ArrayList<>();

        while (resultSet.next()){
            ExercisesLibraryDto exercisesLibraryDto = new ExercisesLibraryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
            exercisesLibrary.add(exercisesLibraryDto);
        }
        return exercisesLibrary;
    }

    public String getNextUserId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT exercise_id FROM Exercise ORDER BY exercise_id DESC LIMIT 1");
        String  tableCharacter = "EX";

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
