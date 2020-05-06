package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.domain.employees.AbstractEmployee;
import com.carrepairshop.springframework.agh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class LogisticianController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees/logisticians")
    public Collection<AbstractEmployee> getAllLogisticians() {
        return employeeService.getAllLogisticians();
    }
}
