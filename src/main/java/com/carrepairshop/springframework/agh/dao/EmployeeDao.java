package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.employees.AbstractEmployee;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeDao {

    Collection<AbstractEmployee> getAllEmployees();

    Optional<AbstractEmployee> getEmployeeById(int id);

    AbstractEmployee getEmployeeByLogin(String login);

    void deleteEmployeeById(int id);

    void insertEmployeeToDb(AbstractEmployee abstractEmployee);

    void updateEmployeeById(AbstractEmployee abstractEmployee);

    Collection<AbstractEmployee> getAllMechanics();

    Collection<AbstractEmployee> getAllLogisticians();

    Collection<AbstractEmployee> getAllAccountants();

    Collection<AbstractEmployee> getAllManagers();
}
