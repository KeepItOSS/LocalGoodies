package com.example.LocalGoodies.api.business_management.model;

import com.example.LocalGoodies.api.business_management.model.DTO.BusinessRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.OffsetDateTime;

@Entity(name = "BUSINESS")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATED_AT")
    private OffsetDateTime created_at;

    @Column(name = "CHANGED_AT")
    private OffsetDateTime changed_at;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "TYPE") @Enumerated(EnumType.STRING)
    private BusinessTypeEnum type;

    public static class Builder {
        private final String name;
        private final String description;
        private final BusinessTypeEnum type;

        private Long id;
        private String phoneNumber = "";
        private String email = "";

        public Builder(String name, String description, BusinessTypeEnum type) {
            this.name = name;
            this.description = description;
            this.type = type;
        }

        public Business build() {
            return new Business(this);
        }

        public Builder id(Long val) {
            id = val; return this;
        }

        public Builder phoneNumber(String val) {
            phoneNumber = val; return this;
        }

        public Builder email(String val) {
            email = val; return this;
        }
    }

    protected Business() { }

    private Business(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.type = builder.type;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.created_at = OffsetDateTime.now();
        this.changed_at = OffsetDateTime.now();
        this.active = false;
    }

    private Business(Long id, String name, String phoneNumber,
             String email, OffsetDateTime createdAt,
             OffsetDateTime changedAt, String description,
             Boolean active, BusinessTypeEnum type) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        created_at = createdAt;
        changed_at = changedAt;
        this.description = description;
        this.active = active;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public OffsetDateTime getChanged_at() {
        return changed_at;
    }

    public Boolean getActive() {
        return active;
    }

    public String getDescription() {
        return description;
    }

    public BusinessTypeEnum getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void update(BusinessRequest businessRequest) {
        this.name = businessRequest.name();
        this.description = businessRequest.description();
        this.type = businessRequest.type();
    }
}
