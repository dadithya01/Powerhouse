package edu.ijse.powerhouse.dto;

public class TrainerListDto {
    private String trainer_id;
    private String user_id;
    private String name;
    private String contact;
    private String address;
    private int age;
    private String specialization;
    private String certification;
    private String  hire_date;
    private String bio;
    private Double rating;

    public TrainerListDto() {
    }

    public TrainerListDto(String trainer_id, String user_id, String name, String contact, String address, int age, String specialization, String certification, String hire_date, String bio, Double rating) {
        this.trainer_id = trainer_id;
        this.user_id = user_id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.age = age;
        this.specialization = specialization;
        this.certification = certification;
        this.hire_date = hire_date;
        this.bio = bio;
        this.rating = rating;
    }
    public String getTrainer_id() {
        return trainer_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public String getName() {
        return name;
    }
    public String getContact() {
        return contact;
    }
    public String getAddress() {
        return address;
    }
    public int getAge() {
        return age;
    }
    public String getSpecialization() {
        return specialization;
    }
    public String getCertification() {
        return certification;
    }
    public String getHire_date() {
        return hire_date;
    }
    public String getBio() {
        return bio;
    }
    public Double getRating() {
        return rating;
    }
    public void setTrainer_id(String trainer_id) {
        this.trainer_id = trainer_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public void setCertification(String certification) {
        this.certification = certification;
    }
    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

}
