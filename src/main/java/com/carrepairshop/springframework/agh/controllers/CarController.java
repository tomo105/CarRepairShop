package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.car.Car;
import com.carrepairshop.springframework.agh.services.CarService;
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
public class CarController {
    private final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    Collection<Car> cars() {
        return carService.getAllCars();
    }

    @GetMapping(value = "/car/{id}")
    ResponseEntity<?> getCarById(@PathVariable("id") int id) {
        Optional<Car> car = carService.getCarById(id);
        return car.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/car/{id}")
    ResponseEntity<?> deleteCarById(@PathVariable("id") int id) {
        LOGGER.info("Request to delete car: {}", id);
        carService.deleteCarById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/car", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Car> updateCarById(@RequestBody Car car) {
        LOGGER.info("Update car: {}", car);
        carService.updateCarById(car);
        return ResponseEntity.ok().body(car);
    }

    @PostMapping(value = "/car", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Car> insertCarToDb(@Valid @RequestBody Car car) throws URISyntaxException {
        LOGGER.info("Request to create group: {}", car);
        carService.insertCarToDb(car);
        return ResponseEntity.created(new URI("/api/car/" + car.getId())).body(car);
    }


}
