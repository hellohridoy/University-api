package com.example.University.Dao;

import com.example.University.Dto.DashBoardDepartmentDto;
import com.example.University.Dto.DashBoardStudentDto;
import com.example.University.Dto.DashBoardTeacherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DashBoardDaoImpl implements DashBoardDao {

  private final JdbcTemplate jdbcTemplate;
  private final ObjectMapper objectMapper;

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

  @Override
  public List<DashBoardTeacherDto> getAllTeacher(String searchParams) {
    String sql = "SELECT id, name, email, contact_no, department, specialization, qualifications FROM teacher";

    // Prepare the SQL statement and parameters
    List<Object> params = new ArrayList<>();
    StringBuilder queryBuilder = new StringBuilder(sql);

    // Check if searchParams is provided
    if (searchParams != null && !searchParams.isEmpty()) {
      // Assuming searchParams is a simple keyword search; adjust logic as needed
      queryBuilder.append(" WHERE name ILIKE ? OR email ILIKE ? OR department ILIKE ?");

      // Prepare parameters for the query
      String searchParam = "%" + searchParams + "%"; // Wildcard for search
      params.add(searchParam);
      params.add(searchParam);
      params.add(searchParam);
    }

    // Execute the query
    return jdbcTemplate.query(queryBuilder.toString(), params.toArray(), new TeacherInfosMapper());
  }


  @Override
  public List<DashBoardStudentDto> getAllStudent(String searchParams) {
    return List.of();
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

  private  class TeacherInfosMapper implements RowMapper<DashBoardTeacherDto> {
    @Override
    public DashBoardTeacherDto mapRow(ResultSet rs, int rowNum) throws SQLException {
      DashBoardTeacherDto teacherDto = new DashBoardTeacherDto();
      teacherDto.setId(rs.getLong("id"));
      teacherDto.setName(rs.getString("name"));
      teacherDto.setEmail(rs.getString("email"));
      teacherDto.setContactNo(rs.getString("contact_no"));
      teacherDto.setDepartment(rs.getString("department"));
      teacherDto.setSpecialization(rs.getString("specialization"));

      // Handle the qualifications JSONB field
      String qualificationsJson = rs.getString("qualifications");
      if (qualificationsJson != null) {
        try {
          // Parse JSON string into Map
          Map<String, Object> qualifications = objectMapper.readValue(qualificationsJson, new TypeReference<Map<String, Object>>() {
          });
          teacherDto.setQualifications(qualifications); // Assuming qualifications is a Map in your DTO
        } catch (Exception e) {
          // Handle JSON parsing error
          e.printStackTrace();
          teacherDto.setQualifications(null); // or set to some default
        }
      } else {
        teacherDto.setQualifications(null); // Handle null qualifications
      }
      return teacherDto;
    }
  }
}
