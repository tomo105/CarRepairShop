package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.domain.employees.AbstractEmployee;
import com.carrepairshop.springframework.agh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/user")
    public Collection<AbstractEmployee> getAllManagers() {
        return employeeService.getAllManagers();
    }

    @GetMapping(value = "/employees")
    Collection<AbstractEmployee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbstractEmployee> insertEmployeeToDb(@Valid @RequestBody AbstractEmployee abstractEmployee)
            throws URISyntaxException {
        employeeService.insertEmployeeToDb(abstractEmployee);
        return ResponseEntity.created(new URI("/api/employee" + abstractEmployee.getId())).body(abstractEmployee);
    }

    @PutMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AbstractEmployee> updateEmployeeById(@RequestBody AbstractEmployee abstractEmployee) {
        employeeService.updateEmployeeById(abstractEmployee);
        return ResponseEntity.ok().body(abstractEmployee);
    }

    @GetMapping(value = "/employee/{id}")
    ResponseEntity<?> getEmployeeById(@PathVariable("id") int id) {
        Optional<AbstractEmployee> employee = employeeService.getEmployeeById(id);
        return employee.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/employee/{id}")
    ResponseEntity<?> deleteEmployeeById(@PathVariable("id") int id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/login")
    public AbstractEmployee currentUserName(Principal principal) {
        return employeeService.getEmployeeByLogin(principal.getName());
    }

    @GetMapping(value = "/byLogin/{login}")
    public AbstractEmployee getEmployeeByLogin(@PathVariable("login") String login) {
        return employeeService.getEmployeeByLogin(login);
    }
}
