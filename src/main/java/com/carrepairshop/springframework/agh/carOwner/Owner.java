package com.carrepairshop.springframework.agh.carOwner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;

}
