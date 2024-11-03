package com.example.University.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DashBoardDepartmentDto {
  private String departmentName;
  private String departmentDescription;
  private List<SubjectDto> subjects;

  @Getter
  @Setter
  public static class SubjectDto {
    private String subjectName;
    private String subjectDescription;

  }

}
