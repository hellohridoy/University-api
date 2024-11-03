package com.example.University.controller;

import com.example.University.Dto.DashBoardDepartmentDto;
import com.example.University.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class DashBoardRestController {

  private final DashBoardService service;

  @GetMapping("/departments")
  public List<DashBoardDepartmentDto> getDepartments(@RequestParam(required = false) String searchParams) {
    return service.getAllDepartmentAndSubjects(searchParams);
  }
}
