package com.example.University.Dao;

import com.example.University.Dto.DashBoardDepartmentDto;

import java.util.List;
import java.util.Map;

public interface DashBoardDao {

  List<DashBoardDepartmentDto> getAllDepartmentAndSubjects(String searchParams);

}
