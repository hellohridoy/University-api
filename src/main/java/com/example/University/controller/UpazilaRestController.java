package com.example.University.controller;

import com.example.University.Dto.UpazilaDetailsDto;
import com.example.University.Dto.UpazilaResponseDto;
import com.example.University.Dto.ZilaResponseDto;
import com.example.University.entity.Upazila;
import com.example.University.service.DemarcationService;
import com.example.University.service.UpazilaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UpazilaRestController {

    private final UpazilaService upazilaService;
    private final DemarcationService demarcationService;

    @PostMapping("/api/v1/demarcation/upazila")
    public ResponseEntity<Upazila> createUpazila(@RequestBody Upazila upazila) {
        Upazila createdUpazila = upazilaService.saveUpazila(upazila);
        return new ResponseEntity<>(createdUpazila, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/demarcation/upazila")
    public List<Upazila> getAllUpazilas() {
        return upazilaService.getAllUpazilas();
    }

    @GetMapping("/api/v1/demarcation/zila-division-by-upazila")
    public ResponseEntity<List<UpazilaResponseDto>> getUpazilasWithZilaAndDivision(
            @RequestParam(required = false) String searchText
    )
    {
        List<UpazilaResponseDto> upazilas = demarcationService.getUpazilasWithZilaAndDivision(searchText);
        return ResponseEntity.ok(upazilas);
    }

    @GetMapping("/api/v1/demarcation/zila-division-by-zila")
    public List<ZilaResponseDto> searchZilas(
            @RequestParam("searchParam") String searchParam
    )
    {
        return demarcationService.getZilasWithZilaAndDivision(searchParam);
    }


    @GetMapping("/api/v1/demarcation/zila/{zilaId}/division/{divisionId}/upazilas")
    public List<UpazilaDetailsDto> getAllUpazilasByZilaIdAndDivisionId(
            @PathVariable("zilaId") Long zilaId,
            @PathVariable("divisionId") Long divisionId,
            @RequestParam(value = "searchParams", required = false) String searchParams) {
        return demarcationService.getAllUpazilasByZilaIdAndDivisionId(zilaId, divisionId, searchParams);
    }


}
