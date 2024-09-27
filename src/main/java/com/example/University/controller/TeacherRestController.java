package com.example.University.controller;

import com.example.University.Dto.SubjectDto;
import com.example.University.entity.Teacher;
import com.example.University.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Restcontroller for Teacher model.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TeacherRestController {
    private final TeacherService teacherService;

    @PostMapping("/v1/university/teacher-infos")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    @PutMapping("/v1/university/teacher-infos/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/v1/university/teacher-infos/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/university/teacher-infos/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/v1/university/teacher-infos")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/v1/teacher/total-monthly-salary")
    public Double getAllTeachersMonthlyPaidByUniversity() {
        try {
            // Call the service method to get the total monthly salary
            return teacherService.getAllTeachersMonthlyPaidByUniversity();
        } catch (Exception e) {
            log.error("Error occurred while fetching total monthly salary: ", e);
        }
        return 0.0;
    }

    @GetMapping("/v1/university/teacher-infos/by-subjects")
    public ResponseEntity<List<SubjectDto>> getAllUniqueSubjects() {
        List<SubjectDto> subjects = teacherService.getAllSubject();
        return ResponseEntity.ok(subjects);
    }
}
