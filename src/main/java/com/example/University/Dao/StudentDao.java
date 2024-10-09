package com.example.University.Dao;

import com.example.University.Dto.StudentInfoBySubjectsDto;
import com.example.University.Dto.StudentResultDto;

import java.util.List;

public interface StudentDao {

    List<StudentResultDto> getAllStudentsWithSubject(String searchParams);

    List<StudentInfoBySubjectsDto>getStudentInfoBySubject(String searchParams);
}
