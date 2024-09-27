package com.example.University.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class SubjectDto {
    private long id;
    private String name;
    private String department;
    private String email;
    private String hireDate;
    private String biography;
    private Map<String, Object> qualifications; // JSONB field as a Map
    private String profilePicture;
}
