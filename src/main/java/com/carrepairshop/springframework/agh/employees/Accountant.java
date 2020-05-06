package com.carrepairshop.springframework.agh.employees;

import com.carrepairshop.springframework.agh.calendars.WorkCalendar;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Accountant extends AbstractEmployee {

    private WorkCalendar<Logistician> workCalendar;

    @Builder
    public Accountant(Integer id, Integer idRole, String role, String name, String surname,
                      String login, String password, Integer experience, Integer experienceInCompany,
                      WorkCalendar<Logistician> workCalendar) {
        super(id, idRole, role, name, surname, login, password, experience, experienceInCompany);
        this.workCalendar = workCalendar;
    }
}
