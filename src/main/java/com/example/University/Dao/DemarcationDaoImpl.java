package com.example.University.Dao;

import com.example.University.Dto.UpazilaDetailsDto;
import com.example.University.Dto.UpazilaResponseDto;
import com.example.University.Dto.ZilaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemarcationDaoImpl implements DemarcationDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<UpazilaResponseDto> getAllUpazilasWithZilaAndDivision(String searchParam) {
        String query = "SELECT u.upazila_name, z.zila_name, d.division_name " +
                "FROM upazila u " +
                "JOIN zila z ON u.zila_id = z.id " +
                "JOIN division d ON z.division_id = d.id";

        if (searchParam != null && !searchParam.isEmpty()) {
            // Convert both the field and the searchParam to lower case for case-insensitive matching
            query += " WHERE LOWER(u.upazila_name) LIKE LOWER(?) OR LOWER(u.upazila_code) LIKE LOWER(?) OR LOWER(u.upazila_number) LIKE LOWER(?)";
            return jdbcTemplate.query(query, new Object[]{"%" + searchParam.toLowerCase() + "%", "%" + searchParam.toLowerCase() + "%", "%" + searchParam.toLowerCase() + "%"},
                    (rs, rowNum) -> new UpazilaResponseDto(
                            rs.getString("upazila_name"),
                            rs.getString("zila_name"),
                            rs.getString("division_name")
                    ));
        } else {
            return jdbcTemplate.query(query,
                    (rs, rowNum) -> new UpazilaResponseDto(
                            rs.getString("upazila_name"),
                            rs.getString("zila_name"),
                            rs.getString("division_name")
                    ));
        }
    }

    @Override
    public List<ZilaResponseDto> getAllZilasWithZilaAndDivision(String searchParam) {
        String sql = "SELECT d.division_name, z.zila_name " +
                "FROM zila z " +
                "LEFT JOIN division d ON z.division_id = d.id " +
                "WHERE LOWER(z.zila_name) LIKE LOWER(?) OR LOWER(z.zila_number) LIKE LOWER(?)";

        return jdbcTemplate.query(sql, new Object[]{"%" + searchParam + "%", "%" + searchParam + "%"}, new ZilaResponseDtoRowMapper());
    }

    @Override
    public List<UpazilaDetailsDto> getAllUpazilasByZilaIdAndDivisionId(Long zilaId, Long divisionId, String searchParams) {
        // Base SQL query
        String sql = "SELECT u.id, u.upazila_name, u.upazila_code, u.upazila_number " +
                "FROM upazila u " +
                "JOIN zila z ON u.zila_id = z.id " +
                "JOIN division d ON z.division_id = d.id " +
                "WHERE z.id = ? AND d.id = ?";

        // Add filtering conditions based on searchParams
        if (searchParams != null && !searchParams.isEmpty()) {
            sql += " AND (LOWER(u.upazila_name) LIKE LOWER(?) OR " +
                    "LOWER(u.upazila_code) LIKE LOWER(?) OR " +
                    "LOWER(u.upazila_number) LIKE LOWER(?))";
            searchParams = "%" + searchParams + "%";  // Add wildcards for LIKE query

            // Execute the query with search parameters
            return jdbcTemplate.query(sql, new Object[]{zilaId, divisionId, searchParams, searchParams, searchParams}, new UpazilaResponseDtoRowMapper());
        }

        // Execute the query without filtering if no searchParams
        return jdbcTemplate.query(sql, new Object[]{zilaId, divisionId}, new UpazilaResponseDtoRowMapper());
    }


    private static class UpazilaResponseDtoRowMapper implements RowMapper<UpazilaDetailsDto> {
        @Override
        public UpazilaDetailsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UpazilaDetailsDto(
                    rs.getLong("id"),
                    rs.getString("upazila_name"),
                    rs.getString("upazila_code"),
                    rs.getString("upazila_number")
            );
        }
    }



    private static class ZilaResponseDtoRowMapper implements RowMapper<ZilaResponseDto> {
        @Override
        public ZilaResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ZilaResponseDto(
                    rs.getString("zila_name"),
                    rs.getString("division_name")
            );
        }
    }
}
