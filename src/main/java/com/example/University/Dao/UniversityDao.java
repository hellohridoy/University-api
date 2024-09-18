package com.example.University.Dao;

import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;

import java.util.List;

public interface UniversityDao {

       List<University> getUniversityBySearch(String searchParams);

       List<UniversityRatingDto>getUniversityRating();

}
