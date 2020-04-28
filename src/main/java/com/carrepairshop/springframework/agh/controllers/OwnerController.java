package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.carOwner.Owner;
import com.carrepairshop.springframework.agh.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping(value = "/owners")
    Collection<Owner> owners() {
        return ownerService.getAllOwners();
    }

    @GetMapping(value = "/owner/{id}")
    public Owner getOwnerById(@PathVariable("id") int id) {
        return ownerService.getOwnerById(id);
    }

    @DeleteMapping(value = "owner/{id}")
    public void deleteCarById(@PathVariable("id") int id) {
        ownerService.deleteOwnerById(id);
    }

    @PutMapping(value = "owner/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOwnerById(@RequestBody Owner owner, @PathVariable("id") int id) {
        ownerService.updateOwnerById(owner, id);
    }

    @PostMapping(value = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertOwnerToDb(@RequestBody Owner owner) {
        ownerService.insertOwnerToDb(owner);
    }

}