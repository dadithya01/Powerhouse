package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.AttendanceDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceModel {

    public boolean saveAttendance(AttendanceDto attendanceDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Attendance (attendance_id, member_id, check_in, check_out, recorded_by) VALUES  (?, ?, ?, ?, ?)",
                attendanceDto.getAttendance_id(),
                attendanceDto.getMember_id(),
                attendanceDto.getCheck_in(),
                attendanceDto.getCheck_out(),
                attendanceDto.getRecorded_by());
    }

    public boolean updateAttendance(AttendanceDto attendanceDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Attendance SET member_id = ?, check_in = ?, check_out = ?, recorded_by = ? WHERE attendance_id = ?",
                attendanceDto.getMember_id(),
                attendanceDto.getCheck_in(),
                attendanceDto.getCheck_out(),
                attendanceDto.getRecorded_by(),
                attendanceDto.getAttendance_id());

    }

    public boolean deleteAttendance(String attendanceId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Attendance WHERE attendance_id = ?", attendanceId);
    }

    public ArrayList<AttendanceDto> getAllAttendance() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Attendance");
        ArrayList<AttendanceDto> attendance = new ArrayList<>();

        while (resultSet.next()){
            AttendanceDto attendanceDto = new AttendanceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            attendance.add(attendanceDto);
        }
        return attendance;
    }

    public String getNextAttendanceId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT attendance_id FROM Attendance ORDER BY attendance_id DESC LIMIT 1");
        char tableCharacter = 'A';

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
