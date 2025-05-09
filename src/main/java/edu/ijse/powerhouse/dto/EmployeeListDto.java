package edu.ijse.powerhouse.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class EmployeeListDto {
    private String employee_id;
    private String name;
    private String contact;
    private String address;
    private int age;
    private Date hire_date;
    private String position;
    private BigDecimal salary;
    private String emergency_contact;

}
