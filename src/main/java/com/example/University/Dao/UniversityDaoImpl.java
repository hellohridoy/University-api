package com.example.University.Dao;

import com.example.University.Dto.UniversityRatingDto;
import com.example.University.entity.University;
import com.example.University.enums.UniversityType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private University mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            University university = new University();
            university.setId(rs.getLong("id"));
            university.setName(rs.getString("name"));
            university.setAddress(rs.getString("address"));
            // Convert string to Enum
            String universityTypeStr = rs.getString("university_type");
            UniversityType universityType = UniversityType.valueOf(universityTypeStr.toUpperCase());
            university.setUniversityType(universityType);
            university.setRating(rs.getDouble("rating")); // Assuming rating is a double
            university.setDescription(rs.getString("description"));
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
