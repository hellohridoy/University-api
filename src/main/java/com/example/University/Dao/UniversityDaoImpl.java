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
 * University: Dao Implement.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class UniversityDaoImpl implements UniversityDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<University> getUniversityBySearch(String searchParams) {
        String sql = """
           
           SELECT
              *
           FROM
               university
           WHERE
               (name ILIKE CONCAT('%', :searchParams, '%') OR
                address ILIKE CONCAT('%', :searchParams, '%') OR
                university_type ILIKE CONCAT('%', :searchParams, '%') OR
                rating::text ILIKE CONCAT('%', :searchParams, '%') OR
                description ILIKE CONCAT('%', :searchParams, '%'));
            """;

        Map<String, String> params = new HashMap<>();
        params.put("searchParams", searchParams);

        return jdbcTemplate.query(sql, params, this::mapRow);
    }

    @Override
    public List<UniversityRatingDto> getUniversityRating() {
       String sql = """
               select * from university where rating>:rating;
               """;
        Map<String, Double> params = new HashMap<>();
        params.put("rating", 4.50);
        return jdbcTemplate.query(sql, params, this::universityRatingMapper);
    }

    @Override
    public List<UniversityOverviewDto> getUniversityPositionWordWide() {
        String sql = """
                    SELECT
                        u .id,
                        u.university_name,
                        u.university_address,
                        t.department,
                        u.university_rating,
                        u.university_type,
                        t.teachers_name,
                        t.specialization,
                        t.qualifications,
                        t.hire_date
                    FROM
                        university u
                            LEFT JOIN
                        teacher t ON u.id = t.id;
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

        return jdbcTemplate.query(sql.toString(), params, this::UniversityByTypeMapper);
    }

    private University UniversityByTypeMapper(ResultSet rs, int rowNum) throws SQLException {
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
        university.setDepartment(rs.getString("department"));
        university.setAddress(rs.getString("university_address"));
        university.setUniversity_rating(String.valueOf(rs.getDouble("university_rating")));
        university.setUniversity_type(rs.getString("university_type"));
        university.setTeachers_name(rs.getString("teachers_name"));
        university.setSpecialization(rs.getString("specialization"));

        // Handle JSONB field (qualifications)
        ObjectMapper objectMapper = new ObjectMapper();
        String qualificationsJson = rs.getString("qualifications");

            // Check if qualificationsJson is not null or empty
            if (qualificationsJson != null && !qualificationsJson.trim().isEmpty()) {
                try {
                    Map<String, Object> qualifications = objectMapper.readValue(qualificationsJson, new TypeReference<Map<String, Object>>() {});
                    university.setQualifications(qualifications);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error parsing qualifications JSON", e);
                }
            } else {
                university.setQualifications(null); // or an empty map, if appropriate
            }

        university.setHire_date(String.valueOf(rs.getDate("hire_date")));

        return university;
    }



    private University mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            University university = new University();
            university.setId(rs.getLong("id"));
            university.setUniversityName(rs.getString("name"));
            university.setUniversityAddress(rs.getString("address"));
            // Convert string to Enum
            String universityTypeStr = rs.getString("university_type");
            UniversityType universityType = UniversityType.valueOf(universityTypeStr.toUpperCase());
            university.setUniversityType(universityType);
            university.setUniversityRating(rs.getDouble("rating")); // Assuming rating is a double
            university.setUniversityDescription(rs.getString("description"));
            return university;
        }catch (SQLException e) {
            log.error("Error mapping row to UniversityMappings", e);
        }
        return null;

    }

    private UniversityRatingDto universityRatingMapper(ResultSet rs, int rowNum) throws SQLException {
        try {
            UniversityRatingDto universityRatingDto = new UniversityRatingDto();
            universityRatingDto.setId(rs.getLong("id"));
            universityRatingDto.setUniversityName(rs.getString("name"));
            universityRatingDto.setAddress(rs.getString("address"));
            // Convert string to Enum
            String universityTypeStr = rs.getString("university_type");
            UniversityType universityType = UniversityType.valueOf(universityTypeStr.toUpperCase());

            universityRatingDto.setRatings(rs.getDouble("rating")); // Assuming rating is a double
            universityRatingDto.setDescription(rs.getString("description"));

            return universityRatingDto;
        }catch (SQLException e) {
            log.error("Error mapping row to UniversityMappings", e);
        }
        return null;

    }

}
