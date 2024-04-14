package com.example.LocalGoodies.api.business_management.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity(name = "BUSINESS")
public class Business {

    Business() { }

    Business(String name, String phoneNumber,
             String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.created_at = OffsetDateTime.now();
        this.changed_at = OffsetDateTime.now();
        this.active = false;
    }

    Business(Long id, String name, String phoneNumber,
             String email, OffsetDateTime created_at,
             OffsetDateTime changed_at, Boolean active) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.created_at = OffsetDateTime.now();
        this.changed_at = OffsetDateTime.now();
        this.active = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATED_AT")
    private OffsetDateTime created_at;

    @Column(name = "CHANGED_AT")
    private OffsetDateTime changed_at;

    @Column(name = "ACTIVE")
    private Boolean active;

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
}
