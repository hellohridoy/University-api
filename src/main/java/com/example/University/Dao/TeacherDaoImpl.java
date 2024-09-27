package com.example.University.Dao;

import com.example.University.Dto.SubjectDto;
import com.example.University.entity.Teacher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * University: Dao Implement.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class TeacherDaoImpl implements TeacherDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public double findTotalMonthlySalaryByUniversity() {
    String sql = """
            SELECT SUM(t.salary) FROM Teacher t;
            """;

        // Map of parameters (not used in this case, but necessary for NamedParameterJdbcTemplate)
        Map<String, Object> params = new HashMap<>();

        // Execute the query and return the result
        Double totalSalary = namedParameterJdbcTemplate.queryForObject(sql, params, Double.class);

        // If no teachers exist, return 0 as total salary
        return totalSalary != null ? totalSalary : 0.0;
    }

    @Override
    public List<SubjectDto> getAllUniqueSubjects() {
       String sql = """
            
               SELECT DISTINCT ON(t.department)
                       t.id,
                       t.name,
                       t.department,
                       t.email,
                       t.hire_date,
                       t.biography,
                       t.qualifications,
                       t.profile_picture
               FROM Teacher t
               ORDER BY t.department, t.id;
                                        
               """;

        // No dynamic parameters for this query
        Map<String, Object> params = new HashMap<>();
        // Execute the query and map the result to SubjectDto
        return namedParameterJdbcTemplate.query(sql, params, subjectRowMapper());
    }

    private RowMapper<SubjectDto> subjectRowMapper() {
        return (rs, rowNum) -> {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(rs.getLong("id"));
            subjectDto.setName(rs.getString("name"));
            subjectDto.setDepartment(rs.getString("department")); // Assuming SubjectDto has this field
            subjectDto.setEmail(rs.getString("email"));
            subjectDto.setHireDate(rs.getString("hire_date"));
            subjectDto.setBiography(rs.getString("biography"));

            // Parse the qualifications JSON field into Map<String, Object>
            ObjectMapper objectMapper = new ObjectMapper();
            String qualificationsJson = rs.getString("qualifications");
            Map<String, Object> qualifications = null;
            try {
                qualifications = objectMapper.readValue(qualificationsJson, new TypeReference<Map<String, Object>>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            subjectDto.setQualifications(qualifications);

            subjectDto.setProfilePicture(rs.getString("profile_picture"));
            return subjectDto;
        };
    }
}
