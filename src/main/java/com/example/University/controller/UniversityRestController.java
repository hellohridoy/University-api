package com.example.University.controller;


import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;
import com.example.University.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Restcontroller for University model.
 */
@RestController
@RequiredArgsConstructor
public class UniversityRestController {

    private final UniversityService universityService;
    @PostMapping("/v1/university/university-infos")
    public ResponseEntity<University> createUniversity(@RequestBody University university) {
        return ResponseEntity.ok(universityService.saveUniversity(university));
    }

    @GetMapping("/v1/university/universities-infos")
    public ResponseEntity<List<University>> getAllUniversities() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @GetMapping("/v1/university/universities-infos/{id}")
    public ResponseEntity<?> getUniversityById(@PathVariable Long id) {
        University university = universityService.getUniversityById(id);
        if (university == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("University with ID " + id + " not found.");
        }
        return ResponseEntity.ok(university);
    }


    @PutMapping("/v1/university/universities-infos/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University updatedUniversity) {
        University university = universityService.getUniversityById(id);
        if (university == null) {
            return ResponseEntity.notFound().build();
        }
        updatedUniversity.setId(id);
        return ResponseEntity.ok(universityService.saveUniversity(updatedUniversity));
    }

    @DeleteMapping("/v1/university/delete-university-infos/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable Long id) {
        boolean isDeleted = universityService.deleteUniversity(id);

        if (isDeleted) {
            return ResponseEntity.ok("University with ID " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.ok("University with ID " + id + " was not found");
        }
    }




    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        try {
            String imagePath = "path/to/save/" + imageFile.getOriginalFilename();
            imageFile.transferTo(new File(imagePath));
            return ResponseEntity.ok("Image uploaded successfully: " + imagePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
        }
    }

    @GetMapping("/v1/university/search-university-infos")
    public List<University> fetchUniversityInfosBySearched(
            @RequestParam(required = false) Optional<String> searchParams) {
        try {
            return universityService.getUniversitiesBySearch(searchParams.orElse(""));
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch member categories.", e);
        }
    }

    @GetMapping("/v1/university/fetch-university-infos-by-ratings")
    public List<UniversityRatingDto> fetchUniversityInfosBySearched() {
        try {
            return universityService.getUniversityInfoByRatings();
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch member categories.", e);
        }
    }
}
