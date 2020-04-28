package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.carOwner.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


@Repository("mysqlOwner")
public class MySqlOwnerImpl implements OwnerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class OwnerRowMapper implements RowMapper<Owner> {

        @Override
        public Owner mapRow(ResultSet resultSet, int i) throws SQLException {
            Owner owner = new Owner();
            owner.setId(resultSet.getInt("id"));
            owner.setName(resultSet.getString("name"));
            owner.setSurname(resultSet.getString("surname"));
            owner.setPhoneNumber(resultSet.getString("phoneNumber"));
            return owner;
        }
    }


    @Override
    public Collection<Owner> getAllOwners() {
        // SELECT * FROM table_name
        final String sql = "SELECT id, name, surname, phoneNumber FROM OWNER ";
        List<Owner> owners = jdbcTemplate.query(sql, new OwnerRowMapper());
        return owners;
    }

    @Override
    public Owner getOwnerById(int id) {
        // SELECT column_name(s) FROM table_name where column = value
        final String sql = "SELECT id, name, surname, phoneNumber FROM OWNER " +
                "WHERE id = ?";
        Owner owner = jdbcTemplate.queryForObject(sql, new OwnerRowMapper(), id);
        return owner;

    }

    @Override
    public void deleteOwnerById(int id) {
        final String sql = "DELETE FROM OWNER WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateOwnerById(Owner owner,int id) {
        final String sql = "UPDATE OWNER SET name=?,surname=?,phoneNumber=? WHERE id = ?";
        final String name = owner.getName();
        final String surname = owner.getSurname();
        final String phoneNumber = owner.getPhoneNumber();
        jdbcTemplate.update(sql,new Object[] {name, surname, phoneNumber,id});
    }

    @Override
    public void insertOwnerToDb(Owner owner) {
        final String sql = "INSERT INTO OWNER (id,name, surname, phoneNumber) VALUES (?,?,?,?)";
        final int id = owner.getId();
        final String name = owner.getName();
        final String surname = owner.getSurname();
        final String phoneNumber = owner.getPhoneNumber();
        jdbcTemplate.update(sql,new Object[] {id, name, surname, phoneNumber});
    }


}
