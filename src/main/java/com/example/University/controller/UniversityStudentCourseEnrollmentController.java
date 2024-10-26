package com.example.University.controller;

import com.example.University.Dto.UniversityStudentInfoDto;
import com.example.University.entity.Customer;
import com.example.University.service.CustomerService;
import com.example.University.service.UniversityStudentCourseEnrollMentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UniversityStudentCourseEnrollmentController {

  private final UniversityStudentCourseEnrollMentService universityStudentCourseEnrollMentService;

  @GetMapping("/api/v1/university/student-infos")
  public ResponseEntity<List<UniversityStudentInfoDto>> getAllStudentInfo(@RequestParam(required = false) String searchParams) {
    List<UniversityStudentInfoDto> studentInfoList = universityStudentCourseEnrollMentService.getAllStudentInfo(searchParams);
    return ResponseEntity.ok(studentInfoList); // Return the list of students with status 200 (OK)
  }
}
