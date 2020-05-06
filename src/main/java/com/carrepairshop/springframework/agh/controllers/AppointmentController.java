package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.domain.Appointment;
import com.carrepairshop.springframework.agh.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/appointments")
    Collection<Appointment> appointments() {
        LOGGER.info("send appointments: {}", appointmentService.getAllAppointments());
        return appointmentService.getAllAppointments();
    }

    @GetMapping(value = "/appointment/{id}")
    ResponseEntity<?> getAppointmentById(@PathVariable("id") int id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/appointment/byNumberCar/{numberCar}")
    public Optional<Appointment> getAppointmentByNumberCar(@PathVariable("numberCar") String numberCar) {
        return appointmentService.getAppointmentByNumberCar(numberCar);
    }

    @GetMapping(value = "/appointment/byNameUser/{nameUser}")
    public Optional<Appointment> getAppointmentByNameUser(@PathVariable("nameUser") String nameUser) {
        return appointmentService.getAppointmentByNameUser(nameUser);
    }


    @DeleteMapping(value = "/appointment/{id}")
    ResponseEntity<?> deleteAppointmentById(@PathVariable("id") int id) {
        LOGGER.info("Request to delete appointment: {}", id);
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/appointment", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Appointment> updateAppointmentById(@RequestBody Appointment appointment) {
        LOGGER.info("Update appointment: {}", appointment);
        appointmentService.updateAppointmentById(appointment);
        return ResponseEntity.ok().body(appointment);
    }

    @PostMapping(value = "/appointment", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Appointment> insertAppointmentToDb(@Valid @RequestBody Appointment appointment) throws URISyntaxException {
        LOGGER.info("Request to create group: {}", appointment);
        appointmentService.insertAppointmentToDb(appointment);
        return ResponseEntity.created(new URI("/api/appointment/" + appointment.getId())).body(appointment);
    }
}
