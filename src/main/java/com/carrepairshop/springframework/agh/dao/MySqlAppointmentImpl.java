package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.car.Appointment;
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

@Repository("psqlAppointment")
public class MySqlAppointmentImpl implements AppointmentDao {

    private final Logger LOGGER = LoggerFactory.getLogger(MySqlAppointmentImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class AppointmentRowMapper implements RowMapper<Appointment> {

        @Override
        public Appointment mapRow(ResultSet resultSet, int i) throws SQLException {
            Appointment appointment = new Appointment();
            appointment.setId(resultSet.getInt("id"));
            appointment.setNameUser(resultSet.getString("nameUser"));
            appointment.setNumberCar(resultSet.getString("numberCar"));
            appointment.setData(resultSet.getDate("data"));
            appointment.setDescription(resultSet.getString("description"));
            return appointment;
        }
    }

    private static final String getAppointment = "SELECT id, nameUser, numberCar , data, description FROM APPOINTMENT ";

    @Override
    public Optional<Appointment> getAppointmentByNameUser(String nameUser) {
        LOGGER.info("Request to get Appointment by nameUser: {}", nameUser);
        final String sql = getAppointment + " WHERE APPOINTMENT.nameUser=?";
        Appointment appointment = jdbcTemplate.queryForObject(sql, new AppointmentRowMapper(), nameUser);
        return Optional.of(appointment);
    }

    @Override
    public Optional<Appointment> getAppointmentByNumberCar(String numberCar) {
        LOGGER.info("Request to get Appointment by numberCar: {}", numberCar);
        final String sql = getAppointment + " WHERE APPOINTMENT.numberCar=?";
        Appointment appointment = jdbcTemplate.queryForObject(sql, new AppointmentRowMapper(), numberCar);
        return Optional.of(appointment);
    }

    @Override
    public Collection<Appointment> getAllAppointments() {
        LOGGER.info("Request to get all Appointments");
        return jdbcTemplate.query(getAppointment, new AppointmentRowMapper());
    }

    @Override
    public Optional<Appointment> getAppointmentById(int id) {
        LOGGER.info("Request to get Appointment by id: {}", id);
        final String sql = getAppointment + " WHERE APPOINTMENT.id = ?";
        Appointment appointment = jdbcTemplate.queryForObject(sql, new AppointmentRowMapper(), id);
        return Optional.of(appointment);
    }

    @Override
    public void deleteAppointmentById(int id) {
        final String sql = "DELETE FROM APPOINTMENT WHERE id= ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateAppointmentById(Appointment appointment) {
        final String sql = "UPDATE APPOINTMENT SET nameUser=?,numberCar=?,data=? description=? WHERE id = ?";
        final String nameUser = appointment.getNameUser();
        final String numberCar = appointment.getNumberCar();
        final int id = appointment.getId();
        final Date data = appointment.getData();
        final String description = appointment.getDescription();
        LOGGER.info("Appointment parameters in updateAppointment nameUser: {}", nameUser);
        jdbcTemplate.update(sql, new Object[]{nameUser, numberCar, data, description, id});
    }

    @Override
    public void insertAppointmentToDb(Appointment appointment) {
        final String sql = "INSERT INTO APPOINTMENT ( nameUser,numberCar,data, description ) VALUES (?,?,?, ?)";
        final String nameUser = appointment.getNameUser();
        final String numberCar = appointment.getNumberCar();
        final Date data = appointment.getData();
        final String description = appointment.getDescription();
        jdbcTemplate.update(sql, new Object[]{nameUser, numberCar, data, description});
    }
}
