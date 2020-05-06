package com.carrepairshop.springframework.agh.domain.calendars;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WorkCalendar<T> {

    private ArrayList<String> workCalendar = new ArrayList<>();
}

