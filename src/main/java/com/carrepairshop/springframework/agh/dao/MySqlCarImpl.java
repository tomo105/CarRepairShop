package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.car.Car;
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

@Repository("mysqlCar")
public class MySqlCarImpl implements CarDao {
    private final Logger LOGGER = LoggerFactory.getLogger(MySqlCarImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String getCar = "SELECT id, client, phoneNumber, brand, model, registrationNumber FROM CAR ";


    private static class CarRowMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setId(resultSet.getInt("id"));
            car.setClient(resultSet.getString("client"));
            car.setPhoneNumber(resultSet.getLong("phoneNumber"));
            car.setBrand(resultSet.getString("brand"));
            car.setModel(resultSet.getString("model"));
            car.setRegistrationNumber(resultSet.getString("registrationNumber"));
            return car;
        }
    }

    @Override
    public Collection<Car> getAllCars() {
        return jdbcTemplate.query(getCar, new CarRowMapper());
    }

    @Override
    public Optional<Car> getCarById(int id) { // SELECT column_name(s) FROM table_name where column = value
        final String sql = getCar + " WHERE CAR.id = ?";
        Car car = jdbcTemplate.queryForObject(sql, new CarRowMapper(), id);
        return Optional.of(car);
    }

    @Override
    public void deleteCarById(int id) {
        final String sql = "DELETE FROM CAR WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    @Override
    public void updateCarById(Car car) {
        final String sql = "UPDATE CAR SET model=?,brand=?,registrationNumber=?,client=?,phoneNumber=? WHERE id = ?";
        final String model = car.getModel();
        final String brand = car.getBrand();
        final int id = car.getId();
        final String registrationNumber = car.getRegistrationNumber();
        final String client = car.getClient();
        final Long phoneNumber = car.getPhoneNumber();
        LOGGER.info("Car parameters in updateCar method: {}", model);
        jdbcTemplate.update(sql, new Object[]{model, brand, registrationNumber, client, phoneNumber, id});
    }

    @Override
    public void insertCarToDb(Car car) {
        final String sql = "INSERT INTO CAR (model,brand,registrationNumber,client,phoneNumber) VALUES (?,?,?,?,?)";
        final String model = car.getModel();
        final String brand = car.getBrand();
        final String registrationNumber = car.getRegistrationNumber();
        final String client = car.getClient();
        final Long phoneNumber = car.getPhoneNumber();

        jdbcTemplate.update(sql, new Object[]{model, brand, registrationNumber, client, phoneNumber});

    }
}
