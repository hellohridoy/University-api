package com.example.University.Dao;

import com.example.University.Dto.UniversityStudentInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UniversityStudentCourseEnrollMentDaoImpl implements UniversityStudentCourseEnrollMentDao {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<UniversityStudentInfoDto> getAllStudentInfo(String searchParams) {
    String sql = "SELECT s.student_id AS student_id, " +
      "       s.first_name AS name, " +
      "       c.course_name AS course_name, " +
      "       c.course_description AS course_description, " +
      "       c.department AS department_name, " +
      "       c.instructor_name AS instructor_name, " +
      "       s.address AS address, " +
      "       s.city AS city, " +
      "       s.country AS country " +
      "FROM university_students s " +
      "JOIN university_courses c ON c.course_id = s.student_id"; // Review join condition

    // Check if searchParams is provided and adjust the query
    if (searchParams != null && !searchParams.isEmpty()) {
      sql += " WHERE s.first_name ILIKE ? OR s.city ILIKE ?"; // Using ILIKE for case-insensitive matching
      return jdbcTemplate.query(sql, new Object[]{"%" + searchParams + "%", "%" + searchParams + "%"}, new UniversityStudentInfoDtoRowMapper());
    }

    // If no searchParams, return all student info
    return jdbcTemplate.query(sql, new UniversityStudentInfoDtoRowMapper());
  }

  private static class UniversityStudentInfoDtoRowMapper implements RowMapper<UniversityStudentInfoDto> {
    @Override
    public UniversityStudentInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new UniversityStudentInfoDto(
        rs.getLong("student_id"),
        rs.getString("name"),
        rs.getString("course_name"),
        rs.getString("course_description"),
        rs.getString("department_name"),
        rs.getString("instructor_name"),
        rs.getString("address"),
        rs.getString("city"),
        rs.getString("country")
      );
    }
  }
}
