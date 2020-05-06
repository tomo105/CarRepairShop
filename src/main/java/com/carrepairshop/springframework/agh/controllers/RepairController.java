package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.domain.Repair;
import com.carrepairshop.springframework.agh.service.RepairService;
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
public class RepairController {


    private final Logger LOGGER = LoggerFactory.getLogger(RepairController.class);

    @Autowired
    private RepairService repairService;


    @GetMapping(value = "/repairs")
    Collection<Repair> repairs() {
        LOGGER.info("send repairs: {}", repairService.getAllRepairs());
        return repairService.getAllRepairs();
    }

    @GetMapping(value = "/repair/{id}")
    ResponseEntity<?> getRepairById(@PathVariable("id") int id) {
        Optional<Repair> repair = repairService.getRepairById(id);
        return repair.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/repair/byNumberCar/{numberCar}")
    public Optional<Repair> getRepairByNumberCar(@PathVariable("numberCar") String numberCar) {
        return repairService.getRepairByNumberCar(numberCar);
    }

    @GetMapping(value = "/repair/byNameUser/{nameUser}")
    public Optional<Repair> getRepairByNameUser(@PathVariable("nameUser") String nameUser) {
        return repairService.getRepairByNameUser(nameUser);
    }


    @DeleteMapping(value = "/repair/{id}")
    ResponseEntity<?> deleteRepairById(@PathVariable("id") int id) {
        LOGGER.info("Request to delete repair: {}", id);
        repairService.deleteRepairById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/repair", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Repair> updateRepairById(@RequestBody Repair repair) {
        LOGGER.info("Update repair: {}", repair);
        repairService.updateRepairById(repair);
        return ResponseEntity.ok().body(repair);
    }

    @PostMapping(value = "/repair", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Repair> insertRepairToDb(@Valid @RequestBody Repair repair) throws URISyntaxException {
        LOGGER.info("Request to create group: {}", repair);
        repairService.insertRepairToDb(repair);
        return ResponseEntity.created(new URI("/api/repair/" + repair.getId())).body(repair);
    }
}
