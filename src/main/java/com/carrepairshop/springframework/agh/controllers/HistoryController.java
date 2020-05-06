package com.carrepairshop.springframework.agh.controllers;

import com.carrepairshop.springframework.agh.domain.History;
import com.carrepairshop.springframework.agh.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class HistoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    private HistoryService historyService;

    @GetMapping(value = "/history")
    Collection<History> history() {
        return historyService.getAllHistory();
    }

}