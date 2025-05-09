package edu.ijse.powerhouse.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TrainerListDto {
    private String trainer_id;
    private String user_id;
    private String name;
    private String contact;
    private String address;
    private int age;
    private String specialization;
    private String certification;
    private Date hire_date;
    private String bio;
    private BigDecimal rating;

}
