package com.carrepairshop.springframework.agh.services;

import com.carrepairshop.springframework.agh.car.Repair;
import com.carrepairshop.springframework.agh.dao.RepairDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RepairService {


    @Autowired
    @Qualifier("mysqlRepair")
    private RepairDao repairDao;

    public Collection<Repair> getAllRepairs() {
        return this.repairDao.getAllRepairs();
    }

    public Optional<Repair> getRepairById(int id) {
        return this.repairDao.getRepairById(id);
    }

    public Optional<Repair> getRepairByNameUser(String nameUser) {
        return this.repairDao.getRepairByNameUser(nameUser);
    }

    public Optional<Repair> getRepairByNumberCar(String numberCar) {
        return this.repairDao.getRepairByNumberCar(numberCar);
    }

    public void deleteRepairById(int id) {
        this.repairDao.deleteRepairById(id);
    }

    public void updateRepairById(Repair repair) {
        this.repairDao.updateRepairById(repair);
    }

    public void insertRepairToDb(Repair repair) {
        this.repairDao.insertRepairToDb(repair);
    }


}
