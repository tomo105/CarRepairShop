package com.carrepairshop.springframework.agh.services;

import com.carrepairshop.springframework.agh.dao.RoleDao;
import com.carrepairshop.springframework.agh.employees.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    @Qualifier("Role")
    private RoleDao roleDao;

    public Collection<Role> getAllRoles() {
        return this.roleDao.getAllRoles();
    }

    public Optional<Role> getRoleById(int id) {
        return this.roleDao.getRoleById(id);
    }
}
