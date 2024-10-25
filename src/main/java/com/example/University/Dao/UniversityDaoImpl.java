package com.example.University.Dao;

import com.example.University.Dto.UniversityOverviewDto;
import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;
import com.example.University.enums.UniversityType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * University: Dao Implementation.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class UniversityDaoImpl implements UniversityDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<University> getUniversityBySearch(String searchParams) {
        String sql = """
            SELECT * FROM university
            WHERE university_name ILIKE CONCAT('%', :searchParams, '%')
            OR university_address ILIKE CONCAT('%', :searchParams, '%')
            OR university_type ILIKE CONCAT('%', :searchParams, '%')
            OR university_rating::text ILIKE CONCAT('%', :searchParams, '%')
            OR university_description ILIKE CONCAT('%', :searchParams, '%');
            """;

        Map<String, String> params = new HashMap<>();
        params.put("searchParams", searchParams);

        return jdbcTemplate.query(sql, params, this::mapRow);
    }

    @Override
    public List<UniversityRatingDto> getUniversityRating() {
        String sql = """
            SELECT * FROM university WHERE university_rating > :rating;
            """;
        Map<String, Double> params = new HashMap<>();
        params.put("rating", 4.50);
        return jdbcTemplate.query(sql, params, this::universityRatingMapper);
    }

    @Override
    public List<UniversityOverviewDto> getUniversityPositionWordWide() {
        String sql = """
            SELECT
                u.id,
                u.university_name,
                u.university_address,
                u.university_rating,
                u.university_type,
                t.teachers_name,
                t.specialization,
                t.qualifications,
                t.hire_date
            FROM university u
            LEFT JOIN teacher t ON u.id = t.university_id;
            """;

        return jdbcTemplate.query(sql, this::universityInfo);
    }

    @Override
    public List<University> getUniversityDetailsByType(UniversityType universityType, double universityRatings) {
        return List.of();
    }

  @Override
  public List<University> getUniversityDetailsByType(UniversityType universityType, Double universityRatings) {
    StringBuilder sql = new StringBuilder("SELECT * FROM university WHERE 1=1");

    // Map to hold parameters
    Map<String, Object> params = new HashMap<>();

    // Add filtering by universityType if provided
    if (universityType != null) {
      sql.append(" AND university_type = :university_type");
      params.put("university_type", universityType.name());
    }

    // Add filtering by universityRatings if provided
    if (universityRatings != null) {
      sql.append(" AND university_rating >= :university_rating");
      params.put("university_rating", universityRatings);
    }

    return jdbcTemplate.query(sql.toString(), params, this::universityByTypeMapper);
  }

    private University universityByTypeMapper(ResultSet rs, int rowNum) throws SQLException {
        University university = new University();
        university.setId(rs.getLong("id"));
        university.setUniversityName(rs.getString("university_name"));
        university.setUniversityAddress(rs.getString("university_address"));
        university.setUniversityType(UniversityType.valueOf(rs.getString("university_type").toUpperCase()));
        university.setUniversityDescription(rs.getString("university_description"));
        university.setUniversityImage(rs.getString("university_image"));
        university.setUniversityRating(rs.getDouble("university_rating"));
        return university;
    }

    public UniversityOverviewDto universityInfo(ResultSet rs, int rowNum) throws SQLException {
        UniversityOverviewDto university = new UniversityOverviewDto();
        university.setId(rs.getInt("id"));
        university.setUniversity_name(rs.getString("university_name"));
        university.setDepartment(rs.getString("university_department"));
        university.setAddress(rs.getString("university_address"));
        university.setUniversity_rating(String.valueOf(rs.getDouble("university_rating")));
        university.setUniversity_type(rs.getString("university_type"));
        university.setTeachers_name(rs.getString("teachers_name"));
        university.setSpecialization(rs.getString("specialization"));

        // Handle JSONB field (qualifications)
        ObjectMapper objectMapper = new ObjectMapper();
        String qualificationsJson = rs.getString("qualifications");

        if (qualificationsJson != null && !qualificationsJson.trim().isEmpty()) {
            try {
                Map<String, Object> qualifications = objectMapper.readValue(qualificationsJson, new TypeReference<>() {});
                university.setQualifications(qualifications);
            } catch (JsonProcessingException e) {
                log.error("Error parsing qualifications JSON", e);
            }
        } else {
            university.setQualifications(Collections.emptyMap());
        }

        university.setHire_date(rs.getDate("hire_date").toString());

        return university;
    }

    private University mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            University university = new University();
            university.setId(rs.getLong("id"));
            university.setUniversityName(rs.getString("university_name"));
            university.setUniversityAddress(rs.getString("university_address"));
            // Convert string to Enum
            String universityTypeStr = rs.getString("university_type");
            UniversityType universityType = UniversityType.valueOf(universityTypeStr.toUpperCase());
            university.setUniversityType(universityType);
            university.setUniversityRating(rs.getDouble("university_rating"));
            university.setUniversityDescription(rs.getString("university_description"));
            return university;
        } catch (SQLException e) {
            log.error("Error mapping row to University entity", e);
            throw e; // Rethrow to properly handle failure cases
        }
    }

    private UniversityRatingDto universityRatingMapper(ResultSet rs, int rowNum) throws SQLException {
        try {
            UniversityRatingDto universityRatingDto = new UniversityRatingDto();
            universityRatingDto.setId(rs.getLong("id"));
            universityRatingDto.setUniversityName(rs.getString("university_name"));
            universityRatingDto.setAddress(rs.getString("university_address"));
            universityRatingDto.setRatings(rs.getDouble("university_rating"));
            universityRatingDto.setDescription(rs.getString("university_description"));
            return universityRatingDto;
        } catch (SQLException e) {
            log.error("Error mapping row to UniversityRatingDto", e);
            throw e; // Rethrow to handle errors properly
        }
    }

}
