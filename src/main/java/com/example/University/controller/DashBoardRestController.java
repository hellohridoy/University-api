package com.example.University.controller;

import com.example.University.Dto.DashBoardDepartmentDto;
import com.example.University.Dto.DashBoardStudentDto;
import com.example.University.Dto.DashBoardTeacherDto;
import com.example.University.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DashBoardRestController {

  private final DashBoardService service;

  @GetMapping("v1/university/university-infos/departments-infos")
  public List<DashBoardDepartmentDto> getDepartments(
    @RequestParam(required = false)
    String searchParams) {
    return service.getAllDepartmentAndSubjects(searchParams);
  }

  @GetMapping("v1/university/university-infos/subject-infos")
  public List<DashBoardTeacherDto> getAllSubjects(
    @RequestParam(required = false)
    String searchParams) {
    return service.getAllTeacherForDashBoard(searchParams);
  }

  @GetMapping("v1/university/university-infos/teacher-infos")
  public List<DashBoardTeacherDto> getTeachers(
    @RequestParam(required = false)
    String searchParams) {
    return service.getAllTeacherForDashBoard(searchParams);
  }
}
