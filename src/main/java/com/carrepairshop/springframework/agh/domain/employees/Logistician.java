package com.carrepairshop.springframework.agh.domain.employees;

import com.carrepairshop.springframework.agh.domain.calendars.WorkCalendar;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Logistician extends AbstractEmployee {

    private WorkCalendar<Logistician> workCalendar;

    @Builder
    public Logistician(Integer id, Integer idRole, String role, String name, String surname,
                       String login, String password, Integer experience, Integer experienceInCompany,
                       WorkCalendar<Logistician> workCalendar) {
        super(id, idRole, role, name, surname, login, password, experience, experienceInCompany);
        this.workCalendar = workCalendar;
    }
}
