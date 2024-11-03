package com.example.University.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DashBoardTeacherDto {
  private Long id;
  private String teachersName;
  private String teacherPhoneNumber;
  private String department;
  private Map<String, Object> qualifications; // JSONB field as a Map
}
