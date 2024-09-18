package com.example.University.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UniversityRatingDto {
    private Long id;

    private String universityName;

    private String universityType;

    private double ratings;

    private String address;

    private String description;
}
