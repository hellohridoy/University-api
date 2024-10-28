package com.example.University.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UniversityStudentInfoDto {

  private Integer studentId;

  private String studentName;

  private String courseName;

  private  String courseDescription;

  private String departmentName;

  private String instructorName;

  private String address;

  private String city;

  private String Country;
}
