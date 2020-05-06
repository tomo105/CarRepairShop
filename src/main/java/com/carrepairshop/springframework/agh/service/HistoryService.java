package com.carrepairshop.springframework.agh.service;

import com.carrepairshop.springframework.agh.dao.HistoryDao;
import com.carrepairshop.springframework.agh.domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HistoryService {

    @Autowired
    @Qualifier("mysqlHistory")
    private HistoryDao historyDao;

    public Collection<History> getAllHistory() {
        return this.historyDao.getAllHistory();
    }

}
