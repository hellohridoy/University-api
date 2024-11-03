package com.example.University.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DashBoardStudentDto {
  private Long id;
  private String name;
  private String department;
  private String email;
  private Map<String, Object> subjects;
}
