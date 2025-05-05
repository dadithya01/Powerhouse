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

//    public UserListTM() {
//    }
//
//    public UserListTM(String user_Id, String name, String phone, String email, String user_Name, String password,
//            String user_Type_Id, LocalDate registration_Date, String status) {
//        this.user_Id = user_Id;
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.user_Name = user_Name;
//        this.password = password;
//        this.user_Type_Id = user_Type_Id;
//        this.registration_Date = registration_Date;
//        this.status = status;
//    }
//
//    public String getUser_Id() {
//        return user_Id;
//    }
//    public void setUser_Id(String user_Id) {
//        this.user_Id = user_Id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getPhone() {
//        return phone;
//    }
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getUser_Name() {
//        return user_Name;
//    }
//    public void setUser_Name(String user_Name) {
//        this.user_Name = user_Name;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public String getUser_Type_Id() {
//        return user_Type_Id;
//    }
//    public void setUser_Type_Id(String user_Type_Id) {
//        this.user_Type_Id = user_Type_Id;
//    }
//    public LocalDate getRegistration_Date() {
//        return registration_Date;
//    }
//    public void setRegistration_Date(LocalDate registration_Date) {
//        this.registration_Date = registration_Date;
//    }
//    public String getStatus() {
//        return status;
//    }
//    public void setStatus(String status) {
//        this.status = status;
//    }
//    @Override
//    public String toString() {
//        return "UserListTM{" +
//                "user_Id='" + user_Id + '\'' +
//                ", name='" + name + '\'' +
//                ", phone='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                ", user_Name='" + user_Name + '\'' +
//                ", password='" + password + '\'' +
//                ", user_Type_Id='" + user_Type_Id + '\'' +
//                ", registration_Date=" + registration_Date +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
