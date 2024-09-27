package com.example.University.entity;

import com.example.University.BaseEntityRequirement.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Getter
@Setter
public class Teacher extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(nullable = false)
    private double salary;

    @Column(columnDefinition = "TEXT")
    private String biography;

    // Store only the image path, actual image saved in file system
    private String profilePicture;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> qualifications;
}
