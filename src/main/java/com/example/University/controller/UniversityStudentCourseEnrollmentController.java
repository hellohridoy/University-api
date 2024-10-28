package com.example.University.controller;

import com.example.University.Dto.UniversityStudentInfoDto;
import com.example.University.entity.Customer;
import com.example.University.service.CustomerService;
import com.example.University.service.UniversityStudentCourseEnrollMentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UniversityStudentCourseEnrollmentController {

  private final UniversityStudentCourseEnrollMentService universityStudentCourseEnrollMentService;

  @GetMapping("/api/v1/university/student-infos")
  public ResponseEntity<List<UniversityStudentInfoDto>> searchStudents(
    @RequestParam(required = false) Integer studentId,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String courseName,
    @RequestParam(required = false) String instructorName,
    @RequestParam(required = false) String address,
    @RequestParam(required = false) String city) {

    List<UniversityStudentInfoDto> result = universityStudentCourseEnrollMentService.getAllStudentInfo(
      studentId,
      name,
      courseName,
      instructorName,
      address,
      city);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
