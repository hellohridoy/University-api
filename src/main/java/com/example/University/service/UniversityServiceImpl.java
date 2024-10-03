package com.example.University.service;

import com.example.University.Dao.UniversityDao;
import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;
import com.example.University.enums.UniversityType;
import com.example.University.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for university model.
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;
    private final UniversityDao universityDao;

    @Override
    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id).orElse(null);
    }

    public boolean deleteUniversity(Long id) {
        if (universityRepository.existsById(id)) {
            universityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<University> getUniversitiesBySearch(String searchParams) {
        String searchString = searchParams.trim();
        if (searchParams.isEmpty()) {
            return universityDao.getUniversityBySearch("") ;
        }
        return universityDao.getUniversityBySearch(searchString) ;
    }

    @Override
    public List<UniversityRatingDto> getUniversityInfoByRatings() {
        return universityDao.getUniversityRating();
    }


    //Need Dto for better required response
    @Override
    public List<University> getUniversityByTypeAndRatings(UniversityType universityType,Double universityRatings) {
        return universityDao.getUniversityDetailsByType(universityType,universityRatings);
    }
}
