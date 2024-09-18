//package com.example.University.mapper;
//
//import com.example.University.entity.University;
//import com.example.University.enums.UniversityType;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UniversityMapper implements RowMapper<University> {
//
//    @Override
//    public University mapRow(ResultSet rs, int rowNum) throws SQLException {
//        University university = new University();
//        university.setId(rs.getLong("id"));
//        university.setName(rs.getString("name"));
//        university.setAddress(rs.getString("address"));
//        // Convert string to Enum
//        String universityTypeStr = rs.getString("university_type");
//        UniversityType universityType = UniversityType.valueOf(universityTypeStr.toUpperCase());
//        university.setUniversityType(universityType);
//        university.setRating(rs.getDouble("rating")); // Assuming rating is a double
//        university.setDescription(rs.getString("description"));
//        return university;
//    }
//
//
//}