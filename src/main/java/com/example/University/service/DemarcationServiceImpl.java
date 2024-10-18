package com.example.University.service;

import com.example.University.Dao.DemarcationDao;
import com.example.University.Dto.UpazilaDetailsDto;
import com.example.University.Dto.UpazilaResponseDto;
import com.example.University.Dto.ZilaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemarcationServiceImpl implements DemarcationService {

    private final DemarcationDao demarcationDao;


    @Override
    public List<UpazilaResponseDto> getUpazilasWithZilaAndDivision(String searchParam) {
        return demarcationDao.getAllUpazilasWithZilaAndDivision(searchParam);

    }

    @Override
    public List<ZilaResponseDto> getZilasWithZilaAndDivision(String searchParam) {
        return demarcationDao.getAllZilasWithZilaAndDivision(searchParam);
    }

    @Override
    public List<UpazilaDetailsDto> getAllUpazilasByZilaIdAndDivisionId(Long zilaId, Long divisionId, String searchParam) {
        return demarcationDao.getAllUpazilasByZilaIdAndDivisionId(zilaId, divisionId,searchParam);
    }
}
