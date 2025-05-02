package edu.ijse.powerhouse.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

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

    public TrainerListDto() {
    }

    public TrainerListDto(String trainer_id, String user_id, String name, String contact, String address, int age,
            String specialization, String certification, Date hire_date, String bio, BigDecimal rating) {
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

    public void setTrainer_id(String trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "TrainerListDto{" +
                "trainer_id='" + trainer_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", specialization='" + specialization + '\'' +
                ", certification='" + certification + '\'' +
                ", hire_date=" + hire_date +
                ", bio='" + bio + '\'' +
                ", rating=" + rating +
                '}';
    }
}
