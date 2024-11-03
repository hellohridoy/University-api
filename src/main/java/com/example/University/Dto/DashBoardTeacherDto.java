package com.example.University.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DashBoardTeacherDto {
  private Long id;  // Add if you have an ID
  private String name;
  private String email;
  private String contactNo; // Note: Adjusted to match Java conventions
  private String department;
  private String specialization;
  private Map<String, Object> qualifications;// JSONB field as a Map

  @Getter
  @Setter
  public static class Qualifications {
    private String degree;
    private String institution;
    private int year;
  }
}
