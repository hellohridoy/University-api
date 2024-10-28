package com.example.University.service;

import com.example.University.Dto.UniversityStudentInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UniversityStudentCourseEnrollMentService{
  List<UniversityStudentInfoDto> getAllStudentInfo(Integer studentId, String name, String courseName,
                                                   String instructorName, String address, String city);

}
