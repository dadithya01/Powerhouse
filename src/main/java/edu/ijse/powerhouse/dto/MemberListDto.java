package edu.ijse.powerhouse.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberListDto {
    private String member_id;
    private String name;
    private BigDecimal weight;
    private BigDecimal height;
    private int age;
    private String contact;
    private String emergency_contact;
    private String medical_conditions;
    private String fitness_goals;
    private Date register_date;
    private String membership_status;
    private String added_by;

}
