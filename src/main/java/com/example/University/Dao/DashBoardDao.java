package com.example.University.Dao;

import com.example.University.Dto.DashBoardDepartmentDto;
import com.example.University.Dto.DashBoardStudentDto;
import com.example.University.Dto.DashBoardTeacherDto;

import java.util.List;
import java.util.Map;

public interface DashBoardDao {

  List<DashBoardDepartmentDto> getAllDepartmentAndSubjects(String searchParams);

  List<DashBoardTeacherDto>getAllTeacher(String searchParams);

  List<DashBoardStudentDto> getAllStudent(String searchParams);

}
