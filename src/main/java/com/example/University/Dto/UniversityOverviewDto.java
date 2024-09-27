package com.example.University.Dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class UniversityOverviewDto {
    private long id;
    private String university_name;
    private String department;
    private String address;
    private String university_rating;
    private String university_type;
    private String teachers_name;
    private String specialization;
    private Map<String, Object> qualifications; // JSONB field as a Map
    private String hire_date;
}
