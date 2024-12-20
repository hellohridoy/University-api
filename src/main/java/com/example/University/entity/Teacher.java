package com.example.University.entity;

import com.example.University.BaseEntityRequirement.AbstractBaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Teacher extends AbstractBaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teachersName;

    @Column(nullable = false)
    private String teachersEmail;

    @Column(nullable = false)
    private String teacherPhoneNumber;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "university_id")
  private University university;

  @ManyToMany
  @JoinTable(
    name = "teacher_student",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  private List<Student> students;


}
