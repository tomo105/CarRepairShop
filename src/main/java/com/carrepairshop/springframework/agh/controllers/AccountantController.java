package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.employees.AbstractEmployee;
import com.carrepairshop.springframework.agh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class AccountantController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees/accountants")
    Collection<AbstractEmployee> getAllAccountants() {
        return employeeService.getAllAccountants();
    }
}
