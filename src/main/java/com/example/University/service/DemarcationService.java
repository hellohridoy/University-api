package com.example.University.service;

import com.example.University.Dto.UpazilaDetailsDto;
import com.example.University.Dto.UpazilaResponseDto;
import com.example.University.Dto.ZilaResponseDto;

import java.util.List;

public interface DemarcationService {
    List<UpazilaResponseDto> getUpazilasWithZilaAndDivision(String searchParam);

    List<ZilaResponseDto>getZilasWithZilaAndDivision(String searchParam);

    List<UpazilaDetailsDto> getAllUpazilasByZilaIdAndDivisionId(Long zilaId, Long divisionId, String searchParam);

}
