package edu.ijse.powerhouse.model;

import edu.ijse.powerhouse.dto.EquipmentDto;
import edu.ijse.powerhouse.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquipmentModel {

    public boolean saveEquipment(EquipmentDto equipmentDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Equipment (equipment_id, name, description, purchase_date, cost,quantity, maintenance_schedule, last_maintenance_date, status, added_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                equipmentDto.getEquipment_id(),
                equipmentDto.getName(),
                equipmentDto.getDescription(),
                equipmentDto.getPurchase_date(),
                equipmentDto.getCost(),
                equipmentDto.getQuantity(),
                equipmentDto.getMaintenance_schedule(),
                equipmentDto.getLast_maintenance_date(),
                equipmentDto.getStatus(),
                equipmentDto.getAdded_by());
    }

    public boolean updateEquipment(EquipmentDto equipmentDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Equipment SET name = ?, description = ?, purchase_date = ?, cost = ?,quantity=?, maintenance_schedule = ?, last_maintenance_date = ?, status = ?, added_by = ? WHERE equipment_id = ?",
                equipmentDto.getName(),
                equipmentDto.getDescription(),
                equipmentDto.getPurchase_date(),
                equipmentDto.getCost(),
                equipmentDto.getQuantity(),
                equipmentDto.getMaintenance_schedule(),
                equipmentDto.getLast_maintenance_date(),
                equipmentDto.getStatus(),
                equipmentDto.getAdded_by(),
                equipmentDto.getEquipment_id());
    }

    public boolean deleteEquipment(String equipment_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Equipment WHERE equipment_id = ?", equipment_id);
    }

    public ArrayList<EquipmentDto> getAllEquipments() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Equipment");
        ArrayList<EquipmentDto> equipmentList = new ArrayList<>();

        while (resultSet.next()){
            EquipmentDto equipmentDto = new EquipmentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            );
            equipmentList.add(equipmentDto);
        }
        return equipmentList;
    }

    public String getNextEquipmentId() throws SQLException , ClassNotFoundException{
        ResultSet resultSet = CrudUtil.execute("SELECT equipment_id FROM Equipment ORDER BY equipment_id DESC LIMIT 1");
        String tableCharacter = "EQ";

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
