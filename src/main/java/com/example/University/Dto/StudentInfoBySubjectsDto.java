package com.example.University.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
public class StudentInfoBySubjectsDto {
    private Long id;
    private String name;
    private String email;
    private String department;
    private Map<String, Object>  subjects;
}
