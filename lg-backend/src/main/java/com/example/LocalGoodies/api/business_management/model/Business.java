package com.example.LocalGoodies.api.business_management.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity(name = "BUSINESS")
public class Business {

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

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE")
    private Boolean active;

    Business() { }

    Business(Long id, String name, String phoneNumber,
             String email, OffsetDateTime created_at,
             OffsetDateTime changed_at, String description,
             Boolean active) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.created_at = created_at;
        this.changed_at = changed_at;
        this.description = description;
        this.active = active;
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
}
