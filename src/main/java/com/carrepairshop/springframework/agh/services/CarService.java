package com.carrepairshop.springframework.agh.services;

import com.carrepairshop.springframework.agh.car.Car;
import com.carrepairshop.springframework.agh.dao.ICarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    @Qualifier("mysqlCar")
    private ICarDao carDao;

    public Collection<Car> getAllCars() {
        return this.carDao.getAllCars();
    }

    public Optional<Car> getCarById(int id) {
        return this.carDao.getCarById(id);
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
