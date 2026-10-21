package com.aishwarya.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Duration (in months) is required")
    @Min(value = 1, message = "Duration must be at least 1 month")
    @Column(nullable = false)
    private Integer durationMonths;

    public Course() {
    }

    public Course(String name, Integer durationMonths) {
        this.name = name;
        this.durationMonths = durationMonths;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getDurationMonths() { return durationMonths; }
    public void setDurationMonths(Integer durationMonths) { this.durationMonths = durationMonths; }
}
