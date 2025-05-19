package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.TrainerListDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrainerListModel {

    public boolean saveTrainer(TrainerListDto trainerListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Trainer (trainer_id, user_id, name, contact, address, age, specialization, certification, hire_date, bio, rating) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
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
                trainerListDto.getRating());
    }

    public boolean updateTrainer(TrainerListDto trainerListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Trainer SET user_id = ?, name = ?, contact = ?, address = ?, age = ?, specialization = ?, certification = ?, hire_date = ?, bio = ?, rating = ? WHERE trainer_id = ?",
                trainerListDto.getUser_id(),
                trainerListDto.getName(),
                trainerListDto.getContact(),
                trainerListDto.getAddress(),
                trainerListDto.getAge(),
                trainerListDto.getSpecialization(),
                trainerListDto.getCertification(),
                trainerListDto.getHire_date(),
                trainerListDto.getBio(),
                trainerListDto.getRating(),
                trainerListDto.getTrainer_id());

    }

    public boolean deleteTrainer(String trainer_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Trainer WHERE trainer_id = ?", trainer_id);
    }

    public ArrayList<TrainerListDto> getAllTrainers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Trainer");
        ArrayList<TrainerListDto> trainerList = new ArrayList<>();

        while (resultSet.next()){
            TrainerListDto trainerListDto = new TrainerListDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getDouble(11)
            );
            trainerList.add(trainerListDto);
        }
        return trainerList;
    }

    public String getNextTrainerId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT trainer_id FROM Trainer ORDER BY trainer_id DESC LIMIT 1");
        char tableCharacter = 'T';

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableCharacter+ "1";
    }
}
