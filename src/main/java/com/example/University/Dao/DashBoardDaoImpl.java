package com.example.University.Dao;

import com.example.University.Dto.DashBoardDepartmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DashBoardDaoImpl implements DashBoardDao {
  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<DashBoardDepartmentDto> getAllDepartmentAndSubjects(String searchParams) {
    // Start with the base query
    String sql = "SELECT dd.name AS department_name, dd.description AS department_description, " +
      "ds.name AS subject_name, ds.description AS subject_description " +
      "FROM dashboarddepartment dd " +
      "LEFT JOIN dashboardsubjects ds ON dd.id = ds.department_id";

    // Initialize a list to hold query parameters
    List<Object> params = new ArrayList<>();

    // If searchParams is provided, add a WHERE clause to the query
    if (searchParams != null && !searchParams.isEmpty()) {
      sql += " WHERE dd.name ILIKE ?";
      params.add("%" + searchParams + "%"); // Add the search value
    }

    // Execute the query with the parameters
    return jdbcTemplate.query(sql, params.toArray(), new DepartmentSubjectMapper());
  }

  private static class DepartmentSubjectMapper implements RowMapper<DashBoardDepartmentDto> {
    @Override
    public DashBoardDepartmentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
      DashBoardDepartmentDto dto = new DashBoardDepartmentDto();
      dto.setDepartmentName(rs.getString("department_name"));
      dto.setDepartmentDescription(rs.getString("department_description"));
      List<DashBoardDepartmentDto.SubjectDto> subjects = new ArrayList<>();
      DashBoardDepartmentDto.SubjectDto subjectDto = new DashBoardDepartmentDto.SubjectDto();
      subjectDto.setSubjectName(rs.getString("subject_name"));
      subjectDto.setSubjectDescription(rs.getString("subject_description"));
      subjects.add(subjectDto);
      dto.setSubjects(subjects);
      return dto;
    }
  }
}
