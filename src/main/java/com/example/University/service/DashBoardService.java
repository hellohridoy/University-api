package com.example.University.service;

import com.example.University.Dto.DashBoardDepartmentDto;

import java.util.List;
import java.util.Map;

public interface DashBoardService {

  List<DashBoardDepartmentDto> getAllDepartmentAndSubjects(String searchParams);

}
