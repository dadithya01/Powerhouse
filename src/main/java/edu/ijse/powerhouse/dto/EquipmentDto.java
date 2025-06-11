package edu.ijse.powerhouse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EquipmentDto {

    private String equipment_id;
    private String name;
    private String description;
    private String purchase_date;
    private Double cost;
    private int quantity;
    private String maintenance_schedule;
    private String last_maintenance_date;
    private String status;
    private String added_by;
}
