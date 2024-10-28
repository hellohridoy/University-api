package com.example.University.Dao;

import com.example.University.Dto.UniversityStudentInfoDto;
import com.example.University.service.UniversityStudentCourseEnrollMentService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UniversityStudentCourseEnrollMentDaoImpl implements UniversityStudentCourseEnrollMentDao {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<UniversityStudentInfoDto> searchStudents(Integer studentId, String name, String courseName,
                                                       String instructorName, String address, String city) {

    String sql = """
      SELECT s.student_id AS student_id,
             s.first_name AS name,
             c.course_name AS course_name,
             c.course_description AS course_description,
             c.department AS department_name,
             c.instructor_name AS instructor_name,
             s.address AS address,
             s.city AS city,
             s.country AS country
      FROM university_students s
      JOIN university_courses c ON c.course_id = s.student_id
      WHERE (:studentId::Integer IS NULL OR s.student_id = :studentId)
        AND (:name IS NULL OR LOWER(s.first_name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:courseName IS NULL OR LOWER(c.course_name) LIKE LOWER(CONCAT('%', :courseName, '%')))
        AND (:instructorName IS NULL OR LOWER(c.instructor_name) LIKE LOWER(CONCAT('%', :instructorName, '%')))
        AND (:address IS NULL OR LOWER(s.address) LIKE LOWER(CONCAT('%', :address, '%')))
        AND (:city IS NULL OR LOWER(s.city) LIKE LOWER(CONCAT('%', :city, '%')))
      """;

    // Use a HashMap to store query parameters
    Map<String, Object> params = new HashMap<>();
    params.put("studentId", studentId);
    params.put("name", name != null ? "%" + name.toLowerCase() + "%" : null);
    params.put("courseName", courseName != null ? "%" + courseName.toLowerCase() + "%" : null);
    params.put("instructorName", instructorName != null ? "%" + instructorName.toLowerCase() + "%" : null);
    params.put("address", address != null ? "%" + address.toLowerCase() + "%" : null);
    params.put("city", city != null ? "%" + city.toLowerCase() + "%" : null);

    // Execute the query using JdbcTemplate and return the results
    return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) ->
      new UniversityStudentInfoDto(
        rs.getInt("student_id"),
        rs.getString("name"),
        rs.getString("course_name"),
        rs.getString("course_description"),
        rs.getString("department_name"),
        rs.getString("instructor_name"),
        rs.getString("address"),
        rs.getString("city"),
        rs.getString("country")
      )
    );
  }
}
