package com.example.University.controller;

import com.example.University.Dto.UniversityOverviewDto;
import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;
import com.example.University.enums.UniversityType;
import com.example.University.service.TeacherService;
import com.example.University.service.UniversityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UniversityRestController {

    private final UniversityService universityService;
    private final TeacherService teacherService;

    @PostMapping("/v1/university/university-infos")
    public ResponseEntity<University> createUniversity(@RequestBody University university) {
        log.info("POST /v1/university/university-infos - Creating a new university: {}", university);
        return ResponseEntity.ok(universityService.saveUniversity(university));
    }

    @GetMapping("/v1/university/universities-infos")
    public ResponseEntity<List<University>> getAllUniversities() {
        log.info("GET /v1/university/universities-infos - Fetching all universities");
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @GetMapping("/v1/university/university-infos/{id}")
    public ResponseEntity<?> getUniversityById(@PathVariable Long id) {
        log.info("GET /v1/university/universities-infos/{} - Fetching university with ID: {}", id, id);
        University university = universityService.getUniversityById(id);
        if (university != null) {
            return ResponseEntity.ok(university);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/v1/university/universities-infos/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University updatedUniversity) {
        log.info("PUT /v1/university/universities-infos/{} - Updating university with ID: {}", id, id);
        University university = universityService.getUniversityById(id);
        if (university == null) {
            log.warn("PUT /v1/university/universities-infos/{} - University with ID {} not found", id, id);
            return ResponseEntity.notFound().build();
        }
        updatedUniversity.setId(id);
        return ResponseEntity.ok(universityService.saveUniversity(updatedUniversity));
    }

    @DeleteMapping("/v1/university/university-infos/{id}")
    public ResponseEntity<String> deleteUniversity(@PathVariable Long id) {
        log.info("DELETE /v1/university/university-infos/{} - Deleting university with ID: {}", id, id);
        boolean isDeleted = universityService.deleteUniversity(id);

        if (isDeleted) {
            log.info("DELETE /v1/university/university-infos/{} - University with ID {} deleted successfully", id, id);
            return ResponseEntity.ok("University with ID " + id + " has been deleted successfully.");
        } else {
            log.warn("DELETE /v1/university/university-infos/{} - University with ID {} not found", id, id);
            return ResponseEntity.ok("University with ID " + id + " was not found");
        }
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        log.info("POST /uploadImage - Uploading image: {}", imageFile.getOriginalFilename());
        try {
            String imagePath = "path/to/save/" + imageFile.getOriginalFilename();
            imageFile.transferTo(new File(imagePath));
            log.info("POST /uploadImage - Image uploaded successfully to: {}", imagePath);
            return ResponseEntity.ok("Image uploaded successfully: " + imagePath);
        } catch (IOException e) {
            log.error("POST /uploadImage - Image upload failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
        }
    }

    @GetMapping("/v1/university/university-infos/by-search")
    public List<University> fetchUniversityInfosBySearched(@RequestParam(required = false) Optional<String> searchParams) {
        log.info("GET /v1/university/university-infos/by-search - Searching universities with parameters: {}", searchParams.orElse("No parameters provided"));
        try {
            return universityService.getUniversitiesBySearch(searchParams.orElse(""));
        } catch (Exception e) {
            log.error("GET /v1/university/university-infos/by-search - Unable to fetch university infos", e);
            throw new RuntimeException("Unable to fetch member categories.", e);
        }
    }

    @GetMapping("/v1/university/university-infos/university-ratings")
    public List<UniversityRatingDto> fetchUniversityInfosBySearched() {
        log.info("GET /v1/university/university-infos/university-ratings - Fetching university ratings");
        try {
            return universityService.getUniversityInfoByRatings();
        } catch (Exception e) {
            log.error("GET /v1/university/university-infos/university-ratings - Unable to fetch university ratings", e);
            throw new RuntimeException("Unable to fetch member categories.", e);
        }
    }

    @GetMapping("/v1/university/search-university-infos/world-wide")
    public List<UniversityOverviewDto> getUniversityPositionWorldWide() {
        log.info("GET /v1/university/search-university-infos/world-wide - Fetching university positions worldwide");
        return teacherService.getUniversityOverview();
    }

    @GetMapping("/v1/university/search-university-infos/by-university-type-and-ratings")
    public ResponseEntity<List<University>> getUniversityDetails(
            @RequestParam(required = false) String universityType,
            @RequestParam(required = false) Double universityRatings) {
        log.info("GET /v1/university/search-university-infos/by-university-type-and-ratings - Fetching universities by type: {} and ratings: {}", universityType, universityRatings);
        List<University> universities = universityService.getUniversityByTypeAndRatings(
                UniversityType.valueOf(universityType),
                universityRatings);

        if (universities.isEmpty()) {
            log.info("GET /v1/university/search-university-infos/by-university-type-and-ratings - No universities found for type: {} and ratings: {}", universityType, universityRatings);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(universities);
    }
}
