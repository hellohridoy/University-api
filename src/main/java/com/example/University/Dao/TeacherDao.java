package com.example.University.Dao;

import com.example.University.Dto.SubjectDto;


import java.util.List;

public interface TeacherDao {

    double findTotalMonthlySalaryByUniversity();

    List<SubjectDto> getAllUniqueSubjects();
}
