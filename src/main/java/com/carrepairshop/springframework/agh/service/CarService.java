package com.carrepairshop.springframework.agh.service;

import com.carrepairshop.springframework.agh.domain.Car;
import com.carrepairshop.springframework.agh.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    @Qualifier("mysqlCar")
    private CarDao carDao;

    public Collection<Car> getAllCars() {
        return this.carDao.getAllCars();
    }

    public Optional<Car> getCarById(int id) {
        return this.carDao.getCarById(id);
    }

    public Collection<Car> getCarByOwnerSurname(String surname) {
        return this.carDao.getCarByOwnerSurname(surname);
    }

    public Collection<Car> getCarByOwnerPhoneNumber(String phoneNumber) {
        return this.carDao.getCarByOwnerPhoneNumber(phoneNumber);
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        return this.carDao.getCarByRegistrationNumber(registrationNumber);
    }

    public Collection<Car> getCarByBrand(String brand) {
        return this.carDao.getCarByBrand(brand);
    }

    public Collection<Car> getCarByModel(String model) {
        return this.carDao.getCarByModel(model);
    }

    public void deleteCarById(int id) {
        this.carDao.deleteCarById(id);
    }

    public void updateCarById(Car car) {
        this.carDao.updateCarById(car);
    }

    public void insertCarToDb(Car car) {
        this.carDao.insertCarToDb(car);
    }

}
