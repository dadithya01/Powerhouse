package edu.ijse.powerhouse.dto;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserListDto {
    private String user_Id;
    private String name;
    private String phone;
    private String email;
    private String user_Name;
    private String password;
    private String user_Type_Id;
    private Date registration_Date;
    private Timestamp last_Login;
    private String status;

}
