package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.db.DBConnection;
import edu.ijse.powerhouse.dto.UserListDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserListModel {

    public boolean isDuplicateUser(String email, String userName, String phone) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM Users WHERE email = ? OR Username = ? OR phone = ?",
                email, userName, phone
        );
        return rs.next(); // If there’s at least one result, it's a duplicate
    }

    public boolean isDuplicateUserForUpdate(String userId, String email, String userName, String phone) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM Users WHERE (email = ? OR Username = ? OR phone = ?) AND user_Id != ?",
                email, userName, phone, userId
        );
        return rs.next();
    }

    public boolean saveUser(UserListDto userListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Users (user_Id, name, phone, email, Username, Password, user_Type_Id, registration_Date, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                userListDto.getUserId(),
                userListDto.getName(),
                userListDto.getPhone(),
                userListDto.getEmail(),
                userListDto.getUserName(),
                userListDto.getPassword(),
                userListDto.getUserTypeId(),
                userListDto.getRegistrationDate(),
                userListDto.getStatus());
    }

    public boolean updateUser(UserListDto userListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Users SET name = ?, phone = ?, email = ?, Username = ?, Password = ?, user_Type_Id = ?, registration_Date = ?, Status = ? WHERE user_Id = ?",
                userListDto.getName(),
                userListDto.getPhone(),
                userListDto.getEmail(),
                userListDto.getUserName(),
                userListDto.getPassword(),
                userListDto.getUserTypeId(),
                userListDto.getRegistrationDate(),
                userListDto.getStatus(),
                userListDto.getUserId());
    }

    public boolean deleteUser(String userId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Users WHERE user_Id = ?", userId);
    }

    public ArrayList<UserListDto> getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Users");
        ArrayList<UserListDto> userList = new ArrayList<>();

        while (resultSet.next()){
            UserListDto userListDto = new UserListDto(
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
            userList.add(userListDto);
        }
        return userList;
    }

    public String getNextUserId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT user_id FROM Users ORDER BY user_id DESC LIMIT 1");
        char tableCharacter = 'U';

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
