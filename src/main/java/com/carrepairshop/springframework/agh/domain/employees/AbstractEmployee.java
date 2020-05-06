package com.carrepairshop.springframework.agh.domain.employees;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AbstractEmployee {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer idRole;
    private String setRole;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Integer experience;
    private Integer experienceInCompany;
}
