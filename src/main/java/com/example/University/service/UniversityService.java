package com.example.University.service;

import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;

import java.util.List;


/**
 * Interface for University Model.
 */
public interface UniversityService {

    University saveUniversity(University university);

    List<University> getAllUniversities();

    University getUniversityById(Long id);

    boolean deleteUniversity(Long id);

    List<University> getUniversitiesBySearch(String searchParams);

    List<UniversityRatingDto>getUniversityInfoByRatings();
}
