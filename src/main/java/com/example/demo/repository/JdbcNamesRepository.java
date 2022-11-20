package com.example.demo.repository;

import com.example.demo.model.Gender;
import com.example.demo.model.Name;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcNamesRepository implements NamesRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcNamesRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Name> getAll() {
        String query = "SELECT * FROM names ORDER BY name";
        return namedParameterJdbcTemplate.query(
                query,
                (rs, rowNum) -> this.mapNameRowsToName(rs)
        );
    }

    @Override
    public Name getName(String name) {
        String query = "SELECT * FROM names WHERE name = :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", name);
        List<Name> result = namedParameterJdbcTemplate.query(
                query,
                namedParameters,
                (rs, rowNum) -> this.mapNameRowsToName(rs)
        );
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public void createName(Name name) {
        String createQuery = "INSERT INTO names(name, gender, origin, meaning) " +
                "VALUES(:name, :gender::gender, :origin, :meaning) " +
                "ON CONFLICT DO NOTHING";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", name.getName())
                .addValue("gender", name.getGender().name())
                .addValue("origin", name.getOrigin())
                .addValue("meaning", name.getMeaning());
        namedParameterJdbcTemplate.update(createQuery, namedParameters);
    }

    @Override
    public void updateName(Name name) {
        String createQuery = "UPDATE names " +
                "SET name = :name, gender = :gender::gender, origin = :origin, meaning = :meaning " +
                "WHERE name = :name";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", name.getName())
                .addValue("gender", name.getGender().name())
                .addValue("origin", name.getOrigin())
                .addValue("meaning", name.getMeaning());
        namedParameterJdbcTemplate.update(createQuery, namedParameters);
    }

    private Name mapNameRowsToName(ResultSet rs) throws SQLException {
        Name nameResult = new Name();
        nameResult.setName(rs.getString("name"));
        nameResult.setOrigin(rs.getString("origin"));
        nameResult.setMeaning(rs.getString("meaning"));
        nameResult.setGender(Gender.valueOf(rs.getString("gender")));
        return nameResult;
    }
}
