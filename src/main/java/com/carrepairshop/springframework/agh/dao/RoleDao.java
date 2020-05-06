package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.employees.Role;

import java.util.Collection;
import java.util.Optional;

public interface RoleDao {

    Collection<Role> getAllRoles();

    Optional<Role> getRoleById(int id);
}
