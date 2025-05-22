package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.MemberListDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberListModel {
    public boolean saveMember(MemberListDto memberListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Member (member_id, name, weight, height, age, contact, emergency_contact, medical_conditions, fitness_goals, register_date, membership_status, added_by) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                memberListDto.getMember_id(),
                memberListDto.getName(),
                memberListDto.getWeight(),
                memberListDto.getHeight(),
                memberListDto.getAge(),
                memberListDto.getContact(),
                memberListDto.getEmergency_contact(),
                memberListDto.getMedical_conditions(),
                memberListDto.getFitness_goals(),
                memberListDto.getRegister_date(),
                memberListDto.getMembership_status(),
                memberListDto.getAdded_by());
    }

    public boolean updateMember(MemberListDto memberListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Member SET name = ?, weight = ?, height = ?, age = ?, contact = ?, emergency_contact = ?, medical_conditions = ?, fitness_goals = ?, register_date = ?, membership_status = ?, added_by = ? WHERE member_id = ?",
                memberListDto.getName(),
                memberListDto.getWeight(),
                memberListDto.getHeight(),
                memberListDto.getAge(),
                memberListDto.getContact(),
                memberListDto.getEmergency_contact(),
                memberListDto.getMedical_conditions(),
                memberListDto.getFitness_goals(),
                memberListDto.getRegister_date(),
                memberListDto.getMembership_status(),
                memberListDto.getAdded_by(),
                memberListDto.getMember_id());

    }

    public boolean deleteMember(String member_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Member WHERE member_id = ?", member_id);
    }

    public ArrayList<MemberListDto> getAllMembers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Member");
        ArrayList<MemberListDto> memberList = new ArrayList<>();

        while (resultSet.next()){
            MemberListDto memberListDto = new MemberListDto(
                    resultSet.getString(1)
                    ,resultSet.getString(2)
                    ,resultSet.getDouble(3)
                    ,resultSet.getDouble(4)
                    ,resultSet.getInt(5)
                    ,resultSet.getString(6)
                    ,resultSet.getString(7)
                    ,resultSet.getString(8)
                    ,resultSet.getString(9)
                    ,resultSet.getString(10)
                    ,resultSet.getString(11)
                    ,resultSet.getString(12)
            );
            memberList.add(memberListDto);
        }
        return memberList;
    }

    public String getNextMemberId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT member_id FROM Member ORDER BY member_id DESC LIMIT 1");
        char tableCharacter = 'M';

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
