package edu.ijse.powerhouse.dto;

public class MembershipTypeDto {
    private String membership_type_id;
    private String name;
    private String description;
    private String duration;
    private Double price;
    private String features;
    private String status;

    public MembershipTypeDto() {
    }

    public MembershipTypeDto(String membership_type_id, String name, String description, String duration, Double price, String features, String status) {
        this.membership_type_id = membership_type_id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.features = features;
        this.status = status;
    }

    public String getMembership_type_id() {
        return membership_type_id;
    }

    public void setMembership_type_id(String membership_type_id) {
        this.membership_type_id = membership_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
