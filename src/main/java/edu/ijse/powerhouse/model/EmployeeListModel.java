package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.EmployeeListDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeListModel {

    public boolean saveEmployee(EmployeeListDto employeeListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Employee (employee_id, name, contact, address, age, hire_date, position, salary, emergency_contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                employeeListDto.getEmployee_id(),
                employeeListDto.getName(),
                employeeListDto.getContact(),
                employeeListDto.getAddress(),
                employeeListDto.getAge(),
                employeeListDto.getHire_date(),
                employeeListDto.getPosition(),
                employeeListDto.getSalary(),
                employeeListDto.getEmergency_contact());
    }

    public boolean updateEmployee(EmployeeListDto employeeListDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Employee SET name = ?, contact = ?, address = ?, age = ?, hire_date = ?, position = ?, salary = ?, emergency_contact = ? WHERE employee_id = ?",
                employeeListDto.getName(),
                employeeListDto.getContact(),
                employeeListDto.getAddress(),
                employeeListDto.getAge(),
                employeeListDto.getHire_date(),
                employeeListDto.getPosition(),
                employeeListDto.getSalary(),
                employeeListDto.getEmergency_contact(),
                employeeListDto.getEmployee_id());
    }

    public boolean deleteEmployee(String employee_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Employee WHERE employee_id = ?", employee_id);
    }

    public ArrayList<EmployeeListDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Employee");
        ArrayList<EmployeeListDto> employeeList = new ArrayList<>();

        while (resultSet.next()){
            EmployeeListDto employeeListDto = new EmployeeListDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getDouble(8),
                    resultSet.getString(9)
            );
            employeeList.add(employeeListDto);
        }
        return employeeList;
    }

    public String getNextEmployeeId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT employee_id FROM Employee ORDER BY employee_id DESC LIMIT 1");
        char tableCharacter = 'E';

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
