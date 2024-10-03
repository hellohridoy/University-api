package com.example.University.Dao;

import com.example.University.Dto.UniversityOverviewDto;
import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;
import com.example.University.enums.UniversityType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UniversityDao {

       List<University> getUniversityBySearch(String searchParams);

       List<UniversityRatingDto>getUniversityRating();

       List<UniversityOverviewDto> getUniversityPositionWordWide();


       List<University> getUniversityDetailsByType(UniversityType universityType, double universityRatings);

       List<University> getUniversityDetailsByType(UniversityType universityType, Double universityRatings);
}
