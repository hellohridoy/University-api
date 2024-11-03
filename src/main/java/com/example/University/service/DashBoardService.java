package com.example.University.service;

import com.example.University.Dto.DashBoardDepartmentDto;
import com.example.University.Dto.DashBoardStudentDto;
import com.example.University.Dto.DashBoardTeacherDto;

import java.util.List;
import java.util.Map;

public interface DashBoardService {

  List<DashBoardDepartmentDto>getAllDepartmentAndSubjects(String searchParams);

  List<DashBoardTeacherDto>getAllTeacherForDashBoard(String searchParams);

  List<DashBoardStudentDto>getAllStudentForDashBoard(String searchParams);

}
