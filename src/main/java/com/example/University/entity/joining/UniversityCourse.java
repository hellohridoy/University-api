package com.example.University.entity.joining;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "university_courses")
public class UniversityCourse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "course_id")
  private int courseId;

  @Column(name = "course_name")
  private String courseName;

  @Column(name = "course_description")
  private String courseDescription;

  @Column(name = "start_date")
  @Temporal(TemporalType.DATE)
  private Date startDate;

  @Column(name = "end_date")
  @Temporal(TemporalType.DATE)
  private Date endDate;

  @Column(name = "instructor_name")
  private String instructorName;

  @Column(name = "credits")
  private int credits;

  @Column(name = "room_number")
  private String roomNumber;

  @Column(name = "schedule")
  private String schedule;

  @Column(name = "department")
  private String department;
}
