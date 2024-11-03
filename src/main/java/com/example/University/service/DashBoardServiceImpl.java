package com.example.University.service;

import com.example.University.Dao.DashBoardDao;
import com.example.University.Dto.DashBoardDepartmentDto;
import com.example.University.Dto.DashBoardStudentDto;
import com.example.University.Dto.DashBoardTeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService{

  private final DashBoardDao dashBoardDao;

  @Override
  public List<DashBoardDepartmentDto> getAllDepartmentAndSubjects(String searchParams) {
    return dashBoardDao.getAllDepartmentAndSubjects(searchParams);
  }

  @Override
  public List<DashBoardTeacherDto> getAllTeacherForDashBoard(String searchParams) {
    return dashBoardDao.getAllTeacher(searchParams);
  }

  @Override
  public List<DashBoardStudentDto> getAllStudentForDashBoard(String searchParams) {
    return dashBoardDao.getAllStudent(searchParams);
  }
}
