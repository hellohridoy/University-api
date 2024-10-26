package com.example.University.Dao;

import com.example.University.Dto.UniversityStudentInfoDto;

import java.util.List;

public interface UniversityStudentCourseEnrollMentDao {
  List<UniversityStudentInfoDto> getAllStudentInfo(String searchParams);

}
