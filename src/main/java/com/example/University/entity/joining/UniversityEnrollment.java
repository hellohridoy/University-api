package com.example.University.entity.joining;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "university_enrollments")
public class UniversityEnrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "enrollment_id")
  private int enrollmentId;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private UniversityStudent student;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private UniversityCourse course;

  @Column(name = "enrollment_date")
  @Temporal(TemporalType.DATE)
  private Date enrollmentDate;

  @Column(name = "grade")
  private String grade;

  @Column(name = "status")
  private String status;

  @Column(name = "semester")
  private String semester;

  @Column(name = "payment_status")
  private String paymentStatus;

  @Column(name = "scholarship_eligibility")
  private boolean scholarshipEligibility;
}
