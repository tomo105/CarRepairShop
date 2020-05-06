package com.carrepairshop.springframework.agh.domain.employees;

import com.carrepairshop.springframework.agh.domain.calendars.WorkCalendar;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mechanic extends AbstractEmployee {

    private WorkCalendar<Mechanic> workCalendar;

    @Builder
    public Mechanic(Integer id, Integer idRole, String role, String name, String surname,
                    String login, String password, Integer experience, Integer experienceInCompany,
                    WorkCalendar<Mechanic> workCalendar) {
        super(id, idRole, role, name, surname, login, password, experience, experienceInCompany);
        this.workCalendar = workCalendar;
    }
}
