package edu.ijse.powerhouse.dto.tm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserListTM {
    private String user_Id;
    private String name;
    private String phone;
    private String email;
    private String user_Name;
    private String password;
    private String user_Type_Id;
    private LocalDate registration_Date;
    private String status;

}
