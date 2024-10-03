package com.example.University.entity;

import com.example.University.BaseEntityRequirement.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Getter
@Setter

public class Student extends AbstractBaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> subjects;

}
