package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.domain.Car;

import java.util.Collection;
import java.util.Optional;

public interface CarDao {
    Collection<Car> getAllCars();

    Optional<Car> getCarById(int id);

    Collection<Car> getCarByOwnerSurname( String surname );

    Collection<Car> getCarByOwnerPhoneNumber( String phoneNumber );

    Car getCarByRegistrationNumber( String registrationNumber );

    Collection<Car> getCarByBrand( String brand );

    Collection<Car> getCarByModel( String model );
    void deleteCarById(int id);

    void updateCarById(Car car);

    void insertCarToDb(Car car);
}
