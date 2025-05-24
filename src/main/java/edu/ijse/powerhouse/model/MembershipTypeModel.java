package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.MembershipTypeDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipTypeModel {

    public boolean saveMembershipType(MembershipTypeDto membershipTypeDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Membership_Type (membership_type_id, name, description, duration, price, features, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                membershipTypeDto.getMembership_type_id(),
                membershipTypeDto.getName(),
                membershipTypeDto.getDescription(),
                membershipTypeDto.getDuration(),
                membershipTypeDto.getPrice(),
                membershipTypeDto.getFeatures(),
                membershipTypeDto.getStatus());
    }

    public boolean updateMembershipType(MembershipTypeDto membershipTypeDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Membership_Type SET name = ?, description = ?, duration = ?, price = ?, features = ?, status = ? WHERE membership_type_id = ?",
                membershipTypeDto.getName(),
                membershipTypeDto.getDescription(),
                membershipTypeDto.getDuration(),
                membershipTypeDto.getPrice(),
                membershipTypeDto.getFeatures(),
                membershipTypeDto.getStatus(),
                membershipTypeDto.getMembership_type_id());

    }

    public boolean deleteMembershipType(String membershipTypeId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Membership_Type WHERE membership_type_id = ?", membershipTypeId);
    }

    public ArrayList<MembershipTypeDto> getAllMembershipTypes() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Membership_Type");
        ArrayList<MembershipTypeDto> membershipTypes = new ArrayList<>();

        while (resultSet.next()){
            MembershipTypeDto membershipTypeDto = new MembershipTypeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            membershipTypes.add(membershipTypeDto);
        }
        return membershipTypes;
    }

    public String getNextMembershipTypeId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT membership_type_id FROM Membership_Type ORDER BY membership_type_id DESC LIMIT 1");
        String  tableCharacter = "MT";

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring( 2);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);

            return nextIdString;
        }
        return tableCharacter+ "1";
    }
}
