package com.example.University.entity;

import com.example.University.BaseEntityRequirement.AbstractBaseEntity;
import com.example.University.enums.UniversityType;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Table(name = "university")
public class University extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UniversityType universityType;

    private double rating;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Store only the image path, actual image saved in file system
    private String image;

    private LocalDate startingDate;

    private LocalDateTime casuallyOpensAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> value;
}