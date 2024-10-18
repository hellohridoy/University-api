package com.example.University.Dao;

import com.example.University.Dto.UpazilaDetailsDto;
import com.example.University.Dto.UpazilaResponseDto;
import com.example.University.Dto.ZilaResponseDto;

import java.util.List;

public interface DemarcationDao {
    List<UpazilaResponseDto> getAllUpazilasWithZilaAndDivision(String searchParam);

    List<ZilaResponseDto> getAllZilasWithZilaAndDivision(String searchParam);

    List<UpazilaDetailsDto> getAllUpazilasByZilaIdAndDivisionId(Long zilaId, Long divisionId, String searchParam);
}

