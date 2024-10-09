package com.example.University.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class StudentResultDto {

    private Long id;

    private String name;

    private Map<String, Object> results; // JSONB field as a Map
}
