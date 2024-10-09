package com.example.University.service;

import com.example.University.Dto.StudentInfoBySubjectsDto;
import com.example.University.Dto.StudentResultDto;
import com.example.University.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    List<Student> getAllStudents();

    void deleteStudentById(Long id);

    List<StudentResultDto> getAllStudentResults(String studentName);

    List<StudentInfoBySubjectsDto> getStudentBySubjects(String searchParams);
}
