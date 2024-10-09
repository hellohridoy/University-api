package com.example.University.Dao;

import com.example.University.Dto.StudentInfoBySubjectsDto;
import com.example.University.Dto.StudentResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<StudentResultDto> getAllStudentsWithSubject(String searchParams) {
        String sql;
        Map<String, Object> params = new HashMap<>();

        // If name is null or empty, return all students
        if (searchParams == null || searchParams.isEmpty()) {
            sql = """
            SELECT id, name, email, department, subjects
            FROM student;
            """;
        } else {
            sql = """
            SELECT id, name, email, department, subjects
            FROM student
            WHERE name = (:searchParams);
            """;
            params.put("searchParams", searchParams);
        }

        // Execute the query with or without parameters
        return jdbcTemplate.query(sql, params, this::StudentResultMapper);
    }


    @Override
    public List<StudentInfoBySubjectsDto> getStudentInfoBySubject(String searchParams) {
        String sql;

        // If searchParams is null or empty, return all students
        if (searchParams == null || searchParams.isEmpty()) {
            sql = """
                SELECT id, name, email, department, subjects
                FROM student;
                """;
        } else {
            sql = """
                SELECT id, name, email, department, subjects
                FROM student
                WHERE EXISTS (
                    SELECT 1
                    FROM jsonb_each_text(subjects) AS subject_pair(subject_name, subject_value)
                    WHERE LOWER(subject_name) = LOWER(:searchParams)
                );
                """;
        }

        Map<String, Object> params = new HashMap<>();
        if (searchParams != null && !searchParams.isEmpty()) {
            params.put("searchParams", searchParams);
        }

        return jdbcTemplate.query(sql, params, this::StudentInfoBySubjectMapper);
    }

    private StudentInfoBySubjectsDto StudentInfoBySubjectMapper(ResultSet resultSet, int i) throws SQLException {
        StudentInfoBySubjectsDto studentInfo = new StudentInfoBySubjectsDto();
        studentInfo.setId(resultSet.getLong("id"));
        studentInfo.setName(resultSet.getString("name"));
        studentInfo.setEmail(resultSet.getString("email"));
        studentInfo.setDepartment(resultSet.getString("department"));

        // Parse the 'subjects' JSONB field into Map<String, Object>
        ObjectMapper objectMapper = new ObjectMapper();
        String subjectsJson = resultSet.getString("subjects");
        Map<String, Object> subjects = null;
        try {
            subjects = objectMapper.readValue(subjectsJson, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        studentInfo.setSubjects(subjects);
        return studentInfo;
    }

    private StudentResultDto StudentResultMapper(ResultSet resultSet, int i) throws SQLException {
        StudentResultDto studentResult = new StudentResultDto();
        studentResult.setId(resultSet.getLong("id"));
        studentResult.setName(resultSet.getString("name"));

        // Parse 'subjects' JSON field into Map<String, Object>
        ObjectMapper objectMapper = new ObjectMapper();
        String subjectsJson = resultSet.getString("subjects");

        // Check if the JSON string is valid and not empty
        if (subjectsJson != null && !subjectsJson.trim().isEmpty()) {
            try {
                Map<String, Object> results = objectMapper.readValue(subjectsJson, new TypeReference<Map<String, Object>>() {});
                studentResult.setResults(results);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error parsing subjects JSON", e);
            }
        } else {
            studentResult.setResults(null); // or set an empty map if appropriate
        }
        return studentResult;
    }
}
