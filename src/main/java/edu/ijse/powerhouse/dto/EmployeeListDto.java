package edu.ijse.powerhouse.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

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

    public EmployeeListDto() {
    }

    public EmployeeListDto(String employee_id, String name, String contact, String address, int age, Date hire_date,
            String position, BigDecimal salary, String emergency_contact) {
        this.employee_id = employee_id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.age = age;
        this.hire_date = hire_date;
        this.position = position;
        this.salary = salary;
        this.emergency_contact = emergency_contact;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
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

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(String emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    @Override
    public String toString() {
        return "EmployeeListDto{" +
                "employee_id='" + employee_id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", hire_date=" + hire_date +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", emergency_contact='" + emergency_contact + '\'' +
                '}';
    }
}
