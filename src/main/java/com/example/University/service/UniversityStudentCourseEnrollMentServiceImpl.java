package com.example.University.service;

import com.example.University.Dao.UniversityStudentCourseEnrollMentDao;
import com.example.University.Dto.UniversityStudentInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UniversityStudentCourseEnrollMentServiceImpl implements UniversityStudentCourseEnrollMentService{

  private final UniversityStudentCourseEnrollMentDao universityStudentCourseEnrollMentDao;

  @Override
  public List<UniversityStudentInfoDto> getAllStudentInfo(String searchParams) {
    return universityStudentCourseEnrollMentDao.getAllStudentInfo(searchParams);

  }
}
