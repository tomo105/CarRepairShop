package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.carOwner.Owner;

import java.util.Collection;

public interface OwnerDao {
    Collection<Owner> getAllOwners();

    Owner getOwnerById(int id);

    void deleteOwnerById(int id);

    void updateOwnerById(Owner owner, int id);

    void insertOwnerToDb(Owner owner);
}
