package edu.ijse.powerhouse.dto;

public class UserTypeDto {
    private String user_Type_Id;
    private String type;

    public UserTypeDto() {
    }

    public UserTypeDto(String user_Type_Id, String type) {
        this.user_Type_Id = user_Type_Id;
        this.type = type;
    }
    public String getUser_Type_Id() {
        return user_Type_Id;
    }

    public void setUser_Type_Id(String user_Type_Id) {
        this.user_Type_Id = user_Type_Id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
