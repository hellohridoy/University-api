package com.example.University.Dao;

import com.example.University.Dto.SubjectDto;
import com.example.University.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    double findTotalMonthlySalaryByUniversity();
    List<SubjectDto> getAllUniqueSubjects();
}
