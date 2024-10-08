package com.example.demo.dto;
import com.example.demo.entities.employeeentity;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
public class employeedto {
    // Fields, getters, and setters

    // Public no-argument constructor
    public employeedto() {
    }

    @Setter
    @Getter
    private long id;
    @Setter
    @Getter
    @NotNull(message = "Required field in employee")
    private String Name;
    private String email;
    private Integer age;
    private LocalDate Dateofjoining;
    @JsonProperty("isActive")
    private Boolean isActiveEmployee;

    public employeedto(String employeeid, String kushal, String mail, int age, LocalDate dateofjoining, boolean isActive) {

    }

    public employeedto(long id, String name, String email, Integer age, LocalDate dateofjoining, boolean isActive) {
        this.id = id;
        this.Name = name;
        this.email = email;
        this.age = age;
        this.Dateofjoining = dateofjoining;
        this.isActiveEmployee = isActive;
    }
}


