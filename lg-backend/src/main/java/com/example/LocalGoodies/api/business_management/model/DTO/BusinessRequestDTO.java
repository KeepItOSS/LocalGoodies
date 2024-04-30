package com.example.LocalGoodies.api.business_management.model.DTO;
import com.example.LocalGoodies.api.business_management.model.BusinessTypeEnum;

public class BusinessRequestDTO {
    private String name;
    private String description;
    private BusinessTypeEnum type;

    private String phoneNumber = "";
    private String email = "";

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BusinessTypeEnum getType() {
        return type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(BusinessTypeEnum type) {
        this.type = type;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
