package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.employees.AbstractEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("Employee")
public class EmployeeImpl implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeImpl.class);

    public static class EmployeeRowMapper implements RowMapper<AbstractEmployee> {
        @Override
        public AbstractEmployee mapRow(ResultSet resultSet, int i) throws SQLException {
            AbstractEmployee abstractEmployee = new AbstractEmployee();
            abstractEmployee.setId(resultSet.getInt("id"));
            abstractEmployee.setSetRole(resultSet.getString("setRole"));
            abstractEmployee.setName(resultSet.getString("name"));
            abstractEmployee.setSurname(resultSet.getString("surname"));
            abstractEmployee.setExperience(resultSet.getInt("experience"));
            abstractEmployee.setExperienceInCompany(resultSet.getInt("experienceInCompany"));
            return abstractEmployee;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<AbstractEmployee> getAllEmployees() {
        final String sql = "SELECT USERS.id, setRole, name, surname, experience, experienceInCompany " +
                "FROM USERS JOIN ROLE ON USERS.setRole=ROLE.role";
        LOGGER.info("Getting all employees");
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public Optional<AbstractEmployee> getEmployeeById(int id) {
        final String sql = "SELECT USERS.id, setRole, name, surname, experience, experienceInCompany " +
                "FROM USERS JOIN ROLE ON USERS.setRole=ROLE.role " +
                "WHERE USERS.id = ?";
        LOGGER.info("Getting employee with id: " + id);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id));
    }

    @Override
    public AbstractEmployee getEmployeeByLogin(String login) {
        final String sql = "SELECT USERS.id, setRole, name, surname, experience, experienceInCompany " +
                "FROM USERS JOIN ROLE ON USERS.setRole=ROLE.role " +
                "WHERE USERS.login = ?";
        LOGGER.info("Getting employee with login: " + login);
        return jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), login);
    }

    @Override
    public void deleteEmployeeById(int id) {
        final String sql = "DELETE FROM USERS WHERE id = ?";
        LOGGER.info("Deleting employee with id: " + id);
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void insertEmployeeToDb(AbstractEmployee abstractEmployee) {
        final String sql = "INSERT INTO USERS " +
                "(setRole, name, surname, login, password, experience, experienceInCompany) " +
                "VALUES (?,?,?,?,?,?,?)";
        List<Object> values = getEmployeeValues(abstractEmployee);
        LOGGER.info("Inserting employee: " + abstractEmployee);
        jdbcTemplate.update(sql, values.toArray());
    }

    @Override
    public void updateEmployeeById(AbstractEmployee abstractEmployee) {
        final String sql = "UPDATE USERS SET setRole= ?, name = ?,surname= ?, " +
                "login = ?, password = ?, experience = ?, experienceInCompany = ? WHERE id = ?";

        List<Object> values = new ArrayList<>(getEmployeeValues(abstractEmployee));
        final int id = abstractEmployee.getId();
        LOGGER.info("id: " + values);
        values.add(id);
        LOGGER.info("Updating employee: " + abstractEmployee);
        jdbcTemplate.update(sql, values.toArray());
    }

    private List<Object> getEmployeeValues(AbstractEmployee abstractEmployee) {
        final String setRole = abstractEmployee.getSetRole();
        final String name = abstractEmployee.getName();
        final String surname = abstractEmployee.getSurname();
        final String login = abstractEmployee.getLogin();
        final String password = abstractEmployee.getPassword();
        final int experience = abstractEmployee.getExperience();
        final int experienceInCompany = abstractEmployee.getExperienceInCompany();

        return Arrays.asList(setRole, name, surname, login, password, experience, experienceInCompany);
    }

    @Override
    public Collection<AbstractEmployee> getAllMechanics() {
        final String sql = "SELECT USERS.id, setRole, name, surname, experience, experienceInCompany " +
                "FROM USERS JOIN ROLE ON USERS.setRole=ROLE.role " +
                "WHERE USERS.setRole = 'Mechanic'";
        LOGGER.info("Getting all mechanics");
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public Collection<AbstractEmployee> getAllLogisticians() {
        final String sql = "SELECT USERS.id, setRole, name, surname, experience, experienceInCompany " +
                "FROM USERS JOIN ROLE ON USERS.setRole=ROLE.role " +
                "WHERE USERS.setRole = 'Logistician'";
        LOGGER.info("Getting all logisticians");
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public Collection<AbstractEmployee> getAllAccountants() {
        final String sql = "SELECT USERS.id, setRole, name, surname, experience, experienceInCompany " +
                "FROM USERS JOIN ROLE ON USERS.setRole=ROLE.role " +
                "WHERE USERS.setRole = 'Accountant'";
        LOGGER.info("Getting all accountants");
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public Collection<AbstractEmployee> getAllManagers() {
        final String sql = "SELECT USERS.id, setRole, name, surname, experience, experienceInCompany " +
                "FROM USERS JOIN ROLE ON USERS.setRole=ROLE.role " +
                "WHERE USERS.setRole = 'Manager'";
        LOGGER.info("Getting all managers");
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }
}
