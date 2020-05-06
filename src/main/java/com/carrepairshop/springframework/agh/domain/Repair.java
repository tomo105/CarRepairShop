package com.carrepairshop.springframework.agh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repair {

    @Id
    @GeneratedValue
    private Integer id;
    @NonNull
    private String nameUser;
    private String numberCar;
    private Date data;
    private double sparesCosts;
    private double serviceCosts;
    private String note;
}

