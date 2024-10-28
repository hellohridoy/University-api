package com.example.University.Dao;

import com.example.University.Dto.UniversityStudentInfoDto;

import java.util.List;

public interface UniversityStudentCourseEnrollMentDao {

  List<UniversityStudentInfoDto> searchStudents(Integer studentId, String name, String courseName,
                                                String instructorName, String address, String city);

}
