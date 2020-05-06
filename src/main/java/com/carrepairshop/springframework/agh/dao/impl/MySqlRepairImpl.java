package com.carrepairshop.springframework.agh.dao.impl;


import com.carrepairshop.springframework.agh.dao.RepairDao;
import com.carrepairshop.springframework.agh.domain.Repair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository("mysqlRepair")
public class MySqlRepairImpl implements RepairDao {

    private final Logger LOGGER = LoggerFactory.getLogger(MySqlRepairImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class RepairRowMapper implements RowMapper<Repair> {

        @Override
        public Repair mapRow(ResultSet resultSet, int i) throws SQLException {
            Repair repair = new Repair();
            repair.setId(resultSet.getInt("id"));
            repair.setNameUser(resultSet.getString("nameUser"));
            repair.setNumberCar(resultSet.getString("numberCar"));
            repair.setData(resultSet.getDate("data"));
            repair.setSparesCosts(resultSet.getDouble("sparesCosts"));
            repair.setServiceCosts(resultSet.getDouble("serviceCosts"));
            repair.setNote(resultSet.getString("note"));
            return repair;
        }
    }

    private static final String getRepair = "SELECT id, nameUser, numberCar , data, sparesCosts, serviceCosts,note FROM REPAIR ";

    @Override
    public Optional<Repair> getRepairByNameUser(String nameUser) {
        LOGGER.info("Request to get Repair by nameUser: {}", nameUser);
        final String sql = getRepair + " WHERE REPAIR.nameUser=?";
        Repair repair = jdbcTemplate.queryForObject(sql, new RepairRowMapper(), nameUser);
        return Optional.of(repair);
    }

    @Override
    public Optional<Repair> getRepairByNumberCar(String numberCar) {
        LOGGER.info("Request to get Repair by numberCar: {}", numberCar);
        final String sql = getRepair + " WHERE REPAIR.numberCar=?";
        Repair repair = jdbcTemplate.queryForObject(sql, new RepairRowMapper(), numberCar);
        return Optional.of(repair);
    }


    @Override
    public Collection<Repair> getAllRepairs() {
        LOGGER.info("Request to get all Repairs");
        return jdbcTemplate.query(getRepair, new RepairRowMapper());
    }

    @Override
    public Optional<Repair> getRepairById(int id) {
        LOGGER.info("Request to get Repair by id: {}", id);
        final String sql = getRepair + " WHERE REPAIR.id = ?";
        Repair repair = jdbcTemplate.queryForObject(sql, new RepairRowMapper(), id);
        return Optional.of(repair);
    }

    @Override
    public void deleteRepairById(int id) {
        final String sql = "DELETE FROM REPAIR WHERE id= ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateRepairById(Repair repair) {
        final String sql = "UPDATE REPAIR SET nameUser=?,numberCar=?,data=?,sparesCosts=?,serviceCosts=?,note=? WHERE id = ?";
        final String nameUser = repair.getNameUser();
        final String numberCar = repair.getNumberCar();
        final int id = repair.getId();
        final Date data = repair.getData();
        final double sparesCosts = repair.getSparesCosts();
        final double serviceCosts = repair.getServiceCosts();
        LOGGER.info("Repair parameters in updateRepair nameUser: {}", nameUser);
        jdbcTemplate.update(sql, new Object[]{nameUser, numberCar, data, sparesCosts, serviceCosts, id});
    }

    @Override
    public void insertRepairToDb(Repair repair) {
        final String sql = "INSERT INTO REPAIR (nameUser,numberCar,data,sparesCosts,serviceCosts) VALUES (?,?,?,?,?)";
        final String nameUser = repair.getNameUser();
        final String numberCar = repair.getNumberCar();
        final Date data = repair.getData();
        final double sparesCosts = repair.getSparesCosts();
        final double serviceCosts = repair.getServiceCosts();
        jdbcTemplate.update(sql, new Object[]{nameUser, numberCar, data, sparesCosts, serviceCosts});

    }

}
