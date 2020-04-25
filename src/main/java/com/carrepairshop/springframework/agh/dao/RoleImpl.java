package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.employees.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository("Role")
public class RoleImpl implements RoleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleImpl.class);
    private static final String SQL_QUERY = "SELECT id, role FROM ROLE";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet resultSet, int i) throws SQLException {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setRole(resultSet.getString("role"));
            return role;
        }
    }

    @Override
    public Collection<Role> getAllRoles() {
        LOGGER.info("Getting all roles");
        return jdbcTemplate.query(SQL_QUERY, new RoleRowMapper());
    }

    @Override
    public Optional<Role> getRoleById(int id) {
        final String sqlRole = SQL_QUERY + " WHERE ROLE.id = ?";
        LOGGER.info("Getting role with id: " + id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sqlRole, new RoleRowMapper(), id));
    }
}
