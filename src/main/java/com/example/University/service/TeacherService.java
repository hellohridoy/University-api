package com.example.University.service;

import com.example.University.Dto.SubjectDto;
import com.example.University.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    Teacher updateTeacher(Long id, Teacher teacher);
    void deleteTeacher(Long id);
    Teacher getTeacherById(Long id);
    List<Teacher> getAllTeachers();
    Double getAllTeachersMonthlyPaidByUniversity();
    List<SubjectDto> getAllSubject();
}
