package com.carrepairshop.springframework.agh.services;

import com.carrepairshop.springframework.agh.car.Appointment;
import com.carrepairshop.springframework.agh.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    @Qualifier("psqlAppointment")
    private AppointmentDao appointmentDao;

    public Collection<Appointment> getAllAppointments() {
        return this.appointmentDao.getAllAppointments();
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return this.appointmentDao.getAppointmentById(id);
    }

    public Optional<Appointment> getAppointmentByNameUser(String nameUser) {
        return this.appointmentDao.getAppointmentByNameUser(nameUser);
    }

    public Optional<Appointment> getAppointmentByNumberCar(String numberCar) {
        return this.appointmentDao.getAppointmentByNumberCar(numberCar);
    }

    public void deleteAppointmentById(int id) {
        this.appointmentDao.deleteAppointmentById(id);
    }

    public void updateAppointmentById(Appointment appointment) {
        this.appointmentDao.updateAppointmentById(appointment);
    }

    public void insertAppointmentToDb(Appointment appointment) {
        this.appointmentDao.insertAppointmentToDb(appointment);
    }

}