package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.UserTypeDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserTypeModel {

    public boolean saveUserType(UserTypeDto userTypeDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO User_Types (user_type_id, type) VALUES (?, ?, ?, ?, ?)",
                userTypeDto.getUser_Type_Id(),
                userTypeDto.getType());
    }

    public boolean updateUserType(UserTypeDto userTypeDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE User_Types SET type = ? WHERE user_type_id = ?",
                userTypeDto.getType(),
                userTypeDto.getUser_Type_Id());
    }

    public boolean deleteUserType(String userTypeId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" Delete From User_Types where user_type_id = ? ", userTypeId);
    }

    public ArrayList<UserTypeDto> getAllUserTypes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM User_Types");
        ArrayList<UserTypeDto> userType = new ArrayList<>();

        while (resultSet.next()){
            UserTypeDto userTypeDto = new UserTypeDto(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
            userType.add(userTypeDto);
        }
        return userType;
    }

    public String getNextUserTypeId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT user_type_id FROM User_Types ORDER BY user_type_id DESC LIMIT 1");
        String tableCharacter = "UT";

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
