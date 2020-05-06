package com.carrepairshop.springframework.agh.service;

import com.carrepairshop.springframework.agh.dao.EmployeeDao;
import com.carrepairshop.springframework.agh.domain.employees.AbstractEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    @Qualifier("Employee")
    private EmployeeDao employeeDao;

    public void deleteEmployeeById(int id) {
        this.employeeDao.deleteEmployeeById(id);
    }

    public Collection<AbstractEmployee> getAllEmployees() {
        return this.employeeDao.getAllEmployees();
    }

    public Optional<AbstractEmployee> getEmployeeById(int id) {
        return this.employeeDao.getEmployeeById(id);
    }

    public AbstractEmployee getEmployeeByLogin(String login) {
        return this.employeeDao.getEmployeeByLogin(login);
    }

    public Collection<AbstractEmployee> getAllLogisticians() {
        return this.employeeDao.getAllLogisticians();
    }

    public Collection<AbstractEmployee> getAllMechanics() {
        return this.employeeDao.getAllMechanics();
    }

    public Collection<AbstractEmployee> getAllAccountants() {
        return this.employeeDao.getAllAccountants();
    }

    public Collection<AbstractEmployee> getAllManagers() {
        return this.employeeDao.getAllManagers();
    }

    public void insertEmployeeToDb(AbstractEmployee abstractEmployee) {
        this.employeeDao.insertEmployeeToDb(abstractEmployee);
    }

    public void updateEmployeeById(AbstractEmployee abstractEmployee) {
        this.employeeDao.updateEmployeeById(abstractEmployee);
    }
}
